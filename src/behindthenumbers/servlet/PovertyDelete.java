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


@SuppressWarnings("serial")
@WebServlet("/povertydelete")
public class PovertyDelete extends HttpServlet{
	
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
        // Provide a title and render the JSP.
        messages.put("title", "Delete Poverty Record");        
        req.getRequestDispatcher("/PovertyDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String RecordID = req.getParameter("RecordID");
        if (RecordID == null || RecordID.trim().isEmpty()) {
            messages.put("title", "Invalid RecordID");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the BlogUser.
	        Poverty poverty = new Poverty(Integer.parseInt(RecordID));
	        try {
	        	poverty = povertyDao.delete(poverty);
	        	// Update the message.
		        if (poverty == null) {
		            messages.put("title", "Successfully deleted record " + RecordID);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete record " + RecordID);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/PovertyDelete.jsp").forward(req, resp);
	}

}
