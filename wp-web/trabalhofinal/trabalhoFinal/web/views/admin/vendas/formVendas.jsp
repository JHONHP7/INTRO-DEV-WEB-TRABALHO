<%-- 
    Document   : formVendas
    Created on : 26 de jun. de 2024, 06:34:59
    Author     : jhonatan
--%>
<%@page import="entidade.Produtos"%>
<%@page import="entidade.Clientes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entidade.Vendas"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Formulário de Vendas</title>
        <link href="http://localhost:8080/trabalhoFinal/views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="row mt-5 justify-content-center">
                <div class="col-lg-6 col-md-8 col-sm-10 col-12 mx-auto">
                    <%
                        Vendas venda = (Vendas) request.getAttribute("venda");
                        String acao = (String) request.getAttribute("acao");
                        String titulo = "";
                        switch (acao) {
                            case "Incluir":
                                titulo = "Incluir Venda";
                                break;
                            case "Alterar":
                                titulo = "Alterar Venda";
                                break;
                            case "Excluir":
                                titulo = "Excluir Venda";
                                break;
                        }

                        String msgError = (String) request.getAttribute("msgError");

                        // Obtendo listas de clientes e produtos
                        List<Clientes> listaClientes = (List<Clientes>) request.getAttribute("listaClientes");
                        List<Produtos> listaProdutos = (List<Produtos>) request.getAttribute("listaProdutos");
                    %>
                    <h1 class="text-center"><%= titulo%></h1>
                    <% if (msgError != null && !msgError.isEmpty()) {%>
                    <div class="alert alert-danger" role="alert">
                        <%= msgError%>
                    </div>
                    <% }%>

                    <form action="/trabalhoFinal/admin/vendedor/vendedorCadastroVendas" method="post">
                        <input type="hidden" name="id" value="<%= venda != null ? venda.getId() : ""%>">

                        <div class="mb-3">
                            <label for="cliente" class="form-label">Cliente</label>
                            <select class="form-control" id="cliente" name="cliente">
                                <option value="">Selecione um cliente</option>
                                <% for (Clientes cliente : listaClientes) {%>
                                <option value="<%= cliente.getId()%>" <%= venda != null && venda.getIdCliente() == cliente.getId() ? "selected" : ""%>><%= cliente.getNome()%></option>
                                <% } %>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="produto" class="form-label">Produto</label>
                            <select class="form-control" id="produto" name="produto">
                                <option value="">Selecione um produto</option>
                                <% for (Produtos produto : listaProdutos) {%>
                                <option value="<%= produto.getId()%>" <%= venda != null && venda.getIdProduto() == produto.getId() ? "selected" : ""%>><%= produto.getNome()%></option>
                                <% }%>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="quantidadeVenda" class="form-label">Quantidade Venda</label>
                            <input type="text" class="form-control" id="quantidadeVenda" name="quantidadeVenda" value="<%= venda != null ? venda.getQuantidadeVenda() : ""%>">
                        </div>

                        <div class="mb-3 text-center">
                            <button type="submit" class="btn btn-primary" name="btEnviar" value="Incluir">Incluir</button>
                            <button type="submit" class="btn btn-warning" name="btEnviar" value="Alterar">Alterar</button>
                            <button type="submit" class="btn btn-danger" name="btEnviar" value="Excluir">Excluir</button>
                            <a href="/trabalhoFinal/admin/vendedor/vendedorCadastroVendas?acao=Listar" class="btn btn-secondary">Retornar</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script src="http://localhost:8080/trabalhoFinal/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
