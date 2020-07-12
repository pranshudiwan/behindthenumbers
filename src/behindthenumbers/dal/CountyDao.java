package behindthenumbers.dal;

import behindthenumbers.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CountyDao {
	protected static ConnectionManager connectionManager;

	private static CountyDao instance = null;
	protected CountyDao() {
		connectionManager = new ConnectionManager();
	}
	public static CountyDao getInstance() {
		if(instance == null) {
			instance = new CountyDao();
		}
		return instance;
	}

	public County create(County county) throws SQLException {
		String insertCounty =
			"INSERT INTO County(CountyFips,CountyName,StateID) " +
			"VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCounty);
			insertStmt.setInt(1, county.getCountyFips());
			insertStmt.setString(2, county.getCountyName());
			insertStmt.setInt(3, county.getStateID());
			insertStmt.executeUpdate();

			return county;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}
	
	
	public County update(County county, String newname) throws SQLException {
		String updateCounty = "UPDATE County SET CountyName=? WHERE CountyFips=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCounty);
			updateStmt.setString(1, newname);
			updateStmt.setInt(2, county.getCountyFips());
			updateStmt.executeUpdate();
			county.setCountyName(newname);
			return county;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	public County getCountyFromFips(int CountyFips) throws SQLException {
		// To build an BlogUser object, we need the Persons record, too.
		String selectCounty = "Select CountyName From County Where CountyFips=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCounty);
			selectStmt.setInt(1, CountyFips);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultcountyname = results.getString("CountyName");
				County county = new County(resultcountyname);
				return county;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	public static List<County> getCountyFromName(String CountyName) throws SQLException {
		// To build an BlogUser object, we need the Persons record, too.
		List<County> counties = new ArrayList<County>();
		String selectCounty = "Select * From County Where CountyName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCounty);
			selectStmt.setString(1, CountyName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultcountyname = results.getString("CountyName");
				int resultfips = results.getInt("CountyFIPS");
				int resultstate = results.getInt("StateID");
				County county = new County(resultfips, resultcountyname, resultstate);
				counties.add(county);
				return counties;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	public County delete(County county) throws SQLException {
		String deleteCounty = "DELETE FROM County WHERE CountyName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCounty);
			deleteStmt.setString(1, county.getCountyName());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Persons instance.
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
		
}