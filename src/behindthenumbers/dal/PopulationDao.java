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
import java.util.Map;

import behindthenumbers.dal.ConnectionManager;
import behindthenumbers.model.Population;
import behindthenumbers.model.Population.PopulationChangeType;

/**
 * PopulationDao contributes to the DAL of the BehindTheNumbers application. It is an intermediary to
 * perform CRUD operations on the Population table in the BehindTheNumbers schema in a SQL instance.
 */
public class PopulationDao {

	static final Map<String, String> mapEnumStringsToJava = Map.of("Birth", "birth", "Natural Increase",
			"naturalIncrease", "Death", "death");

	static final Map<String, String> mapEnumStringsToSql = Map.of("birth", "Birth", "naturalIncrease",
			"Natural Increase", "death", "Death");

	protected ConnectionManager connectionManager;

	private static PopulationDao instance = null;

	protected PopulationDao() {
		connectionManager = new ConnectionManager();
	}

	public static PopulationDao getInstance() {
		if (instance == null) {
			instance = new PopulationDao();
		}
		return instance;
	}

	/**
	 * Save the Population instance by storing it in your MySQL instance. This runs
	 * a INSERT statement.
	 */
	public Population create(Population population) throws SQLException {
		String insertPopulation = "INSERT INTO Population(Year,PopulationChangeType,Count,Rate,"
				+ "TotalPopulation,CountyID) VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPopulation, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, population.getYear());
			insertStmt.setString(2, mapEnumStringsToSql.get(population.getPopulationChangeType().name()));
			insertStmt.setInt(3, population.getCount());
			insertStmt.setObject(4, population.getRate(), Types.DECIMAL);
			insertStmt.setObject(5, population.getTotalPopulation(), Types.INTEGER);
			insertStmt.setInt(6, population.getCountyId());
			insertStmt.executeUpdate();

			// Update the RecordId of the current Population object to reflect the
			// auto-incremented value.
			ResultSet set = insertStmt.getGeneratedKeys();
			while (set.next()) {
				int newKey = set.getInt(1);
				population.setRecordId(newKey);
			}

			return population;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	/**
	 * Method to get a Population record from your MySQL instance by providing a
	 * recordId.
	 * 
	 * @param recordId the ID of the record where Population information should be
	 *                  retrieved.
	 * @return a Population object representing the population data indexed by
	 *         recordId.
	 * @throws SQLException if a SQL-related error occurs.
	 */
	public Population getPopulationByRecordId(int recordId) throws SQLException {
		String selectMigration = String.format(
				"SELECT RecordID,Year,PopulationChangeType,Count,Rate,TotalPopulation,CountyID " 
		+ "FROM Population WHERE RecordID=?;",
				recordId);
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMigration);
			selectStmt.setInt(1, recordId);
			results = selectStmt.executeQuery();
			if (results.next()) {
				int year = results.getInt("Year");
				PopulationChangeType populationChangeType = PopulationChangeType
						.valueOf(mapEnumStringsToJava.get(results.getString("PopulationChangeType")));
				Integer count = (Integer) results.getObject("Count"); // may be null
				BigDecimal rate = (BigDecimal) results.getObject("Rate"); // may be null
				Integer totalPopulation = (Integer) results.getObject("TotalPopulation"); // may be null
				int countyId = results.getInt("CountyID");

				Population population = new Population(recordId, year, populationChangeType, count, rate, totalPopulation, countyId);
				return population;
			}
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
		return null;
	}

	/**
	 * Retrieve a list of Population records where total population exceeds a
	 * certain number.
	 * 
	 * @param total the minimum total population count value threshold for
	 *                          records to be returned.
	 * @return a List<Population> of Population objects storing each returned
	 *         Population record's values.
	 * @throws SQLException if a SQL-related error occurs.
	 */
	public List<Population> getPopulationByMinTotalPopulation(int total) throws SQLException {
		String selectMigration = "SELECT RecordID,Year,PopulationChangeType,Count,Rate,TotalPopulation,CountyID "
				+ "FROM Population WHERE TotalPopulation >= ?";
		List<Population> populationList = new ArrayList<Population>();
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMigration);
			selectStmt.setInt(1, total);
			results = selectStmt.executeQuery();
			while (results.next()) {
				int recordId = results.getInt("RecordID");
				int year = results.getInt("Year");
				PopulationChangeType populationChangeType = PopulationChangeType
						.valueOf(mapEnumStringsToJava.get(results.getString("PopulationChangeType")));
				Integer count = (Integer) results.getObject("Count"); // may be null
				BigDecimal rate = (BigDecimal) results.getObject("Rate"); // may be null
				Integer totalPopulation = (Integer) results.getObject("TotalPopulation"); // may be null
				int countyId = results.getInt("CountyID");

				Population population = new Population(recordId, year, populationChangeType, 
						count, rate, totalPopulation, countyId);
				populationList.add(population);
			}
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
		return populationList;
	}

	/**
	 * Update the total population number for a particular Population record by indexing the
	 * SQL Migration table by RecordID, then performing an UPDATE procedure.
	 * 
	 * @param population Population object representing the record in the SQL database
	 *                  that will be updated.
	 * @param newTotal   int representing the new total population value.
	 * @return Population object updated to reflect the new migration rate value.
	 * @throws SQLException if a SQL-related error occurs.
	 */
	public Population updateTotalPopulation(Population population, int newTotal) throws SQLException {
		String updateExpiration = "UPDATE Population SET TotalPopulation=? WHERE RecordID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateExpiration);
			updateStmt.setInt(1, newTotal);
			updateStmt.setInt(2, population.getRecordId());
			updateStmt.executeUpdate();

			// Update the total population value of the Java object before returning to the caller.
			population.setCount(newTotal);
			return population;
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
	 * Delete a Population record from the SQL BehindTheNumbers application by indexing
	 * its unique RecordId.
	 * 
	 * @param population an Population object storing information about the record to
	 *                  be deleted.
	 * @return null so that the Population instance is now null and does not store
	 *         deleted information.
	 * @throws SQLException if a SQL-related error occurs.
	 */
	public Population delete(Population population) throws SQLException {
		String deletePopulation = "DELETE FROM Population WHERE RecordId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePopulation);
			deleteStmt.setInt(1, population.getRecordId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Population instance.
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

}
