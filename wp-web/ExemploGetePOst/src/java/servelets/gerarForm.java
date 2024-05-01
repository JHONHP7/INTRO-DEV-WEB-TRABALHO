package servelets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/gerarForm"})
public class gerarForm extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
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

}