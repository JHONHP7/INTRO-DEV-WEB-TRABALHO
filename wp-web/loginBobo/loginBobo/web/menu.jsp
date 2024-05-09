<%@page contentType="text/html" pageEncoding="UTF-8" import="aplicacao.Usuario" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp">Home</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-link" href="comentarios.jsp">Coment&aacute;rios</a>

                <%
                    // testar se está logado
                    HttpSession sessao = request.getSession(false);
                    if (sessao != null) {
                        Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");
                        if (usuarioLogado != null) { %>
                            <a class="nav-link" href="logOut">Logout</a>
                <%  } else { %>
                            <a class="nav-link" href="formLogin.jsp">Login</a>
                <%    }
                    }%>



            </div>
        </div>
    </div>
</nav>
