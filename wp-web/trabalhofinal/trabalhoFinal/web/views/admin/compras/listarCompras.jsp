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
        <style>
            html, body {
                height: 100%;
            }
            body {
                display: flex;
                flex-direction: column;
            }
            .container {
                flex: 1;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="row mt-5 justify-content-center">
                <div class="col-lg-8 col-md-10 col-sm-12">
                    <h1 class="mb-3 text-center">Lista de Compras</h1>
                    <div class="d-flex justify-content-start mb-3">
                        <a href="/trabalhoFinal/admin/comprador/comprasController?acao=Incluir" class="btn btn-primary">Incluir</a>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Quantidade</th>
                                    <th>Data</th>
                                    <th>Valor</th>
                                    <th>Fornecedor</th>
                                    <th>Produto</th>
                                    <th>Funcionário</th>      
                                    <th>Ações</th>        
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    ArrayList<Compras> listaCompras = (ArrayList<Compras>) request.getAttribute("listaCompras");
                                    if (listaCompras != null) {
                                        for (Compras compra : listaCompras) {
                                %>
                                <tr>
                                    <td><%= compra.getId()%></td>
                                    <td><%= compra.getQuantidadeCompra()%></td>
                                    <td><%= compra.getDataCompra()%></td>
                                    <td><%= compra.getValorCompra()%></td>
                                    <td><%= compra.getIdFornecedor()%></td>
                                    <td><%= compra.getIdProduto()%></td>
                                    <td><%= compra.getIdFuncionario()%></td>
                                    <td>
                                        <div class="btn-group" role="group">
                                            <a href="/trabalhoFinal/admin/comprador/comprasController?acao=Alterar&id=<%= compra.getId()%>" class="btn btn-warning btn-sm me-2">Alterar</a>
                                            <a href="/trabalhoFinal/admin/comprador/comprasController?acao=Excluir&id=<%= compra.getId()%>" class="btn btn-danger btn-sm">Excluir</a>
                                        </div>
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
        </div>
        <jsp:include page="/views/comum/footer.jsp" />             
        <script src="http://localhost:8080/trabalhoFinal/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
