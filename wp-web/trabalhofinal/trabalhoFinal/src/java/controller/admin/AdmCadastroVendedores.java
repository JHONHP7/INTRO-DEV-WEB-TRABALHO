package controller.admin;

import entidade.Funcionarios;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.FuncionarioDAO;

@WebServlet(name = "AdmCadastroVendedores", urlPatterns = {"/admin/administrador/cadastroVendedores"})
public class AdmCadastroVendedores extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String acao = request.getParameter("acao");
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        RequestDispatcher rd;

        switch (acao) {
            case "Listar":
                ArrayList<Funcionarios> listaVendedores = funcionarioDAO.getAllVendedores();
                request.setAttribute("listaVendedores", listaVendedores);
                rd = request.getRequestDispatcher("/views/admin/vendedores/listaVendedores.jsp");
                rd.forward(request, response);
                break;

            case "Alterar":
            case "Excluir":
                int id = Integer.parseInt(request.getParameter("id"));
                Funcionarios funcionario = funcionarioDAO.get(id);
                request.setAttribute("funcionario", funcionario);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);
                rd = request.getRequestDispatcher("/views/admin/vendedores/formVendedores.jsp");
                rd.forward(request, response);
                break;

            case "Incluir":
                request.setAttribute("funcionario", new Funcionarios());
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);
                rd = request.getRequestDispatcher("/views/admin/vendedores/formVendedores.jsp");
                rd.forward(request, response);
                break;

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int id = request.getParameter("id") != null && !request.getParameter("id").isEmpty() ? Integer.parseInt(request.getParameter("id")) : 0;
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");
        String btEnviar = request.getParameter("btEnviar");
        RequestDispatcher rd;
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

        boolean cpfValid = true;
        boolean senhaValid = true;
        if ("Incluir".equals(btEnviar) || "Alterar".equals(btEnviar)) {
            cpfValid = isCpfValid(cpf);
            senhaValid = isSenhaValid(senha);
        }

        boolean cpfExists = funcionarioDAO.cpfExists(cpf);

        if (!cpfValid) {
            request.setAttribute("msgError", "O CPF deve ter exatamente 14 caracteres no formato XXX.XXX.XXX-XX.");
        } else if (!senhaValid && ("Incluir".equals(btEnviar) || "Alterar".equals(btEnviar))) {
            request.setAttribute("msgError", "A senha deve ter entre 8 e 10 caracteres.");
        } else if (nome.isEmpty() || cpf.isEmpty() || senha.isEmpty()) {
            request.setAttribute("msgError", "É necessário preencher todos os campos.");
        } else if (cpfExists && "Incluir".equals(btEnviar)) {
            request.setAttribute("msgError", "O CPF já está cadastrado.");
        } else if (cpfExists && "Alterar".equals(btEnviar) && !funcionarioDAO.get(id).getCpf().equals(cpf)) {
            request.setAttribute("msgError", "O CPF já está cadastrado.");
        } else {
            Funcionarios funcionario = new Funcionarios(id, nome, cpf, senha, "1"); // Assuming "1" is the code for "vendedor"
            try {
                switch (btEnviar) {
                    case "Incluir":
                        funcionarioDAO.insert(funcionario);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        funcionarioDAO.update(funcionario);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        funcionarioDAO.delete(id);
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                }
                request.setAttribute("link", "/trabalhoFinal/admin/administrador/cadastroVendedores?acao=Listar");
                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                rd.forward(request, response);
                return;
            } catch (IOException | ServletException ex) {
                throw new RuntimeException("Falha em operação de vendedor", ex);
            }
        }

        // Recarregar o objeto Funcionario e encaminhar para a mesma página se houver erro
        Funcionarios funcionario = new Funcionarios(id, nome, cpf, senha, "1"); // Assuming "1" is the code for "vendedor"
        request.setAttribute("funcionario", funcionario);
        request.setAttribute("acao", btEnviar);
        rd = request.getRequestDispatcher("/views/admin/vendedores/formVendedores.jsp");
        rd.forward(request, response);
    }

    private boolean isCpfValid(String cpf) {
        return cpf != null && cpf.length() == 14 && cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    }

    private boolean isSenhaValid(String senha) {
        return senha != null && senha.length() >= 8 && senha.length() <= 10;
    }
}
