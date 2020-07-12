/**
 * Qingyao Zheng
 * CS5200 HW4
 */

package behindthenumbers.dal;

import behindthenumbers.model.*;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Cov19Dao {
	protected ConnectionManager connectionManager;

	private static Cov19Dao instance = null;

	protected Cov19Dao() {
		connectionManager = new ConnectionManager();
	}

	public static Cov19Dao getInstance() {
		if (instance == null) {
			instance = new Cov19Dao();
		}
		return instance;
	}

	public Cov19 create(Cov19 cov19) throws SQLException {
		String insertCov19 = "INSERT INTO Cov19(" + "Date,CaseType,Count,CountyID) VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;

		try {
			connection = connectionManager.getConnection();
			// Cov19 has an auto-generated key. So we want to retrieve that key.
			insertStmt = connection.prepareStatement(insertCov19, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setDate(1, cov19.getDate());
			insertStmt.setString(2, cov19.getCaseType());
			insertStmt.setLong(3, cov19.getCount());
			insertStmt.setInt(4, cov19.getCountyID());

			insertStmt.executeUpdate();

			resultKey = insertStmt.getGeneratedKeys();
			int cov19Key = -1;
			if (resultKey.next()) {
				cov19Key = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			cov19.setRecordID(cov19Key);
			return cov19;
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
			if (resultKey != null) {
				resultKey.close();
			}
		}
	}

	public Cov19 getCov19ByRecordID(int recordID) throws SQLException {
		String selectcov19 = "SELECT RecordID,Date,CaseType,Count,CountyID " + "FROM Cov19 WHERE RecordID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectcov19);
			selectStmt.setInt(1, recordID);

			results = selectStmt.executeQuery();

			if (results.next()) {
				Cov19 cov19 = new Cov19(results.getInt("RecordID"), results.getDate("Date"),
						results.getString("CaseType"), results.getLong("Count"), results.getInt("CountyID"));
				return cov19;
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

	public List<Cov19> getCov19ByCountyID(int countyID) throws SQLException {
		String selectcov19 = "SELECT RecordID,Date,CaseType,Count,CountyID " + "FROM Cov19 WHERE countyID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectcov19);
			selectStmt.setInt(1, countyID);

			results = selectStmt.executeQuery();
			
			List<Cov19> cov19List = new ArrayList<>();

			while (results.next()) {
				Cov19 cov19 = new Cov19(results.getInt("RecordID"), results.getDate("Date"),
						results.getString("CaseType"), results.getLong("Count"), results.getInt("CountyID"));
				cov19List.add(cov19);
			}
			return cov19List;
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
	 * Update the expiration date of the Cov19 instance. This runs a UPDATE
	 * statement.
	 */
	public Cov19 updateCount(Cov19 cov19, Long newCount) throws SQLException {
		String updateCov19 = "UPDATE Cov19 SET Count=? WHERE RecordID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCov19);
			updateStmt.setLong(1, newCount);
			updateStmt.setInt(2, cov19.getRecordID());
			updateStmt.executeUpdate();

			// Update the cov19 parameters before returning to the caller.
			cov19.setCount(newCount);
			return cov19;
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
	 * Delete the Cov19 instance. This runs a DELETE statement.
	 */
	public Cov19 delete(Cov19 cov19) throws SQLException {
		String deleteCov19 = "DELETE FROM Cov19 WHERE RecordID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCov19);
			deleteStmt.setInt(1, cov19.getRecordID());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Cov19 instance.
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
