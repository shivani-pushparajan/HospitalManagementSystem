package global.coda.hospitalmanagementsystem.operations;

import static global.coda.hospitalmanagementsystem.constants.ApplicationConstants.HMS100E;
import static global.coda.hospitalmanagementsystem.constants.ApplicationConstants.HMS101E;
import static global.coda.hospitalmanagementsystem.constants.ApplicationConstants.HMS102E;
import static global.coda.hospitalmanagementsystem.constants.ApplicationConstants.HMS103E;
import static global.coda.hospitalmanagementsystem.constants.ApplicationConstants.HMS104E;
import static global.coda.hospitalmanagementsystem.constants.ApplicationConstants.HMS105E;
import static global.coda.hospitalmanagementsystem.constants.ApplicationConstants.HMS801D;
import static global.coda.hospitalmanagementsystem.constants.ApplicationConstants.MESSAGES;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import global.coda.hospitalmanagementsystem.exceptions.InvalidAgeInputException;
import global.coda.hospitalmanagementsystem.exceptions.InvalidPatientIdError;
import global.coda.hospitalmanagementsystem.exceptions.PatientObjectArrayEmptyException;
import global.coda.hospitalmanagementsystem.exceptions.PatientObjectArrayOverflowException;
import global.coda.hospitalmanagementsystem.exceptions.PatientRecordNotFoundException;
import global.coda.hospitalmanagementsystem.model.Patient;

//Class with functions to create,delete,update and read patient records

public class PatientOperations {
	private static final Logger LOGGER = LoggerFactory.getLogger(PatientOperations.class);
	private static final ResourceBundle MESSAGES_BUNDLE = ResourceBundle.getBundle(MESSAGES);

	/*
	 * Creates a patient record
	 *
	 * @param HashMap<Integer, Patient> patients
	 * 
	 * @param int countOfPatients
	 * 
	 * @param String nameOfPatient
	 * 
	 * @param int ageOfPatient
	 * 
	 * @param String bloodGroupOfPatient
	 * 
	 * @param List<String> patientAddress
	 * 
	 * @return int countOfPatients
	 * 
	 * @throws PatientObjectArrayOverflowException
	 * 
	 * @throws InvalidAgeInputException
	 */
	public int createPatientObject(HashMap<Integer, Patient> patients, int countOfPatients, String nameOfPatient,
			int ageOfPatient, String bloodGroupOfPatient, List<String> patientAddress)
			throws PatientObjectArrayOverflowException, InvalidAgeInputException {
		// Exception thrown when the map structure is full
		if (countOfPatients >= 100) {
			throw new PatientObjectArrayOverflowException(MESSAGES_BUNDLE.getString(HMS100E));
		}
		// Exception thrown when age is invalid
		if (ageOfPatient <= 0) {
			throw new InvalidAgeInputException(MESSAGES_BUNDLE.getString(HMS101E));
		}
		Patient temporaryPatientObject = new Patient();
		temporaryPatientObject.setPatientId(countOfPatients + 1);
		temporaryPatientObject.setNameOfPatient(nameOfPatient);
		temporaryPatientObject.setAgeOfPatient(ageOfPatient);
		temporaryPatientObject.setBloodGroup(bloodGroupOfPatient);
		temporaryPatientObject.setPatientAddress(patientAddress);
		// Adds the object to the array
		patients.put(temporaryPatientObject.getPatientId(), temporaryPatientObject);
		countOfPatients++;

		LOGGER.debug(MESSAGES_BUNDLE.getString(HMS801D));
		return countOfPatients;

	}

	// Delete a Patient Record

	/*
	 * Deletes a record using patient id
	 *
	 * @param HashMap<Integer, Patient> patients
	 * 
	 * @param int countOfPatients
	 * 
	 * @param int patientId
	 * 
	 * @return int countOfPatients
	 * 
	 * @throws PatientObjectArrayEmptyException
	 * 
	 * @throws InvalidPatientIdError
	 * 
	 * @throws PatientRecordNotFoundException
	 */
	public int deletePatientObject(HashMap<Integer, Patient> patients, int countOfPatients, int patientId)
			throws PatientObjectArrayEmptyException, InvalidPatientIdError, PatientRecordNotFoundException {
		// Exception thrown when array is empty
		if (countOfPatients <= 0) {
			throw new PatientObjectArrayEmptyException(MESSAGES_BUNDLE.getString(HMS102E));
		}

		// Exception when a invalid patient id is entered
		if (patients.containsKey(patientId) == false) {
			throw new PatientRecordNotFoundException(MESSAGES_BUNDLE.getString(HMS104E));
		}
		// Deletes a patient record
		patients.remove(patientId);
		countOfPatients--;
		LOGGER.debug(MESSAGES_BUNDLE.getString(HMS801D));
		return countOfPatients;
	}

	// Update a Patient Record's Name

