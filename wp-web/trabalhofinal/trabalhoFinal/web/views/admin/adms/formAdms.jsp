<%-- 
    Document   : formAdms
    Created on : 26 de jun. de 2024, 04:59:14
    Author     : jhonatan
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entidade.Funcionarios"%>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Formulário Funcionários</title>
        <link href="http://localhost:8080/trabalhoFinal/views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="row mt-5">
                <div class="col-sm-6 offset-3">
                    <%
                        String acao = (String) request.getAttribute("acao");
                        Funcionarios funcionario = (Funcionarios) request.getAttribute("funcionario");
                        if ("Incluir".equals(acao)) {
                            out.println("<h1>Incluir Administrador</h1>");
                        } else if ("Alterar".equals(acao)) {
                            out.println("<h1>Alterar Administrador</h1>");
                        } else if ("Excluir".equals(acao)) {
                            out.println("<h1>Excluir Administrador</h1>");
                        }
                        String msgError = (String) request.getAttribute("msgError");
                        if (msgError != null && !msgError.isEmpty()) {
                    %>
                    <div class="alert alert-danger" role="alert">
                        <%= msgError%>
                    </div>
                    <% }%>

                    <form action="/trabalhoFinal/admin/administrador/cadastroAdms" method="POST" accept-charset="UTF-8">
                        <input type="hidden" name="id" value="<%= funcionario != null ? funcionario.getId() : ""%>" class="form-control">
                        <div class="mb-3">
                            <label for="nome" class="form-label">Nome</label>
                            <input type="text" name="nome" value="<%= funcionario != null ? funcionario.getNome() : ""%>" class="form-control" <%= "Excluir".equals(acao) ? "readonly" : ""%>>
                        </div>
                        <div class="mb-3">
                            <label for="cpf" class="form-label">CPF</label>
                            <input  type="text" name="cpf" minlength="11" maxlength="11"  placeholder="Apenas números" value="<%= funcionario != null ? funcionario.getCpf() : ""%>" class="form-control" <%= "Excluir".equals(acao) ? "readonly" : ""%>>
                        </div>
                        <div class="mb-3">
                            <label for="senha" class="form-label">Senha</label>
                            <input type="password" name="senha" value="<%= funcionario != null ? funcionario.getSenha() : ""%>" class="form-control" <%= "Excluir".equals(acao) ? "readonly" : ""%>>
                        </div>
                        <div>
                            <input type="submit" name="btEnviar" value="<%= acao%>" class="btn btn-primary">
                            <a href="/trabalhoFinal/admin/administrador/cadastroAdms?acao=Listar" class="btn btn-danger">Retornar</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script src="http://localhost:8080/trabalhoFinal/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>

