/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jhonatan-silva
 */
@WebServlet(name = "calcularIMC", urlPatterns = {"/calcularIMC"})
public class calcularIMC extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /**
         * Pegando os parametros do request e fazendo calculo do imc
         */
        String altura_string = request.getParameter("altura");
        String peso_string = request.getParameter("peso");

        if (altura_string.isEmpty() || peso_string.isEmpty()) {
            /**
             * Se não preencher vai voltar pro form
             */
            RequestDispatcher resposta = request.getRequestDispatcher("/FormIMC.html");
            resposta.forward(request, response);

        } else {
            try {
                double peso = Double.valueOf(peso_string);
                double altura = Double.valueOf(altura_string);
                double imc = 0.0;
                imc = peso / (altura * altura);
                if (Double.isInfinite(imc) || Double.isNaN(imc)) {
                    throw new ArithmeticException();
                }
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>resultado imc</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Seu imc e: "+imc+"</h1>");
                out.println("</body>");
                out.println("</html>");

            } catch (Exception e) {
                RequestDispatcher resposta = request.getRequestDispatcher("imcError");
                resposta.forward(request, response);
            }
        }

    }
}
