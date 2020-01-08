package global.coda.hospitalmanagementsystem.db.dao.dbimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import global.coda.hospitalmanagementsystem.model.Branch;

public class BranchDatabaseDao extends DatabaseConnection {
	Connection connection = getDatabaseConnection();
	private static final Logger LOGGER = LoggerFactory.getLogger(BranchDatabaseDao.class);

	public boolean addBranchDb(Branch branch) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("insert into t_branch(branch_name,branch_city) values(?,?)");
			statement.setString(1, branch.getBranchName());
			statement.setString(2, branch.getBranchCity());
			int rowsAffected = statement.executeUpdate();
			if (rowsAffected == 1) {
				return true;
			}

		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
		}
		return false;
	}

	public boolean modifyBranchDb(int modifyChoice, int branchId, String newValue) {
		try {
			switch (modifyChoice) {
			case 1: {
				PreparedStatement statement = connection
						.prepareStatement("update branch set branch_name=? where pk_branch_id=?");
				statement.setString(1, newValue);
				statement.setInt(2, branchId);
				int rowsAffected = statement.executeUpdate();
				if (rowsAffected == 1) {
					return true;
				}

			}
				break;
			case 2: {
				PreparedStatement statement = connection
						.prepareStatement("update branch set branch_city=? where pk_branch_id=?");
				statement.setString(1, newValue);
				statement.setInt(2, branchId);
				int rowsAffected = statement.executeUpdate();
				if (rowsAffected == 1) {
					return true;
				}

			}
			}
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
		}

		return false;
	}

	public List<Branch> viewBranchDb() {
		List<Branch> branchList = new LinkedList<Branch>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select pk_branch_id,branch_name,branch_city from t_branch");
			if (resultSet.next() == false) {
				return null;
			}
			while (resultSet.next()) {
				Branch branch = new Branch();
				branch.setBranchId(resultSet.getInt(1));
				branch.setBranchName(resultSet.getString(2));
				branch.setBranchCity(resultSet.getString(3));
				branchList.add(branch);
			}
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
		}
		return branchList;
	}

	public boolean deleteBranchDb(int branchId) {
		try {
			PreparedStatement statement = connection
					.prepareStatement("update branch set is_active=0 where pk_branch_id=? and is_active=1");
			statement.setInt(1, branchId);
			int rowsAffected = statement.executeUpdate();
			if (rowsAffected == 1) {
				return true;
			}
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
		}
		return false;
	}
}
