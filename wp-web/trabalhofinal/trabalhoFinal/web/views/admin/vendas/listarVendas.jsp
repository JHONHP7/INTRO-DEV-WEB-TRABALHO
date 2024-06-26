<%-- 
    Document   : listarVendas
    Created on : 26 de jun. de 2024, 06:35:10
    Author     : jhonatan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Vendas"%>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Listar Vendas</title>
        <link href="http://localhost:8080/trabalhoFinal/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="row mt-5 justify-content-center">
                <div class="col-lg-10 col-md-10 col-sm-10 col-12 mx-auto">
                    <h1 class="text-center">Lista de Vendas</h1>
                    <a href="/trabalhoFinal/admin/vendedor/vendedorCadastroVendas?acao=Incluir" class="mb-2 btn btn-primary">Incluir</a>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Quantidade</th>
                                    <th>Data</th>
                                    <th>Valor</th>
                                    <th>ID Cliente</th>
                                    <th>ID Produto</th>
                                    <th>ID Funcionário</th>
                                    <th>Ações</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% ArrayList<Vendas> listaVendas = (ArrayList<Vendas>) request.getAttribute("listaVendas");
                                    if (listaVendas != null) {
                                        for (Vendas venda : listaVendas) {%>
                                <tr>
                                    <td><%= venda.getId()%></td>
                                    <td class="text-nowrap"><%= venda.getQuantidadeVenda()%></td>
                                    <td class="text-nowrap"><%= venda.getDataVenda()%></td>
                                    <td class="text-nowrap"><%= venda.getValorVenda()%></td>
                                    <td class="text-nowrap"><%= venda.getIdCliente()%></td>
                                    <td class="text-nowrap"><%= venda.getIdProduto()%></td>
                                    <td class="text-nowrap"><%= venda.getIdFuncionario()%></td>
                                    <td>
                                        <div class="btn-group" role="group">
                                            <a href="/trabalhoFinal/admin/vendedor/vendedorCadastroVendas?acao=Alterar&id=<%= venda.getId()%>" class="btn btn-warning me-1">Alterar</a>
                                            <a href="/trabalhoFinal/admin/vendedor/vendedorCadastroVendas?acao=Excluir&id=<%= venda.getId()%>" class="btn btn-danger">Excluir</a>
                                        </div>
                                    </td>
                                </tr>
                                <%      }
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <script src="http://localhost:8080/trabalhoFinal/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
