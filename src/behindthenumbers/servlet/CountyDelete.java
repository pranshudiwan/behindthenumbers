package behindthenumbers.servlet;
import behindthenumbers.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import behindthenumbers.dal.*;
import behindthenumbers.model.*;


@WebServlet("/countydelete")
public class CountyDelete extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected CountyDao countydao;
	
	@Override
	public void init() throws ServletException {
		countydao = CountyDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete County");        
        req.getRequestDispatcher("/CountyDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String countyname = req.getParameter("CountyName");
        if (countyname == null || countyname.trim().isEmpty()) {
            messages.put("title", "Invalid CountyName");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the BlogUser.
	        County county = new County(countyname);
	        try {
	        	county = countydao.delete(county);
	        	// Update the message.
		        if (county == null) {
		            messages.put("title", "Successfully deleted " + countyname);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + countyname);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CountyDelete.jsp").forward(req, resp);
    }
}
