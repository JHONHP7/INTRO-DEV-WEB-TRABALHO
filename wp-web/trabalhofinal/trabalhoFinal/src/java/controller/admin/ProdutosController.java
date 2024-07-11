package controller.admin;

import entidade.Categorias;
import entidade.Produtos;
import model.CategoriaDAO;
import model.ProdutoDAO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ProdutosController", urlPatterns = {"/admin/comprador/produtosController"})
public class ProdutosController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String acao = request.getParameter("acao");
        ProdutoDAO produtoDAO = new ProdutoDAO();
        RequestDispatcher rd;

        switch (acao) {
            case "Listar":
                ArrayList<Produtos> listaProdutos = produtoDAO.getAll();
                request.setAttribute("listaProdutos", listaProdutos);
                rd = request.getRequestDispatcher("/views/admin/produtos/listaProdutos.jsp");
                rd.forward(request, response);
                break;

            case "Alterar":
            case "Excluir":
                int id = Integer.parseInt(request.getParameter("id"));
                Produtos produto = produtoDAO.get(id);
                if (produto != null) {
                    request.setAttribute("produto", produto);
                    request.setAttribute("acao", acao);
                    rd = request.getRequestDispatcher("/views/admin/produtos/formProdutos.jsp");
                    rd.forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Produto não encontrado");
                }
                break;

            case "Incluir":
                request.setAttribute("produto", new Produtos());
                request.setAttribute("acao", acao);
                rd = request.getRequestDispatcher("/views/admin/produtos/formProdutos.jsp");
                rd.forward(request, response);
                break;

            default:
                response.sendRedirect("/trabalhoFinal/admin/comprador/produtosController?acao=Listar");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String nomeProduto = request.getParameter("nomeProduto");
        String descricao = request.getParameter("descricao");
        double precoCompra = 0.0;
        double precoVenda = 0.0;
        int quantidadeDisponivel = 0;
        char liberadoVenda = 'N';
        int idCategoria = 0;
        String btEnviar = request.getParameter("btEnviar");

        try {
            String idParam = request.getParameter("id");
            int id = (idParam != null && !idParam.trim().isEmpty()) ? Integer.parseInt(idParam) : 0;

            String precoCompraParam = request.getParameter("precoCompra");
            String precoVendaParam = request.getParameter("precoVenda");
            String quantidadeDisponivelParam = request.getParameter("quantidadeDisponivel");
            String liberadoVendaParam = request.getParameter("liberadoVenda");
            String idCategoriaParam = request.getParameter("idCategoria");

            if (precoCompraParam != null && !precoCompraParam.trim().isEmpty()) {
                precoCompra = Double.parseDouble(precoCompraParam);
            }

            if (precoVendaParam != null && !precoVendaParam.trim().isEmpty()) {
                precoVenda = Double.parseDouble(precoVendaParam);
            }

            if (quantidadeDisponivelParam != null && !quantidadeDisponivelParam.trim().isEmpty()) {
                quantidadeDisponivel = Integer.parseInt(quantidadeDisponivelParam);
            }

            if (liberadoVendaParam != null && !liberadoVendaParam.trim().isEmpty()) {
                liberadoVenda = liberadoVendaParam.charAt(0);
            }

            if (idCategoriaParam != null && !idCategoriaParam.trim().isEmpty()) {
                idCategoria = Integer.parseInt(idCategoriaParam);
            }

            if ("Incluir".equals(btEnviar)) {
                CategoriaDAO categoriaDAO = new CategoriaDAO();
                Categorias categoria = categoriaDAO.get(idCategoria);
                if (categoria == null || categoria.getId() == 0) {
                    request.setAttribute("errorMessage", "Categoria de produtos não existe");
                    request.setAttribute("link", "/trabalhoFinal/admin/comprador/produtosController?acao=Incluir");
                    RequestDispatcher rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                    rd.forward(request, response);
                    return;
                }
            }

            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produtos produto = criarProduto(id, nomeProduto, descricao, precoCompra, precoVenda, quantidadeDisponivel, liberadoVenda, idCategoria);

            if ("Incluir".equals(btEnviar)) {
                produtoDAO.insert(produto);
                request.setAttribute("link", "/trabalhoFinal/admin/comprador/produtosController?acao=Listar");
                request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
            } else if ("Alterar".equals(btEnviar)) {
                produtoDAO.update(produto);
                request.setAttribute("link", "/trabalhoFinal/admin/comprador/produtosController?acao=Listar");
                request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
            } else if ("Excluir".equals(btEnviar)) {
                produtoDAO.delete(id);
                request.setAttribute("link", "/trabalhoFinal/admin/comprador/produtosController?acao=Listar");
                request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
            } else if ("Colocar para Venda".equals(btEnviar)) {
                produtoDAO.updateLiberadoVenda(id, 'S');
                request.setAttribute("link", "/trabalhoFinal/admin/comprador/produtosController?acao=Listar");
                request.setAttribute("msgOperacaoRealizada", "Produto colocado para venda com sucesso");
            } else if ("Retirar da Venda".equals(btEnviar)) {
                produtoDAO.updateLiberadoVenda(id, 'N');
                request.setAttribute("link", "/trabalhoFinal/admin/comprador/produtosController?acao=Listar");
                request.setAttribute("msgOperacaoRealizada", "Produto retirado da venda com sucesso");
            } else {
                throw new IllegalArgumentException("Ação não reconhecida");
            }

            request.getRequestDispatcher("/views/comum/showMessage.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Formato numérico inválido: " + e.getMessage());
            request.setAttribute("link", "/trabalhoFinal/admin/comprador/produtosController?acao=Incluir");
            RequestDispatcher rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Ocorreu um erro ao processar a operação: " + e.getMessage());
            request.setAttribute("link", "/trabalhoFinal/admin/comprador/produtosController?acao=Incluir");
            RequestDispatcher rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
            rd.forward(request, response);
        }
    }

    private Produtos criarProduto(int id, String nomeProduto, String descricao, double precoCompra, double precoVenda, int quantidadeDisponivel, char liberadoVenda, int idCategoria) {
        Produtos produto = new Produtos();
        produto.setId(id);
        produto.setNomeProduto(nomeProduto);
        produto.setDescricao(descricao);
        produto.setPrecoCompra(precoCompra);
        produto.setPrecoVenda(precoVenda);
        produto.setQuantidadeDisponivel(quantidadeDisponivel);
        produto.setLiberadoVenda(liberadoVenda);
        produto.setIdCategoria(idCategoria);
        return produto;
    }
}
