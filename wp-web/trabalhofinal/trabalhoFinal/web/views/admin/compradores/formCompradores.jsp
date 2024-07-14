<%-- 
    Document   : formCompradores
    Created on : 26 de jun. de 2024, 04:23:42
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
        <title>Formulário Compradores</title>
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
            <div class="row mt-5">
                <div class="col-md-6 offset-md-3 col-sm-8 offset-sm-2 col-10 offset-1">
                    <%
                        String acao = (String) request.getAttribute("acao");
                        Funcionarios funcionario = (Funcionarios) request.getAttribute("funcionario");
                        if ("Incluir".equals(acao)) {
                            out.println("<h1>Incluir Comprador</h1>");
                        } else if ("Alterar".equals(acao)) {
                            out.println("<h1>Alterar Comprador</h1>");
                        } else if ("Excluir".equals(acao)) {
                            out.println("<h1>Excluir Comprador</h1>");
                        }
                        String msgError = (String) request.getAttribute("msgError");
                        if (msgError != null && !msgError.isEmpty()) {
                    %>
                    <div class="alert alert-danger" role="alert">
                        <%= msgError%>
                    </div>
                    <% }%>

                    <form action="/trabalhoFinal/admin/administrador/cadastroCompradores" method="POST" accept-charset="UTF-8">
                        <input type="hidden" name="id" value="<%= funcionario != null ? funcionario.getId() : ""%>" class="form-control">
                        <div class="mb-3">
                            <label for="nome" class="form-label">Nome</label>
                            <input type="text" name="nome" placeholder="Digite seu nome" value="<%= funcionario != null ? funcionario.getNome() : ""%>" class="form-control" <%= "Excluir".equals(acao) ? "readonly" : "required"%>>
                        </div>
                        <div class="mb-3">
                            <label for="cpf" class="form-label">CPF</label>
                            <input type="text" name="cpf" minlength="14" maxlength="14" placeholder="123.456.789-00" value="<%= funcionario != null ? funcionario.getCpf() : ""%>" class="form-control" <%= "Excluir".equals(acao) ? "readonly" : "required"%>>
                        </div>
                        <div class="mb-3">
                            <label for="senha" class="form-label">Senha</label>
                            <input type="password" name="senha" minlength="8" maxlength="10" value="<%= funcionario != null ? funcionario.getSenha() : ""%>" class="form-control" <%= "Excluir".equals(acao) ? "readonly" : "required"%>>
                        </div>
                        <div>
                            <input type="submit" name="btEnviar" value="<%= acao%>" class="btn btn-primary">
                            <a href="/trabalhoFinal/admin/administrador/cadastroCompradores?acao=Listar" class="btn btn-danger">Retornar</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="/views/comum/footer.jsp" />           
        <script src="http://localhost:8080/trabalhoFinal/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
