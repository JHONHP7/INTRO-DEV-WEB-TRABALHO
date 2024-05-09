package servlet;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import aplicacao.Usuario;

@WebServlet(urlPatterns = {"/enviarDados"})
public class enviarDados extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // pegando os parâmetros do request
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        if (nome.isEmpty() || endereco.isEmpty()) {
            // dados não foram preenchidos retorna ao formulário
            RequestDispatcher rd = request.getRequestDispatcher("/formUsuario.html");
            rd.forward(request, response);
        } else {
         
            Usuario usuario = new Usuario(nome, endereco);
            
            request.setAttribute("usuario", usuario);
            RequestDispatcher rd = request.getRequestDispatcher("/resposta.jsp");
            rd.forward(request, response);
           
        }
    }

}
