package behindthenumbers.servlet;

import behindthenumbers.dal.*;
import behindthenumbers.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class to update the number of days with AQI for an AirQuality record, 
 * using the AirQualityUpdate.jsp WebContent file.
 */
@WebServlet("/airqualityupdate")
public class AirQualityUpdate extends HttpServlet {

	/**
	 * 
	 */
	protected AirQualityDao airQualityDao;
	
	@Override
	public void init() throws ServletException {
		airQualityDao = AirQualityDao.getInstance();
	}
	
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve recordID and daysWithAQI value, and validate.
        String recordId = req.getParameter("recordId");
        if (recordId == null || recordId.trim().isEmpty()) {
            messages.put("success", "Please enter valid integer representing a RecordID.");
        }
        else {
        
        	try {
        		AirQuality airQuality = airQualityDao.getAirQualityByRecordId(Integer.parseInt(recordId));
        		if(airQuality == null) {
        			messages.put("success", "Record does not exist.");
        		}
        		req.setAttribute("airQuality", airQuality);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/AirQualityUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve recordID, new AQI, and validate.
        String recordId = req.getParameter("recordId");
        if (recordId == null || recordId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid RecordID.");
        } else {
        	try {
        		AirQuality airQuality = airQualityDao.getAirQualityByRecordId(Integer.parseInt(recordId));
        		if(airQuality == null) {
        			messages.put("success", "Record does not exist. No update to perform.");
        		} else {
        			String newAqiNum = req.getParameter("daysWithAqi");
        			if (newAqiNum == null) {
        				messages.put("success", "Please enter a valid AQI Number.");
        			} else {
        			airQuality = airQualityDao.updateDaysWithAqi(airQuality, Integer.parseInt(newAqiNum));
					messages.put("success", "Successfully updated " + recordId);
        			}
        		}
        		req.setAttribute("recordId", recordId);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        req.getRequestDispatcher("/AirQualityUpdate.jsp").forward(req, resp);
        
	}
}