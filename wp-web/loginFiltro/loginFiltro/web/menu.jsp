<%@page contentType="text/html" pageEncoding="UTF-8" import="entidade.Usuario" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/home">Home</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <%
                    // testar se está logado
                    HttpSession sessao = request.getSession(false);
                    if (sessao != null) {
                        Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");
                        if (usuarioLogado != null) { %>
                            <a class="nav-link" href="http://localhost:8080/restrito/dashboard">Dashboard</a>
                            <a class="nav-link" href="http://localhost:8080/restrito/logOut">Logout</a>
                <%  } else { %>
                            <a class="nav-link" href="http://localhost:8080/comentarios.jsp">Coment&aacute;rios</a>
                            <a class="nav-link" href="http://localhost:8080/formLogin.jsp">Login</a>
                <%    }
                    }%>



            </div>
        </div>
    </div>
</nav>
