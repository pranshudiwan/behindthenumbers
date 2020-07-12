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
 * Class to delete an AirQuality record, using the AirQualityDelete.jsp WebContent file.
 */
@WebServlet("/airqualitydelete")
public class AirQualityDelete extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
        // Provide a title and render the JSP.
        messages.put("title", "Delete Air Quality");        
        req.getRequestDispatcher("/AirQualityDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String recordId = req.getParameter("RecordID");
        if (recordId == null) {
            messages.put("title", "Invalid RecordID for AirQuality");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the AirQuality record.
	        AirQuality airQuality = new AirQuality(Integer.parseInt(recordId));
	        try {
	        	airQuality = airQualityDao.delete(airQuality);
	        	// Update the message.
		        if (airQuality == null) {
		            messages.put("title", "Successfully deleted " + recordId);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + recordId);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/AirQualityDelete.jsp").forward(req, resp);
    }
}