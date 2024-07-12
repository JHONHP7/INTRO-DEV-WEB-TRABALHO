package filtro;

import entidade.Funcionarios;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "filtroRestrito", urlPatterns = {"/admin/*"})
public class filtroRestrito implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        Funcionarios funcionario = (Funcionarios) httpRequest.getSession().getAttribute("funcionario");

        if (funcionario != null) {
            String papelFuncionario = funcionario.getPapel();

            String requestURI = httpRequest.getRequestURI();
            if (requestURI.equals(httpRequest.getContextPath() + "/admin/logOut")) {
                chain.doFilter(request, response);
            } else if (requestURI.startsWith(httpRequest.getContextPath() + "/admin/administrador") && papelFuncionario.equals("0")) {
                chain.doFilter(request, response);
            } else if (requestURI.startsWith(httpRequest.getContextPath() + "/admin/vendedor") && papelFuncionario.equals("1")) {
                chain.doFilter(request, response);
            } else if (requestURI.startsWith(httpRequest.getContextPath() + "/admin/comprador") && papelFuncionario.equals("2")) {
                chain.doFilter(request, response);
            } else {
                String mensagem = "Você não possui permissão para acessar esta página.";
                String link = httpRequest.getContextPath() + "/home";

                httpRequest.setAttribute("msgOperacaoRealizada", mensagem);
                httpRequest.setAttribute("link", link);

                httpRequest.getRequestDispatcher("/views/comum/showMessage.jsp").forward(request, response);
            }
        } else {
            ((HttpServletResponse) response).sendRedirect("http://localhost:8080/trabalhoFinal/home");
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}
