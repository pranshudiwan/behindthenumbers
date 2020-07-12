package behindthenumbers.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import behindthenumbers.dal.*;
import behindthenumbers.model.*;


@WebServlet("/povertyupdate")
public class PovertyUpdate extends HttpServlet{

	
protected PovertyDao povertyDao;
	
	@Override
	public void init() throws ServletException {
		povertyDao = PovertyDao.getInstance();
	}
	
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String recordId = req.getParameter("RecordID");
        if (recordId == null || recordId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid RecordID.");
        } else {
        	try {
        		int RecordId = Integer.parseInt(recordId);
        		Poverty poverty = povertyDao.getPovertyRecordByID(RecordId);
        		
        		if(poverty == null) {
        			messages.put("success", "Poverty record does not exist.");
        		}
        		req.setAttribute("poverty", poverty);
        		
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/PovertyUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		
		
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String id = req.getParameter("RecordID");
        if (id == null || id.trim().isEmpty()) {
            messages.put("success", "Please enter a valid RecordID.");
        } else {
        	try {
        		int RecordId = Integer.parseInt(id);
        		
        		
        		Poverty poverty = povertyDao.getPovertyRecordByID(RecordId);
        		
        		
        		if(poverty == null) {
        			messages.put("success", "RecordID does not exist. No update to perform.");
        		} else {
        			String newPovertyPopulation = req.getParameter("PovertyPopulation");
     
        			if (newPovertyPopulation == null || newPovertyPopulation.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid number.");
        	        } else {
        	        	int newPovPopulation = Integer.parseInt(newPovertyPopulation);
        	        	poverty = povertyDao.updateTotalPopulationinPoverty(poverty, newPovPopulation);
        	        	
        	        }
        		}
        		req.setAttribute("poverty", poverty);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/PovertyUpdate.jsp").forward(req, resp);
    }
}
