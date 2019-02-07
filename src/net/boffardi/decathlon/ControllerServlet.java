package net.boffardi.decathlon;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.boffardi.decathlon.api.Performance;
import net.boffardi.decathlon.api.Utils;
import net.boffardi.decathlon.api.db.PerformanceDAO;
 
/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * 
 */
public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public void init() {
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
 
        try {
            switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertPerformance(request, response);
                break;
            case "/delete":
                deletePerformance(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updatePerformance(request, response);
                break;
            default:
                listPerformance(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
 
    private void listPerformance(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Performance> listPerformance = PerformanceDAO.listAllPerformances();
        request.setAttribute("scoreboard", listPerformance);
        RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("form.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String id = request.getParameter("id");
        Performance existingPerformance = PerformanceDAO.getPerformance(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("form.jsp");
        request.setAttribute("Performance", existingPerformance);
        dispatcher.forward(request, response);
 
    }
 
    private void insertPerformance(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
    	
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String discipline = request.getParameter("discipline");
        
        /* TODO MAP ALL FIELDS HERE FOR INSERT */
 
        PerformanceDAO.createPerformance(firstName, lastName, Utils.getDiscipline(discipline));
        response.sendRedirect("list");
    }
 
    private void updatePerformance(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
    	String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String discipline = request.getParameter("discipline");
 
        /* TODO MAP ALL FIELDS HERE FOR UPDATE */
        

       // Performance Performance = new Performance();
       //  PerformanceDAO.updatePerformance(Performance);
        response.sendRedirect("list");
    }
 
    private void deletePerformance(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

    	String id = request.getParameter("id");
        PerformanceDAO.deletePerformance(id);
        response.sendRedirect("list");
 
    }
}