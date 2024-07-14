<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="entidade.Vendas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Cadastro de Vendas</title>
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
                                titulo = "Incluir Venda";
                                break;
                            case "Alterar":
                                titulo = "Alterar Venda";
                                break;
                            case "Excluir":
                                titulo = "Excluir Venda";
                                break;
                            default:
                                titulo = "Cadastro de Venda";
                                break;
                        }

                        Vendas venda = (Vendas) request.getAttribute("venda");
                        String formattedDate = "";
                        if (venda != null && venda.getDataVenda() != null) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            formattedDate = sdf.format(venda.getDataVenda());
                        }
                        String msgError = (String) request.getAttribute("msgError");
                    %>
                    <h1 class="text-center"><%= titulo%></h1>
                    <% if (msgError != null && !msgError.isEmpty()) {%>
                    <div class="alert alert-danger" role="alert">
                        <%= msgError%>
                    </div>
                    <% }%>

                    <form action="/trabalhoFinal/admin/vendedor/vendedorCadastroVendas" method="POST" accept-charset="UTF-8">
                        <input type="hidden" name="id" value="<%= venda != null ? venda.getId() : "0"%>" class="form-control">

                        <div class="mb-3">
                            <label for="quantidadeVenda" class="form-label">Quantidade</label>
                            <input type="number" name="quantidadeVenda" placeholder="Quantidade de unidades" value="<%= venda != null ? venda.getQuantidadeVenda() : ""%>" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label for="dataVenda" class="form-label">Data da Venda</label>
                            <input type="date" name="dataVenda"  value="<%= formattedDate%>" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label for="idCliente" class="form-label">ID do Cliente</label>
                            <input type="number" name="idCliente" placeholder="ID do Cliente" value="<%= venda != null ? venda.getIdCliente() : ""%>" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label for="idProduto" class="form-label">ID do Produto</label>
                            <input type="number" name="idProduto" placeholder="ID do Produto" value="<%= venda != null ? venda.getIdProduto() : ""%>" class="form-control" required>
                        </div>

                        <div class="mb-3 text-center">
                            <input type="submit" name="btEnviar" value="<%= acao%>" class="btn btn-primary">
                            <a href="/trabalhoFinal/admin/vendedor/vendedorCadastroVendas?acao=Listar" class="btn btn-danger">Retornar</a>
                        </div>
                    </form>

                </div>
            </div>
        </div>
        <jsp:include page="/views/comum/footer.jsp" />
        <script src="http://localhost:8080/trabalhoFinal/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
