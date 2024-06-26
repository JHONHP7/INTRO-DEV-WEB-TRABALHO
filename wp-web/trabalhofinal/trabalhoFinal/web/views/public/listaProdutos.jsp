<%-- 
    Document   : Produtos
    Created on : 24 de jun. de 2024, 00:54:29
    Author     : jhonatan-silva
--%>

<%@page import="entidade.Produtos"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Lista de Produtos</title>
        <link href="http://localhost:8080/trabalhoFinal/views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <%@include file="../comum/menu.jsp" %>
            <div class="mt-5">
                <h1>Lista de Produtos</h1>

                <div class="row">
                    <%
                        ArrayList<Produtos> allProdutos = (ArrayList<Produtos>) request.getAttribute("allProdutos");
                        if (allProdutos != null) {
                            for (Produtos produto : allProdutos) {
                    %>
                    <div class="col-sm-4 mb-4">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title"><%= produto.getNomeProduto()%></h5>
                                <p class="card-text"><%= produto.getDescricao()%></p>
                                <p class="card-text">Preço: R$ <%= produto.getPrecoVenda()%></p>
                            </div>
                        </div>
                    </div>
                    <%
                        }
                    } else {
                    %>
                    <p>Nenhum produto disponível.</p>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
        <script src="http://localhost:8080/trabalhoFinal/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
