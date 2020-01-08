package global.coda.HospitalManagementSystem.operations;

import static global.coda.HospitalManagementSystem.constants.TestConstants.INPUT_ADDRESS_LINE_1;
import static global.coda.HospitalManagementSystem.constants.TestConstants.INPUT_ADDRESS_LINE_2;
import static global.coda.HospitalManagementSystem.constants.TestConstants.INPUT_ADDRESS_LINE_3;
import static global.coda.HospitalManagementSystem.constants.TestConstants.INPUT_COUNT_OF_PATIENTS_INVALID;
import static global.coda.HospitalManagementSystem.constants.TestConstants.INPUT_COUNT_OF_PATIENTS_VALID;
import static global.coda.HospitalManagementSystem.constants.TestConstants.INPUT_INT_AGE;
import static global.coda.HospitalManagementSystem.constants.TestConstants.INPUT_STRING_BLOOD_GROUP;
import static global.coda.HospitalManagementSystem.constants.TestConstants.INPUT_STRING_NAME;
import static global.coda.HospitalManagementSystem.constants.TestConstants.TEST_VARIABLES;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import global.coda.hospitalmanagementsystem.exceptions.InvalidAgeInputException;
import global.coda.hospitalmanagementsystem.exceptions.InvalidPatientIdError;
import global.coda.hospitalmanagementsystem.exceptions.PatientObjectArrayEmptyException;
import global.coda.hospitalmanagementsystem.exceptions.PatientObjectArrayOverflowException;
import global.coda.hospitalmanagementsystem.exceptions.PatientRecordNotFoundException;
import global.coda.hospitalmanagementsystem.model.Patient;
import global.coda.hospitalmanagementsystem.operations.PatientOperations;

public class PatientServicesTest {

	private PatientOperations patientOperations;
	HashMap<Integer, Patient> inputPatientMap;
	private static final ResourceBundle TEST_BUNDLE = ResourceBundle.getBundle(TEST_VARIABLES);

	@BeforeMethod
	public void setUp() {

		inputPatientMap = new HashMap<Integer, Patient>();
		patientOperations = new PatientOperations();

	}

	@Test
	public void createPatientObjectTestValid() throws PatientObjectArrayOverflowException, InvalidAgeInputException {
		String inputName = TEST_BUNDLE.getString(INPUT_STRING_NAME);
		int inputAge = Integer.parseInt(TEST_BUNDLE.getString(INPUT_INT_AGE));
		String inputBloodGroup = TEST_BUNDLE.getString(INPUT_STRING_BLOOD_GROUP);
		List<String> inputAddress = new LinkedList<String>();
		inputAddress.add(TEST_BUNDLE.getString(INPUT_ADDRESS_LINE_1));
		inputAddress.add(TEST_BUNDLE.getString(INPUT_ADDRESS_LINE_2));
		inputAddress.add(TEST_BUNDLE.getString(INPUT_ADDRESS_LINE_3));
		int countOfPatients = patientOperations.createPatientObject(inputPatientMap,
				Integer.parseInt(TEST_BUNDLE.getString(INPUT_COUNT_OF_PATIENTS_VALID)), inputName, inputAge,
				inputBloodGroup, inputAddress);

		assertTrue(countOfPatients == Integer.parseInt(TEST_BUNDLE.getString(INPUT_COUNT_OF_PATIENTS_VALID)) + 1);
	}

