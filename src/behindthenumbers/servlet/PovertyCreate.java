package behindthenumbers.servlet;

import java.io.IOException;
import java.math.BigDecimal;
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



@WebServlet("/povertycreate")
public class PovertyCreate extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected PovertyDao povertyDao;

	@Override
	public void init() throws ServletException {
		povertyDao = PovertyDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		// Just render the JSP.
		req.getRequestDispatcher("/PovertyCreate.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		// Retrieve and validate CountyID.
		String countyId = req.getParameter("CountyID");
		if (countyId == null || countyId.trim().isEmpty()) {
			messages.put("success", "Invalid County ID");
		} else {
			// Create the Poverty record.
			
			int year = Integer.parseInt(req.getParameter("Year"));
			
        	int povertyPopulation = Integer.parseInt(req.getParameter("PovertyPopulation"));
        	
        	
        	BigDecimal percentPovertyPopulation = new BigDecimal(req.getParameter("PercentPovertyPopulation"));
        	
        	Integer ageGroupId = Integer.parseInt(req.getParameter("AgeGroupID"));
        	
			Integer countyID = Integer.parseInt(req.getParameter("CountyID"));

			try {
				Poverty poverty = new Poverty(year, povertyPopulation, percentPovertyPopulation, 
						 ageGroupId, countyID);
	        	
	        	poverty = PovertyDao.create(poverty);
				messages.put("success", "Successfully created record" + poverty.getRecordID());
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}

		req.getRequestDispatcher("/PovertyCreate.jsp").forward(req, resp);
	}
	
}