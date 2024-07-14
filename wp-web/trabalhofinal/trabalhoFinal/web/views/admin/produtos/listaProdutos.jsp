<%-- 
    Document   : listaProdutos
    Created on : 11 de jul. de 2024, 03:25:30
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
        <link rel="shortcut icon" href="#">
        <title>Lista de Produtos</title>
        <link href="http://localhost:8080/trabalhoFinal/views/bootstrap/bootstrap.min.css" rel="stylesheet">
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
        <div class="container pb-5">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="row mt-5 justify-content-center">
                <div class="col-lg-10 col-md-10 col-sm-10 col-12 mx-auto">
                    <h1 class="text-center">Lista de Produtos</h1>
                    <a href="/trabalhoFinal/admin/comprador/produtosController?acao=Incluir" class="mb-2 btn btn-primary">Incluir</a>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Nome do Produto</th>
                                    <th>Descrição</th>
                                    <th>Preço de Compra</th>
                                    <th>Preço de Venda</th>
                                    <th>Quantidade Disponível</th>
                                    <th>Liberado para Venda</th>
                                    <th>Ações</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    ArrayList<Produtos> listaProdutos = (ArrayList<Produtos>) request.getAttribute("listaProdutos");
                                    if (listaProdutos != null) {
                                        for (Produtos produto : listaProdutos) {
                                            boolean liberadoParaVenda = produto.getLiberadoVenda() == 'S';
                                %>
                                <tr>
                                    <td><%= produto.getId()%></td>
                                    <td><%= produto.getNomeProduto()%></td>
                                    <td><%= produto.getDescricao()%></td>
                                    <td><%= produto.getPrecoCompra()%></td>
                                    <td><%= produto.getPrecoVenda()%></td>
                                    <td><%= produto.getQuantidadeDisponivel()%></td>
                                    <td><%= liberadoParaVenda ? "Sim" : "Não"%></td>
                                    <td>
                                        <div class="btn-group" role="group">
                                            <a href="/trabalhoFinal/admin/comprador/produtosController?acao=Alterar&id=<%= produto.getId()%>" class="btn btn-warning me-1">Alterar</a>
                                            <a href="/trabalhoFinal/admin/comprador/produtosController?acao=Excluir&id=<%= produto.getId()%>" class="btn btn-danger me-1">Excluir</a>
                                            <% if (liberadoParaVenda) {%>
                                            <form action="/trabalhoFinal/admin/comprador/produtosController" method="POST" class="d-inline">
                                                <input type="hidden" name="id" value="<%= produto.getId()%>">
                                                <input type="hidden" name="btEnviar" value="Retirar da Venda">
                                                <input type="submit" value="Retirar da Venda" class="btn btn-danger">
                                            </form>
                                            <% } else {%>
                                            <form action="/trabalhoFinal/admin/comprador/produtosController" method="POST" class="d-inline">
                                                <input type="hidden" name="id" value="<%= produto.getId()%>">
                                                <input type="hidden" name="btEnviar" value="Colocar para Venda">
                                                <input type="submit" value="Colocar para Venda" class="btn btn-success">
                                            </form>
                                            <% } %>
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
