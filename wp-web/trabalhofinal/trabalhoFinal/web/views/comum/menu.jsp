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
                        Funcionarios funcionarioLogado = (Funcionarios) sessao.getAttribute("funcionario");
                        if (funcionarioLogado != null) { %>
<!--                        <a class="nav-link" href="/trabalhoFinal/admin/dashboard">Dashboard</a>
                            <a class="nav-link" href="/trabalhoFinal/admin/CategoriaController?acao=Listar">Categorias</a>-->
                            <a class="nav-link" href="/trabalhoFinal/admin/logOut">Logout</a>
                <%
                    String papel = funcionarioLogado.getPapel();
                    if ("1".equals(papel)) { %>
                            <a class="nav-link" href="/trabalhoFinal/admin/vendedor/listaClientes?acao=Listar">Cadastro de Clientes</a>
                            <a class="nav-link" href="/trabalhoFinal/admin/vendedor/vendedorCadastroVendas?acao=Listar">Cadastro de Vendas</a>
                            
                    <% } else if ("0".equals(papel)) { %>
                            <a class="nav-link" href="/trabalhoFinal/admin/administrador/cadastroVendedores?acao=Listar">Cadastro de Vendedores</a>
                            <a class="nav-link" href="/trabalhoFinal/admin/administrador/cadastroCompradores?acao=Listar">Cadastro de Compradores</a>
                            <a class="nav-link" href="/trabalhoFinal/admin/administrador/cadastroAdms?acao=Listar">Cadastro de Administradores</a>
                            <a class="nav-link" href="/trabalhoFinal/admin/administrador/relatorios?acao=ListarReceita">Relatório</a>
                            <a class="nav-link" href="/trabalhoFinal/admin/administrador/estoque?acao=ListarReceita">Estoque</a>
                            
                    <% } else if ("2".equals(papel)) { %>
                            <a class="nav-link" href="/trabalhoFinal/admin/comprador/listaFornecedores?acao=Listar">Cadastro de Fornecedores</a>
                            <a class="nav-link" href="/trabalhoFinal/admin/comprador/comprasController?acao=Listar">Cadastro de Compras</a>
                            <a class="nav-link" href="/trabalhoFinal/admin/comprador/categoriaController?acao=Listar">Cadastro de categorias</a>
                            <a class="nav-link" href="/trabalhoFinal/admin/comprador/produtosController?acao=Listar">Colocar produtos para venda</a>
                            
                    <% } %>
                    <% } else { %>
                            <a class="nav-link" href="/trabalhoFinal/MostrarProdutos">Produtos</a>
                            <a class="nav-link" href="/trabalhoFinal/AutenticaController?acao=Login">Login</a>
                    <% }
                } else { %>
                <a class="nav-link" href="/trabalhoFinal/MostrarProdutos">Produtos</a>
                <a class="nav-link" href="/trabalhoFinal/AutenticaController?acao=Login">Login</a>
                <% }%>
            </div>
        </div>
    </div>
</nav>
