package global.coda.hospitalmanagementsystem.db.userinterface;

import java.util.List;

import global.coda.hospitalmanagementsystem.model.Branch;

public interface BranchInterface {
	public boolean addBranch(Branch branch);

	public boolean modifyBranch(int modifyChoice, int branchId, String newValue);

	public List<Branch> viewBranch();

	public boolean deleteBranch(int branchId);
}
