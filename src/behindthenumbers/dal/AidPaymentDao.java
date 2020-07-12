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

public class AidPaymentDao {
	protected ConnectionManager connectionManager;

	private static AidPaymentDao instance = null;

	protected AidPaymentDao() {
		connectionManager = new ConnectionManager();
	}

	public static AidPaymentDao getInstance() {
		if (instance == null) {
			instance = new AidPaymentDao();
		}
		return instance;
	}

	public AidPayment create(AidPayment aidPayment) throws SQLException {
		String insertAidPayment = "INSERT INTO AidPayment("
				+ "Institution,`Total Payments In Dollars`,StateID) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;

		try {
			connection = connectionManager.getConnection();
			// AidPayment has an auto-generated key. So we want to retrieve that key.
			insertStmt = connection.prepareStatement(insertAidPayment, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, aidPayment.getInstitution());
			insertStmt.setBigDecimal(2, aidPayment.getTotalPaymentInDollars());
			insertStmt.setInt(3, aidPayment.getStateID());

			insertStmt.executeUpdate();

			resultKey = insertStmt.getGeneratedKeys();
			int aidPaymentKey = -1;
			if (resultKey.next()) {
				aidPaymentKey = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			aidPayment.setRecordID(aidPaymentKey);
			return aidPayment;
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

	public AidPayment getAidPaymentByRecordID(int recordID) throws SQLException {
		String selectaidPayment = "SELECT RecordID,Institution,`Total Payments In Dollars`,StateID "
				+ "FROM AidPayment WHERE RecordID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectaidPayment);
			selectStmt.setInt(1, recordID);

			results = selectStmt.executeQuery();

			if (results.next()) {
				AidPayment aidPayment = new AidPayment(results.getInt("RecordID"), results.getString("Institution"),
						results.getBigDecimal("Total Payments In Dollars"), results.getInt("StateID"));
				return aidPayment;
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
	 * Update the expiration date of the AidPayment instance. This runs a UPDATE
	 * statement.
	 */
	public AidPayment updateTotalPaymentInDollars(AidPayment aidPayment, BigDecimal newAmount) throws SQLException {
		String updateAidPayment = "UPDATE AidPayment SET `Total Payments In Dollars`=? WHERE RecordID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateAidPayment);
			updateStmt.setBigDecimal(1, newAmount);
			updateStmt.setInt(2, aidPayment.getRecordID());
			updateStmt.executeUpdate();

			// Update the aidPayment parameters before returning to the caller.
			aidPayment.setTotalPaymentInDollars(newAmount);
			return aidPayment;
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
	 * Delete the AidPayment instance. This runs a DELETE statement.
	 */
	public AidPayment delete(AidPayment aidPayment) throws SQLException {
		String deleteAidPayment = "DELETE FROM AidPayment WHERE RecordID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAidPayment);
			deleteStmt.setInt(1, aidPayment.getRecordID());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the AidPayment instance.
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
