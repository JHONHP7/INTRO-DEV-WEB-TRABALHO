
import entidade.auxiliar.TotalVendasProduto;
import entidade.auxiliar.TotalVendasDiarias;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.VendaDAO;

@WebServlet(name = "AdmRelatorios", urlPatterns = {"/admin/administrador/relatorios"})
public class AdmRelatorios extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String acao = request.getParameter("acao");
        VendaDAO vendaDAO = new VendaDAO();
        RequestDispatcher rd;
        if ("ListarReceita".equals(acao)) {
            ArrayList<TotalVendasProduto> listaTotalVendasProduto = vendaDAO.getTotalVendasPorProduto();
            ArrayList<TotalVendasDiarias> listaTotalVendasDiarias = vendaDAO.getTotalVendasDiarias();
            request.setAttribute("listaTotalVendasProduto", listaTotalVendasProduto);
            request.setAttribute("listaTotalVendasDiarias", listaTotalVendasDiarias);
            rd = request.getRequestDispatcher("/views/admin/relatorios/relatorio.jsp");
            rd.forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ação inválida.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
