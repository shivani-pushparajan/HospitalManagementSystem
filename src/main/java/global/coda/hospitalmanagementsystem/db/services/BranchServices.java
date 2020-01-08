package global.coda.hospitalmanagementsystem.db.services;

import java.util.List;

import global.coda.hospitalmanagementsystem.db.dao.dbimplementation.BranchDatabaseDao;
import global.coda.hospitalmanagementsystem.db.userinterface.BranchInterface;
import global.coda.hospitalmanagementsystem.model.Branch;

public class BranchServices implements BranchInterface {

	@Override
	public boolean addBranch(Branch branch) {
		return new BranchDatabaseDao().addBranchDb(branch);
	}

	@Override
	public boolean modifyBranch(int modifyChoice, int branchId, String newValue) {
		return new BranchDatabaseDao().modifyBranchDb(modifyChoice, branchId, newValue);
	}

	@Override
	public List<Branch> viewBranch() {
		return new BranchDatabaseDao().viewBranchDb();
	}

	@Override
	public boolean deleteBranch(int branchId) {
		return new BranchDatabaseDao().deleteBranchDb(branchId);
	}

}
