<%@page contentType="text/html" pageEncoding="UTF-8" import="aplicacao.Usuario" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Login</title>
        <link href="bootstrap/bootstrap.min.css"  rel="stylesheet"> 
    </head>
    <body>
        <div class="container">
            <jsp:include page="menu.jsp" />
            <div class="mt-5">

                        <%
                        // testar se está logado
                        HttpSession sessao = request.getSession(false);
                        if (sessao != null) {
                            Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");
                            if (usuarioLogado != null) {
                                out.println("<h1>Usuário logado com sucesso</h1>");
                                out.println("<h2>Nome: " + usuarioLogado.getNome() + "</h2>");
                            } else { %>
                                 <jsp:forward page="formLogin.jsp"/>;
                        <%    }
                        } else { %>
                                 <jsp:forward page="formLogin.jsp"/>
                       <%   } %>

                    
            </div>
        </div>
    </body>
</html>
