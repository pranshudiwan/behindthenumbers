package behindthenumbers.dal;

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
import behindthenumbers.model.AirQuality;
import behindthenumbers.model.AirQuality.DayType;

/**
 * AirQualityDao contributes to the DAL of the BehindTheNumbers application. It is an intermediary to
 * perform CRUD operations on the AirQualityDao table in the BehindTheNumbers schema in a SQL instance.
 */
public class AirQualityDao {

	static final Map<String, String> mapEnumStringsToSql = Map.of("goodDays", "Good Days", "moderateDays",
			"Moderate Days", "unhealthyForSensitiveGroups", "Unhealthy For Sensitive Groups", "unhealthyDays",
			"Unhealthy Days", "veryUnhealthyDays", "Very Unhealthy Days", "hazardousDays", "Hazardous Days");

	static final Map<String, String> mapEnumStringsToJava = Map.of("Good Days", "goodDays", "Moderate Days",
			"moderateDays", "Unhealthy For Sensitive Groups", "unhealthyForSensitiveGroups", "Unhealthy Days",
			"unhealthyDays", "Very Unhealthy Days", "veryUnhealthyDays", "Hazardous Days", "hazardousDays");

	protected ConnectionManager connectionManager;

	private static AirQualityDao instance = null;

	protected AirQualityDao() {
		connectionManager = new ConnectionManager();
	}

	public static AirQualityDao getInstance() {
		if (instance == null) {
			instance = new AirQualityDao();
		}
		return instance;
	}

	/**
	 * Save the AirQuality instance by storing it in your MySQL instance. This runs
	 * a INSERT statement.
	 */
	public AirQuality create(AirQuality airQuality) throws SQLException {
		String insertAirQuality = "INSERT INTO AirQuality(Year,DayType,Count,"
				+ "DaysInYearWithAQI,MaxAQI,CountyID) VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAirQuality, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, airQuality.getYear());
			insertStmt.setString(2, mapEnumStringsToSql.get(airQuality.getDayType().name()));
			insertStmt.setObject(3, airQuality.getCount(), Types.INTEGER);
			insertStmt.setObject(4, airQuality.getDaysInYearWithAqi(), Types.INTEGER);
			insertStmt.setObject(5, airQuality.getMaxAqi(), Types.INTEGER);
			insertStmt.setInt(6, airQuality.getCountyId());
			insertStmt.executeUpdate();

			// Update the RecordId of the current AirQuality object to reflect the
			// auto-incremented value.
			ResultSet set = insertStmt.getGeneratedKeys();
			while (set.next()) {
				int newKey = set.getInt(1);
				airQuality.setRecordId(newKey);
			}

			return airQuality;
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
	 * Method to get an AirQuality record from your MySQL instance by providing a
	 * recordId.
	 * 
	 * @param recordId the ID of the record whose AirQuality information should be
	 *                 retrieved.
	 * @return a AirQuality object representing the air quality data indexed by
	 *         recordId.
	 * @throws SQLException if a SQL-related error occurs.
	 */
	public AirQuality getAirQualityByRecordId(int recordId) throws SQLException {
		String selectAirQuality = String
				.format("SELECT RecordID, Year, DayType, Count, DaysInYearWithAQI, MaxAQI, CountyID "
						+ "FROM AirQuality WHERE RecordID=?;", recordId);
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAirQuality);
			selectStmt.setInt(1, recordId);
			results = selectStmt.executeQuery();
			if (results.next()) {
				int year = results.getInt("Year");
				DayType dayType = AirQuality.DayType.valueOf(mapEnumStringsToJava.get(results.getString("DayType")));
				Integer count = (Integer) results.getObject("Count");
				Integer daysInYearWithAqi = (Integer) results.getObject("DaysInYearWithAQI");
				Integer maxAqi = (Integer) results.getObject("MaxAQI");
				int countyId = results.getInt("CountyID");

				AirQuality airQuality = new AirQuality(recordId, year, dayType, count, daysInYearWithAqi, maxAqi,
						countyId);
				return airQuality;
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
	 * Retrieve a list of counties where MaxAQI is greater than a minimum threshold
	 * defined by the user.
	 * 
	 * @param maxAqi the value for MaxAQI where counties whose MaxAQI value is
	 *               higher should be returned.
	 * @return a List<AirQuality> of AirQuality objects storing each returned
	 *         AirQuality record's values.
	 * @throws SQLException if a SQL-related error occurs.
	 */
	public List<AirQuality> getAirQualityByMinimumMaxAqi(int maxAqi) throws SQLException {
		String selectAirQuality = "SELECT RecordID, Year, DayType, Count, DaysInYearWithAQI, MaxAQI, CountyID "
				+ "FROM AirQuality WHERE MaxAQI>?";
		List<AirQuality> airQualityList = new ArrayList<AirQuality>();
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAirQuality);
			selectStmt.setInt(1, maxAqi);
			results = selectStmt.executeQuery();
			while (results.next()) {
				int recordId = results.getInt("RecordID");
				int year = results.getInt("Year");
				DayType dayType = AirQuality.DayType.valueOf(mapEnumStringsToJava.get(results.getString("DayType")));
				Integer count = (Integer) results.getObject("Count");
				Integer daysInYearWithAqi = (Integer) results.getObject("DaysInYearWithAQI");
				Integer returnedMaxAqi = (Integer) results.getObject("MaxAQI");
				int matchingId = results.getInt("CountyID");

				AirQuality airQuality = new AirQuality(recordId, year, dayType, count, daysInYearWithAqi,
						returnedMaxAqi, matchingId);
				airQualityList.add(airQuality);
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
		return airQualityList;
	}

	/**
	 * Update the days with recorded AQI variable for a county in a particular year by
	 * indexing the record in the SQL AirQuality table by its RecordID, 
	 * then performing an UPDATE procedure.
	 * 
	 * @param airQuality     AirQuality object representing the card in the SQL
	 *                       database that will be updated.
	 * @param newDaysWithAqi int representing the new daysWithAQI value.
	 * @return AirQuality object updated to reflect the new daysWithAqi value.
	 * @throws SQLException if a SQL-related error occurs.
	 */
	public AirQuality updateDaysWithAqi(AirQuality airQuality, int newDaysWithAqi) throws SQLException {
		String updateDaysWithAqi = "UPDATE AirQuality SET DaysInYearWithAQI=? WHERE RecordID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateDaysWithAqi);
			updateStmt.setInt(1, newDaysWithAqi);
			updateStmt.setInt(2, airQuality.getRecordId());
			updateStmt.executeUpdate();

			// Update the Java object's days with AQI variable before returning to the caller.
			airQuality.setDaysInYearWithAqi(newDaysWithAqi);
			return airQuality;
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
	 * Delete an AirQuality record from the SQL BehindTheNumbers application by indexing its
	 * unique RecordId.
	 * 
	 * @param airQuality an AirQuality object storing information about the record to
	 *                   be deleted.
	 * @return null so that the AirQuality instance is now null and does not store
	 *         deleted information.
	 * @throws SQLException if a SQL-related error occurs.
	 */
	public AirQuality delete(AirQuality airQuality) throws SQLException {
		String deleteAirQuality = "DELETE FROM AirQuality WHERE RecordId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAirQuality);
			deleteStmt.setInt(1, airQuality.getRecordId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the AirQuality instance.
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
