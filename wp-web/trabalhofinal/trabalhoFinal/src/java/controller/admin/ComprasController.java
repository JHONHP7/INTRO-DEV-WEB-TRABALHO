/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import entidade.Compras;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CompraDAO;

/**
 *
 * @author Pedro
 */
@WebServlet(name = "comprador/listaCompras", urlPatterns = {"/admin/comprador/listaCompras"})
public class ComprasController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String acao = (String) request.getParameter("acao");
        Compras compra = new Compras();
        CompraDAO compraDAO = new CompraDAO();
        RequestDispatcher rd;

        switch (acao) {
            case "Listar":
                ArrayList<Compras> listaCompras = compraDAO.getAll();
                request.setAttribute("listaCompras", listaCompras);
                rd = request.getRequestDispatcher("/views/admin/compras/listarCompras.jsp");
                rd.forward(request, response);
                break;

            case "Alterar":
            case "Excluir":
                int id = Integer.parseInt(request.getParameter("id"));
                compra = compraDAO.get(id);
                request.setAttribute("compra", compra);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);
                rd = request.getRequestDispatcher("/views/admin/compras/formCompras.jsp");
                rd.forward(request, response);
                break;

            case "Incluir":
                request.setAttribute("compra", compra);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);
                rd = request.getRequestDispatcher("/views/admin/compras/formCompras.jsp");
                rd.forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String idStr = request.getParameter("id");
        String quantidadeCompraStr = request.getParameter("quantidadeCompra");
        String dataCompraStr = request.getParameter("dataCompra");
        String valorCompraStr = request.getParameter("valorCompra");
        String idFornecedorStr = request.getParameter("idFornecedor");
        String idProdutoStr = request.getParameter("idProduto");
        String idFuncionarioStr = request.getParameter("uf");
        String btEnviar = request.getParameter("btEnviar");
        RequestDispatcher rd;

        if (idStr.isEmpty() || quantidadeCompraStr.isEmpty() || dataCompraStr.isEmpty() || valorCompraStr.isEmpty() || idFornecedorStr.isEmpty() || idProdutoStr.isEmpty() || idFuncionarioStr.isEmpty()) {
            Compras compra = new Compras();
            switch (btEnviar) {
                case "Alterar":
                case "Excluir":
                    try {
                        int id = Integer.parseInt(idStr);
                        CompraDAO compraDAO = new CompraDAO();
                        compra = compraDAO.get(id);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        throw new RuntimeException("Falha em uma query para cadastro de compra");
                    }
                    break;
            }
            request.setAttribute("compra", compra);
            request.setAttribute("acao", btEnviar);
            request.setAttribute("msgError", "É necessário preencher todos os campos");
            rd = request.getRequestDispatcher("/views/admin/Compras/formCompras.jsp");
            rd.forward(request, response);
        } else {
            try {
                int id = Integer.parseInt(idStr);
                int quantidadeCompra = Integer.parseInt(quantidadeCompraStr);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dataCompra = sdf.parse(dataCompraStr);
                float valorCompra = Integer.parseInt(valorCompraStr);
                int idFornecedor = Integer.parseInt(idFornecedorStr);
                int idProduto = Integer.parseInt(idProdutoStr);
                int idFuncionario = Integer.parseInt(idFuncionarioStr);

                Compras compra = new Compras(id, quantidadeCompra, dataCompra, valorCompra, idFornecedor, idProduto, idFuncionario);
                CompraDAO compraDAO = new CompraDAO();
                try {
                    switch (btEnviar) {
                        case "Incluir":
                            compraDAO.insert(compra);
                            request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                            break;
                        case "Alterar":
                            compraDAO.update(compra);
                            request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                            break;
                        case "Excluir":
                            compraDAO.delete(id);
                            request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                            break;
                    }
                    request.setAttribute("link", "/trabalhoFinal/admin/comprador/listaCompras?acao=Listar");
                    rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                    rd.forward(request, response);
                } catch (IOException | ServletException ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de usuario");
                }
            } catch (NumberFormatException | ParseException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Erro na conversão de dados");
            }
        }
    }
}
