package behindthenumbers.servlet;

import behindthenumbers.dal.*;
import behindthenumbers.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cov19create")
public class Cov19Create extends HttpServlet {

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
		// Just render the JSP.
		req.getRequestDispatcher("/Cov19Create.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);

		String caseType = req.getParameter("caseType");
		Long count = Long.parseLong(req.getParameter("count"));
		int countyID = Integer.parseInt(req.getParameter("countyID"));
		// date must be in the format yyyy-mm-dd.
		Date date;
		try {
			date = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("date")).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		try {
			// Exercise: parse the input for StatusLevel.
			Cov19 cov19 = new Cov19(date, caseType, count, countyID);
			cov19 = cov19Dao.create(cov19);
			messages.put("success", "Successfully created record ID " + cov19.getRecordID());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}

		req.getRequestDispatcher("/Cov19Create.jsp").forward(req, resp);
	}
}
