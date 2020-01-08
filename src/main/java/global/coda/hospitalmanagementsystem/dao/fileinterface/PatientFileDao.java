package global.coda.hospitalmanagementsystem.dao.fileinterface;

import java.util.HashMap;

import global.coda.hospitalmanagementsystem.model.Patient;

public interface PatientFileDao {
	abstract public int readPatientFile(HashMap<Integer, Patient> patients, int patientCount);

	abstract public void writePatientFile(HashMap<Integer, Patient> patientMap);

}
