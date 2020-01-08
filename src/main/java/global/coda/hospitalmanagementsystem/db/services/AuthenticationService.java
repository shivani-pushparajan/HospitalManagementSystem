package global.coda.hospitalmanagementsystem.db.services;

import java.util.List;

import global.coda.hospitalmanagementsystem.db.dao.dbimplementation.AuthenticationDaoImpl;

public class AuthenticationService {

	public List<Integer> authenticateUser(String username, String password) {
		return new AuthenticationDaoImpl().authenticateUserDb(username, password);
	}

}
