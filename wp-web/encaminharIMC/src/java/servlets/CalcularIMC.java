/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

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
@WebServlet(name = "CalcularIMC", urlPatterns = {"/CalcularIMC"})
public class CalcularIMC extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String peso_string = request.getParameter("peso");
        String altura_string = request.getParameter("altura");
        
        if(peso_string.isEmpty() || altura_string.isEmpty()){
            RequestDispatcher rd = request.getRequestDispatcher("/FormIMC.html");
            rd.forward(request, response);
        }else{
            try{
                double peso = Double.valueOf(peso_string);
                double altura = Double.valueOf(altura_string);
                double imc = 0.0;
                imc = peso/(altura*altura);
                if(Double.isInfinite(imc) || Double.isNaN(imc)){
                    throw new ArithmeticException();
                }
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Resutado do IMC</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Seu IMC é:</h1>");
                out.println("<p>" + imc + "</p>");

                if (imc < 18.0) {
                    out.println("<p>Cuidado! Abaixo!</p>");
                } else if (imc < 25.0) {
                    out.println("<p>Parabéns! Peso ideal!</p>");
                } else {
                    out.println("<p>Cuidado! Acima!</p>");
                }
                out.println("</body>");
                out.println("</html>");
                out.close();
            }catch(Exception e){
                // encaminhar para erro
                // continua como post
                RequestDispatcher rd = request.getRequestDispatcher("imcError");
                rd.forward(request, response);
            }
        }
    }



}
