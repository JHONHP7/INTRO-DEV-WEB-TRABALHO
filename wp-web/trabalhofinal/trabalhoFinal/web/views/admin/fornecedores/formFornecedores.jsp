<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Fornecedores"%>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Formulário Fornecedores</title>
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
        <div class="container pb-5">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="row mt-5">
                <div class="col-12 col-md-6 offset-md-3">
                    <%
                        Fornecedores fornecedor = (Fornecedores) request.getAttribute("fornecedor");
                        String acao = (String) request.getAttribute("acao");

                        switch (acao) {
                            case "Incluir":
                                out.println("<h1>Incluir Fornecedor</h1>");
                                break;
                            case "Alterar":
                                out.println("<h1>Alterar Fornecedor</h1>");
                                break;
                            case "Excluir":
                                out.println("<h1>Excluir Fornecedor</h1>");
                                break;
                        }

                        String msgError = (String) request.getAttribute("msgError");
                        if ((msgError != null) && (!msgError.isEmpty())) {%>
                    <div class="alert alert-danger" role="alert">
                        <%= msgError%>
                    </div>
                    <% }%>

                    <form action="/trabalhoFinal/admin/comprador/listaFornecedores" method="POST" accept-charset="UTF-8">
                        <input type="hidden" name="id" value="<%= (fornecedor != null) ? Integer.toString(fornecedor.getId()) : ""%>" class="form-control">

                        <div class="mb-3">
                            <label for="razao_social" class="form-label">Razão Social</label>
                            <input type="text" name="razao_social" 
                                   <%= acao.equals("Excluir") ? "readonly" : ""%>
                                   value="<%= (fornecedor != null && fornecedor.getRazaoSocial() != null) ? fornecedor.getRazaoSocial() : ""%>" 
                                   class="form-control"
                                   placeholder="Razão Social">
                        </div>

                        <div class="mb-3">
                            <label for="cnpj" class="form-label">CNPJ</label>
                            <input type="text" name="cnpj" minlength="18" maxlength="18" 
                                   placeholder="XX.XXX.XXX/0001-XX" 
                                   <%= acao.equals("Excluir") ? "readonly" : ""%>
                                   value="<%= (fornecedor != null && fornecedor.getCnpj() != null) ? fornecedor.getCnpj() : ""%>" 
                                   class="form-control">
                        </div>

                        <div class="mb-3">
                            <label for="endereco" class="form-label">Endereço</label>
                            <input type="text" name="endereco" 
                                   <%= acao.equals("Excluir") ? "readonly" : ""%>
                                   value="<%= (fornecedor != null && fornecedor.getEndereco() != null) ? fornecedor.getEndereco() : ""%>" 
                                   class="form-control"
                                   placeholder="Endereço">
                        </div>

                        <div class="mb-3">
                            <label for="bairro" class="form-label">Bairro</label>
                            <input type="text" name="bairro" 
                                   <%= acao.equals("Excluir") ? "readonly" : ""%>
                                   value="<%= (fornecedor != null && fornecedor.getBairro() != null) ? fornecedor.getBairro() : ""%>" 
                                   class="form-control"
                                   placeholder="Bairro">
                        </div>

                        <div class="mb-3">
                            <label for="cidade" class="form-label">Cidade</label>
                            <input type="text" name="cidade" 
                                   <%= acao.equals("Excluir") ? "readonly" : ""%>
                                   value="<%= (fornecedor != null && fornecedor.getCidade() != null) ? fornecedor.getCidade() : ""%>" 
                                   class="form-control"
                                   placeholder="Cidade">
                        </div>

                        <div class="mb-3">
                            <label for="uf" class="form-label">UF</label>
                            <input type="text" name="uf" minlength="2" maxlength="2" 
                                   <%= acao.equals("Excluir") ? "readonly" : ""%>
                                   value="<%= (fornecedor != null && fornecedor.getUf() != null) ? fornecedor.getUf() : ""%>" 
                                   class="form-control"
                                   placeholder="UF">
                        </div>

                        <div class="mb-3">
                            <label for="cep" class="form-label">CEP</label>
                            <input type="text" name="cep" 
                                   <%= acao.equals("Excluir") ? "readonly" : ""%>
                                   value="<%= (fornecedor != null && fornecedor.getCep() != null) ? fornecedor.getCep() : ""%>" 
                                   class="form-control"
                                   placeholder="CEP">
                        </div>

                        <div class="mb-3">
                            <label for="telefone" class="form-label">Telefone</label>
                            <input type="text" name="telefone" 
                                   <%= acao.equals("Excluir") ? "readonly" : ""%>
                                   value="<%= (fornecedor != null && fornecedor.getTelefone() != null) ? fornecedor.getTelefone() : ""%>" 
                                   class="form-control"
                                   placeholder="Telefone">
                        </div>

                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="text" name="email" 
                                   <%= acao.equals("Excluir") ? "readonly" : ""%>
                                   value="<%= (fornecedor != null && fornecedor.getEmail() != null) ? fornecedor.getEmail() : ""%>" 
                                   class="form-control"
                                   placeholder="Email">
                        </div>

                        <div>
                            <input type="submit" name="btEnviar" value="<%= acao%>" class="btn btn-primary">
                            <a href="/trabalhoFinal/admin/comprador/listaFornecedores?acao=Listar" class="btn btn-danger">Retornar</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="/views/comum/footer.jsp" />
        <script src="http://localhost:8080/trabalhoFinal/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>

