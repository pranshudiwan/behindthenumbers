package behindthenumbers.servlet;
import behindthenumbers.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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


@WebServlet("/countycreate")
public class CountyCreate extends HttpServlet {
	
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
        //Just render the JSP.   
        req.getRequestDispatcher("/CountyCreate.jsp").forward(req, resp);
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
            messages.put("success", "Invalid countyname");
        } else {
        	// Create the BlogUser.
        	String scountyfips = req.getParameter("CountyFips");
        	int countyfips = Integer.parseInt(scountyfips);
        	
        	String sstateid = req.getParameter("StateID");
        	int stateid = Integer.parseInt(sstateid);

	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	County county = new County(countyfips, countyname, stateid);
	        	county = countydao.create(county);
	        	messages.put("success", "Successfully created " + countyname);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CountyCreate.jsp").forward(req, resp);
    }
}
