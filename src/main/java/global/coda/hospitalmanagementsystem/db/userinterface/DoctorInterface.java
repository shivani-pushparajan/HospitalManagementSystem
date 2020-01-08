package global.coda.hospitalmanagementsystem.db.userinterface;

import java.util.List;

import global.coda.hospitalmanagementsystem.model.Doctor;

public interface DoctorInterface {

	public boolean addDoctor(Doctor doctor);

	public boolean modifyDoctor(int modifyChoice, int doctorId, String newValue);

	public List<Doctor> viewDoctor(int branchId);

	public boolean deleteDoctor(int doctorId);
}
