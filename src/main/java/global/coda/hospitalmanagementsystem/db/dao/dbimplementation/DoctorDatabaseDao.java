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
import global.coda.hospitalmanagementsystem.model.Doctor;

public class DoctorDatabaseDao extends DatabaseConnection {
	private static final Logger LOGGER = LoggerFactory.getLogger(DoctorDatabaseDao.class);
	private static final ResourceBundle DB_QUERIES_BUNDLE = ResourceBundle.getBundle(DatabaseQueryConstants.DB_QUERIES);
	private static final ResourceBundle DB_MESSAGES_BUNDLE = ResourceBundle
			.getBundle(DatabaseMessageConstants.DATABASE_MESSAGES);

	public boolean addDoctor(Doctor doctor) {
		int affectedRowCount = 0;
		boolean result = false;
		Connection connection = getDatabaseConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(
					DB_QUERIES_BUNDLE.getString(DatabaseQueryConstants.HDB002DQ), Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, doctor.getUsername());
			statement.setString(2, doctor.getPassword());
			affectedRowCount = statement.executeUpdate();
			if (affectedRowCount > 0) {
				try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						doctor.setDoctorId(generatedKeys.getInt(1));
					} else {
						throw new SQLException(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB003E));
					}
				}
				statement = connection.prepareStatement(DB_QUERIES_BUNDLE.getString(DatabaseQueryConstants.HDB003DQ));
				statement.setInt(1, doctor.getDoctorId());
				statement.setString(2, doctor.getDoctorSpecialisation());
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

	public boolean updateDoctor(int modifyChoice, int userId, String newValue) {
		Connection connection = getDatabaseConnection();
		int result = 0;
		try {
			if (modifyChoice == 1) {
				PreparedStatement statement = connection
						.prepareStatement(DB_QUERIES_BUNDLE.getString(DatabaseQueryConstants.HDB004DQ));
				statement.setString(1, newValue);
				statement.setInt(2, userId);
				result = statement.executeUpdate();
			} else if (modifyChoice == 2) {
				PreparedStatement statement = connection
						.prepareStatement(DB_QUERIES_BUNDLE.getString(DatabaseQueryConstants.HDB005DQ));
				statement.setString(1, newValue);
				statement.setInt(2, userId);
				result = statement.executeUpdate();
			} else if (modifyChoice == 3) {
				PreparedStatement statement = connection
						.prepareStatement(DB_QUERIES_BUNDLE.getString(DatabaseQueryConstants.HDB006DQ));
				statement.setString(1, newValue);
				statement.setInt(2, userId);
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

	public List<Doctor> viewDoctor(int branchId) {
		Connection connection = getDatabaseConnection();
		List<Doctor> doctorList = null;
		try {
			PreparedStatement statement = connection
					.prepareStatement(DB_QUERIES_BUNDLE.getString(DatabaseQueryConstants.HDB007DQ));

			statement.setInt(1, branchId);
			ResultSet resultSet = statement.executeQuery();
			doctorList = new LinkedList<Doctor>();
			while (resultSet.next()) {
				Doctor doctorObject = new Doctor();

				doctorObject.setDoctorId(resultSet.getInt(1));
				doctorObject.setUsername(resultSet.getString(2));
				doctorObject.setDoctorSpecialisation(resultSet.getString(3));
				doctorObject.setAvailableTime(resultSet.getTimestamp(4));
				doctorList.add(doctorObject);
			}

		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
		}
		return doctorList;

	}

	public boolean deleteDoctor(int doctorId) {
		Connection connection = getDatabaseConnection();
		int result = 0;
		try {
			PreparedStatement statement = connection
					.prepareStatement(DB_QUERIES_BUNDLE.getString(DatabaseQueryConstants.HDB008DQ));
			statement.setInt(1, doctorId);
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
