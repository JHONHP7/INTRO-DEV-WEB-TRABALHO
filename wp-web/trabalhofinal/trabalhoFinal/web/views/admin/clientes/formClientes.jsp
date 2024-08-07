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
                    <h1 class="text-center"><%= titulo%></h1>
                    <% if (msgError != null && !msgError.isEmpty()) {%>
                    <div class="alert alert-danger" role="alert">
                        <%= msgError%>
                    </div>
                    <% }%>

                    <form action="/trabalhoFinal/admin/vendedor/listaClientes" method="POST" accept-charset="UTF-8">
                        <input type="hidden" name="id" value="<%= cliente != null ? cliente.getId() : ""%>" class="form-control">

                        <div class="mb-3">
                            <label for="nome" class="form-label">Nome</label>
                            <input type="text" name="nome" <%= acao.equals("Excluir") ? "readonly" : "required"%> 
                                   value="<%= cliente != null && cliente.getNome() != null && !"null".equals(cliente.getNome()) ? cliente.getNome() : ""%>"
                                   class="form-control" placeholder="Nome completo">
                        </div>

                        <div class="mb-3">
                            <label for="cpf" class="form-label">CPF</label>
                            <input type="text" name="cpf" minlength="11" maxlength="14" placeholder="123.456.789-00" <%= acao.equals("Excluir") ? "readonly" : "required"%> 
                                   value="<%= cliente != null && cliente.getCpf() != null && !"null".equals(cliente.getCpf()) ? cliente.getCpf() : ""%>" class="form-control" pattern="\d{3}\.\d{3}\.\d{3}-\d{2}">
                        </div>

                        <div class="mb-3">
                            <label for="endereco" class="form-label">Endereço</label>
                            <input type="text" name="endereco" <%= acao.equals("Excluir") ? "readonly" : "required"%> 
                                   value="<%= cliente != null && cliente.getEndereco() != null && !"null".equals(cliente.getEndereco()) ? cliente.getEndereco() : ""%>" class="form-control" placeholder="Rua, número, complemento">
                        </div>

                        <div class="mb-3">
                            <label for="bairro" class="form-label">Bairro</label>
                            <input type="text" name="bairro" <%= acao.equals("Excluir") ? "readonly" : "required"%> 
                                   value="<%= cliente != null && cliente.getBairro() != null && !"null".equals(cliente.getBairro()) ? cliente.getBairro() : ""%>" class="form-control" placeholder="Bairro">
                        </div>

                        <div class="mb-3">
                            <label for="cidade" class="form-label">Cidade</label>
                            <input type="text" name="cidade" <%= acao.equals("Excluir") ? "readonly" : "required"%> 
                                   value="<%= cliente != null && cliente.getCidade() != null && !"null".equals(cliente.getCidade()) ? cliente.getCidade() : ""%>" class="form-control" placeholder="Cidade">
                        </div>

                        <div class="mb-3">
                            <label for="uf" class="form-label">UF</label>
                            <input type="text" name="uf" minlength="2" maxlength="2" placeholder="UF" <%= acao.equals("Excluir") ? "readonly" : "required"%> 
                                   value="<%= cliente != null && cliente.getUf() != null && !"null".equals(cliente.getUf()) ? cliente.getUf() : ""%>" class="form-control" pattern="[A-Z]{2}">
                        </div>

                        <div class="mb-3">
                            <label for="cep" class="form-label">CEP</label>
                            <input type="text" name="cep" minlength="8" maxlength="8" placeholder="00000000" <%= acao.equals("Excluir") ? "readonly" : "required"%> 
                                   value="<%= cliente != null && cliente.getCep() != null && !"null".equals(cliente.getCep()) ? cliente.getCep() : ""%>" class="form-control">
                        </div>

                        <div class="mb-3">
                            <label for="telefone" class="form-label">Telefone</label>
                            <input type="text" name="telefone" minlength="10" maxlength="15" placeholder="(00) 00000-0000" <%= acao.equals("Excluir") ? "readonly" : "required"%> 
                                   value="<%= cliente != null && cliente.getTelefone() != null && !"null".equals(cliente.getTelefone()) ? cliente.getTelefone() : ""%>" class="form-control">
                        </div>

                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" name="email" <%= acao.equals("Excluir") ? "readonly" : "required"%> 
                                   value="<%= cliente != null && cliente.getEmail() != null && !"null".equals(cliente.getEmail()) ? cliente.getEmail() : ""%>" class="form-control" placeholder="exemplo@dominio.com">
                        </div>

                        <div class="mb-3 text-center">
                            <input type="submit" name="btEnviar" value="<%= acao%>" class="btn btn-primary">
                            <a href="/trabalhoFinal/admin/vendedor/listaClientes?acao=Listar" class="btn btn-danger">Retornar</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="/views/comum/footer.jsp" />
        <script src="http://localhost:8080/trabalhoFinal/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
