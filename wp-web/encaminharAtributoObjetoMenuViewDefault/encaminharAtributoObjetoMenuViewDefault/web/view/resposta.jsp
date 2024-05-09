<%@page contentType="text/html" pageEncoding="UTF-8" import="aplicacao.Usuario" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Dados do Usuário</title>
        <link href="bootstrap/bootstrap.min.css"  rel="stylesheet"> 
    </head>
    <body>
        <div class="container">
            <jsp:include page="/view/menu.html" />
            <div class="mt-5">

                <%
                    Usuario usuario = (Usuario) request.getAttribute("usuario");
                %>
                
                <h1>Dados recebidos do Usuário</h1>
                <h3>Nome: <%= usuario.getNome() %></h1>
                <h3>Endereço: <%= usuario.getEndereco()%> </h1>
                <%= request.getContextPath()%>
            </div>
        </div>
    </body>
</html>
