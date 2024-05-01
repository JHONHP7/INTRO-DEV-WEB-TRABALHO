package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/CalculaIMC"})
public class CalculaIMC extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // pegando os parâmetros do request
        String peso_string = request.getParameter("peso");
        String altura_string = request.getParameter("altura");

        if (peso_string.isEmpty() || altura_string.isEmpty()) {
            // dados não foram preenchidos retorna ao formulário
            RequestDispatcher rd = request.getRequestDispatcher("/FormIMC.html");
            rd.forward(request, response);
        } else {
            try {
                double peso = Double.valueOf(peso_string);
                double altura = Double.valueOf(altura_string);
                double imc = 0.0;
                imc = peso / (altura * altura);
                if (Double.isInfinite(imc) || Double.isNaN(imc)) {
                    throw new ArithmeticException();
                }

// Criar um atributo no request para passar o valor para o servlet ImcView
                request.setAttribute("imc", imc);
                RequestDispatcher rd = request.getRequestDispatcher("/imcResposta");
                rd.forward(request, response);

            } catch (Exception e) {
                // encaminhar para erro
                // continua como post
                RequestDispatcher rd = request.getRequestDispatcher("imcError");
                rd.forward(request, response);
            }
        }
    }
}