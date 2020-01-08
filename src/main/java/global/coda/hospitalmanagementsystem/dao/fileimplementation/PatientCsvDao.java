package global.coda.hospitalmanagementsystem.dao.fileimplementation;

import static global.coda.hospitalmanagementsystem.constants.ApplicationConstants.PATIENT_CSV_FILE_PATH;
import static global.coda.hospitalmanagementsystem.constants.ApplicationConstants.PATIENT_FILE_PATH;
import static global.coda.hospitalmanagementsystem.constants.ApplicationConstants.SEPERATOR;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import global.coda.hospitalmanagementsystem.dao.exceptions.DirectoryPathProvidedException;
import global.coda.hospitalmanagementsystem.dao.fileinterface.PatientFileDao;
import global.coda.hospitalmanagementsystem.model.Patient;

public class PatientCsvDao implements PatientFileDao {
	private static final ResourceBundle PATH_BUNDLE = ResourceBundle.getBundle(PATIENT_FILE_PATH);

	@Override
	public int readPatientFile(HashMap<Integer, Patient> patients, int patientCount) {
		String csvFilePath = PATH_BUNDLE.getString(PATIENT_CSV_FILE_PATH);
		BufferedReader csvReader = null;
		try {

			csvReader = new BufferedReader(new FileReader(csvFilePath));
			String row = null;
			// Reading the CSV file line by line

			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(SEPERATOR);
				Patient patient = new Patient();

				patient.setPatientId(Integer.parseInt(data[0]));
				patient.setNameOfPatient(data[1]);
				patient.setAgeOfPatient(Integer.parseInt(data[2]));
				patient.setBloodGroup(data[3]);
				List<String> address = new LinkedList<String>();
				address.add(data[4]);
				address.add(data[5]);
				address.add(data[6]);
				patient.setPatientAddress(address);
				patients.put(patient.getPatientId(), patient);
				patientCount++;

			}

			// Closing the buffered reader connection object

			csvReader.close();

		} catch (IOException e) {

			System.out.println(e.getMessage());
			System.exit(0);
		}
		return patientCount;
	}

	@Override
	public void writePatientFile(HashMap<Integer, Patient> patientMap) {
		String csvFilePath = PATH_BUNDLE.getString(PATIENT_CSV_FILE_PATH);
		try {

			if (new File(csvFilePath).isDirectory()) {
				throw new DirectoryPathProvidedException("Path to a directory instead of csv file is provided");
			}
			FileWriter csvWriter = new FileWriter(csvFilePath);
			for (Map.Entry<Integer, Patient> entry : patientMap.entrySet()) {
				Patient patientObj = (Patient) entry.getValue();
				csvWriter.write(String.valueOf(patientObj.getPatientId()));
				csvWriter.append(SEPERATOR);
				csvWriter.append(patientObj.getNameOfPatient());
				csvWriter.append(SEPERATOR);
				csvWriter.append(String.valueOf(patientObj.getAgeOfPatient()));
				csvWriter.append(SEPERATOR);
				csvWriter.append(patientObj.getBloodGroup());
				csvWriter.append(SEPERATOR);
				csvWriter.append(patientObj.getPatientAddress().get(0));
				csvWriter.append(SEPERATOR);
				csvWriter.append(patientObj.getPatientAddress().get(1));
				csvWriter.append(SEPERATOR);
				csvWriter.append(patientObj.getPatientAddress().get(2));
				csvWriter.append("\n");
			}

			csvWriter.flush();
			csvWriter.close();
		} catch (Exception exception) {
			System.out.print(exception.getMessage());
			System.exit(0);
		}
	}

}
