package global.coda.hospitalmanagementsystem.model;

import java.sql.Timestamp;

public class Doctor {
	private int doctorId;
	private String password;
	private String username;
	private String doctorSpecialisation;
	private Timestamp availableTime;

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
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

	public String getDoctorSpecialisation() {
		return doctorSpecialisation;
	}

	public void setDoctorSpecialisation(String doctorSpecialisation) {
		this.doctorSpecialisation = doctorSpecialisation;
	}

	public Timestamp getAvailableTime() {
		return availableTime;
	}

	public void setAvailableTime(Timestamp availableTime) {
		this.availableTime = availableTime;
	}

	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", password=" + password + ", username=" + username
				+ ", doctorSpecialisation=" + doctorSpecialisation + ", availableTime=" + availableTime + "]";
	}

}
