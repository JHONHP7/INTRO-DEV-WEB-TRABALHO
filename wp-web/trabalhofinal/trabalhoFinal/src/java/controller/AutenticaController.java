package controller;

import entidade.Funcionarios;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.FuncionarioDAO;

/**
 *
 * @author Leonardo
 */
@WebServlet(name = "AutenticaController", urlPatterns = {"/AutenticaController"})
public class AutenticaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/views/autenticacao/formLogin.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        String cpf_func = request.getParameter("cpf");
        String senha_func = request.getParameter("senha");

        if (cpf_func.isEmpty() || senha_func.isEmpty()) {

            request.setAttribute("msgError", "Usuário e/ou senha incorreto");
            rd = request.getRequestDispatcher("/views/autenticacao/formLogin.jsp");
            rd.forward(request, response);

        } else {
            Funcionarios funcionarioObtido;
            Funcionarios funcionario = new Funcionarios(cpf_func, senha_func);
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            
            try {
                funcionarioObtido = funcionarioDAO.Logar(funcionario);

            } catch (Exception ex) {
                throw new RuntimeException("Falha na query para Logar");
            }
            String papelFuncionario = funcionarioObtido.getPapel();
            if (funcionarioObtido.getId() != 0) {
                if (papelFuncionario.equals("0")){
                    HttpSession session = request.getSession();
                    session.setAttribute("funcionario", funcionarioObtido);

                    rd = request.getRequestDispatcher("/admin/administrador");
                    rd.forward(request, response);
                } else if (papelFuncionario.equals("1")){
                    HttpSession session = request.getSession();
                    session.setAttribute("funcionario", funcionarioObtido);

                    rd = request.getRequestDispatcher("/admin/vendedor");
                    rd.forward(request, response);
                } else if (papelFuncionario.equals("2")){
                    HttpSession session = request.getSession();
                    session.setAttribute("funcionario", funcionarioObtido);

                    rd = request.getRequestDispatcher("/admin/comprador");
                    rd.forward(request, response);
                }
            } else {
                request.setAttribute("msgError", "Cpf e/ou senha incorreto");
                rd = request.getRequestDispatcher("/views/autenticacao/formLogin.jsp");
                rd.forward(request, response);

            }
        }
    }

}
