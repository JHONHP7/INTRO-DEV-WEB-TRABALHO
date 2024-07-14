<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.sql.Date"%>
<%@ page import="entidade.Compras"%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Cadastro de Compras</title>
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
                <div class="col-lg-8 col-md-10 col-sm-12">
                    <%
                        String acao = (String) request.getAttribute("acao");
                        if (acao == null) {
                            acao = "Incluir";
                        }

                        String titulo = "";
                        switch (acao) {
                            case "Incluir":
                                titulo = "Incluir Compra";
                                break;
                            case "Alterar":
                                titulo = "Alterar Compra";
                                break;
                            case "Excluir":
                                titulo = "Excluir Compra";
                                break;
                            default:
                                titulo = "Cadastro de Compra";
                                break;
                        }

                        Compras compra = (Compras) request.getAttribute("compra");
                        String formattedDate = "";
                        if (compra != null && compra.getDataCompra() != null) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            formattedDate = sdf.format(compra.getDataCompra());
                        }
                        String msgError = (String) request.getAttribute("msgError");
                    %>
                    <h1 class="text-center"><%= titulo%></h1>
                    <% if (msgError != null && !msgError.isEmpty()) {%>
                    <div class="alert alert-danger" role="alert">
                        <%= msgError%>
                    </div>
                    <% }%>

                    <form action="/trabalhoFinal/admin/comprador/comprasController" method="POST" accept-charset="UTF-8">
                        <input type="hidden" name="id" value="<%= compra != null ? compra.getId() : "0"%>" class="form-control">

                        <div class="mb-3">
                            <label for="quantidadeCompra" class="form-label">Quantidade</label>
                            <input type="number" name="quantidadeCompra" value="<%= compra != null ? compra.getQuantidadeCompra() : ""%>" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label for="dataCompra" class="form-label">Data da Compra</label>
                            <input type="date" name="dataCompra" value="<%= formattedDate%>" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label for="idFornecedor" class="form-label">ID do Fornecedor</label>
                            <input type="number" name="idFornecedor" value="<%= compra != null ? compra.getIdFornecedor() : ""%>" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label for="idProduto" class="form-label">ID do Produto</label>
                            <input type="number" name="idProduto" value="<%= compra != null ? compra.getIdProduto() : ""%>" class="form-control" required>
                        </div>

                        <div class="mb-3 text-center">
                            <input type="submit" name="btEnviar" value="<%= acao%>" class="btn btn-primary">
                            <a href="/trabalhoFinal/admin/comprador/comprasController?acao=Listar" class="btn btn-danger">Retornar</a>
                        </div>
                    </form>

                </div>
            </div>
        </div>
        <jsp:include page="/views/comum/footer.jsp" />
        <script src="http://localhost:8080/trabalhoFinal/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
