/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(filterName = "filtro", servletNames = {"serv1", "serv2"})
public class filtro implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        String url = ((HttpServletRequest) request).getRequestURL().toString();
        System.out.println(" => Aqui estou executando o filtro no request = " + url);
        chain.doFilter(request, response);//sends request to 
        System.out.println(" => Aqui estou executando o filtro no response");
    }

    @Override
    public void destroy() {
    }  

}
