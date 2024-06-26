<%-- 
    Document   : listarCategorias
    Created on : 25 de jun. de 2024, 20:06:11
    Author     : Pedro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Compras"%>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Listar Compras</title>
        <link href="http://localhost:8080/trabalhoFinal/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="row mt-5">
                <div class="col-sm-12">
                    <h1>Lista de Compras</h1>
                    <a href="/trabalhoFinal/admin/comprador/listaCompras?acao=Incluir" class="mb-2 btn btn-primary">Incluir</a>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>QuantidadeCompra</th>
                                <th>DataCompra</th>
                                <th>ValorCompra</th>
                                <th>IdFornecedor</th>
                                <th>IdProduto</th>
                                <th>IdFuncionario</th>      
                                <th>Ações</th>        
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                ArrayList<Compras> listaCompras = (ArrayList<Compras>) request.getAttribute("listaCompras");
                                if (listaCompras != null) {
                                    for (Compras compra: listaCompras) {
                            %>
                            <tr>
                                <td><%= compra.getId() %></td>
                                <td><%= compra.getQuantidadeCompra() %></td>
                                <td><%= compra.getDataCompra() %></td>
                                <td><%= compra.getValorCompra() %></td>
                                <td><%= compra.getIdFornecedor() %></td>
                                <td><%= compra.getIdProduto() %></td>
                                <td><%= compra.getIdFuncionario() %></td>
             
                                <td>
                                    <a href="/trabalhoFinal/admin/comprador/listaCompras?acao=Alterar&id=<%= compra.getId() %>" class="btn btn-warning">Alterar</a>
                                    <a href="/trabalhoFinal/admin/comprador/listaCompras?acao=Excluir&id=<%= compra.getId() %>" class="btn btn-danger">Excluir</a>
                                </td>
                            </tr>
                            <%
                                    }
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <script src="http://localhost:8080/trabalhoFinal/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>

