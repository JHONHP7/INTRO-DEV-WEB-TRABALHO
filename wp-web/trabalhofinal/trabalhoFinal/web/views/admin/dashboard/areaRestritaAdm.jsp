<%@page contentType="text/html" pageEncoding="UTF-8" import="entidade.Funcionarios" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Área Restrita</title>
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
            <div class="mt-5">
                <div class="container">
                    <div class="jumbotron jumbotron-fluid bg-light p-5 rounded">
                        <div class="container">
                            <h1 class="display-4">Área Restrita</h1>
                            <%
                                Funcionarios funcionario = (Funcionarios) session.getAttribute("funcionario");
                                out.println("<p class='lead'>Bem-vindo administrador <strong>" + funcionario.getNome() + "</strong></p>");
                            %>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/views/comum/footer.jsp" />
        <script src="http://localhost:8080/trabalhoFinal/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
