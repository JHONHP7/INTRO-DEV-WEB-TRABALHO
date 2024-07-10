<%-- 
    Document   : listarCompradores
    Created on : 26 de jun. de 2024, 04:23:56
    Author     : jhonatan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Funcionarios"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="#">
    <title>Listar Vendedores</title>
    <link href="http://localhost:8080/trabalhoFinal/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
</head>
<body>
    <div class="container">
        <jsp:include page="../../comum/menu.jsp" />
        <div class="row mt-5 justify-content-center">
            <div class="col-lg-10 col-md-10 col-sm-10 col-12 mx-auto">
                <h1 class="text-center">Lista de Compradores</h1>
                <a href="/trabalhoFinal/admin/administrador/cadastroCompradores?acao=Incluir" class="mb-2 btn btn-primary">Incluir</a>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nome</th>
                                <th>CPF</th>
                                <th>Senha</th>
                                <th>Papel</th>
                                <th>Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% ArrayList<Funcionarios> listarCompradores = (ArrayList<Funcionarios>) request.getAttribute("listarCompradores");
                                if (listarCompradores != null) {
                                    for (Funcionarios funcionario : listarCompradores) { %>
                            <tr>
                                <td><%= funcionario.getId() %></td>
                                <td class="text-nowrap"><%= funcionario.getNome() %></td>
                                <td class="text-nowrap"><%= funcionario.getCpf() %></td>
                                <td class="text-nowrap"><%= funcionario.getSenha() %></td>
                                <td class="text-nowrap"><%= funcionario.getPapel() %></td>
                                <td>
                                    <div class="btn-group" role="group">
                                        <a href="/trabalhoFinal/admin/administrador/cadastroCompradores?acao=Alterar&id=<%= funcionario.getId() %>" class="btn btn-warning me-1">Alterar</a>
                                        <a href="/trabalhoFinal/admin/administrador/cadastroCompradores?acao=Excluir&id=<%= funcionario.getId() %>" class="btn btn-danger">Excluir</a>
                                    </div>
                                </td>
                            </tr>
                            <%      }
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
