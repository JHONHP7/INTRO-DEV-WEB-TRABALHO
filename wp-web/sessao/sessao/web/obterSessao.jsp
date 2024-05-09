<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Uso de Cookie</title>
        <link href="bootstrap/bootstrap.min.css"  rel="stylesheet"> 
    </head>
    <body>
        <div class="container">
            <jsp:include page="menu.html" />
            <div class="mt-5">

                <h3>Valor da Sessão</h3>

                <%
                    String nome = (String) session.getAttribute("nome");

                    if (nome != null) {
                        out.println("Nome na sessão =" + nome);
                    } else {
                        out.println("Não tem Nome na sessão");
                    }
                %>

                <script src="bootstrap/bootstrap.bundle.min.js"></script>
            </div>
        </div>
    </body>
</html>

