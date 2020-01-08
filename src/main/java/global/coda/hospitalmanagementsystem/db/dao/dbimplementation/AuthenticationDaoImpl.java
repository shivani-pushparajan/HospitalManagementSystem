 package global.coda.hospitalmanagementsystem.db.dao.dbimplementation;

import static global.coda.hospitalmanagementsystem.db.constants.DatabaseQueryConstants.DB_QUERIES;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import global.coda.hospitalmanagementsystem.db.constants.DatabaseQueryConstants;
import global.coda.hospitalmanagementsystem.db.dao.dbinterface.AuthenticationDao;

public class AuthenticationDaoImpl extends DatabaseConnection implements AuthenticationDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationDaoImpl.class);
	private static final ResourceBundle DB_QUERIES_BUNDLE = ResourceBundle.getBundle(DB_QUERIES);

	@Override
	public List<Integer> authenticateUserDb(String username, String password) {
		List<Integer> userValues = new LinkedList<Integer>();
		try {
			Connection connection = getDatabaseConnection();
			PreparedStatement authenticateStmt = connection
					.prepareStatement(DB_QUERIES_BUNDLE.getString(DatabaseQueryConstants.HDB001AQ));
			authenticateStmt.setString(1, username);
			authenticateStmt.setString(2, password);
			ResultSet resultSet = authenticateStmt.executeQuery();
			if (resultSet.next() == false) {
				userValues = null;
				return userValues;
			}
			userValues.add(resultSet.getInt(1));
			userValues.add(resultSet.getInt(2));
			userValues.add(resultSet.getInt(3));
			connection.close();

		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
		}
		return userValues;
	}

}
