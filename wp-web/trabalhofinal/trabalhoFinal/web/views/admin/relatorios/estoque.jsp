<%-- 
    Document   : estoque
    Created on : 11 de jul. de 2024, 05:38:36
    Author     : jhonatan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Produtos"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Relatório de Estoque</title>
        <link href="http://localhost:8080/trabalhoFinal/views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="row mt-5 justify-content-center">
                <div class="col-lg-10 col-md-10 col-sm-12 col-12 mx-auto">
                    <h1 class="text-center">Relatório de Estoque</h1>

                    <div class="table-responsive mt-4">
                        <table class="table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Nome do Produto</th>
                                    <th>Descrição</th>
                                    <th>Preço de Compra</th>
                                    <th>Preço de Venda</th>
                                    <th>Quantidade Disponível</th>
                                    <th>Categoria</th>
                                    <th>Liberado para Venda</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    ArrayList<Produtos> listaProdutos = (ArrayList<Produtos>) request.getAttribute("listaProdutos");
                                    if (listaProdutos != null && !listaProdutos.isEmpty()) {
                                        for (Produtos produto : listaProdutos) {
                                %>
                                <tr>
                                    <td><%= produto.getId()%></td>
                                    <td><%= produto.getNomeProduto()%></td>
                                    <td><%= produto.getDescricao()%></td>
                                    <td><%= String.format("%.2f", produto.getPrecoCompra())%></td>
                                    <td><%= String.format("%.2f", produto.getPrecoVenda())%></td>
                                    <td><%= produto.getQuantidadeDisponivel()%></td>
                                    <td><%= produto.getIdCategoria()%></td>
                                    <td><%= produto.getLiberadoVenda() == 'S' ? "Sim" : "Não"%></td>
                                </tr>
                                <%
                                    }
                                } else {
                                %>
                                <tr>
                                    <td colspan="8" class="text-center">Nenhum produto encontrado</td>
                                </tr>
                                <%
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

