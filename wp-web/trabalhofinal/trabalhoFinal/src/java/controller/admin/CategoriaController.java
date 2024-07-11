/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import entidade.Categorias;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CategoriaDAO;

@WebServlet(name = "categoria/listaCategorias", urlPatterns = {"/admin/comprador/categoriaController"})
public class CategoriaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String acao = request.getParameter("acao");
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        RequestDispatcher rd;

        switch (acao) {
            case "Listar":
                ArrayList<Categorias> listaCategorias = categoriaDAO.getAll();
                request.setAttribute("listaCategorias", listaCategorias);
                rd = request.getRequestDispatcher("/views/admin/categorias/listaCategorias.jsp");
                rd.forward(request, response);
                break;

            case "Alterar":
            case "Excluir":
                int id = Integer.parseInt(request.getParameter("id"));
                Categorias categoria = categoriaDAO.get(id);
                request.setAttribute("categoria", categoria);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);
                rd = request.getRequestDispatcher("/views/admin/categorias/formCategorias.jsp");
                rd.forward(request, response);
                break;

            case "Incluir":
                request.setAttribute("categoria", new Categorias());
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);
                rd = request.getRequestDispatcher("/views/admin/categorias/formCategorias.jsp");
                rd.forward(request, response);
                break;

            default:
                response.sendRedirect("/trabalhoFinal/admin/comprador/categoriaController?acao=Listar");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int id = request.getParameter("id") != null && !request.getParameter("id").isEmpty()
                ? Integer.parseInt(request.getParameter("id")) : 0;
        String nomeCategoria = request.getParameter("nome_categoria");
        String btEnviar = request.getParameter("btEnviar");
        RequestDispatcher rd;

        if (nomeCategoria == null || nomeCategoria.isEmpty()) {
            Categorias categoria = new Categorias();
            if (id > 0) {
                CategoriaDAO categoriaDAO = new CategoriaDAO();
                categoria = categoriaDAO.get(id);
            }
            request.setAttribute("categoria", categoria);
            request.setAttribute("acao", btEnviar);
            request.setAttribute("msgError", "É necessário preencher todos os campos");
            rd = request.getRequestDispatcher("/views/admin/categorias/formCategorias.jsp");
            rd.forward(request, response);
        } else {
            Categorias categoria = new Categorias();
            categoria.setId(id);
            categoria.setNomeCategoria(nomeCategoria);
            CategoriaDAO categoriaDAO = new CategoriaDAO();

            try {
                switch (btEnviar) {
                    case "Incluir":
                        categoriaDAO.insert(categoria);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        categoriaDAO.update(categoria);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        categoriaDAO.delete(id);
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                }
                request.setAttribute("link", "/trabalhoFinal/admin/comprador/categoriaController?acao=Listar");
                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                rd.forward(request, response);
            } catch (IOException | ServletException ex) {
                throw new RuntimeException("Falha ao realizar operação com categorias: " + ex.getMessage(), ex);
            }
        }
    }

}
