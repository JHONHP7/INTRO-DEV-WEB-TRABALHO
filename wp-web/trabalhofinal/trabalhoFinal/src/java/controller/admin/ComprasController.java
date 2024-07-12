package controller.admin;

import entidade.Compras;
import entidade.Fornecedores;
import entidade.Funcionarios;
import entidade.Produtos;
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
import model.CompraDAO;
import model.ProdutoDAO;
import model.FornecedorDAO;

@WebServlet(name = "ComprasController", urlPatterns = {"/admin/comprador/comprasController"})
public class ComprasController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String acao = request.getParameter("acao");
        CompraDAO compraDAO = new CompraDAO();
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
                    ArrayList<Compras> listaCompras = compraDAO.getAllByFuncionario(idFuncionario);
                    request.setAttribute("listaCompras", listaCompras);
                    rd = request.getRequestDispatcher("/views/admin/compras/listarCompras.jsp");
                    rd.forward(request, response);
                    break;

                case "Alterar":
                case "Excluir":
                    int id = Integer.parseInt(request.getParameter("id"));
                    Compras compra = compraDAO.get(id);
                    request.setAttribute("compra", compra);
                    request.setAttribute("acao", acao);
                    rd = request.getRequestDispatcher("/views/admin/compras/formCompras.jsp");
                    rd.forward(request, response);
                    break;

                case "Incluir":
                    rd = request.getRequestDispatcher("/views/admin/compras/formCompras.jsp");
                    rd.forward(request, response);
                    break;

                default:
                    rd = request.getRequestDispatcher("/views/admin/compras/listarCompras.jsp");
                    rd.forward(request, response);
                    break;
            }
        } catch (Exception ex) {
            request.setAttribute("errorMessage", "Erro ao processar a requisição: " + ex.getMessage());
            rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String btEnviar = request.getParameter("btEnviar");
        CompraDAO compraDAO = new CompraDAO();
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

        int idFuncionario = funcionarioLogado.getId();

        try {
            switch (btEnviar) {
                case "Incluir":
                    Compras compraIncluir = criarCompraAPartirRequest(request);
                    Produtos produtoIncluir = produtoDAO.get(compraIncluir.getIdProduto());

                    if (!verificarProdutoExiste(compraIncluir.getIdProduto())) {
                        request.setAttribute("errorMessage", "Esse produto não existe. Não é possível realizar a compra.");
                    } else if (compraIncluir.getQuantidadeCompra() < 1) {
                        request.setAttribute("errorMessage", "A quantidade da compra deve ser maior que zero.");
                    } else if (!verificarFornecedorExiste(compraIncluir.getIdFornecedor())) {
                        request.setAttribute("errorMessage", "Esse fornecedor não existe. Não é possível realizar a compra.");
                    } else {
                        produtoIncluir.setQuantidadeDisponivel(produtoIncluir.getQuantidadeDisponivel() + compraIncluir.getQuantidadeCompra());
                        produtoDAO.update(produtoIncluir);

                        compraDAO.insert(compraIncluir);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                    }
                    break;

                case "Alterar":
                    Compras compraAlterar = criarCompraAPartirRequest(request);
                    Compras compraOriginal = compraDAO.get(compraAlterar.getId());

                    if (compraOriginal == null) {
                        request.setAttribute("errorMessage", "Compra original não encontrada.");
                    } else {
                        Produtos produtoOriginal = produtoDAO.get(compraOriginal.getIdProduto());
                        Produtos produtoNovo = produtoDAO.get(compraAlterar.getIdProduto());

                        if (!verificarProdutoExiste(compraAlterar.getIdProduto())) {
                            request.setAttribute("errorMessage", "Esse produto não existe para atualização.");
                        } else if (compraAlterar.getQuantidadeCompra() <= 0) {
                            request.setAttribute("errorMessage", "A quantidade da compra deve ser maior que zero.");
                        } else if (!verificarFornecedorExiste(compraAlterar.getIdFornecedor())) {
                            request.setAttribute("errorMessage", "Esse fornecedor não existe. Não é possível realizar a compra.");
                        } else {
                            int quantidadeOriginal = compraOriginal.getQuantidadeCompra();
                            int quantidadeNova = compraAlterar.getQuantidadeCompra();
                            int diferenca = quantidadeNova - quantidadeOriginal;

                            if (produtoOriginal.getId() != produtoNovo.getId()) {
                                produtoOriginal.setQuantidadeDisponivel(produtoOriginal.getQuantidadeDisponivel() - quantidadeOriginal);
                                produtoDAO.update(produtoOriginal);

                                produtoNovo.setQuantidadeDisponivel(produtoNovo.getQuantidadeDisponivel() + quantidadeNova);
                            } else {
                                produtoNovo.setQuantidadeDisponivel(produtoNovo.getQuantidadeDisponivel() + diferenca);
                            }
                            produtoDAO.update(produtoNovo);

                            compraDAO.update(compraAlterar);
                            request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        }
                    }
                    request.setAttribute("link", "/trabalhoFinal/admin/comprador/comprasController?acao=Listar");
                    rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                    rd.forward(request, response);
                    return;

                case "Excluir":
                    int idExcluir = Integer.parseInt(request.getParameter("id"));
                    Compras compraExcluir = compraDAO.get(idExcluir);

                    if (compraExcluir == null) {
                        request.setAttribute("errorMessage", "Compra não encontrada.");
                    } else {
                        Produtos produtoExcluir = produtoDAO.get(compraExcluir.getIdProduto());

                        if (!verificarProdutoExiste(compraExcluir.getIdProduto())) {
                            request.setAttribute("errorMessage", "Esse produto não existe para atualização.");
                        } else {
                            produtoExcluir.setQuantidadeDisponivel(produtoExcluir.getQuantidadeDisponivel() - compraExcluir.getQuantidadeCompra());
                            produtoDAO.update(produtoExcluir);

                            compraDAO.delete(idExcluir);
                            request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        }
                    }
                    request.setAttribute("link", "/trabalhoFinal/admin/comprador/comprasController?acao=Listar");
                    rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                    rd.forward(request, response);
                    return;

                default:
                    request.setAttribute("errorMessage", "Ação inválida.");
                    break;
            }
            request.setAttribute("link", "/trabalhoFinal/admin/comprador/comprasController?acao=Listar");
            rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
            rd.forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("link", "/trabalhoFinal/admin/comprador/comprasController?acao=Listar");
            request.setAttribute("errorMessage", "Erro ao processar operação de compra: " + ex.getMessage());
            rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
            rd.forward(request, response);
        }
    }

    private Compras criarCompraAPartirRequest(HttpServletRequest request) {

        HttpSession session = request.getSession();
        Funcionarios funcionarioLogado = (Funcionarios) session.getAttribute("funcionario");
        int idFuncionario = (funcionarioLogado != null) ? funcionarioLogado.getId() : 0;

        int id = parseIntOrDefault(request.getParameter("id"));
        int quantidadeCompra = parseIntOrDefault(request.getParameter("quantidadeCompra"));

        String dataCompraStr = request.getParameter("dataCompra");
        java.util.Date dataCompraUtil = (dataCompraStr == null || dataCompraStr.isEmpty()) ? new java.util.Date() : parseDate(dataCompraStr);

        java.sql.Date dataCompraSql = new java.sql.Date(dataCompraUtil.getTime());

        int idProduto = parseIntOrDefault(request.getParameter("idProduto"));
        int idFornecedor = parseIntOrDefault(request.getParameter("idFornecedor"));

        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produtos produto = produtoDAO.get(idProduto);

        float valorCompra = (produto != null ? (float) (produto.getPrecoCompra() * quantidadeCompra) : 0);

        return new Compras(id, quantidadeCompra, dataCompraUtil, valorCompra, idFornecedor, idProduto, idFuncionario);
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
        return produto.getId() != 0;
    }

    private boolean verificarFornecedorExiste(int idFornecedor) {
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        Fornecedores fornecedor = fornecedorDAO.get(idFornecedor);
        return fornecedor.getId() != 0;
    }

}
