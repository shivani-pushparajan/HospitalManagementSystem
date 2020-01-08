package global.coda.hospitalmanagementsystem.db.dao.dbimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import global.coda.hospitalmanagementsystem.db.constants.DatabaseMessageConstants;
import global.coda.hospitalmanagementsystem.db.constants.DatabaseQueryConstants;
import global.coda.hospitalmanagementsystem.model.Patient;

public class PatientDatabaseDao extends DatabaseConnection {
	private static final Logger LOGGER = LoggerFactory.getLogger(PatientDatabaseDao.class);
	private static final ResourceBundle DB_QUERIES_BUNDLE = ResourceBundle.getBundle(DatabaseQueryConstants.DB_QUERIES);
	private static final ResourceBundle DB_MESSAGES_BUNDLE = ResourceBundle
			.getBundle(DatabaseMessageConstants.DATABASE_MESSAGES);

	public boolean addPatient(Patient patient) {
		int affectedRowCount = 0;
		boolean result = false;
		Connection connection = getDatabaseConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(
					DB_QUERIES_BUNDLE.getString(DatabaseQueryConstants.HDB001PQ), Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, patient.getUsername());
			statement.setString(2, patient.getPassword());
			affectedRowCount = statement.executeUpdate();
			if (affectedRowCount > 0) {
				try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						patient.setPatientId(generatedKeys.getInt(1));
					} else {
						throw new SQLException(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB003E));
					}
				}
				statement = connection.prepareStatement(DB_QUERIES_BUNDLE.getString(DatabaseQueryConstants.HDB002PQ));
				statement.setInt(1, patient.getPatientId());
				statement.setString(2, patient.getPatientDisease());
				affectedRowCount = statement.executeUpdate();
				if (affectedRowCount > 0) {
					result = true;
				}
			}
		} catch (SQLException exception) {
			LOGGER.error(exception.getMessage());
		}
		return result;
	}

	public boolean updatePatient(int modifyChoice, int patientId, String newValue) {
		Connection connection = getDatabaseConnection();
		int result = 0;
		try {
			if (modifyChoice == 1) {
				PreparedStatement statement = connection
						.prepareStatement(DB_QUERIES_BUNDLE.getString(DatabaseQueryConstants.HDB003PQ));
				statement.setString(1, newValue);
				statement.setInt(2, patientId);
				result = statement.executeUpdate();
			} else if (modifyChoice == 2) {
				PreparedStatement statement = connection
						.prepareStatement(DB_QUERIES_BUNDLE.getString(DatabaseQueryConstants.HDB004PQ));
				statement.setString(1, newValue);
				statement.setInt(2, patientId);
				result = statement.executeUpdate();
			} else if (modifyChoice == 3) {
				PreparedStatement statement = connection
						.prepareStatement(DB_QUERIES_BUNDLE.getString(DatabaseQueryConstants.HDB005PQ));
				statement.setString(1, newValue);
				statement.setInt(2, patientId);
				result = statement.executeUpdate();
			}
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
		}
		if (result > 0) {
			return true;
		}
		return false;
	}

	public List<Patient> viewPatient(int branchId) {
		Connection connection = getDatabaseConnection();
		List<Patient> patientList = null;
		try {
			PreparedStatement statement = connection
					.prepareStatement(DB_QUERIES_BUNDLE.getString(DatabaseQueryConstants.HDB006PQ));
			statement.setInt(1, branchId);
			ResultSet resultSet = statement.executeQuery();
			patientList = new LinkedList<Patient>();
			while (resultSet.next()) {
				Patient patientObject = new Patient();
				patientObject.setPatientId(resultSet.getInt(1));
				patientObject.setUsername(resultSet.getString(2));
				patientObject.setPatientDisease(resultSet.getString(3));
				patientObject.setJoinedDate(resultSet.getTimestamp(4));
				patientList.add(patientObject);
			}

		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
		}
		return patientList;

	}

	public boolean deletePatient(int patientId) {
		Connection connection = getDatabaseConnection();
		int result = 0;
		try {
			PreparedStatement statement = connection
					.prepareStatement(DB_QUERIES_BUNDLE.getString(DatabaseQueryConstants.HDB007PQ));
			statement.setInt(1, patientId);
			result = statement.executeUpdate();
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
		}
		if (result == 1) {
			return true;
		}
		return false;
	}

}
