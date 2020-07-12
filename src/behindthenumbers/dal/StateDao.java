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

import behindthenumbers.model.*;


public class StateDao {
	protected static ConnectionManager connectionManager;

	private static StateDao instance = null;
	protected StateDao() {
		connectionManager = new ConnectionManager();
	}
	public static StateDao getInstance() {
		if(instance == null) {
			instance = new StateDao();
		}
		return instance;
	}
	
	public State create(State state) throws SQLException {
		String insertState =
			"INSERT INTO State(StateName,StateAbbreviation) " +
			"VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertState);
			insertStmt.setString(1, state.getStateName());
			insertStmt.setString(2, state.getStateAbbreviation());
			insertStmt.executeUpdate();

			return state;
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
	
	
	public State update(State state, String newname) throws SQLException {
		String updateCounty = "UPDATE State SET StateName=? WHERE StateAbbreviation=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCounty);
			updateStmt.setString(1, newname);
			updateStmt.setString(2, state.getStateAbbreviation());
			updateStmt.executeUpdate();
			state.setStateName(newname);
			return state;
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
	
	
	public State getStateFromAbbreviation(String StateAbbreviation) throws SQLException {
		// To build an BlogUser object, we need the Persons record, too.
		String selectState = "Select * From State Where StateAbbreviation=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectState);
			selectStmt.setString(1, StateAbbreviation);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultstatename = results.getString("StateName");
				String resultstateabb = results.getString("StateAbbreviation");
				State state = new State(resultstatename, resultstateabb);
				return state;
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
	
	public State delete(State state) throws SQLException {
		String deleteState = "DELETE FROM State WHERE StateName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteState);
			deleteStmt.setString(1, state.getStateName());
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