import entidade.Vendas;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        switch (acao) {
            case "Listar":
                ArrayList<Vendas> listaVendas = vendaDAO.getAll();
                request.setAttribute("listaVendas", listaVendas);
                rd = request.getRequestDispatcher("/views/admin/vendas/listarVendas.jsp");
                rd.forward(request, response);
                break;

            case "Alterar":
            case "Excluir":
                int id = Integer.parseInt(request.getParameter("id"));
                Vendas venda = vendaDAO.get(id);
                request.setAttribute("venda", venda);
                request.setAttribute("msgError", "");
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
        RequestDispatcher rd;

        try {
            switch (btEnviar) {
                case "Incluir":
                    Vendas vendaIncluir = criarVendaAPartirRequest(request);
                    vendaDAO.insert(vendaIncluir);
                    request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                    break;

                case "Alterar":
                    Vendas vendaAlterar = criarVendaAPartirRequest(request);
                    vendaDAO.update(vendaAlterar);
                    request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                    break;

                case "Excluir":
                    int id = Integer.parseInt(request.getParameter("id"));
                    vendaDAO.delete(id);
                    request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                    break;
            }

            request.setAttribute("link", "/trabalhoFinal/admin/vendedor/vendedorCadastroVendas?acao=Listar");
            rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
            rd.forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException("Falha ao processar operação de venda");
        }
    }

    private Vendas criarVendaAPartirRequest(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        int quantidadeVenda = Integer.parseInt(request.getParameter("quantidadeVenda"));
        Date dataVenda = new Date();
        float valorVenda = Float.parseFloat(request.getParameter("valorVenda"));
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        int idProduto = Integer.parseInt(request.getParameter("idProduto"));
        int idFuncionario = Integer.parseInt(request.getParameter("idFuncionario"));

        return new Vendas(id, quantidadeVenda, dataVenda, valorVenda, idCliente, idProduto, idFuncionario);
    }
}
