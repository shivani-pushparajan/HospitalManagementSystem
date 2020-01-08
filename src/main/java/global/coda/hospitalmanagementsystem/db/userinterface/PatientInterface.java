package global.coda.hospitalmanagementsystem.db.userinterface;

import java.util.List;

import global.coda.hospitalmanagementsystem.model.Patient;

public interface PatientInterface {
	public boolean addPatient(Patient patient);

	// public boolean patientEntry(int doctorId, int patientId, int branchId);

	public boolean modifyPatient(int modifyChoice, int patientId, String newValue);

	public List<Patient> viewPatient(int branchId);

	public boolean deletePatient(int patientId);

}
