/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            String nome = request.getParameter("nome");
            String idade = request.getParameter("idade");
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Formulário de entrada de dados</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>o seu nome e idade sao "+nome + " "+ idade +"</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}
