/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.FuncionarioDAO;
import entidade.Funcionarios;

/**
 *
 * @author jhonatan
 */
@WebServlet(name = "AdmCadastroVendedores", urlPatterns = {"/admin/administrador/cadastroVendedores"})
public class AdmCadastroVendedores extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String acao = (String) request.getParameter("acao");
        Funcionarios funcionario = new Funcionarios();
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
                funcionario = funcionarioDAO.get(id);
                request.setAttribute("funcionario", funcionario);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);
                rd = request.getRequestDispatcher("/views/admin/vendedores/formVendedores.jsp");
                rd.forward(request, response);
                break;

            case "Incluir":
                request.setAttribute("funcionario", funcionario);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);
                rd = request.getRequestDispatcher("/views/admin/vendedores/formVendedores.jsp");
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
        String senha = request.getParameter("senha");
        String papel = "1"; // Considerando que o papel do vendedor seja sempre "1"
        String btEnviar = request.getParameter("btEnviar");
        RequestDispatcher rd;

        if (nome.isEmpty() || cpf.isEmpty() || senha.isEmpty()) {
            Funcionarios funcionario = new Funcionarios();
            switch (btEnviar) {
                case "Alterar":
                case "Excluir":
                    try {
                        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
                        funcionario = funcionarioDAO.get(id);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        throw new RuntimeException("Falha ao buscar funcionário");
                    }
                    break;
            }
            request.setAttribute("funcionario", funcionario);
            request.setAttribute("acao", btEnviar);
            request.setAttribute("msgError", "É necessário preencher todos os campos");
            rd = request.getRequestDispatcher("/views/admin/vendedores/listaVendedores.jsp");
            rd.forward(request, response);
        } else {
            Funcionarios funcionario = new Funcionarios(id, nome, cpf, senha, papel);
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
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
            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em operação de funcionário");
            }
        }
    }
}
