package global.coda.hospitalmanagementsystem.db.dao.dbinterface;

import java.util.List;

public interface AuthenticationDao {

	public List<Integer> authenticateUserDb(String username, String password);
}
