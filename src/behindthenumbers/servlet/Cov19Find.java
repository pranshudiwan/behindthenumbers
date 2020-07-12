package behindthenumbers.servlet;

import behindthenumbers.dal.*;
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

/**
 * FindUsers is the primary entry point into the application.
 * 
 * Note the logic for doGet() and doPost() are almost identical. However, there
 * is a difference: doGet() handles the http GET request. This method is called
 * when you put in the /findusers URL in the browser. doPost() handles the http
 * POST request. This method is called after you click the submit button.
 * 
 * To run: 1. Run the SQL script to recreate your database schema:
 * http://goo.gl/86a11H. 2. Insert test data. You can do this by running
 * blog.tools.Inserter (right click, Run As > JavaApplication. Notice that this
 * is similar to Runner.java in our JDBC example. 3. Run the Tomcat server at
 * localhost. 4. Point your browser to
 * http://localhost:8080/BlogApplication/findusers.
 */
@WebServlet("/cov19find")
public class Cov19Find extends HttpServlet {

	protected Cov19Dao cov19Dao;

	@Override
	public void init() throws ServletException {
		cov19Dao = Cov19Dao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		List<Cov19> cov19List = new ArrayList<Cov19>();

		String countyID = req.getParameter("countyID");
		if (countyID == null || countyID.trim().isEmpty()) {
			messages.put("success", "Please enter a county ID.");
		} else {
			try {
				cov19List = cov19Dao.getCov19ByCountyID(Integer.parseInt(countyID));

				messages.put("success", "Displaying results for County " + countyID);
				messages.put("previousCountyID", "" + countyID);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}
		req.setAttribute("cov19List", cov19List);

		req.getRequestDispatcher("/Cov19Find.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
