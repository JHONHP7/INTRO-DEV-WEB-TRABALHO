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
    <title>Listar Clientes</title>
    <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
</head>
<body>
    <div class="container">
        <jsp:include page="../../comum/menu.jsp" />
        <div class="row mt-5 justify-content-center">
            <div class="col-lg-10 col-md-10 col-sm-10 col-12 mx-auto">
                <h1 class="text-center">Lista de Clientes</h1>
                <a href="/trabalhoFinal/admin/vendedor/listaClientes?acao=Incluir" class="mb-2 btn btn-primary">Incluir</a>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nome</th>
                                <th>CPF</th>
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
                                ArrayList<Clientes> listaClientes = (ArrayList<Clientes>) request.getAttribute("listaClientes");
                                if (listaClientes != null) {
                                    for (Clientes cliente : listaClientes) {
                            %>
                            <tr>
                                <td><%= cliente.getId() %></td>
                                <td><%= cliente.getNome() %></td>
                                <td><%= cliente.getCpf() %></td>
                                <td><%= cliente.getEndereco() %></td>
                                <td><%= cliente.getBairro() %></td>
                                <td><%= cliente.getCidade() %></td>
                                <td><%= cliente.getUf() %></td>
                                <td><%= cliente.getCep() %></td>
                                <td><%= cliente.getTelefone() %></td>
                                <td><%= cliente.getEmail() %></td>
                                <td>
                                    <a href="/trabalhoFinal/admin/vendedor/listaClientes?acao=Alterar&id=<%= cliente.getId() %>" class="btn btn-warning">Alterar</a>
                                    <a href="/trabalhoFinal/admin/vendedor/listaClientes?acao=Excluir&id=<%= cliente.getId() %>" class="btn btn-danger">Excluir</a>
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
    <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>
