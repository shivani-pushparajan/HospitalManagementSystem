package global.coda.hospitalmanagementsystem.db.services;

import java.util.List;

import global.coda.hospitalmanagementsystem.db.dao.dbimplementation.DoctorDatabaseDao;
import global.coda.hospitalmanagementsystem.db.userinterface.DoctorInterface;
import global.coda.hospitalmanagementsystem.model.Doctor;

public class DoctorServices implements DoctorInterface {

	@Override
	public boolean addDoctor(Doctor doctor) {
		return new DoctorDatabaseDao().addDoctor(doctor);

	}

	@Override
	public boolean modifyDoctor(int modifyChoice, int doctorId, String newValue) {

		return new DoctorDatabaseDao().updateDoctor(modifyChoice, doctorId, newValue);

	}

	@Override
	public List<Doctor> viewDoctor(int branchId) {
		List<Doctor> doctorList = new DoctorDatabaseDao().viewDoctor(branchId);
		return doctorList;
	}

	@Override
	public boolean deleteDoctor(int doctorId) {
		return new DoctorDatabaseDao().deleteDoctor(doctorId);

	}

}
