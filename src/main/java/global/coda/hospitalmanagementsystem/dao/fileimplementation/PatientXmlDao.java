package global.coda.hospitalmanagementsystem.dao.fileimplementation;

import static global.coda.hospitalmanagementsystem.constants.ApplicationConstants.PATIENT_FILE_PATH;
import static global.coda.hospitalmanagementsystem.constants.ApplicationConstants.PATIENT_XML_FILE_PATH;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import global.coda.hospitalmanagementsystem.dao.fileinterface.PatientFileDao;
import global.coda.hospitalmanagementsystem.model.Patient;

public class PatientXmlDao implements PatientFileDao {
	private static final ResourceBundle PATH_BUNDLE = ResourceBundle.getBundle(PATIENT_FILE_PATH);

	@Override
	public int readPatientFile(HashMap<Integer, Patient> patients, int patientCount) {
		try {
			String xmlFilePath = PATH_BUNDLE.getString(PATIENT_XML_FILE_PATH);
			File fXmlFile = new File(xmlFilePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = (Document) dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList patientList = doc.getElementsByTagName("patient");
			patientCount = patientList.getLength();
			for (int i = 0; i < patientCount; i++) {
				Node patientNode = patientList.item(i);
				int patientId = Integer.parseInt(patientNode.getAttributes().getNamedItem("id").getNodeValue());
				if (patientNode.getNodeType() == Node.ELEMENT_NODE) {
					Element patientElement = (Element) patientNode;
					String patientName = patientElement.getElementsByTagName("name").item(0).getTextContent();

					String patientBloodGroup = patientElement.getElementsByTagName("bloodgroup").item(0)
							.getTextContent();
					int patientAge = Integer
							.parseInt(patientElement.getElementsByTagName("age").item(0).getTextContent());
					List<String> patientAddress = new LinkedList<String>();
					patientAddress.add(patientElement.getElementsByTagName("address1").item(0).getTextContent());
					patientAddress.add(patientElement.getElementsByTagName("address2").item(0).getTextContent());
					patientAddress.add(patientElement.getElementsByTagName("address3").item(0).getTextContent());
					Patient patient = new Patient();
					patient.setPatientId(patientId);
					patient.setNameOfPatient(patientName);
					patient.setBloodGroup(patientBloodGroup);
					patient.setAgeOfPatient(patientAge);
					patient.setPatientAddress(patientAddress);
					patients.put(patient.getPatientId(), patient);
				}
			}
		} catch (Exception exception) {
			System.out.print(exception.getMessage());
		}
		return patientCount;
	}

	@Override
	public void writePatientFile(HashMap<Integer, Patient> patientMap) {
		String xmlFilePath = PATH_BUNDLE.getString(PATIENT_XML_FILE_PATH);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			Element rootElement = doc.createElementNS("https://www.hospital.com/patient", "hospital");
			// append root element to document
			doc.appendChild(rootElement);

			for (Map.Entry<Integer, Patient> e : patientMap.entrySet()) {
				Element patientElement = doc.createElement("patient");
				patientElement.setAttribute("id", String.valueOf(e.getValue().getPatientId()));
				rootElement.appendChild(patientElement);
				Patient patient = (Patient) e.getValue();
				// Adding patient details
				Element nameElement = doc.createElement("name");
				nameElement.appendChild(doc.createTextNode(patient.getNameOfPatient()));
				patientElement.appendChild(nameElement);
				Element bloodGroupElement = doc.createElement("bloodgroup");
				bloodGroupElement.appendChild(doc.createTextNode(patient.getBloodGroup()));
				patientElement.appendChild(bloodGroupElement);
				Element ageElement = doc.createElement("age");
				ageElement.appendChild(doc.createTextNode(String.valueOf(patient.getAgeOfPatient())));
				patientElement.appendChild(ageElement);
				Element addressElement1 = doc.createElement("address1");
				addressElement1.appendChild(doc.createTextNode(patient.getPatientAddress().get(0)));
				patientElement.appendChild(addressElement1);
				Element addressElement2 = doc.createElement("address2");
				addressElement2.appendChild(doc.createTextNode(patient.getPatientAddress().get(1)));
				patientElement.appendChild(addressElement2);
				Element addressElement3 = doc.createElement("address3");
				addressElement3.appendChild(doc.createTextNode(patient.getPatientAddress().get(2)));
				patientElement.appendChild(addressElement3);

				// write the content into XML file
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(xmlFilePath));
				transformer.transform(source, result);

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
