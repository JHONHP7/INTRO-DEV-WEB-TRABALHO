<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Clientes"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="#">
    <title>Cliente</title>
    <link href="http://localhost:8080/trabalhoFinal/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
</head>
<body>

<div class="container">
    <jsp:include page="../../comum/menu.jsp" />
    <div class="row mt-5 justify-content-center">
        <div class="col-lg-6 col-md-8 col-sm-10">
            <%
                Clientes cliente = (Clientes) request.getAttribute("cliente");
                String acao = (String) request.getAttribute("acao");
                String titulo = "";
                switch (acao) {
                    case "Incluir":
                        titulo = "Incluir Cliente";
                        break;
                    case "Alterar":
                        titulo = "Alterar Cliente";
                        break;
                    case "Excluir":
                        titulo = "Excluir Cliente";
                        break;
                }

                String msgError = (String) request.getAttribute("msgError");
            %>
            <h1 class="text-center"><%= titulo %></h1>
            <% if (msgError != null && !msgError.isEmpty()) { %>
                <div class="alert alert-danger" role="alert">
                    <%= msgError %>
                </div>
            <% } %>

            <form action="/trabalhoFinal/admin/vendedor/listaClientes" method="POST" accept-charset="UTF-8">
                <input type="hidden" name="id" value="<%= cliente != null ? cliente.getId() : "" %>" class="form-control">

                <div class="mb-3">
                    <label for="nome" class="form-label">Nome</label>
                    <input type="text" name="nome" <%= acao.equals("Excluir") ? "readonly" : "" %> value="<%= cliente != null ? cliente.getNome() : "" %>" class="form-control">
                </div>

                <div class="mb-3">
                    <label for="cpf" class="form-label">CPF</label>
                    <input type="text" name="cpf" <%= acao.equals("Excluir") ? "readonly" : "" %> value="<%= cliente != null ? cliente.getCpf() : "" %>" class="form-control">
                </div>

                <div class="mb-3">
                    <label for="endereco" class="form-label">Endereço</label>
                    <input type="text" name="endereco" <%= acao.equals("Excluir") ? "readonly" : "" %> value="<%= cliente != null ? cliente.getEndereco() : "" %>" class="form-control">
                </div>

                <div class="mb-3">
                    <label for="bairro" class="form-label">Bairro</label>
                    <input type="text" name="bairro" <%= acao.equals("Excluir") ? "readonly" : "" %> value="<%= cliente != null ? cliente.getBairro() : "" %>" class="form-control">
                </div>

                <div class="mb-3">
                    <label for="cidade" class="form-label">Cidade</label>
                    <input type="text" name="cidade" <%= acao.equals("Excluir") ? "readonly" : "" %> value="<%= cliente != null ? cliente.getCidade() : "" %>" class="form-control">
                </div>

                <div class="mb-3">
                    <label for="uf" class="form-label">UF</label>
                    <input type="text" name="uf" <%= acao.equals("Excluir") ? "readonly" : "" %> value="<%= cliente != null ? cliente.getUf() : "" %>" class="form-control">
                </div>

                <div class="mb-3">
                    <label for="cep" class="form-label">CEP</label>
                    <input type="text" name="cep" <%= acao.equals("Excluir") ? "readonly" : "" %> value="<%= cliente != null ? cliente.getCep() : "" %>" class="form-control">
                </div>

                <div class="mb-3">
                    <label for="telefone" class="form-label">Telefone</label>
                    <input type="text" name="telefone" <%= acao.equals("Excluir") ? "readonly" : "" %> value="<%= cliente != null ? cliente.getTelefone() : "" %>" class="form-control">
                </div>

                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="text" name="email" <%= acao.equals("Excluir") ? "readonly" : "" %> value="<%= cliente != null ? cliente.getEmail() : "" %>" class="form-control">
                </div>

                <div class="mb-3 text-center">
                    <input type="submit" name="btEnviar" value="<%= acao %>" class="btn btn-primary">
                    <a href="/trabalhoFinal/admin/vendedor/listaClientes?acao=Listar" class="btn btn-danger">Retornar</a>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="http://localhost:8080/trabalhoFinal/views/bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>
