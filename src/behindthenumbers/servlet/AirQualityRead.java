package behindthenumbers.servlet;

import behindthenumbers.dal.*;
import behindthenumbers.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class to read/retrieve a list of AirQuality records by providing a threshold for MaxAQI score, 
 * using the AirQualityRead.jsp WebContent file.
 */
@WebServlet("/airqualityread")
public class AirQualityRead extends HttpServlet {
	
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

        List<AirQuality> airQualitiesList = new ArrayList<AirQuality>();
        
        // Retrieve and validate threshold for AQI.
        // threshold is retrieved from the URL query string.
        String maxAqi = req.getParameter("maxAqi");
        // Retrieve list of records with at least minimum value for MaxAQI, and store as a message.
        if (maxAqi == null || maxAqi.trim().isEmpty()) {
            messages.put("success", "Please enter a valid integer for AQI.");
        } else {
        try {
			airQualitiesList = airQualityDao.getAirQualityByMinimumMaxAqi(Integer.parseInt(maxAqi));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		messages.put("success", "Displaying results for counties with maxAQI values higher than " + maxAqi);
		// Save the previous search term, so it can be used as the default
		// in the input box when rendering AirQualityRead.jsp.
		messages.put("previousMaxAqi", maxAqi.toString());
		
		req.setAttribute("airQualitiesList", airQualitiesList);
		
		req.getRequestDispatcher("/AirQualityRead.jsp").forward(req, resp);
        }

	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		
		this.doGet(req, resp);
    }
}