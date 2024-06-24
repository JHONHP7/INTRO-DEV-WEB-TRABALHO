package controller;

import model.ProdutoDAO;
import entidade.Produtos;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MostrarProdutos", urlPatterns = {"/MostrarProdutos"})
public class MostrarProdutos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        try {
            ArrayList<Produtos> allProdutos = produtoDAO.getAll();
            
            // Filtra produtos disponíveis e liberados para venda
            List<Produtos> produtosFiltrados = new ArrayList<>();
            for (Produtos produto : allProdutos) {
                if (produto.getQuantidadeDisponivel() != 0 && produto.getLiberadoVenda() == 'S') {
                    produtosFiltrados.add(produto);
                }
            }
            
            request.setAttribute("allProdutos", produtosFiltrados);
            RequestDispatcher rd = request.getRequestDispatcher("/views/public/listaProdutos.jsp");
            rd.forward(request, response);

        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
            throw new ServletException("Falha ao obter lista de produtos", ex);
        }
    }
}
