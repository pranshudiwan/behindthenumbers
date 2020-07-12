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

@WebServlet("/cov19delete")
public class Cov19Delete extends HttpServlet {

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
		// Provide a title and render the JSP.
		messages.put("title", "Delete Cov19");
		req.getRequestDispatcher("/Cov19Delete.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		int recordID = Integer.parseInt(req.getParameter("recordID"));
		// Delete the Cov19.
		Cov19 cov19 = new Cov19(recordID);
		try {
			cov19 = cov19Dao.delete(cov19);
			// Update the message.
			if (cov19 == null) {
				messages.put("title", "Successfully deleted record " + recordID);
			} else {
				messages.put("title", "Failed to delete record " + recordID);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}

		req.getRequestDispatcher("/Cov19Delete.jsp").forward(req, resp);
	}
}
