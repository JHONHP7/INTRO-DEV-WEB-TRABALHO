<%-- 
    Document   : formCategorias
    Created on : 11 de jul. de 2024, 02:48:50
    Author     : jhonatan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entidade.Categorias"%>
<%@page import="model.CategoriaDAO"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title><%= request.getAttribute("titulo")%></title>
        <link href="http://localhost:8080/trabalhoFinal/views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="row mt-5 justify-content-center">
                <div class="col-lg-6 col-md-8 col-sm-10">
                    <%
                        Categorias categoria = (Categorias) request.getAttribute("categoria");
                        String acao = (String) request.getAttribute("acao");
                        String titulo = "";
                        switch (acao) {
                            case "Incluir":
                                titulo = "Incluir Categoria";
                                break;
                            case "Alterar":
                                titulo = "Alterar Categoria";
                                break;
                            case "Excluir":
                                titulo = "Excluir Categoria";
                                break;
                        }

                        String msgError = (String) request.getAttribute("msgError");
                    %>
                    <h1 class="text-center"><%= titulo%></h1>
                    <% if (msgError != null && !msgError.isEmpty()) {%>
                    <div class="alert alert-danger" role="alert">
                        <%= msgError%>
                    </div>
                    <% }%>

                    <form action="/trabalhoFinal/admin/comprador/categoriaController" method="POST" accept-charset="UTF-8">
                        <input type="hidden" name="id" value="<%= categoria != null ? categoria.getId() : ""%>" class="form-control">

                        <div class="mb-3">
                            <label for="nome_categoria" class="form-label">Nome da Categoria</label>
                            <input type="text" name="nome_categoria" <%= acao.equals("Excluir") ? "readonly" : ""%> value="<%= categoria != null ? categoria.getNomeCategoria() : ""%>" class="form-control">
                        </div>

                        <div class="mb-3 text-center">
                            <input type="submit" name="btEnviar" value="<%= acao%>" class="btn btn-primary">
                            <a href="/trabalhoFinal/admin/comprador/categoriaController?acao=Listar" class="btn btn-danger">Retornar</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <script src="http://localhost:8080/trabalhoFinal/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>

