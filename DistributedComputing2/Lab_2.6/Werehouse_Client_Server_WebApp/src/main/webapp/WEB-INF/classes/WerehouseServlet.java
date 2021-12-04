import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import DatabaseConnection.WerehouseDatabaseConnection;

@WebServlet("/werehouse")
public class WerehouseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("products", WerehouseDatabaseConnection.getAllProducts());
        req.setAttribute("groups", WerehouseDatabaseConnection.getAllSections());
        getServletContext().getRequestDispatcher("/info.jsp").forward(req, resp);
    }
}
