package global.coda.hospitalmanagementsystem.db.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import global.coda.hospitalmanagementsystem.model.User;
import global.coda.hospitalmanagementsystem.db.dao.dbimplementation.DatabaseConnection;
import global.coda.hospitalmanagementsystem.db.dao.dbimplementation.DoctorDatabaseDao;
public class UserServices {
	private static final Logger LOGGER = LoggerFactory.getLogger(DoctorDatabaseDao.class);
	public User viewUser(String username) {
		User user = new User();
		Connection connection = new DatabaseConnection().getDatabaseConnection();
		try {
			PreparedStatement statement = connection
					.prepareStatement("select pk_userid,accountnumber,roleId from t_users where username=?");

			statement.setString(1, username);
			ResultSet resultSet = statement.executeQuery();				
				user.setAccountNumber(resultSet.getInt(2));
				user.setRoleId(resultSet.getInt(3));
				user.setUserId(resultSet.getInt(1));
	

		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
		}
		return user;
	}
}
