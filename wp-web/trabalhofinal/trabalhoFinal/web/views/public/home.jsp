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
                <div class="mt-5">
                    <h3>Seja Bem-vindo ao trabalho de Jhonatan e Pedro</h3>
                    <h4>Aqui vendemos poucas coisas mas com trabalho honesto</h4>
                </div>
            </div>
        </div>
        <jsp:include page="../comum/footer.jsp" />
        <script src="http://localhost:8080/trabalhoFinal/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
