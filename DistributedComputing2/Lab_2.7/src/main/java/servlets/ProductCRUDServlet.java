package servlets;

import data.dao.ProductDAO;
import data.dto.ProductDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

//@WebServlet("/CityCRUDServlet")
public class ProductCRUDServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CityCRUDServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getServletPath();
        String action = url.substring(5);
        try {
            switch (action) {
                case "New":
                    showNewForm(request, response);
                    break;
                case "Insert":
                    insertProduct(request, response);
                    break;
                case "Delete":
                    deleteProduct(request, response);
                    break;
                case "Edit":
                    showEditForm(request, response);
                    break;
                case "Update":
                    updateProduct(request, response);
                    break;
                case "List":
                    listProduct(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<ProductDTO> listProduct = ProductDAO.findAll();
        request.setAttribute("listProduct", listProduct);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ProductList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("ProductForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        ProductDTO existingProduct = ProductDAO.findById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ProductForm.jsp");
        request.setAttribute("product", existingProduct);
        dispatcher.forward(request, response);

    }

    private void insertProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        Long sectionId = Long.parseLong(request.getParameter("sectionId"));
        Long price = Long.parseLong(request.getParameter("price"));

        ProductDTO newProduct = new ProductDTO();
        newProduct.setName(name);
        newProduct.setProductId(countryId);
        newProduct.setPrice(price);
        ProductDAO.insert(newProduct);
        response.sendRedirect("ProductList");
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        Long sectionId = Long.parseLong(request.getParameter("sectionId"));
        Long price = Long.parseLong(request.getParameter("price"));

        ProductDTO product = new ProductDTO(id, sectionId, name, price);
        ProductDAO.update(product);
        response.sendRedirect("ProductList");
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        ProductDTO product = new ProductDTO();
        product.setId(id);
        ProductDAO.delete(product);
        response.sendRedirect("ProductList");
    }
}
