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


@WebServlet("/countyupdate")
public class CountyUpdate extends HttpServlet {
	
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

        // Retrieve user and validate.
        int countyfips = Integer.parseInt(req.getParameter("CountyFips"));
        if (countyfips == 0) {
            messages.put("success", "Please enter a valid fips code.");
        } else {
        	try {
        		County county = countydao.getCountyFromFips(countyfips);
        		if(countyfips == 0) {
        			messages.put("success", "countyfips does not exist.");
        		}
        		req.setAttribute("county", county);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CountyUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        int countyfips = Integer.parseInt(req.getParameter("CountyFips"));
        if (countyfips == 0) {
            messages.put("success", "Please enter a valid fips code.");
        } else {
        	try {
        		County county = countydao.getCountyFromFips(countyfips);
        		if(countyfips == 0) {
        			messages.put("success", "UserName does not exist. No update to perform.");
        		} else {
        			String newCountyName = req.getParameter("NewCountyName");
        			if (newCountyName == null) {
        	            messages.put("success", "Please enter a valid LastName.");
        	        } else {
        	        	county = countydao.update(county, newCountyName);
        	        	messages.put("success", "Successfully updated " + newCountyName);
        	        }
        		}
        		req.setAttribute("county", county);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CountyUpdate.jsp").forward(req, resp);
    }
}
