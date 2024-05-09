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

                <h3>Cookies</h3>

                <%
                    String NomedoCookie = "";
                    Cookie[] cookies = request.getCookies();
                    if (cookies != null) {
                        for (int i = 0; i < cookies.length; i++) {
                            if (cookies[i].getName().equals("Nome")) {
                                NomedoCookie = cookies[i].getValue();
                                out.println("<h3>Valor do Cookie = " + NomedoCookie + "</h3>");
                            }
                        }
                        if (NomedoCookie.equals("")){
                            out.println("<h3>Não tem cookie Nome </h3>");
                        }
                    } else {
                        out.println("<h3>Não tem Cookie</h3>");
                    }%>


                <script src="bootstrap/bootstrap.bundle.min.js"></script>
            </div>
        </div>
    </body>
</html>