	@Test
	public void createPatientObjectTestInValid() throws PatientObjectArrayOverflowException, InvalidAgeInputException {
		String inputName = TEST_BUNDLE.getString(INPUT_STRING_NAME);
		int inputAge = Integer.parseInt(TEST_BUNDLE.getString(INPUT_INT_AGE));
		String inputBloodGroup = TEST_BUNDLE.getString(INPUT_STRING_BLOOD_GROUP);
		List<String> inputAddress = new LinkedList<String>();
		inputAddress.add(TEST_BUNDLE.getString(INPUT_ADDRESS_LINE_1));
		inputAddress.add(TEST_BUNDLE.getString(INPUT_ADDRESS_LINE_2));
		inputAddress.add(TEST_BUNDLE.getString(INPUT_ADDRESS_LINE_3));
		try {
			patientOperations.createPatientObject(inputPatientMap,
					Integer.parseInt(TEST_BUNDLE.getString(INPUT_COUNT_OF_PATIENTS_INVALID)), inputName, inputAge,
					inputBloodGroup, inputAddress);
		} catch (Exception exception) {

			String exceptionName = exception.getClass().getSimpleName();
			assertEquals(exceptionName, "PatientObjectArrayOverflowException");
		}
	}

	@Test
	public void deletePatientObjectTestValid() throws PatientObjectArrayEmptyException, InvalidPatientIdError,
			PatientObjectArrayOverflowException, InvalidAgeInputException, PatientRecordNotFoundException {
		String inputName = TEST_BUNDLE.getString(INPUT_STRING_NAME);
		int inputAge = Integer.parseInt(TEST_BUNDLE.getString(INPUT_INT_AGE));
		String inputBloodGroup = TEST_BUNDLE.getString(INPUT_STRING_BLOOD_GROUP);
		List<String> inputAddress = new LinkedList<String>();
		inputAddress.add(TEST_BUNDLE.getString(INPUT_ADDRESS_LINE_1));
		inputAddress.add(TEST_BUNDLE.getString(INPUT_ADDRESS_LINE_2));
		inputAddress.add(TEST_BUNDLE.getString(INPUT_ADDRESS_LINE_3));
		int countOfPatients = patientOperations.createPatientObject(inputPatientMap, 0, inputName, inputAge,
				inputBloodGroup, inputAddress);
		int patientCount = patientOperations.deletePatientObject(inputPatientMap, countOfPatients, 1);

		assertTrue(patientCount == countOfPatients - 1);
	}

	@Test
	public void readPatientObjectTestInvalidEmpty() {

		try {
			patientOperations.readAllPatientObject(inputPatientMap, -1);
		} catch (Exception exception) {

			String exceptionName = exception.getClass().getSimpleName();
			assertEquals(exceptionName, "PatientObjectArrayEmptyException");
		}

	}

	@Test
	public void readPatientObjectTestInvalidOverflow() {

		try {
			patientOperations.readAllPatientObject(inputPatientMap, 101);
		} catch (Exception exception) {

			String exceptionName = exception.getClass().getSimpleName();
			assertEquals(exceptionName, "PatientObjectArrayOverflowException");
		}

	}

	@Test
	public void updatePatientObjectTestValid() throws PatientObjectArrayOverflowException, InvalidAgeInputException,
			InvalidPatientIdError, PatientRecordNotFoundException {
		String inputName = TEST_BUNDLE.getString(INPUT_STRING_NAME);
		int inputAge = Integer.parseInt(TEST_BUNDLE.getString(INPUT_INT_AGE));
		String inputBloodGroup = TEST_BUNDLE.getString(INPUT_STRING_BLOOD_GROUP);
		List<String> inputAddress = new LinkedList<String>();
		inputAddress.add(TEST_BUNDLE.getString(INPUT_ADDRESS_LINE_1));
		inputAddress.add(TEST_BUNDLE.getString(INPUT_ADDRESS_LINE_2));
		inputAddress.add(TEST_BUNDLE.getString(INPUT_ADDRESS_LINE_3));
		int countOfPatients = patientOperations.createPatientObject(inputPatientMap, 0, inputName, inputAge,
				inputBloodGroup, inputAddress);
		patientOperations.updatePatientObjectAge(inputPatientMap, countOfPatients, countOfPatients, 48);
		assertEquals(inputPatientMap.get(countOfPatients).getAgeOfPatient(), 48);

	}

}
