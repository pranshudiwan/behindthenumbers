package behindthenumbers.dal;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import behindthenumbers.model.*;



/**
 * PovertyDao contributes to the DAL of the BehindTheNumbers application. It is an intermediary to
 * perform CRUD operations on the Poverty table in the BehindTheNumbers schema in a SQL instance.
 */
public class PovertyDao {

	
	protected static ConnectionManager connectionManager;

	private static PovertyDao instance = null;
	
	protected PovertyDao() {
		
		connectionManager = new ConnectionManager();
	}
	
	
	public static PovertyDao getInstance() {
		if(instance == null) {
			instance = new PovertyDao();
		}
		return instance;
	}
	
	
	
	
	/**
	 * Creates the poverty instance
	 * @param poverty Poverty object representing the poverty data indexed by
	 *         recordId.
	 * @return Poverty object
	 * @throws SQLException if a SQL error occurs
	 */
	public static Poverty create(Poverty poverty) throws SQLException {
		
		String insertPoverty =
			"INSERT INTO Poverty(Year,PovertyPopulation,PercentPovertyPopulation,"
			+ "AgeGroupID,CountyID) VALUES(?,?,?,?,?);";
		
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		
		try {
			connection = connectionManager.getConnection();
			
			// Poverty has an auto-generated key. So we want to retrieve that key.
			insertStmt = connection.prepareStatement(insertPoverty,
				Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setInt(1, poverty.getYear());
			insertStmt.setObject(2, poverty.getPovertyPopulation(), Types.INTEGER);			// nullable
			insertStmt.setObject(3, poverty.getPercentPovertyPopulation(), Types.DECIMAL);	// nullable
			insertStmt.setInt(4, poverty.getAgeGroupID());
			insertStmt.setInt(5, poverty.getCountyID());
			
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			
			resultKey = insertStmt.getGeneratedKeys();
			int RecordID = -1;
			if(resultKey.next()) {
				RecordID = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			poverty.setRecordID(RecordID);;;
			return poverty;
			
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
	
	
	
	
	
	/**
	 * Get the poverty record indexed by RecordID, fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Poverty instance.
	 */
	public Poverty getPovertyRecordByID(int recordID) throws SQLException {
		
		
		String selectPoverty =
			"SELECT RecordID,Year,PovertyPopulation,PercentPovertyPopulation,AgeGroupID,CountyID " +
			"FROM Poverty " +
			"WHERE RecordID=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPoverty);
			selectStmt.setInt(1, recordID);
			results = selectStmt.executeQuery();
			
			
			if(results.next()) {
				int RecordID = results.getInt("RecordID");
				int Year = results.getInt("Year");
				Integer PovertyPopulation = (Integer)results.getObject("PovertyPopulation");
				BigDecimal PercentPovertyPopulation = (BigDecimal) results.getObject("PercentPovertyPopulation");
				int AgeGroupID = results.getInt("AgeGroupID");
				int CountyID = results.getInt("CountyID");
				
				
				Poverty poverty = new Poverty(RecordID, Year, PovertyPopulation, PercentPovertyPopulation,
						AgeGroupID, CountyID);
				
				return poverty;
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
	
	
	
	/**
	 * Method to read the poverty records for a particular county. This includes records from all age-groups
	 * @param countyID the countyID by which records will be searched
	 * @return a list of poverty records
	 * @throws SQLException when a SQL error occurs
	 */
	public List<Poverty> getPovertyRecordsByCountyID(int countyID) throws SQLException {
		String selectPoverty =
				"SELECT RecordID,Year,PovertyPopulation,PercentPovertyPopulation,AgeGroupID,CountyID " +
				"FROM Poverty " +
				"WHERE CountyID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPoverty);
			selectStmt.setInt(1, countyID);

			results = selectStmt.executeQuery();
			
			List<Poverty> povertyRecordsList = new ArrayList<>();

			while (results.next()) {
				
					int RecordID = results.getInt("RecordID");
					int Year = results.getInt("Year");
					Integer PovertyPopulation = (Integer)results.getObject("PovertyPopulation");
					BigDecimal PercentPovertyPopulation = (BigDecimal) results.getObject("PercentPovertyPopulation");
					int AgeGroupID = results.getInt("AgeGroupID");
					int CountyID = results.getInt("CountyID");
					
					Poverty poverty = new Poverty(RecordID, Year, PovertyPopulation, PercentPovertyPopulation,
							AgeGroupID, CountyID);
					povertyRecordsList.add(poverty);
			}
			
			return povertyRecordsList;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
	}
	
	
	
	/**
	 * Get the poverty record indexed by AgeGroup by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Poverty instance.
	 * 
	 */
	public List<Poverty> getPovertyRecordsByAgeGroup(int ageGroupID) throws SQLException {
		String selectPoverty =
				"SELECT RecordID,Year,PovertyPopulation,PercentPovertyPopulation,AgeGroupID,CountyID " +
				"FROM Poverty " +
				"WHERE AgeGroupID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPoverty);
			selectStmt.setInt(1, ageGroupID);

			results = selectStmt.executeQuery();
			
			List<Poverty> povertyRecordsList = new ArrayList<>();

			while (results.next()) {
				
					int RecordID = results.getInt("RecordID");
					int Year = results.getInt("Year");
					Integer PovertyPopulation = (Integer)results.getObject("PovertyPopulation");
					BigDecimal PercentPovertyPopulation = (BigDecimal) results.getObject("PercentPovertyPopulation");
					int AgeGroupID = results.getInt("AgeGroupID");
					int CountyID = results.getInt("CountyID");
					
					Poverty poverty = new Poverty(RecordID, Year, PovertyPopulation, PercentPovertyPopulation,
							AgeGroupID, CountyID);
					povertyRecordsList.add(poverty);
			}
			
			return povertyRecordsList;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
	}
	
	
	
	
	/**
	 * Updates the povertyPopulation of the poverty instance
	 * @param poverty the poverty instance to be updated
	 * @param newPovertyPopulation the new value with which povertyPopulation is to be updated
	 * @return the updated instance of poverty
	 * @throws SQLException if a SQL error occurs
	 */
	public Poverty updateTotalPopulationinPoverty(Poverty poverty, int newPovertyPopulation) throws SQLException {
		String updateExpiration = "UPDATE Poverty SET PovertyPopulation=? WHERE RecordID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateExpiration);
			updateStmt.setInt(1, newPovertyPopulation);
			updateStmt.setInt(2, poverty.getRecordID());
			updateStmt.executeUpdate();

			// Update the total poverty value of the Java object before returning to the caller.
			poverty.setPovertyPopulation(newPovertyPopulation);
			return poverty;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	
	
	
	/**
	 * Delete the Poverty instance.
	 * This runs a DELETE statement.
	 */
	public Poverty delete(Poverty poverty) throws SQLException {
		
		String deletePoverty = "DELETE FROM Poverty WHERE RecordID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePoverty);
			deleteStmt.setInt(1, poverty.getRecordID());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Poverty instance.
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
