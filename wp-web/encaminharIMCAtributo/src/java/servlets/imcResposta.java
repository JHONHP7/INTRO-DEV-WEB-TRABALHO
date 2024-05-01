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
@WebServlet(name = "imcResposta", urlPatterns = {"/imcResposta"})
public class imcResposta extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            //recuperar o IMC calculado no servlet CalcularIMC
            Double imc = (Double) request.getAttribute("imc");

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Resutado do IMC</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Seu IMC é:</h1>");
            out.println("<p>" + imc + "</p>");

            if (imc < 18.0) {
                out.print("<p>Cuidado! Abaixo do peso!</p>");
            } else if (imc < 25.0) {
                out.print("<p>Parabens! peso ideal!</p>");

            } else {
                out.print("<p>Cuidado!acima do peso ideal!</p>");

            }
            out.println("</body>");
            out.println("</html>");

        } finally {
            out.close();
        }
    }

}
