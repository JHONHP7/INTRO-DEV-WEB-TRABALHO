<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Resultado da Operação</title>
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
            <jsp:include page="/views/comum/menu.jsp" />
            <div class="mt-5">
                <%
                    String errorMessage = (String) request.getAttribute("errorMessage");
                    String msgOperacaoRealizada = (String) request.getAttribute("msgOperacaoRealizada");
                    String link = (String) request.getAttribute("link");

                    if (errorMessage != null && !errorMessage.isEmpty()) {
                %>
                <div class="alert alert-danger" role="alert">
                    <%= errorMessage%>
                </div>
                <%
                } else if (msgOperacaoRealizada != null && !msgOperacaoRealizada.isEmpty()) {
                %>
                <div class="alert alert-success" role="alert">
                    <%= msgOperacaoRealizada%>
                </div>
                <%
                    }
                %>

                <a href="<%= link%>" class="btn btn-danger">Retornar</a>
            </div>
        </div>
        <jsp:include page="/views/comum/footer.jsp" />
        <script src="http://localhost:8080/trabalhoFinal/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
