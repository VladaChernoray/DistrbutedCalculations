package servlets;

import data.dao.ProductDAO;
import data.dao.SectionDAO;
import data.dto.ProductDTO;
import data.dto.SectionDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

//@WebServlet("/CountryCRUDServlet")
public class SectionCRUDServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SectionCRUDServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getServletPath();
        String action = url.substring(8);
        try {
            switch (action) {
                case "New":
                    showNewForm(request, response);
                    break;
                case "Insert":
                    insertSection(request, response);
                    break;
                case "Delete":
                    deleteSection(request, response);
                    break;
                case "Edit":
                    showEditForm(request, response);
                    break;
                case "Update":
                    updateSection(request, response);
                    break;
                case "List":
                    listSections(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listSections(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<SectionDTO> listSection = SectionDAO.findAll();
        request.setAttribute("listSection", listSection);
        RequestDispatcher dispatcher = request.getRequestDispatcher("SectionList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("SectionForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        SectionDTO existingSection = SectionDAO.findById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("SectionForm.jsp");
        request.setAttribute("section", existingSection);
        dispatcher.forward(request, response);

    }

    private void insertSection(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");

        SectionDTO newSection = new SectionDTO();
        newSection.setName(name);
        SectionDAO.insert(newSection);
        response.sendRedirect("SectionList");
    }

    private void updateSection(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");

        SectionDTO section = new SectionDTO(id, name);
        SectionDAO.update(section);
        response.sendRedirect("SectionList");
    }

    private void deleteSection(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        List<ProductDTO> list = ProductDAO.findBySectionId(id);
        assert list != null;
        for (ProductDTO product : list) {
            ProductDAO.delete(product);
        }
        SectionDTO section = new SectionDTO();
        section.setId(id);
        SectionDAO.delete(section);
        response.sendRedirect("SectionList");
    }
}
