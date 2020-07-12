package behindthenumbers.servlet;

import behindthenumbers.dal.*;
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

@WebServlet("/cov19update")
public class Cov19Update extends HttpServlet {

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

		String recordID = req.getParameter("recordID");
		if (recordID == null || recordID.trim().isEmpty()) {
			messages.put("success", "Please enter a valid record ID.");
		} else {
			try {
				Cov19 cov19 = cov19Dao.getCov19ByRecordID(Integer.parseInt(recordID));
				if (cov19 == null) {
					messages.put("success", "Record does not exist.");
				}
				req.setAttribute("cov19", cov19);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}

		req.getRequestDispatcher("/Cov19Update.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		String recordID = req.getParameter("recordID");
		if (recordID == null || recordID.trim().isEmpty()) {
			messages.put("success", "Please enter a valid record ID.");
		} else {
			try {
				Cov19 cov19 = cov19Dao.getCov19ByRecordID(Integer.parseInt(recordID));
				if (cov19 == null) {
					messages.put("success", "Record does not exist. No update to perform.");
				} else {
					String newCount = req.getParameter("count");
					if (newCount == null || newCount.trim().isEmpty()) {
						messages.put("success", "Please enter a valid new count.");
					} else {
						cov19 = cov19Dao.updateCount(cov19, Long.parseLong(newCount));
						messages.put("success", "Successfully updated record " + recordID);
					}
				}
				req.setAttribute("cov19", cov19);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}

		req.getRequestDispatcher("/Cov19Update.jsp").forward(req, resp);
	}
}
