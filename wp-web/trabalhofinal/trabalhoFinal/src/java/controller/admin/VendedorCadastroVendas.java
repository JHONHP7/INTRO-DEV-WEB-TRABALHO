
import entidade.Funcionarios;
import entidade.Produtos;
import entidade.Vendas;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ProdutoDAO;
import model.VendaDAO;

@WebServlet(name = "VendedorCadastroVendas", urlPatterns = {"/admin/vendedor/vendedorCadastroVendas"})
public class VendedorCadastroVendas extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String acao = request.getParameter("acao");
        VendaDAO vendaDAO = new VendaDAO();
        RequestDispatcher rd;

        HttpSession session = request.getSession();
        Funcionarios funcionarioLogado = (Funcionarios) session.getAttribute("funcionario");

        if (funcionarioLogado == null) {
            rd = request.getRequestDispatcher("/views/autenticacao/acessDenied.jsp");
            request.setAttribute("errorMessage", "Por favor, faça login para continuar.");
            rd.forward(request, response);
            return;
        }

        int idFuncionarioLogado = funcionarioLogado.getId();

        switch (acao) {
            case "Listar":
                ArrayList<Vendas> listaVendas = vendaDAO.getByFuncionario(idFuncionarioLogado);
                request.setAttribute("listaVendas", listaVendas);
                rd = request.getRequestDispatcher("/views/admin/vendas/listarVendas.jsp");
                rd.forward(request, response);
                break;

            case "Alterar":
            case "Excluir":
                int id = Integer.parseInt(request.getParameter("id"));
                Vendas venda = vendaDAO.get(id);
                request.setAttribute("venda", venda);
                request.setAttribute("acao", acao);
                rd = request.getRequestDispatcher("/views/admin/vendas/formVendas.jsp");
                rd.forward(request, response);
                break;

            case "Incluir":
                rd = request.getRequestDispatcher("/views/admin/vendas/formVendas.jsp");
                rd.forward(request, response);
                break;

            default:
                rd = request.getRequestDispatcher("/views/admin/vendas/listarVendas.jsp");
                rd.forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String btEnviar = request.getParameter("btEnviar");
        VendaDAO vendaDAO = new VendaDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        RequestDispatcher rd;

        HttpSession session = request.getSession();
        Funcionarios funcionarioLogado = (Funcionarios) session.getAttribute("funcionario");

        if (funcionarioLogado == null) {
            rd = request.getRequestDispatcher("/views/autenticacao/formLogin.jsp");
            request.setAttribute("errorMessage", "Por favor, faça login para continuar.");
            rd.forward(request, response);
            return;
        }

        int idFuncionarioLogado = funcionarioLogado.getId();

        try {
            switch (btEnviar) {
                case "Incluir":
                    Vendas vendaIncluir = criarVendaAPartirRequest(request, idFuncionarioLogado);
                    Produtos produto = produtoDAO.get(vendaIncluir.getIdProduto());

                    if (produto.getQuantidadeDisponivel() < vendaIncluir.getQuantidadeVenda()) {
                        request.setAttribute("errorMessage", "Quantidade disponível do produto é insuficiente.");
                    } else if (produto.getLiberadoVenda() != 'S') {
                        request.setAttribute("errorMessage", "Produto não está liberado para venda.");
                    } else {
                        produto.setQuantidadeDisponivel(produto.getQuantidadeDisponivel() - vendaIncluir.getQuantidadeVenda());
                        produtoDAO.update(produto);

                        vendaDAO.insert(vendaIncluir);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                    }
                    break;

                case "Alterar":
                    Vendas vendaAlterar = criarVendaAPartirRequest(request, idFuncionarioLogado);
                    vendaDAO.update(vendaAlterar);
                    request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                    break;

                case "Excluir":
                    int idExcluir = Integer.parseInt(request.getParameter("id"));
                    vendaDAO.delete(idExcluir);
                    request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                    break;

                default:
                    request.setAttribute("errorMessage", "Ação inválida.");
                    break;
            }
            request.setAttribute("link", "/trabalhoFinal/admin/vendedor/vendedorCadastroVendas?acao=Listar");
            rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
            rd.forward(request, response);
        } catch (Exception ex) {
            throw new ServletException("Falha ao processar operação de venda", ex);
        }
    }

    private Vendas criarVendaAPartirRequest(HttpServletRequest request, int idFuncionario) {
        int id = parseIntOrDefault(request.getParameter("id"));
        int quantidadeVenda = parseIntOrDefault(request.getParameter("quantidadeVenda"));

        String dataVendaStr = request.getParameter("dataVenda");
        Date dataVenda = parseDate(dataVendaStr); // Converte a data para o formato SQL

        int idProduto = parseIntOrDefault(request.getParameter("idProduto"));
        int idCliente = parseIntOrDefault(request.getParameter("idCliente"));

        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produtos produto = produtoDAO.get(idProduto);

        float valorVenda = (float) (produto.getPrecoVenda() * quantidadeVenda);

        return new Vendas(id, quantidadeVenda, dataVenda, valorVenda, idCliente, idProduto, idFuncionario);
    }

    private Date parseDate(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(dateString);
            return new Date(utilDate.getTime());
        } catch (ParseException e) {
            throw new RuntimeException("Data inválida: " + dateString, e);
        }
    }

    private int parseIntOrDefault(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

}
