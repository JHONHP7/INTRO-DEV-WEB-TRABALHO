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
 * @author jhonatan silva
 */
@WebServlet(urlPatterns = {"/processarForm"})
public class processarForm extends HttpServlet {

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
            out.println("<h2>Nome: "+nome+"</h2>");
            out.println("<h2>Idade: "+idade+"</h2>");
            out.println("</body>");
            out.println("</html>");
        }
    }


}