	/*
	 * Updates patient name using patient id
	 * 
	 * @param HashMap<Integer, Patient> patients
	 * 
	 * @param int countOfPatients
	 * 
	 * @param int patientId
	 * 
	 * @param String patientName
	 * 
	 * @
	 */
	public void updatePatientObjectName(HashMap<Integer, Patient> patients, int countOfPatients, int patientId,
			String patientName) throws InvalidPatientIdError, PatientRecordNotFoundException {

		// Exception when a invalid patient id is entered
		if (patients.containsKey(patientId) == false) {
			throw new PatientRecordNotFoundException(MESSAGES_BUNDLE.getString(HMS104E));
		}
		// Updates name of patient
		Patient patient = patients.get(patientId);
		patient.setNameOfPatient(patientName);
		LOGGER.debug(MESSAGES_BUNDLE.getString(HMS801D));

	}

	// Update a Patient Record's Age
	public void updatePatientObjectAge(HashMap<Integer, Patient> patients, int countOfPatients, int patientId,
			int patientAge) throws InvalidPatientIdError, InvalidAgeInputException, PatientRecordNotFoundException {

		// Exception thrown when age is invalid
		if (patientAge <= 0) {
			throw new InvalidAgeInputException(MESSAGES_BUNDLE.getString(HMS101E));
		}
		// Exception when a invalid patient id is entered
		if (patients.containsKey(patientId) == false) {
			throw new PatientRecordNotFoundException(MESSAGES_BUNDLE.getString(HMS104E));
		}
		// Updates age of patient
		Patient patient = patients.get(patientId);
		patient.setAgeOfPatient(patientAge);
		LOGGER.debug(MESSAGES_BUNDLE.getString(HMS801D));

	}

	// Update a Patient Record's Blood Group
	public void updatePatientObjectBloodGroup(HashMap<Integer, Patient> patients, int countOfPatients, int patientId,
			String patientBloodGroup) throws InvalidPatientIdError, PatientRecordNotFoundException {

		// Exception when a invalid patient id is entered
		if (patients.containsKey(patientId) == false) {
			throw new PatientRecordNotFoundException(MESSAGES_BUNDLE.getString(HMS104E));
		}
		// Updates blood group of patient
		Patient patient = patients.get(patientId);
		patient.setBloodGroup(patientBloodGroup);
		LOGGER.debug(MESSAGES_BUNDLE.getString(HMS801D));
	}

	// Update a Patient Record's Address
	public void updatePatientObjectAddress(HashMap<Integer, Patient> patients, int countOfPatients, int patientId,
			List<String> patientAddress) throws InvalidPatientIdError, PatientRecordNotFoundException {

		// Exception when a invalid patient id is entered
		if (patients.containsKey(patientId) == false) {
			throw new PatientRecordNotFoundException(MESSAGES_BUNDLE.getString(HMS104E));
		}
		// Updates address of patient
		Patient patient = patients.get(patientId);
		patient.setPatientAddress(patientAddress);
		LOGGER.debug(MESSAGES_BUNDLE.getString(HMS801D));

	}

	// Read a specific Patient Records using patient ID
	public void readSinglePatientObject(HashMap<Integer, Patient> patients, int countOfPatients, int patientId)
			throws PatientObjectArrayEmptyException, PatientObjectArrayOverflowException,
			PatientRecordNotFoundException {
		// Exception thrown when array is empty
		if (countOfPatients <= 0) {
			throw new PatientObjectArrayEmptyException(MESSAGES_BUNDLE.getString(HMS102E));
		}
		// Exception thrown when the array limit exceeds capacity
		if (countOfPatients > 100) {
			throw new PatientObjectArrayOverflowException(MESSAGES_BUNDLE.getString(HMS105E));
		}
		// Exception when a invalid patient id is entered
		if (patients.containsKey(patientId) == false) {
			throw new PatientRecordNotFoundException(MESSAGES_BUNDLE.getString(HMS103E));
		}
		// Print a patient record using patient id

		LOGGER.debug(patients.get(patientId).toString());
		LOGGER.debug(MESSAGES_BUNDLE.getString(HMS801D));
	}

	// Read consecutive Patient Records
	public void readAllPatientObject(HashMap<Integer, Patient> patients, int countOfPatients)
			throws PatientObjectArrayEmptyException, PatientObjectArrayOverflowException {
		// Exception thrown when array is empty
		if (countOfPatients <= 0) {
			throw new PatientObjectArrayEmptyException(MESSAGES_BUNDLE.getString(HMS102E));
		}
		// Exception thrown when the array limit exceeds capacity
		if (countOfPatients > 100) {
			throw new PatientObjectArrayOverflowException(MESSAGES_BUNDLE.getString(HMS105E));
		}

		// Print all patient records to the user
		for (Map.Entry<Integer, Patient> patient : patients.entrySet()) {
			LOGGER.debug("\n" + patient.getKey() + " " + patient.getValue().toString());
		}
		LOGGER.debug(MESSAGES_BUNDLE.getString(HMS801D));

	}

}
