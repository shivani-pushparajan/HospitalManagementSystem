package global.coda.hospitalmanagementsystem.model;

import java.sql.Timestamp;
import java.util.List;

public class Patient {

	private int patientId;
	private String password;
	private String username;
	private String patientDisease;
	private Timestamp joinedDate;
	private int ageOfPatient;
	private String nameOfPatient;
	private List<String> patientAddress;
	private String bloodGroup;

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPatientDisease() {
		return patientDisease;
	}

	public void setPatientDisease(String patientDisease) {
		this.patientDisease = patientDisease;
	}

	public Timestamp getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(Timestamp joinedDate) {
		this.joinedDate = joinedDate;
	}

	public List<String> getPatientAddress() {
		return patientAddress;
	}

	public void setPatientAddress(List<String> patientAddress) {
		this.patientAddress = patientAddress;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public int getAgeOfPatient() {
		return ageOfPatient;
	}

	public void setAgeOfPatient(int ageOfPatient) {
		this.ageOfPatient = ageOfPatient;
	}

	public String getNameOfPatient() {
		return nameOfPatient;
	}

	public void setNameOfPatient(String nameOfPatient) {
		this.nameOfPatient = nameOfPatient;
	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", password=" + password + ", username=" + username
				+ ", patientDisease=" + patientDisease + ", joinedDate=" + joinedDate + ", ageOfPatient=" + ageOfPatient
				+ ", nameOfPatient=" + nameOfPatient + ", patientAddress=" + patientAddress + ", bloodGroup="
				+ bloodGroup + "]";
	}

}
