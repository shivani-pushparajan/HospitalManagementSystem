package global.coda.hospitalmanagementsystem.db.services;

import java.util.List;

import global.coda.hospitalmanagementsystem.db.dao.dbimplementation.PatientDatabaseDao;
import global.coda.hospitalmanagementsystem.db.userinterface.PatientInterface;
import global.coda.hospitalmanagementsystem.model.Patient;

public class PatientServices implements PatientInterface {

	@Override
	public boolean addPatient(Patient patient) {

		return new PatientDatabaseDao().addPatient(patient);
	}

	@Override
	public boolean modifyPatient(int modifyChoice, int patientId, String newValue) {

		return new PatientDatabaseDao().updatePatient(modifyChoice, patientId, newValue);

	}

	@Override
	public List<Patient> viewPatient(int branchId) {
		List<Patient> patientList = new PatientDatabaseDao().viewPatient(branchId);
		return patientList;
	}

	@Override
	public boolean deletePatient(int patientId) {
		return new PatientDatabaseDao().deletePatient(patientId);

	}

}
