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
import behindthenumbers.model.Migration;
import behindthenumbers.model.Migration.MigrationType;

/**
 * MigrationDao contributes to the DAL of the BehindTheNumbers application. It is an intermediary to
 * perform CRUD operations on the Migration table in the BehindTheNumbers schema in a SQL instance.
 */
public class MigrationDao {

	static final Map<String, String> mapEnumStringsToSql = Map.of("domesticMigration", "Domestic Migration",
			"internationalMigration", "International Migration", "netMigration", "Net Migration");

	static final Map<String, String> mapEnumStringsToJava = Map.of("Domestic Migration", "domesticMigration",
			"International Migration", "internationalMigration", "Net Migration", "netMigration");

	protected ConnectionManager connectionManager;

	private static MigrationDao instance = null;

	protected MigrationDao() {
		connectionManager = new ConnectionManager();
	}

	public static MigrationDao getInstance() {
		if (instance == null) {
			instance = new MigrationDao();
		}
		return instance;
	}

	/**
	 * Save the Migration instance by storing it in your MySQL instance. This runs a
	 * INSERT statement.
	 */
	public Migration create(Migration migration) throws SQLException {
		String insertMigration = "INSERT INTO Migration(Year,MigrationType,Count,Rate,CountyID) VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertMigration, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, migration.getYear());
			insertStmt.setString(2, mapEnumStringsToSql.get(migration.getMigrationType().name()));
			insertStmt.setInt(3, migration.getCount());
			insertStmt.setObject(4, migration.getRate(), Types.DECIMAL);
			insertStmt.setInt(5, migration.getCountyId());
			insertStmt.executeUpdate();

			// Update the RecordId of the current Migration object to reflect the
			// auto-incremented value.
			ResultSet set = insertStmt.getGeneratedKeys();
			while (set.next()) {
				int newKey = set.getInt(1);
				migration.setRecordId(newKey);
			}

			return migration;
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
	 * Method to get a Migration record from your MySQL instance by providing a
	 * recordId.
	 * 
	 * @param migration the RecordID of the Migration record to be
	 *                  retrieved.
	 * @return a Migration object representing the migration data indexed by
	 *         RecordID.
	 * @throws SQLException if a SQL-related error occurs.
	 */
	public Migration getMigrationByRecordId(int recordId) throws SQLException {
		String selectMigration = String.format(
				"SELECT RecordID,Year,MigrationType,Count,Rate,CountyID " + "FROM Migration WHERE RecordID=?;",
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
				MigrationType migrationType = MigrationType
						.valueOf(mapEnumStringsToJava.get(results.getString("MigrationType")));
				Integer count = (Integer) results.getObject("Count"); // may be null
				BigDecimal rate = (BigDecimal) results.getObject("Rate"); // may be null
				int countyId = results.getInt("CountyID");

				Migration migration = new Migration(recordId, year, migrationType, count, rate, countyId);
				return migration;
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
	 * Retrieve a list of Migration records where domestic migration count meets or exceeds a
	 * certain number.
	 * 
	 * @param domesticMigration the minimum domestic migration count value for
	 *                          records to be returned.
	 * @return a List<Migration> of Migration objects storing each returned
	 *         Migration record's values.
	 * @throws SQLException if a SQL-related error occurs.
	 */
	public List<Migration> getMigrationForDomesticThreshold(int domesticMigration) throws SQLException {
		String selectMigration = "SELECT RecordID,Year,MigrationType,Count,Rate,CountyID "
				+ "FROM Migration WHERE MigrationType='Domestic Migration' AND Count >= ?";
		List<Migration> migrationList = new ArrayList<Migration>();
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMigration);
			selectStmt.setInt(1, domesticMigration);
			results = selectStmt.executeQuery();
			while (results.next()) {
				int recordId = results.getInt("RecordID");
				int year = results.getInt("Year");
				MigrationType migrationType = MigrationType
						.valueOf(mapEnumStringsToJava.get(results.getString("MigrationType")));
				Integer count = (Integer) results.getObject("Count"); // may be null
				BigDecimal rate = (BigDecimal) results.getObject("Rate"); // may be null
				int matchingId = results.getInt("CountyID");

				Migration migration = new Migration(recordId, year, migrationType, count, rate, matchingId);
				migrationList.add(migration);
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
		return migrationList;
	}

	/**
	 * Update the migration rate for a particular country and year by indexing the
	 * SQL Migration table by RecordID, then performing an UPDATE procedure.
	 * 
	 * @param migration Migration object representing the record in the SQL database
	 *                  that will be updated.
	 * @param newRate   BigDecimal representing the new rate value.
	 * @return Migration object updated to reflect the new migration rate value.
	 * @throws SQLException if a SQL-related error occurs.
	 */
	public Migration updateMigrationRate(Migration migration, BigDecimal newRate) throws SQLException {
		String updateRate = "UPDATE Migration SET DaysInYearWithAQI=? WHERE RecordID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateRate);
			updateStmt.setBigDecimal(1, newRate);
			updateStmt.setInt(2, migration.getRecordId());
			updateStmt.executeUpdate();

			// Update the migration rate of the Java object before returning to the caller.
			migration.setRate(newRate);
			return migration;
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
	 * Delete a Migration record from the SQL BehindTheNumbers application by indexing
	 * its unique RecordId.
	 * 
	 * @param migration an Migration object storing information about the record to
	 *                  be deleted.
	 * @return null so that the Migration instance is now null and does not store
	 *         deleted information.
	 * @throws SQLException if a SQL-related error occurs.
	 */
	public Migration delete(Migration migration) throws SQLException {
		String deleteMigration = "DELETE FROM Migration WHERE RecordId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteMigration);
			deleteStmt.setInt(1, migration.getRecordId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Migration instance.
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
