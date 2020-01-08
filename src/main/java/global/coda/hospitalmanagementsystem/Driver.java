package global.coda.hospitalmanagementsystem;

import static global.coda.hospitalmanagementsystem.constants.Address.ADDRESS_LINE_1;
import static global.coda.hospitalmanagementsystem.constants.Address.ADDRESS_LINE_2;
import static global.coda.hospitalmanagementsystem.constants.Address.ADDRESS_LINE_3;
import static global.coda.hospitalmanagementsystem.constants.ApplicationConstants.HMS000D;
import static global.coda.hospitalmanagementsystem.constants.ApplicationConstants.HMS001D;
import static global.coda.hospitalmanagementsystem.constants.ApplicationConstants.HMS002D;
import static global.coda.hospitalmanagementsystem.constants.ApplicationConstants.HMS003D;
import static global.coda.hospitalmanagementsystem.constants.ApplicationConstants.HMS004D;
import static global.coda.hospitalmanagementsystem.constants.ApplicationConstants.HMS005D;
import static global.coda.hospitalmanagementsystem.constants.ApplicationConstants.HMS100D;
import static global.coda.hospitalmanagementsystem.constants.ApplicationConstants.HMS101D;
import static global.coda.hospitalmanagementsystem.constants.ApplicationConstants.HMS102D;
import static global.coda.hospitalmanagementsystem.constants.ApplicationConstants.HMS103E;
import static global.coda.hospitalmanagementsystem.constants.ApplicationConstants.HMS106E;
import static global.coda.hospitalmanagementsystem.constants.ApplicationConstants.HMS200D;
import static global.coda.hospitalmanagementsystem.constants.ApplicationConstants.HMS201D;
import static global.coda.hospitalmanagementsystem.constants.ApplicationConstants.HMS202D;
import static global.coda.hospitalmanagementsystem.constants.ApplicationConstants.HMS203D;
import static global.coda.hospitalmanagementsystem.constants.ApplicationConstants.HMS204D;
import static global.coda.hospitalmanagementsystem.constants.ApplicationConstants.HMS205D;
import static global.coda.hospitalmanagementsystem.constants.ApplicationConstants.HMS300D;
import static global.coda.hospitalmanagementsystem.constants.ApplicationConstants.HMS800D;
import static global.coda.hospitalmanagementsystem.constants.ApplicationConstants.MESSAGES;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import global.coda.hospitalmanagementsystem.constants.DriverEnums;
import global.coda.hospitalmanagementsystem.constants.FileEnums;
import global.coda.hospitalmanagementsystem.dao.factory.PatientFileFactory;
import global.coda.hospitalmanagementsystem.dao.fileinterface.PatientFileDao;
import global.coda.hospitalmanagementsystem.exceptions.InvalidAgeInputException;
import global.coda.hospitalmanagementsystem.exceptions.InvalidPatientIdError;
import global.coda.hospitalmanagementsystem.exceptions.InvalidServiceChoiceException;
import global.coda.hospitalmanagementsystem.exceptions.PatientObjectArrayEmptyException;
import global.coda.hospitalmanagementsystem.exceptions.PatientObjectArrayOverflowException;
import global.coda.hospitalmanagementsystem.exceptions.PatientRecordNotFoundException;
import global.coda.hospitalmanagementsystem.model.Patient;
import global.coda.hospitalmanagementsystem.operations.PatientOperations;

public class Driver {
	public static HashMap<Integer, Patient> patients = new HashMap<Integer, Patient>();
	public int countOfPatients = 0;
	private static final Logger LOGGER = LoggerFactory.getLogger(Driver.class);
	private static final ResourceBundle MESSAGES_BUNDLE = ResourceBundle.getBundle(MESSAGES);
	PatientFileDao patientFileDao = null;
	PatientOperations patientOperations = null;

	/**
	 * @input : Case value to load patient data from external files
	 * 
	 *        case 1: CSV File
	 * 
	 *        case 2: XML File
	 * 
	 * @input : Case value to perform a patient service
	 * 
	 *        case 1: Create a patient record
	 * 
	 *        case 2: Read patient record
	 * 
	 *        case 3: Update patient record
	 * 
	 *        case 4: Delete patient record
	 * 
	 */

