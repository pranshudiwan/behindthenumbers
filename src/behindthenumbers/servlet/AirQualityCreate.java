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

import behindthenumbers.dal.AirQualityDao;
import behindthenumbers.model.AirQuality;
import behindthenumbers.model.AirQuality.DayType;

/**
 * Class to create an AirQuality record, using the AirQualityCreate.jsp WebContent file.
 */
@WebServlet("/airqualitycreate")
public class AirQualityCreate extends HttpServlet {

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
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		// Just render the JSP.
		req.getRequestDispatcher("/AirQualityCreate.jsp").forward(req, resp);
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
			// Create the AirQuality record.
			Integer recordId = Integer.parseInt(req.getParameter("RecordID"));
			int year = Integer.parseInt(req.getParameter("Year"));
			DayType dayType = DayType.valueOf(req.getParameter("DayType"));
			Integer count = Integer.parseInt(req.getParameter("Count"));
			Integer daysWithAqi = Integer.parseInt(req.getParameter("DaysInYearWithAQI"));
			Integer maxAqi = Integer.parseInt(req.getParameter("MaxAQI"));
			int county = Integer.parseInt(req.getParameter("CountyID"));

			try {
				AirQuality airQuality = new AirQuality(recordId, year, dayType, count, daysWithAqi, maxAqi, county);
				airQuality = airQualityDao.create(airQuality);
				messages.put("success", "Successfully created record" + recordId);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}

		req.getRequestDispatcher("/AirQualityCreate.jsp").forward(req, resp);
	}
}