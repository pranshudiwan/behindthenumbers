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
 * EmploymentDao contributes to the DAL of the BehindTheNumbers application. It is an intermediary to
 * perform CRUD operations on the Employment table in the BehindTheNumbers schema in a SQL instance.
 */
public class EmploymentDao {

	protected static ConnectionManager connectionManager;

	private static EmploymentDao instance = null;
	
	protected EmploymentDao() {
		
		connectionManager = new ConnectionManager();
	}
	
	
	public static EmploymentDao getInstance() {
		if(instance == null) {
			instance = new EmploymentDao();
		}
		return instance;
	}
	
	
	
	/**
	 * Create the Employment instance.
	 * This runs an NSERT statement.
	 */
	
	public static Employment create(Employment employment) throws SQLException {
		
		String insertEmployment =
			"INSERT INTO Employment(Year,EmployedPopulation,UnemployedPopulation,UnemployedRate,"
			+ "MedianHouseHoldIncomeInDollars,MedianHouseHoldIncomePercentageOfStateTotal,"
			+ "CivilianLaborForceAnnualAverage,CountyID) VALUES(?,?,?,?,?,?,?,?);";
		
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		
		try {
			connection = connectionManager.getConnection();
			
			// Employment has an auto-generated key. So we want to retrieve that key.
			insertStmt = connection.prepareStatement(insertEmployment, Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setObject(1, employment.getYear(), Types.INTEGER);
			insertStmt.setObject(2, employment.getEmployedPopulation(), Types.INTEGER);
			insertStmt.setObject(3, employment.getUnemployedPopulation(), Types.INTEGER);
			insertStmt.setObject(4, employment.getUnemployedRate(), Types.DECIMAL);
			insertStmt.setObject(5, employment.getMedianHouseHoldIncomeInDollars(), Types.INTEGER);
			insertStmt.setObject(6, employment.getMedianHouseHoldIncomePercentageOfStateTotal(), Types.DECIMAL);
			insertStmt.setObject(7, employment.getCivilianLaborForceAnnualAverage(), Types.INTEGER);
			insertStmt.setInt(8, employment.getCountyID());
			
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			
			resultKey = insertStmt.getGeneratedKeys();
			int nextID = -1;
			if(resultKey.next()) {
				nextID = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			employment.setEmploymentRecordID(nextID);
			return employment;
			
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
	 * Get the employment record indexed by RecordID by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Employment instance.
	 * 
	 */
	public List<Employment> getEmploymentRecordByCountyID(int countyID) throws SQLException {
		
		String selectEmployment =
				"SELECT EmploymentRecordID,Year,EmployedPopulation,UnemployedPopulation,UnemployedRate,"
				+ "MedianHouseHoldIncomeInDollars,MedianHouseHoldIncomePercentageOfStateTotal,"
				+ "CivilianLaborForceAnnualAverage,CountyID " +
				"FROM Employment " +
				"WHERE CountyID=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectEmployment);
			selectStmt.setInt(1, countyID);

			results = selectStmt.executeQuery();
			
			List<Employment> employmentRecordsList = new ArrayList<>();

			while (results.next()) {
				
				int EmploymentRecordID = results.getInt("EmploymentRecordID");
				Integer Year = (Integer) results.getObject("Year");
				Integer EmployedPopulation = (Integer) results.getObject("EmployedPopulation");
				Integer UnemployedPopulation = (Integer) results.getObject("UnemployedPopulation");
				BigDecimal UnemployedRate = (BigDecimal)results.getObject("UnemployedRate");
				Integer MedianHouseHoldIncomeInDollars = 
						(Integer) results.getObject("MedianHouseHoldIncomeInDollars");
				BigDecimal MedianHouseHoldIncomePercentageOfStateTotal = 
						(BigDecimal) results.getObject("MedianHouseHoldIncomePercentageOfStateTotal");
				Integer CivilianLaborForceAnnualAverage = 
						(Integer) results.getObject("CivilianLaborForceAnnualAverage");
				int CountyID = results.getInt("CountyID");
				
				Employment employment = new Employment(EmploymentRecordID, Year, EmployedPopulation, 
						UnemployedPopulation, UnemployedRate, MedianHouseHoldIncomeInDollars, 
						MedianHouseHoldIncomePercentageOfStateTotal, CivilianLaborForceAnnualAverage, CountyID);
				
				employmentRecordsList.add(employment);
				
			}
			
			return employmentRecordsList;
			
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
	 * Get the employment record indexed by RecordID by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Employment instance.
	 */
	public Employment getEmploymentRecordByID(int employmentrecordID) throws SQLException {
		
		String selectEmployment = "SELECT Year,EmployedPopulation,UnemployedPopulation,UnemployedRate,"
				+ "MedianHouseHoldIncomeInDollars,MedianHouseHoldIncomePercentageOfStateTotal,"
				+ "CivilianLaborForceAnnualAverage,CountyID " +
			"FROM Employment " +
			"WHERE EmploymentRecordID=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectEmployment);
			selectStmt.setInt(1, employmentrecordID);
			results = selectStmt.executeQuery();
			
			
			if(results.next()) {
				int EmploymentRecordID = results.getInt("EmploymentRecordID");
				Integer Year = (Integer) results.getObject("Year");
				Integer EmployedPopulation = (Integer) results.getObject("EmployedPopulation");
				Integer UnemployedPopulation = (Integer) results.getObject("UnemployedPopulation");
				BigDecimal UnemployedRate = (BigDecimal)results.getObject("UnemployedRate");
				Integer MedianHouseHoldIncomeInDollars = 
						(Integer) results.getObject("MedianHouseHoldIncomeInDollars");
				BigDecimal MedianHouseHoldIncomePercentageOfStateTotal = 
						(BigDecimal) results.getObject("MedianHouseHoldIncomePercentageOfStateTotal");
				Integer CivilianLaborForceAnnualAverage = 
						(Integer) results.getObject("CivilianLaborForceAnnualAverage");
				int CountyID = results.getInt("CountyID");
				
				Employment employment = new Employment(EmploymentRecordID, Year, EmployedPopulation, 
						UnemployedPopulation, UnemployedRate, MedianHouseHoldIncomeInDollars, 
						MedianHouseHoldIncomePercentageOfStateTotal, CivilianLaborForceAnnualAverage, CountyID);
				
				return employment;
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
	
	
	
	
	
	/*
	 * Updates the number of people that are unemployed in a particular education level category, in a particular
	 * year for a particular county by indexing the Employment table by its Record Id.
	 * Performs an UPDATE procedure
	 */
	public Employment updateNumUnemployed(Employment employment, int newNumUnemployed) throws SQLException {
		String updateExpiration = "UPDATE Employment SET UnemployedPopulation=? WHERE RecordID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateExpiration);
			updateStmt.setObject(1, newNumUnemployed);
			updateStmt.setInt(2, employment.getEmploymentRecordID());
			updateStmt.executeUpdate();

			// Update the total education value of the Java object before returning to the caller.
			employment.setUnemployedPopulation(newNumUnemployed);
			return employment;
			
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
	 * Delete the Employment instance.
	 * This runs a DELETE statement.
	 */
	public Employment delete(Employment employment) throws SQLException {
		
		String deleteEmployment = "DELETE FROM Employment WHERE EmploymentRecordID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteEmployment);
			deleteStmt.setInt(1, employment.getEmploymentRecordID());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Employment instance.
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
