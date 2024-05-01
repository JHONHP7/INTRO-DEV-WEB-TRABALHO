/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servelets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jhonatan-silva
 */
@WebServlet(name = "processarForm", urlPatterns = {"/processarForm"})
public class processarForm extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Formulário de Entrada de Dados</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<form action=\"processarForm\" method=\"Post\">");
            out.println("<label for=\"nome\">Nome</label><br>");
            out.println("<input type=\"text\" name=\"nome\"  required><br><br>");
            out.println("<label for=\"idade\">Idade</label><br>");
            out.println("<input type=\"text\"  name=\"idade\"><br><br>");
            out.println("<input type=\"submit\" value=\"Enviar\">");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String nome = request.getParameter("nome");
            String idade = request.getParameter("idade");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Dados Do Formulário</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Dados do Formulário</h1>");
            out.println("<h2>Nome: " + nome + "</h2>");
            out.println("<h2>Idade: " + idade + "</h2>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}
