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
        // pegando os parâmetros do request
        String cpf_func = request.getParameter("cpf");
        String senha_func = request.getParameter("senha");

        // Log dos valores de CPF e senha
        System.out.println("CPF: " + cpf_func);
        System.out.println("Senha: " + senha_func);
        if (cpf_func.isEmpty() || senha_func.isEmpty()) {
            // dados não foram preenchidos retorna ao formulário
            request.setAttribute("msgError", "Usuário e/ou senha incorreto");
            rd = request.getRequestDispatcher("/views/autenticacao/formLogin.jsp");
            rd.forward(request, response);

        } else {
            Funcionarios funcionarioObtido;
            Funcionarios funcionario = new Funcionarios(cpf_func, senha_func);
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            try {
                funcionarioObtido = funcionarioDAO.Logar(funcionario);
                System.out.println("O cpf de funcionario obtido e: " + funcionarioObtido.getCpf());
                System.out.println("A senha de funcionario obtido e: " + funcionarioObtido.getSenha());
                System.out.println("O papel de funcionario obtido e: " + funcionarioObtido.getPapel());
                System.out.println("O id de funcionario obtido e: " + funcionarioObtido.getId());

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query para Logar");
            }

            if (funcionarioObtido.getId() != 0) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", funcionarioObtido);

                rd = request.getRequestDispatcher("/admin/dashboard");
                rd.forward(request, response);

            } else {
                request.setAttribute("msgError", "Cpf e/ou senha incorreto");
                rd = request.getRequestDispatcher("/views/autenticacao/formLogin.jsp");
                rd.forward(request, response);

            }
        }
    }

}
