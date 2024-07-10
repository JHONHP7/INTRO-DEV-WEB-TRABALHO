<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Compras"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="#">
    <title>Comentário</title>
    <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
</head>
<body>
    <div class="container">
        <jsp:include page="../../comum/menu.jsp" />
        <div class="row mt-5">
            <div class="col-sm-4 offset-3">
                <%
                    Compras compra = (Compras) request.getAttribute("compra");
                    String acao = (String) request.getAttribute("acao");
                    switch (acao) {
                        case "Incluir":
                            out.println("<h1>Incluir Compra</h1>");
                            break;
                        case "Alterar":
                            out.println("<h1>Alterar Compra</h1>");
                            break;
                        case "Excluir":
                            out.println("<h1>Excluir Compra</h1>");
                            break;
                    }

                    String msgError = (String) request.getAttribute("msgError");
                    if ((msgError != null) && (!msgError.isEmpty())) {%>
                <div class="alert alert-danger" role="alert">
                    <%= msgError%>
                </div>
                <% }%>

                <form action="/trabalhoFinal/admin/comprador/listaCompras?acao=Incluir" method="POST" accept-charset="UTF-8">
                    <input type="hidden" name="id" value="<%=compra != null ? compra.getId() : ""%>" class="form-control">
                    
                    <div class="mb-3">
                        <label for="quantidadeCompra" class="form-label">Quantidade Compra</label>
                        <input type="text" name="quantidadeCompra" <%= acao.equals("Excluir") ? "readonly" : "" %> value="<%=compra != null ? compra.getQuantidadeCompra() : ""%>" class="form-control">
                    </div>
                    
                    <div class="mb-3">
                        <label for="dataCompra" class="form-label">Data Compra</label>
                        <input type="text" name="dataCompra" <%= acao.equals("Excluir") ? "readonly" : "" %> value="<%=compra != null ? compra.getDataCompra() : ""%>" class="form-control">
                    </div>

                    <div class="mb-3">
                        <label for="valorCompra" class="form-label">Valor Compra</label>
                        <input type="text" name="valorCompra" <%= acao.equals("Excluir") ? "readonly" : "" %> value="<%=compra != null ? compra.getValorCompra() : ""%>" class="form-control">
                    </div>

                    <div class="mb-3">
                        <label for="idFornecedor" class="form-label">Id Fornecedor</label>
                        <input type="text" name="idFornecedor" <%= acao.equals("Excluir") ? "readonly" : "" %> value="<%=compra != null ? compra.getIdFornecedor() : ""%>" class="form-control">
                    </div>

                    <div class="mb-3">
                        <label for="idProduto" class="form-label">Id Produto</label>
                       <input type="text" name="idProduto" <%= acao.equals("Excluir") ? "readonly" : "" %> value="<%=compra != null ? compra.getIdProduto() : ""%>" class="form-control">
                    </div>

                    <div class="mb-3">
                        <label for="idFuncionario" class="form-label">Id Funcionario</label>
                        <input type="text" name="idFuncionario" <%= acao.equals("Excluir") ? "readonly" : "" %> value="<%=compra != null ? compra.getIdFuncionario() : ""%>" class="form-control">
                    </div>

                    <div>
                        <input type="submit" name="btEnviar" value="<%=acao%>" class="btn btn-primary">
                        <a href="/trabalhoFinal/admin/comprador/listaCompras?acao=Listar" class="btn btn-danger">Retornar</a>
                    </div>
                </form>

            </div>
        </div>
    </div>
    <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>