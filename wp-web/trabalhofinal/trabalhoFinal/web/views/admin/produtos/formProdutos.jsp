<%-- 
    Document   : formProdutos
    Created on : 11 de jul. de 2024, 03:29:55
    Author     : jhonatan
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entidade.Produtos"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title><%= (String) request.getAttribute("acao")%> Produto</title>
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
            <div class="row mt-5 justify-content-center">
                <div class="col-lg-8 col-md-10 col-sm-12">
                    <%
                        Produtos produto = (Produtos) request.getAttribute("produto");
                        String acao = (String) request.getAttribute("acao");
                        String titulo = "";
                        switch (acao) {
                            case "Incluir":
                                titulo = "Incluir Produto";
                                break;
                            case "Alterar":
                                titulo = "Alterar Produto";
                                break;
                            case "Excluir":
                                titulo = "Excluir Produto";
                                break;
                        }

                        String msgError = (String) request.getAttribute("msgError");
                        String msgOperacaoRealizada = (String) request.getAttribute("msgOperacaoRealizada");
                    %>
                    <h1 class="text-center"><%= titulo%></h1>
                    <% if (msgError != null && !msgError.isEmpty()) {%>
                    <div class="alert alert-danger" role="alert">
                        <%= msgError%>
                    </div>
                    <% } %>
                    <% if (msgOperacaoRealizada != null && !msgOperacaoRealizada.isEmpty()) {%>
                    <div class="alert alert-success" role="alert">
                        <%= msgOperacaoRealizada%>
                    </div>
                    <% }%>

                    <form action="/trabalhoFinal/admin/comprador/produtosController" method="POST" accept-charset="UTF-8">
                        <input type="hidden" name="id" value="<%= produto != null ? produto.getId() : ""%>" class="form-control">

                        <div class="mb-3">
                            <label for="nomeProduto" class="form-label">Nome do Produto</label>
                            <input type="text" name="nomeProduto" <%= acao.equals("Excluir") ? "readonly" : ""%> value="<%= produto != null ? produto.getNomeProduto() : ""%>" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label for="descricao" class="form-label">Descrição</label>
                            <textarea name="descricao" <%= acao.equals("Excluir") ? "readonly" : ""%> class="form-control" required><%= produto != null ? produto.getDescricao() : ""%></textarea>
                        </div>

                        <div class="mb-3">
                            <label for="precoCompra" class="form-label">Preço de Compra</label>
                            <input type="number" step="0.01" name="precoCompra" <%= acao.equals("Excluir") ? "readonly" : ""%> value="<%= produto != null ? produto.getPrecoCompra() : ""%>" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label for="precoVenda" class="form-label">Preço de Venda</label>
                            <input type="number" step="0.01" name="precoVenda" <%= acao.equals("Excluir") ? "readonly" : ""%> value="<%= produto != null ? produto.getPrecoVenda() : ""%>" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label for="quantidadeDisponivel" class="form-label">Quantidade Disponível</label>
                            <input type="number" name="quantidadeDisponivel" <%= acao.equals("Excluir") ? "readonly" : ""%> value="<%= produto != null ? produto.getQuantidadeDisponivel() : ""%>" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label for="liberadoVenda" class="form-label">Liberado para Venda</label>
                            <select name="liberadoVenda" <%= acao.equals("Excluir") ? "disabled" : ""%> class="form-select" required>
                                <option value="S" <%= produto != null && produto.getLiberadoVenda() == 'S' ? "selected" : ""%>>Sim</option>
                                <option value="N" <%= produto != null && produto.getLiberadoVenda() == 'N' ? "selected" : ""%>>Não</option>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="idCategoria" class="form-label">Categoria</label>
                            <input type="number" name="idCategoria" <%= acao.equals("Excluir") ? "readonly" : ""%> value="<%= produto != null ? produto.getIdCategoria() : ""%>" class="form-control" required>
                        </div>

                        <div class="text-center">
                            <button type="submit" name="btEnviar" value="<%= acao%>" class="btn btn-primary"><%= acao%></button>
                            <a href="/trabalhoFinal/admin/comprador/produtosController?acao=Listar" class="btn btn-danger">Retornar</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="/views/comum/footer.jsp" />
        <script src="http://localhost:8080/trabalhoFinal/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>


