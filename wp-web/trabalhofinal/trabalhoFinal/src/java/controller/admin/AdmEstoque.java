package controller.admin;

import entidade.Produtos;
import model.ProdutoDAO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AdmEstoque", urlPatterns = {"/admin/administrador/estoque"})
public class AdmEstoque extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        ProdutoDAO produtoDAO = new ProdutoDAO();
        ArrayList<Produtos> listaProdutos = produtoDAO.getAll();

        request.setAttribute("listaProdutos", listaProdutos);
        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/relatorios/estoque.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
