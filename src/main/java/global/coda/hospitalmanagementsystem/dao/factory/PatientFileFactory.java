package global.coda.hospitalmanagementsystem.dao.factory;

import global.coda.hospitalmanagementsystem.dao.fileimplementation.PatientCsvDao;
import global.coda.hospitalmanagementsystem.dao.fileimplementation.PatientXmlDao;
import global.coda.hospitalmanagementsystem.dao.fileinterface.PatientFileDao;

public class PatientFileFactory {
	enum fileType {
		CSVFILETYPE, XMLFILETYPE;
	}

	public PatientFileDao getPatientDao(String patientFileType) {
		fileType choice = fileType.valueOf(patientFileType);
		switch (choice) {
		case CSVFILETYPE: {
			return new PatientCsvDao();
		}

		case XMLFILETYPE: {
			return new PatientXmlDao();
		}
		}
		return null;
	}
}
