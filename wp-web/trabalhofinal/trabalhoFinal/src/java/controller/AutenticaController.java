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
        request.setCharacterEncoding("UTF-8");
        RequestDispatcher rd;
        String cpf_func = request.getParameter("cpf");
        String senha_func = request.getParameter("senha");

        if (cpf_func == null || senha_func == null || cpf_func.trim().isEmpty() || senha_func.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Usuário e/ou senha não podem estar vazios.");
            request.setAttribute("link", "/trabalhoFinal/AutenticaController");
            rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
            rd.forward(request, response);
            return;
        }

        Funcionarios funcionarioObtido = null;
        Funcionarios funcionario = new Funcionarios(cpf_func, senha_func);
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

        try {
            funcionarioObtido = funcionarioDAO.Logar(funcionario);
        } catch (Exception ex) {
            // Em caso de erro, definir a mensagem de erro e encaminhar para a página showMessage.jsp
            request.setAttribute("errorMessage", "Ocorreu um erro ao tentar autenticar o usuário. Por favor, tente novamente.");
            request.setAttribute("link", "/trabalhoFinal/AutenticaController");
            rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
            rd.forward(request, response);
            return;
        }

        if (funcionarioObtido != null && funcionarioObtido.getId() != 0) {
            HttpSession session = request.getSession();
            session.setAttribute("funcionario", funcionarioObtido);
            String papelFuncionario = funcionarioObtido.getPapel();
            switch (papelFuncionario) {
                case "0":
                    rd = request.getRequestDispatcher("/admin/administrador");
                    break;
                case "1":
                    rd = request.getRequestDispatcher("/admin/vendedor");
                    break;
                case "2":
                    rd = request.getRequestDispatcher("/admin/comprador");
                    break;
                default:
                    request.setAttribute("errorMessage", "Papel de funcionário inválido.");
                    request.setAttribute("link", "/trabalhoFinal/AutenticaController");
                    rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                    break;
            }
        } else {
            request.setAttribute("errorMessage", "Cpf e/ou senha incorreto");
            request.setAttribute("link", "/trabalhoFinal/AutenticaController");
            rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
        }
        rd.forward(request, response);
    }
}
