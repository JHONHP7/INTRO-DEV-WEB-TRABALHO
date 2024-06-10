/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jhonatan-silva
 */
@WebServlet(urlPatterns = {"/ServletGet"}, initParams = {
    @WebInitParam(name = "serveletget", value = "Value")})
public class ServletGet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()){
            /**
             * TO do output
             */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet alou</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet alou at " + request.getContextPath() + " funcionando "+"</h1>");
            out.println("<h1>Servlet funcionando e execuando pelo doget"+"</h1>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            System.out.println("Deu ruim");
            
        }
    }

}
