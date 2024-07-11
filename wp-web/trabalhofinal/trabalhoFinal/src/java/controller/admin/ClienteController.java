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

        if (nome.isEmpty() || cpf.isEmpty() || endereco.isEmpty() || bairro.isEmpty() || cidade.isEmpty() || uf.isEmpty() || cep.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
            Clientes cliente = new Clientes();
            switch (btEnviar) {
                case "Alterar":
                case "Excluir":
                    try {
                        ClienteDAO clienteDAO = new ClienteDAO();
                        cliente = clienteDAO.get(id);
                    } catch (Exception ex) {
                        throw new RuntimeException("Falha em uma query para cadastro de cliente");
                    }
                    break;
            }
            request.setAttribute("cliente", cliente);
            request.setAttribute("acao", btEnviar);
            request.setAttribute("msgError", "É necessário preencher todos os campos");
            rd = request.getRequestDispatcher("/views/admin/clientes/formClientes.jsp");
            rd.forward(request, response);
        } else {
            Clientes cliente = new Clientes(id, nome, cpf, endereco, bairro, cidade, uf, cep, telefone, email);
            ClienteDAO clienteDAO = new ClienteDAO();
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
                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                rd.forward(request, response);
            } catch (IOException | ServletException ex) {
                throw new RuntimeException("Falha em uma query para cadastro de usuario");
            }
        }
    }

}
