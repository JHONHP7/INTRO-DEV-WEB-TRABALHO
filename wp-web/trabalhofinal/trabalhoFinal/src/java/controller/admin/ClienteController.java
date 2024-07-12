package controller.admin;

import entidade.Clientes;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ClienteDAO;

@WebServlet(name = "vendedor/listaClientes", urlPatterns = {"/admin/vendedor/listaClientes"})
public class ClienteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String acao = (String) request.getParameter("acao");
        Clientes cliente = new Clientes();
        ClienteDAO clienteDAO = new ClienteDAO();
        RequestDispatcher rd;

        switch (acao) {
            case "Listar":
                ArrayList<Clientes> listaClientes = clienteDAO.getAll();
                request.setAttribute("listaClientes", listaClientes);
                rd = request.getRequestDispatcher("/views/admin/clientes/listaClientes.jsp");
                rd.forward(request, response);
                break;

            case "Alterar":
            case "Excluir":
                int id = Integer.parseInt(request.getParameter("id"));
                cliente = clienteDAO.get(id);
                request.setAttribute("cliente", cliente);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);
                rd = request.getRequestDispatcher("/views/admin/clientes/formClientes.jsp");
                rd.forward(request, response);
                break;

            case "Incluir":
                request.setAttribute("cliente", cliente);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);
                rd = request.getRequestDispatcher("/views/admin/clientes/formClientes.jsp");
                rd.forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String endereco = request.getParameter("endereco");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String uf = request.getParameter("uf");
        String cep = request.getParameter("cep");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");
        String btEnviar = request.getParameter("btEnviar");
        RequestDispatcher rd;
        ClienteDAO clienteDAO = new ClienteDAO();

        // Verifica se todos os campos estão preenchidos
        if (nome.isEmpty() || endereco.isEmpty() || bairro.isEmpty() || cidade.isEmpty() || uf.isEmpty() || cep.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
            Clientes cliente = new Clientes(id, nome, cpf, endereco, bairro, cidade, uf, cep, telefone, email);
            request.setAttribute("cliente", cliente);
            request.setAttribute("acao", btEnviar);
            request.setAttribute("msgError", "É necessário preencher todos os campos");
            rd = request.getRequestDispatcher("/views/admin/clientes/formClientes.jsp");
            rd.forward(request, response);
        } else {
            // Verifica se o CPF já existe, exceto para a exclusão
            if (!"Excluir".equals(btEnviar)) {
                boolean cpfAlreadyExists = clienteDAO.cpfExists(cpf);

                if (cpfAlreadyExists && !"Alterar".equals(btEnviar)) {
                    // Se o CPF já está cadastrado e a ação é "Incluir", mostra erro
                    Clientes cliente = new Clientes(id, nome, endereco, bairro, cidade, uf, cep, telefone, email);
                    request.setAttribute("cliente", cliente);
                    request.setAttribute("acao", "Incluir");
                    request.setAttribute("msgError", "CPF já cadastrado");
                    rd = request.getRequestDispatcher("/views/admin/clientes/formClientes.jsp");
                    rd.forward(request, response);
                } else if (cpfAlreadyExists && "Alterar".equals(btEnviar) && !clienteDAO.get(id).getCpf().equals(cpf)) {
                    // Se o CPF já está cadastrado e a ação é "Alterar", mas o CPF fornecido é diferente do CPF atual, mostra erro
                    Clientes cliente = new Clientes(id, nome, cpf, endereco, bairro, cidade, uf, cep, telefone, email);
                    request.setAttribute("cliente", cliente);
                    request.setAttribute("acao", "Alterar");
                    request.setAttribute("msgError", "CPF já cadastrado");
                    rd = request.getRequestDispatcher("/views/admin/clientes/formClientes.jsp");
                    rd.forward(request, response);
                } else {
                    // Se CPF não existe ou a ação é "Alterar" com CPF atual, prossegue
                    processarAcao(clienteDAO, btEnviar, id, nome, cpf, endereco, bairro, cidade, uf, cep, telefone, email, request, response);
                }
            } else {
                // Ação é "Excluir", apenas exclui o cliente
                processarAcao(clienteDAO, btEnviar, id, nome, cpf, endereco, bairro, cidade, uf, cep, telefone, email, request, response);
            }
        }
    }

    private void processarAcao(ClienteDAO clienteDAO, String btEnviar, int id, String nome, String cpf, String endereco, String bairro, String cidade, String uf, String cep, String telefone, String email, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Clientes cliente = new Clientes(id, nome, cpf, endereco, bairro, cidade, uf, cep, telefone, email);
        try {
            switch (btEnviar) {
                case "Incluir":
                    clienteDAO.insert(cliente);
                    request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                    break;
                case "Alterar":
                    clienteDAO.update(cliente);
                    request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                    break;
                case "Excluir":
                    clienteDAO.delete(id);
                    request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                    break;
            }
            request.setAttribute("link", "/trabalhoFinal/admin/vendedor/listaClientes?acao=Listar");
            RequestDispatcher rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
            rd.forward(request, response);
        } catch (IOException | ServletException ex) {
            throw new RuntimeException("Falha em uma query para cadastro de usuario", ex);
        }
    }

}
