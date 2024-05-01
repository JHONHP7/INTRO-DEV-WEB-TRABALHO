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
@WebServlet(name = "imcError", urlPatterns = {"/imcError"})
public class imcError extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            // tratamento do erro
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Resutado do IMC</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("Ocorreu um erro no processamento");
            out.println("<p><a href=\"FormIMC.html\">Formulário Para Cálculo IMC</a></p>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

}
