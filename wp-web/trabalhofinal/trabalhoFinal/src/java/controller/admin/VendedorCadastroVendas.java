package controller.admin;

import entidade.Clientes;
import entidade.Funcionarios;
import entidade.Produtos;
import entidade.Vendas;
import java.io.IOException;
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
import model.ClienteDAO;
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
            rd = request.getRequestDispatcher("/views/autenticacao/formLogin.jsp");
            request.setAttribute("errorMessage", "Por favor, faça login para continuar.");
            rd.forward(request, response);
            return;
        }

        int idFuncionario = funcionarioLogado.getId();

        try {
            switch (acao) {
                case "Listar":
                    ArrayList<Vendas> listaVendas = vendaDAO.getByFuncionario(idFuncionario);
                    request.setAttribute("listaVendas", listaVendas);
                    rd = request.getRequestDispatcher("/views/admin/vendas/listarVendas.jsp");
                    rd.forward(request, response);
                    break;

                case "Alterar":
                case "Excluir":
                    int id = parseIntOrDefault(request.getParameter("id"));
                    Vendas venda = vendaDAO.get(id);
                    if (venda == null) {
                        request.setAttribute("errorMessage", "Venda não encontrada.");
                        request.setAttribute("link", "/trabalhoFinal/admin/vendedor/vendedorCadastroVendas?acao=Listar");
                        rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                        rd.forward(request, response);
                        return;
                    }
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
        } catch (Exception ex) {
            request.setAttribute("errorMessage", "Erro ao processar a requisição: " + ex.getMessage());
            request.setAttribute("link", "/trabalhoFinal/admin/vendedor/vendedorCadastroVendas?acao=Listar");
            rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String btEnviar = request.getParameter("btEnviar");
        VendaDAO vendaDAO = new VendaDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        RequestDispatcher rd;

        HttpSession session = request.getSession();
        Funcionarios funcionarioLogado = (Funcionarios) session.getAttribute("funcionario");
        if (funcionarioLogado == null) {
            rd = request.getRequestDispatcher("/views/autenticacao/formLogin.jsp");
            request.setAttribute("errorMessage", "Por favor, faça login para continuar.");
            rd.forward(request, response);
            return;
        }

        int idFuncionario = funcionarioLogado.getId();

        try {
            switch (btEnviar) {
                case "Incluir":
                    Vendas vendaIncluir = criarVendaAPartirRequest(request, idFuncionario);
                    Produtos produtoIncluir = produtoDAO.get(vendaIncluir.getIdProduto());
                    Clientes clienteIncluir = clienteDAO.get(vendaIncluir.getIdCliente());

                    if (!verificarProdutoExiste(vendaIncluir.getIdProduto())) {
                        request.setAttribute("errorMessage", "Produto não encontrado. Não é possível realizar a venda.");
                    } else if (!verificarClienteExiste(vendaIncluir.getIdCliente())) {
                        request.setAttribute("errorMessage", "Cliente não encontrado. Não é possível realizar a venda.");
                    } else if (vendaIncluir.getQuantidadeVenda() < 1) {
                        request.setAttribute("errorMessage", "A quantidade da venda deve ser maior que zero.");
                    } else if (produtoIncluir.getQuantidadeDisponivel() < vendaIncluir.getQuantidadeVenda()) {
                        request.setAttribute("errorMessage", "Quantidade disponível do produto é insuficiente.");
                    } else if (produtoIncluir.getLiberadoVenda() != 'S') {
                        request.setAttribute("errorMessage", "Produto não está liberado para venda.");
                    } else {
                        produtoIncluir.setQuantidadeDisponivel(produtoIncluir.getQuantidadeDisponivel() - vendaIncluir.getQuantidadeVenda());
                        produtoDAO.update(produtoIncluir);

                        vendaDAO.insert(vendaIncluir);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                    }
                    break;

                case "Alterar":
                    Vendas vendaAlterar = criarVendaAPartirRequest(request, idFuncionario);
                    Vendas vendaOriginal = vendaDAO.get(vendaAlterar.getId());

                    if (vendaOriginal == null) {
                        request.setAttribute("errorMessage", "Venda não encontrada.");
                    } else {
                        Produtos produtoOriginal = produtoDAO.get(vendaOriginal.getIdProduto());
                        Produtos produtoNovo = produtoDAO.get(vendaAlterar.getIdProduto());
                        Clientes clienteNovo = clienteDAO.get(vendaAlterar.getIdCliente());

                        if (!verificarProdutoExiste(vendaAlterar.getIdProduto())) {
                            request.setAttribute("errorMessage", "Produto não encontrado para atualização.");
                        } else if (!verificarClienteExiste(vendaAlterar.getIdCliente())) {
                            request.setAttribute("errorMessage", "Cliente não encontrado para atualização.");
                        } else if (vendaAlterar.getQuantidadeVenda() < 1) {
                            request.setAttribute("errorMessage", "A quantidade da venda deve ser maior que zero.");
                        } else if (produtoNovo.getLiberadoVenda() != 'S') {
                            request.setAttribute("errorMessage", "Produto não está liberado para venda.");
                        } else if (produtoNovo.getQuantidadeDisponivel() < vendaAlterar.getQuantidadeVenda()) {
                            request.setAttribute("errorMessage", "Quantidade disponível do produto é insuficiente.");
                        } else {

                            int quantidadeOriginal = vendaOriginal.getQuantidadeVenda();
                            int quantidadeNova = vendaAlterar.getQuantidadeVenda();
                            int diferenca = quantidadeNova - quantidadeOriginal;

                            if (produtoOriginal != null && produtoOriginal.getId() == produtoNovo.getId()) {
                                produtoOriginal.setQuantidadeDisponivel(produtoOriginal.getQuantidadeDisponivel() + quantidadeOriginal - quantidadeNova);
                                produtoDAO.update(produtoOriginal);
                            } else {
                                if (produtoOriginal != null) {
                                    produtoOriginal.setQuantidadeDisponivel(produtoOriginal.getQuantidadeDisponivel() + quantidadeOriginal);
                                    produtoDAO.update(produtoOriginal);
                                }
                                produtoNovo.setQuantidadeDisponivel(produtoNovo.getQuantidadeDisponivel() - quantidadeNova);
                                produtoDAO.update(produtoNovo);
                            }

                            vendaAlterar.setValorVenda((float) (produtoNovo.getPrecoVenda() * quantidadeNova));
                            vendaDAO.update(vendaAlterar);
                            request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        }
                    }
                    request.setAttribute("link", "/trabalhoFinal/admin/vendedor/vendedorCadastroVendas?acao=Listar");
                    rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                    rd.forward(request, response);
                    return;

                case "Excluir":
                    int idExcluir = parseIntOrDefault(request.getParameter("id"));
                    Vendas vendaExcluir = vendaDAO.get(idExcluir);

                    if (vendaExcluir == null) {
                        request.setAttribute("errorMessage", "Venda não encontrada.");
                    } else {
                        Produtos produtoExcluir = produtoDAO.get(vendaExcluir.getIdProduto());

                        if (!verificarProdutoExiste(vendaExcluir.getIdProduto())) {
                            request.setAttribute("errorMessage", "Produto não encontrado. Não é possível realizar a exclusão.");
                        } else {
                            produtoExcluir.setQuantidadeDisponivel(produtoExcluir.getQuantidadeDisponivel() + vendaExcluir.getQuantidadeVenda());
                            produtoDAO.update(produtoExcluir);

                            vendaDAO.delete(idExcluir);
                            request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        }
                    }
                    request.setAttribute("link", "/trabalhoFinal/admin/vendedor/vendedorCadastroVendas?acao=Listar");
                    rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                    rd.forward(request, response);
                    return;

                default:
                    request.setAttribute("errorMessage", "Ação inválida.");
                    request.setAttribute("link", "/trabalhoFinal/admin/vendedor/vendedorCadastroVendas?acao=Listar");
                    break;
            }
            request.setAttribute("link", "/trabalhoFinal/admin/vendedor/vendedorCadastroVendas?acao=Listar");
            rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
            rd.forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("link", "/trabalhoFinal/admin/vendedor/vendedorCadastroVendas?acao=Listar");
            request.setAttribute("errorMessage", "Erro ao processar operação de venda: " + ex.getMessage());
            rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
            rd.forward(request, response);
        }
    }

    private Vendas criarVendaAPartirRequest(HttpServletRequest request, int idFuncionario) {
        int id = parseIntOrDefault(request.getParameter("id"));
        int quantidadeVenda = parseIntOrDefault(request.getParameter("quantidadeVenda"));

        String dataVendaStr = request.getParameter("dataVenda");
        java.util.Date dataVendaUtil = (dataVendaStr == null || dataVendaStr.isEmpty()) ? new java.util.Date() : parseDate(dataVendaStr);
        java.sql.Date dataVendaSql = new java.sql.Date(dataVendaUtil.getTime());

        int idProduto = parseIntOrDefault(request.getParameter("idProduto"));
        int idCliente = parseIntOrDefault(request.getParameter("idCliente"));

        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produtos produto = produtoDAO.get(idProduto);
        float valorVenda = (produto != null ? (float) (produto.getPrecoVenda() * quantidadeVenda) : 0);

        return new Vendas(id, quantidadeVenda, dataVendaSql, valorVenda, idCliente, idProduto, idFuncionario);
    }

    private java.util.Date parseDate(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(dateString);
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

    private boolean verificarProdutoExiste(int idProduto) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produtos produto = produtoDAO.get(idProduto);
        return produto != null && produto.getId() != 0;
    }

    private boolean verificarClienteExiste(int idCliente) {
        ClienteDAO clienteDAO = new ClienteDAO();
        Clientes cliente = clienteDAO.get(idCliente);
        return cliente != null && cliente.getId() != 0;
    }
}