	public void beginApplication() {
		Scanner sc = new Scanner(System.in);
		LOGGER.debug("Enter the data source of patient records");
		LOGGER.debug("1.CSV FILE");
		LOGGER.debug("2.XML FILE");
		int file_choice = Integer.parseInt(sc.nextLine());
		FileEnums fileChoiceEnum = FileEnums.valueOf(file_choice);
		PatientFileFactory patientFileFactory = new PatientFileFactory();
		try {
			if (file_choice > 2 || file_choice < 1) {
				sc.close();
				throw new InvalidServiceChoiceException(MESSAGES_BUNDLE.getString(HMS106E));
			}
		} catch (InvalidServiceChoiceException exception) {
			LOGGER.error(MESSAGES_BUNDLE.getString(HMS106E));
			System.exit(0);

		}
		switch (fileChoiceEnum) {
		case CSV: {
			patientFileDao = patientFileFactory.getPatientDao("CSVFILETYPE");
		}
			break;
		case XML: {
			patientFileDao = patientFileFactory.getPatientDao("XMLFILETYPE");
		}
		}

		countOfPatients = patientFileDao.readPatientFile(patients, countOfPatients);

		int flag = 0;
		patientOperations = new PatientOperations();
		while (flag == 0) {
			LOGGER.debug(MESSAGES_BUNDLE.getString(HMS000D));
			LOGGER.debug(MESSAGES_BUNDLE.getString(HMS001D));
			LOGGER.debug(MESSAGES_BUNDLE.getString(HMS002D));
			LOGGER.debug(MESSAGES_BUNDLE.getString(HMS003D));
			LOGGER.debug(MESSAGES_BUNDLE.getString(HMS004D));
			LOGGER.debug(MESSAGES_BUNDLE.getString(HMS005D));
			int userChoice = Integer.parseInt(sc.nextLine());
			try {
				if (userChoice > 5 || userChoice < 1) {
					throw new InvalidServiceChoiceException(MESSAGES_BUNDLE.getString(HMS106E));
				}
			} catch (InvalidServiceChoiceException exception) {
				LOGGER.error(MESSAGES_BUNDLE.getString(HMS106E));
				continue;

			}
			LOGGER.debug(MESSAGES_BUNDLE.getString(HMS800D), userChoice);
			DriverEnums userChoiceEnum = DriverEnums.valueOf(userChoice);
			switch (userChoiceEnum) {
			case CREATE: {
				// Create a patient record
				/*
				 * @input patientName String
				 * 
				 * @input patientAge Integer
				 * 
				 * @input patientBloodGroup String
				 * 
				 * @input patientAddress List<String>
				 * 
				 * @returns patientCount Integer
				 */
				try {

					LOGGER.debug(MESSAGES_BUNDLE.getString(HMS100D));
					String nameOfPatient = sc.nextLine();
					LOGGER.debug(MESSAGES_BUNDLE.getString(HMS800D), nameOfPatient);
					LOGGER.debug(MESSAGES_BUNDLE.getString(HMS101D));
					int ageOfPatient = Integer.parseInt(sc.nextLine());
					LOGGER.debug(MESSAGES_BUNDLE.getString(HMS800D), ageOfPatient);
					LOGGER.debug(MESSAGES_BUNDLE.getString(HMS102D));
					String bloodGroupOfPatient = sc.nextLine();
					LOGGER.debug(MESSAGES_BUNDLE.getString(HMS800D), bloodGroupOfPatient);
					List<String> patientAddress = new LinkedList<String>();
					LOGGER.debug(MESSAGES_BUNDLE.getString(ADDRESS_LINE_1.toString()));
					patientAddress.add(sc.nextLine());
					LOGGER.debug(MESSAGES_BUNDLE.getString(ADDRESS_LINE_2.toString()));
					patientAddress.add(sc.nextLine());
					LOGGER.debug(MESSAGES_BUNDLE.getString(ADDRESS_LINE_3.toString()));
					patientAddress.add(sc.nextLine());
					LOGGER.debug(MESSAGES_BUNDLE.getString(HMS800D), patientAddress);

					countOfPatients = patientOperations.createPatientObject(patients, countOfPatients, nameOfPatient,
							ageOfPatient, bloodGroupOfPatient, patientAddress);
					patientFileDao.writePatientFile(patients);

				} catch (PatientObjectArrayOverflowException exception) {
					LOGGER.error(exception.getMessage());
				} catch (InvalidAgeInputException exception) {
					LOGGER.error(exception.getMessage());
				} catch (NumberFormatException exception) {
					LOGGER.error(exception.getMessage());
				}

			}
				break;
			case READ: {
				// Read patient record

				/*
				 * @case Type of read to be performed
				 * 
				 * case 1 : Read single patient record
				 * 
				 * case 2 : Read all patient records
				 */
				LOGGER.debug(MESSAGES_BUNDLE.getString(HMS300D));
				int read_choice = Integer.parseInt(sc.nextLine());
				LOGGER.debug(MESSAGES_BUNDLE.getString(HMS800D), read_choice);
				switch (read_choice) {
				case 1: {
					// Read single Patient Record

					/*
					 * @input patientId Integer
					 * 
					 */
					LOGGER.debug(MESSAGES_BUNDLE.getString(HMS200D));
					int patientId = Integer.parseInt(sc.nextLine());

					LOGGER.debug(MESSAGES_BUNDLE.getString(HMS800D), patientId);
					try {
						patientOperations.readSinglePatientObject(patients, countOfPatients, patientId);
					} catch (PatientObjectArrayEmptyException exception) {
						LOGGER.error(exception.getMessage());
					} catch (PatientObjectArrayOverflowException exception) {
						LOGGER.error(exception.getMessage());
					} catch (PatientRecordNotFoundException exception) {
						LOGGER.error(exception.getMessage());
					}
				}
					break;
				// Read All Patient Records
				case 2:
					try {
						patientOperations.readAllPatientObject(patients, countOfPatients);
					} catch (PatientObjectArrayEmptyException exception) {

						LOGGER.error(exception.getMessage());
					} catch (PatientObjectArrayOverflowException exception) {

						LOGGER.error(exception.getMessage());
					}
				}

			}
				break;
			case UPDATE: {
				// Update Patient Record
				/*
				 * @input patientId
				 * 
				 * @case choose attribute that has to be updated
				 * 
				 * case 1: patientName
				 * 
				 * case 2 : patientAge
				 * 
				 * case 3: patientBloodGroup
				 * 
				 * case 4: patientAddress
				 */
				LOGGER.debug(MESSAGES_BUNDLE.getString(HMS200D));
				int patientId = Integer.parseInt(sc.nextLine());
				try {
					LOGGER.debug(MESSAGES_BUNDLE.getString(HMS800D), patientId);

					// Exception when a invalid patient id is entered
					if (patients.get(patientId) == null) {
						throw new PatientRecordNotFoundException(MESSAGES_BUNDLE.getString(HMS103E));
					}
				} catch (PatientRecordNotFoundException exception) {
					LOGGER.error(MESSAGES_BUNDLE.getString(HMS103E));

				}
				LOGGER.debug(MESSAGES_BUNDLE.getString(HMS201D));
				int update_choice = Integer.parseInt(sc.nextLine());
				LOGGER.debug(MESSAGES_BUNDLE.getString(HMS800D), update_choice);
				switch (update_choice) {
				// Update Patient Name
				/*
				 * @input patientName String
				 */
				case 1: {
					LOGGER.debug(MESSAGES_BUNDLE.getString(HMS202D));
					String patientName = sc.nextLine();
					LOGGER.debug(MESSAGES_BUNDLE.getString(HMS800D), patientName);
					try {
						patientOperations.updatePatientObjectName(patients, countOfPatients, patientId, patientName);
						patientFileDao.writePatientFile(patients);

					} catch (InvalidPatientIdError exception) {

						LOGGER.error(exception.getMessage());
					} catch (PatientRecordNotFoundException exception) {

						LOGGER.error(exception.getMessage());
					}
				}
					break;
				// Update Patient Age
				/*
				 * @input patientAge Integer
				 */
				case 2: {

					LOGGER.debug(MESSAGES_BUNDLE.getString(HMS203D));
					int patientAge = Integer.parseInt(sc.nextLine());
					LOGGER.debug(MESSAGES_BUNDLE.getString(HMS800D), patientAge);
					try {
						patientOperations.updatePatientObjectAge(patients, countOfPatients, patientId, patientAge);
						patientFileDao.writePatientFile(patients);

					} catch (InvalidPatientIdError exception) {

						LOGGER.error(exception.getMessage());
					} catch (InvalidAgeInputException exception) {
						LOGGER.error(exception.getMessage());
					} catch (PatientRecordNotFoundException exception) {
						LOGGER.error(exception.getMessage());
					}
				}
					break;
				// Update Patient Blood Group
				/*
				 * @input patientBloodGroup String
				 */
				case 3: {
					LOGGER.debug(MESSAGES_BUNDLE.getString(HMS204D));
					String patientBloodGroup = sc.nextLine();
					LOGGER.debug(MESSAGES_BUNDLE.getString(HMS800D), patientBloodGroup);
					try {
						patientOperations.updatePatientObjectBloodGroup(patients, countOfPatients, patientId,
								patientBloodGroup);
						patientFileDao.writePatientFile(patients);

					} catch (InvalidPatientIdError exception) {
						LOGGER.error(exception.getMessage());
					} catch (PatientRecordNotFoundException exception) {
						LOGGER.error(exception.getMessage());
					}
				}
					break;
				// Update Patient Address
				/*
				 * @input patientAddress List<String>
				 */
				case 4: {
					LOGGER.debug(MESSAGES_BUNDLE.getString(HMS205D));
					List<String> patientAddress = new LinkedList<String>();
					LOGGER.debug(MESSAGES_BUNDLE.getString(ADDRESS_LINE_1.toString()));
					patientAddress.add(sc.nextLine());
					LOGGER.debug(MESSAGES_BUNDLE.getString(ADDRESS_LINE_1.toString()));
					patientAddress.add(sc.nextLine());
					LOGGER.debug(MESSAGES_BUNDLE.getString(ADDRESS_LINE_1.toString()));
					patientAddress.add(sc.nextLine());
					LOGGER.debug(MESSAGES_BUNDLE.getString(HMS800D), patientAddress);
					try {
						patientOperations.updatePatientObjectAddress(patients, countOfPatients, patientId,
								patientAddress);
						patientFileDao.writePatientFile(patients);

					} catch (PatientRecordNotFoundException exception) {
						LOGGER.error(exception.getMessage());
					} catch (InvalidPatientIdError exception) {
						LOGGER.error(exception.getMessage());
					}
				}

				}

			}

				break;
			case DELETE: {
				// Delete Patient Record
				/*
				 * @input patientId Integer
				 * 
				 * @returns patientCount Integer
				 */
				LOGGER.debug(MESSAGES_BUNDLE.getString(HMS200D));
				int patientId = Integer.parseInt(sc.nextLine());
				LOGGER.debug(MESSAGES_BUNDLE.getString(HMS800D), patientId);
				try {
					countOfPatients = patientOperations.deletePatientObject(patients, countOfPatients, patientId);
					patientFileDao.writePatientFile(patients);

				} catch (PatientObjectArrayEmptyException exception) {

					LOGGER.error(exception.getMessage());
				} catch (InvalidPatientIdError exception) {

					LOGGER.error(exception.getMessage());
				} catch (PatientRecordNotFoundException exception) {
					LOGGER.error(exception.getMessage());
				}
			}

				break;
			case EXIT: {
				LOGGER.debug("The console exits");
				patientFileDao.writePatientFile(patients);
				flag++;
				sc.close();

			}
				break;
			}
		}

	}
}
