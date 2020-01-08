package global.coda.hospitalmanagementsystem.db.dao.dbimplementation;

import static global.coda.hospitalmanagementsystem.db.constants.DatabaseConnectionConstants.DRIVER;
import static global.coda.hospitalmanagementsystem.db.constants.DatabaseConnectionConstants.PASSWORD;
import static global.coda.hospitalmanagementsystem.db.constants.DatabaseConnectionConstants.URL;
import static global.coda.hospitalmanagementsystem.db.constants.DatabaseConnectionConstants.USERNAME;

import java.sql.Connection;
import java.sql.DriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseConnection {
	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConnection.class);
	Connection connection = null;

	public Connection getDatabaseConnection() {
		if (connection == null) {

			try {
				Class.forName(DRIVER);
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			} catch (Exception exception) {
				LOGGER.error(exception.getMessage());
			}
		}
		return connection;
	}

}
