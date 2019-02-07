package net.boffardi.decathlon;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.boffardi.decathlon.api.Performance;
import net.boffardi.decathlon.api.Utils;
import net.boffardi.decathlon.api.db.PerformanceDAO;
import net.boffardi.decathlon.api.db.PerformanceImpl;
import net.boffardi.decathlon.api.types.units.Centimeters;
import net.boffardi.decathlon.api.types.units.Meters;
import net.boffardi.decathlon.api.types.units.Seconds;
import net.boffardi.decathlon.initialize.DerbyDBInitializingListener;
 
/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * 
 */
public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger
            .getLogger(ControllerServlet.class.getName());
 
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("performance_form.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String id = request.getParameter("id");
        Performance existingPerformance = PerformanceDAO.getPerformance(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("performance_form.jsp");
        request.setAttribute("perf", existingPerformance);
        dispatcher.forward(request, response);
 
    }
 
    private void insertPerformance(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
    	
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String discipline = request.getParameter("discipline");
        
        try {

        Performance performance = PerformanceDAO.createPerformance(firstName, lastName, Utils.getDiscipline(discipline));
    	performance.setSprint(new Seconds(request.getParameter("sprint")));
    	performance.setLongJump(new Centimeters(request.getParameter("longJump")));
    	performance.setShotPut(new Meters(request.getParameter("shotPut")));
    	performance.setHighJump(new Centimeters(request.getParameter("highJump")));
    	performance.setFourHundreds(new Seconds(request.getParameter("fourHundreds")));
    	performance.setHurdles(new Seconds(request.getParameter("hurdles")));
    	performance.setDiscus(new Meters(request.getParameter("discus")));
    	performance.setPoleVault(new Centimeters(request.getParameter("poleVault")));
    	performance.setJavelin(new Meters(request.getParameter("javelin")));
    	performance.setM1500sprint(new Seconds(request.getParameter("m1500sprint")));

        
    	PerformanceDAO.updatePerformance(performance);
        } catch (ParseException pe ) {
            response.sendRedirect("new");        	
        }
        response.sendRedirect("list");
    }
 
    private void updatePerformance(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
    	String id = request.getParameter("id");
    	String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String discipline = request.getParameter("discipline");
 
        try {
        Performance performance = new PerformanceImpl(id, firstName, lastName, discipline);
        
    	performance.setSprint(new Seconds(request.getParameter("sprint")));
    	performance.setLongJump(new Centimeters(request.getParameter("longJump")));
    	performance.setShotPut(new Meters(request.getParameter("shotPut")));
    	performance.setHighJump(new Centimeters(request.getParameter("highJump")));
    	performance.setFourHundreds(new Seconds(request.getParameter("fourHundreds")));
    	performance.setHurdles(new Seconds(request.getParameter("hurdles")));
    	performance.setDiscus(new Meters(request.getParameter("discus")));
    	performance.setPoleVault(new Centimeters(request.getParameter("poleVault")));
    	performance.setJavelin(new Meters(request.getParameter("javelin")));
    	performance.setM1500sprint(new Seconds(request.getParameter("m1500sprint")));
                
        PerformanceDAO.updatePerformance(performance);
        } catch (ParseException pe ) {
            log.warning("Validation error in updatePerformence servlet method ");
            pe.printStackTrace();
        }
        response.sendRedirect("list");
    }
 
    private void deletePerformance(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

    	String id = request.getParameter("id");
        PerformanceDAO.deletePerformance(id);
        response.sendRedirect("list");
 
    }
}