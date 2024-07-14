<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Login</title>
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
        <div class="wrapper">
            <div class="container content">
                <jsp:include page="../comum/menu.jsp" />
                <div class="text-center mt-5 mb-4">
                <h3 class="display-4">Trabalho de Desenvolvimento Web – 2024.1</h3>
                <h4 class="display-6 text-muted">Desenvolvido por Jhonatan Silva e Pedro Monnerat</h4>
                <p class="lead mt-4">Esse trabalho é uma aplicação que apoia o controle de compras e vendas de produtos de uma loja física.</p>
            </div>
            </div>
        </div>
        <jsp:include page="../comum/footer.jsp" />
        <script src="http://localhost:8080/trabalhoFinal/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
