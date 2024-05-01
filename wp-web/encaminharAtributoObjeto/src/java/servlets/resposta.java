package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import aplicacao.Usuario;

@WebServlet(urlPatterns = {"/resposta"})
public class resposta extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Usuario usuario = (Usuario) request.getAttribute("usuario");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Resposta</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Dados recebidos do Usuário</h1>");
            out.println("<h3>Nome: " + usuario.getNome() + "</h1>");
            out.println("<h3>Endereço: " + usuario.getEndereco() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
