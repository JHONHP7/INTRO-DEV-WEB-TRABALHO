<%-- 
    Document   : listarCategorias
    Created on : 25 de jun. de 2024, 20:06:11
    Author     : Pedro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Fornecedores"%>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Listar Fornecedores</title>
        <link href="http://localhost:8080/trabalhoFinal/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="row mt-5">
                <div class="col-sm-12">
                    <h1>Lista de Fornecedores</h1>
                    <a href="/trabalhoFinal/admin/comprador/listaFornecedores?acao=Incluir" class="mb-2 btn btn-primary">Incluir</a>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Razão Social</th>
                                <th>CNPJ</th>
                                <th>Endereço</th>
                                <th>Bairro</th>
                                <th>Cidade</th>
                                <th>UF</th>
                                <th>CEP</th>
                                <th>Telefone</th>
                                <th>Email</th>
                                <th>Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                ArrayList<Fornecedores> listaFornecedores = (ArrayList<Fornecedores>) request.getAttribute("listaFornecedores");
                                if (listaFornecedores != null) {
                                    for (Fornecedores fornecedor : listaFornecedores) {
                            %>
                            <tr>
                                <td><%= fornecedor.getId() %></td>
                                <td><%= fornecedor.getRazaoSocial() %></td>
                                <td><%= fornecedor.getCnpj() %></td>
                                <td><%= fornecedor.getEndereco() %></td>
                                <td><%= fornecedor.getBairro() %></td>
                                <td><%= fornecedor.getCidade() %></td>
                                <td><%= fornecedor.getUf() %></td>
                                <td><%= fornecedor.getCep() %></td>
                                <td><%= fornecedor.getTelefone() %></td>
                                <td><%= fornecedor.getEmail() %></td>
                                <td>
                                    <a href="/trabalhoFinal/admin/comprador/listaFornecedores?acao=Alterar&id=<%= fornecedor.getId() %>" class="btn btn-warning">Alterar</a>
                                    <a href="/trabalhoFinal/admin/comprador/listaFornecedores?acao=Excluir&id=<%= fornecedor.getId() %>" class="btn btn-danger">Excluir</a>
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

