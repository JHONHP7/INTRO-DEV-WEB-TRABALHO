package publico;



import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entidade.Usuario;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/efetuarLogin"})
public class efetuarLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // pegando os parâmetros do request
        String nome_user = request.getParameter("nome");
        String senha_user = request.getParameter("senha");
        if (nome_user.isEmpty() || senha_user.isEmpty()) {
            // dados não foram preenchidos retorna ao formulário
            request.setAttribute("msgError", "Usuário e/ou senha incorreto");
            RequestDispatcher rd = request.getRequestDispatcher("/formLogin.jsp");
            rd.forward(request, response);
        } else {
            if (nome_user.equals("admin") && senha_user.equals("123")) {
                Usuario usuario = new Usuario(nome_user);
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuario);
                RequestDispatcher rd = request.getRequestDispatcher("/restrito/dashboard");
                rd.forward(request, response);
            } else {
                request.setAttribute("msgError", "Usuário e/ou senha incorreto");
                RequestDispatcher rd = request.getRequestDispatcher("/formLogin.jsp");
                rd.forward(request, response);
            }
        }
    }

}
