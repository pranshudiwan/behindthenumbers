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

public class HospitalBedsDao {
	protected ConnectionManager connectionManager;

	private static HospitalBedsDao instance = null;

	protected HospitalBedsDao() {
		connectionManager = new ConnectionManager();
	}

	public static HospitalBedsDao getInstance() {
		if (instance == null) {
			instance = new HospitalBedsDao();
		}
		return instance;
	}

	public HospitalBeds create(HospitalBeds hospitalBeds) throws SQLException {
		String insertHospitalBeds = "INSERT INTO HospitalBeds("
				+ "BedCountPerThousandHabitants,BedTypeLookupTableIdID,CountyID) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;

		try {
			connection = connectionManager.getConnection();
			// HospitalBeds has an auto-generated key. So we want to retrieve that key.
			insertStmt = connection.prepareStatement(insertHospitalBeds, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setBigDecimal(1, hospitalBeds.getBedCountPerThousandHabitants());
			insertStmt.setInt(2, hospitalBeds.getBedTypeLookupTableIdID());
			insertStmt.setInt(3, hospitalBeds.getCountyID());

			insertStmt.executeUpdate();

			resultKey = insertStmt.getGeneratedKeys();
			int hospitalBedsKey = -1;
			if (resultKey.next()) {
				hospitalBedsKey = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			hospitalBeds.setHospitalBedRecordID(hospitalBedsKey);
			return hospitalBeds;
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

	public HospitalBeds getHospitalBedsByHospitalBedRecordID(int hospitalBedRecordID) throws SQLException {
		String selecthospitalBeds = "SELECT HospitalBedRecordID,BedCountPerThousandHabitants,BedTypeLookupTableIdID,CountyID "
				+ "FROM HospitalBeds WHERE HospitalBedRecordID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selecthospitalBeds);
			selectStmt.setInt(1, hospitalBedRecordID);

			results = selectStmt.executeQuery();

			if (results.next()) {
				HospitalBeds hospitalBeds = new HospitalBeds(results.getInt("HospitalBedRecordID"),
						results.getBigDecimal("BedCountPerThousandHabitants"), results.getInt("BedTypeLookupTableIdID"),
						results.getInt("CountyID"));
				return hospitalBeds;
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
	 * Update the expiration date of the HospitalBeds instance. This runs a UPDATE
	 * statement.
	 */
	public HospitalBeds updateBedCountPerThousandHabitantst(HospitalBeds hospitalBeds, BigDecimal newBedCount)
			throws SQLException {
		String updateHospitalBeds = "UPDATE HospitalBeds SET BedCountPerThousandHabitants=? WHERE HospitalBedRecordID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateHospitalBeds);
			updateStmt.setBigDecimal(1, newBedCount);
			updateStmt.setInt(2, hospitalBeds.getHospitalBedRecordID());
			updateStmt.executeUpdate();

			// Update the hospitalBeds parameters before returning to the caller.
			hospitalBeds.setBedCountPerThousandHabitants(newBedCount);
			return hospitalBeds;
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
	 * Delete the HospitalBeds instance. This runs a DELETE statement.
	 */
	public HospitalBeds delete(HospitalBeds hospitalBeds) throws SQLException {
		String deleteHospitalBeds = "DELETE FROM HospitalBeds WHERE HospitalBedRecordID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteHospitalBeds);
			deleteStmt.setInt(1, hospitalBeds.getHospitalBedRecordID());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the HospitalBeds instance.
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
