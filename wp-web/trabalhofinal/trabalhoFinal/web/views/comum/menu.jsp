<%@page contentType="text/html" pageEncoding="UTF-8" import="entidade.Funcionarios" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/trabalhoFinal/home">Home</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <%
                    // testar se está logado
                    HttpSession sessao = request.getSession(false);
                    if (sessao != null) {
                        Funcionarios funcionarioLogado = (Funcionarios) session.getAttribute("funcionario");
                        if (funcionarioLogado != null) { %>
                            <a class="nav-link" href="/trabalhoFinal/admin/dashboard">Dashboard</a>
                            <a class="nav-link" href="/trabalhoFinal/admin/CategoriaController?acao=Listar">Categorias</a>
                            <a class="nav-link" href="/trabalhoFinal/admin/logOut">Logout</a>
                <%  } else { %>
                
                            <a class="nav-link" href="/trabalhoFinal/MostrarProdutos">Produtos</a>
                            <a class="nav-link" href="/trabalhoFinal/AutenticaController?acao=Login">Login</a>
                <%    }
                    }%>



            </div>
        </div>
    </div>
</nav>
