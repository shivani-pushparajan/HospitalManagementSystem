package global.coda.hospitalmanagementsystem.db;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import global.coda.hospitalmanagementsystem.db.constants.AdminServicesEnum;
import global.coda.hospitalmanagementsystem.db.constants.DatabaseMessageConstants;
import global.coda.hospitalmanagementsystem.db.constants.RolesEnum;
import global.coda.hospitalmanagementsystem.db.constants.UserServicesEnum;
import global.coda.hospitalmanagementsystem.db.services.AuthenticationService;
import global.coda.hospitalmanagementsystem.db.services.DoctorServices;
import global.coda.hospitalmanagementsystem.db.services.PatientServices;
import global.coda.hospitalmanagementsystem.model.Doctor;
import global.coda.hospitalmanagementsystem.model.Patient;

public class DatabaseDriver {
	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseDriver.class);
	private int userId = 0;
	private int roleId = 0;
	private static final ResourceBundle DB_MESSAGES_BUNDLE = ResourceBundle
			.getBundle(DatabaseMessageConstants.DATABASE_MESSAGES);

	/*
	 * @input username String
	 * 
	 * @input password String
	 * 
	 * Displays seperate menu for corresponding role
	 */
	public void beginApplication() {
		try {
			Scanner sc = new Scanner(System.in);
			// Input for Username and Password
			boolean signedOut = true;
			do {
				LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB001D));
				String username = sc.nextLine();
				LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB002D));
				String password = sc.nextLine();
				// Returns user_id and role_id
				List<Integer> userValues = new AuthenticationService().authenticateUser(username, password);

				if (userValues == null) {
					LOGGER.error(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB001E));
					continue;
				} else {
					userId = userValues.get(0);
					roleId = userValues.get(1);
					signedOut = false;
				}
//Switch to display menu according to user role
				switch (RolesEnum.valueOf(roleId)) {
				case PATIENT: {
					PatientServices patientServices = new PatientServices();
					int patientChoice = 0;
					do {
						LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB003D));
						LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB004D));
						LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB005D));
						LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB020D));
						patientChoice = Integer.parseInt(sc.nextLine());
						if (patientChoice > 3) {
							LOGGER.error(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB021D));
							continue;
						}
						switch (UserServicesEnum.valueOf(patientChoice)) {
						case VIEW: {
							LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB006D));
							int branchId = Integer.parseInt(sc.nextLine());
							List<Doctor> doctorList = new DoctorServices().viewDoctor(branchId);
							if (doctorList != null) {
								for (Doctor doctor : doctorList) {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB014D)
											+ String.valueOf(doctor.getDoctorId()));
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB017D)
											+ doctor.getUsername());
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB015D)
											+ doctor.getDoctorSpecialisation());
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB016D)
											+ String.valueOf(doctor.getAvailableTime()));
								}
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB007D));
							} else {
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB008D));
							}
						}
							break;
						case MODIFY: {
							LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB009D));
							LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB010D));
							LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB011D));
							LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB022D));
							int updateChoice = Integer.parseInt(sc.nextLine());
							switch (updateChoice) {
							case 1: {
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB012D));
								String newUsername = sc.nextLine();
								boolean result = patientServices.modifyPatient(updateChoice, userId, newUsername);
								if (result == true) {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB007D));
								} else {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB008D));
								}
							}
								break;
							case 2: {
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB013D));
								String newPassword = sc.nextLine();
								boolean result = patientServices.modifyPatient(updateChoice, userId, newPassword);
								if (result == true) {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB007D));
								} else {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB008D));
								}
							}
								break;
							case 3: {
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB023D));
								String newDisease = sc.nextLine();
								boolean result = patientServices.modifyPatient(updateChoice, userId, newDisease);
								if (result == true) {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB007D));
								} else {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB008D));
								}
							}
							}

						}

							break;
						case SIGN_OUT: {
							LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB024D));
							signedOut = true;
						}
							break;
						default: {
							LOGGER.error(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB002E));
						}
						}
					} while (patientChoice != 3);
				}
					break;
				case DOCTOR: {
					int doctorChoice = 0;
					DoctorServices doctorServices = new DoctorServices();
					do {
						LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB019D));
						LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB018D));
						LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB005D));
						LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB020D));
						doctorChoice = Integer.parseInt(sc.nextLine());
						if (doctorChoice > 3) {
							LOGGER.error(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB021D));
							continue;
						}
						switch (UserServicesEnum.valueOf(doctorChoice)) {
						case VIEW: {
							LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB006D));
							int branchId = Integer.parseInt(sc.nextLine());

							List<Patient> patientList = new PatientServices().viewPatient(branchId);
							if (patientList != null) {
								for (Patient patient : patientList) {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB025D)
											+ String.valueOf(patient.getPatientId()));
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB026D)
											+ patient.getUsername());
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB027D)
											+ patient.getPatientDisease());
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB028D)
											+ String.valueOf(patient.getJoinedDate()));
								}
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB007D));
							} else {
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB008D));
							}
						}
							break;
						case MODIFY: {
							LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB009D));
							LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB010D));
							LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB011D));
							LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB015D));
							int updateChoice = Integer.parseInt(sc.nextLine());
							switch (updateChoice) {
							case 1: {
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB012D));
								String newUsername = sc.nextLine();
								boolean result = doctorServices.modifyDoctor(updateChoice, userId, newUsername);
								if (result == true) {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB007D));
								} else {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB008D));
								}
							}
								break;
							case 2: {
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB013D));
								String newPassword = sc.nextLine();
								boolean result = doctorServices.modifyDoctor(updateChoice, userId, newPassword);
								if (result == true) {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB007D));
								} else {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB008D));
								}
							}
								break;
							case 3: {
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB029D));
								String newSpecialisation = sc.nextLine();
								boolean result = doctorServices.modifyDoctor(updateChoice, userId, newSpecialisation);
								if (result == true) {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB007D));
								} else {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB008D));
								}
							}
							}

						}

							break;
						case SIGN_OUT: {
							LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB024D));
							signedOut = true;
						}
							break;
						default: {
							LOGGER.error(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB002E));
						}
							break;

						}
					} while (doctorChoice != 3);
				}
					break;
				case LOCAL_ADMIN: {
					int adminChoice = 0;
					do {
						LOGGER.debug("--- Local Administrator Menu ---");
						LOGGER.debug("Enter your Branch Id : ");
						int branchId = Integer.valueOf(sc.nextLine());
						LOGGER.debug("Choose operation to perform : \n1.Add\n2.View\n3.Modify\n4.Delete\n5.Sign Out");
						adminChoice = Integer.valueOf(sc.nextLine());
						switch (AdminServicesEnum.valueOf(adminChoice)) {
						case ADD: {
							LOGGER.debug("---Add---\n1.Patient\n2.Doctor");
							int adminAddChoice = Integer.valueOf(sc.nextLine());
							switch (RolesEnum.valueOf(adminAddChoice)) {
							case PATIENT: {
								Patient patient = new Patient();
								LOGGER.debug("Enter Username");
								patient.setUsername(sc.nextLine());
								LOGGER.debug("Enter password");
								patient.setPassword(sc.nextLine());
								LOGGER.debug("Enter disease");
								patient.setPatientDisease(sc.nextLine());
								boolean result = new PatientServices().addPatient(patient);
								if (result == true) {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB007D));
								} else {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB008D));
								}
							}
								break;
							case DOCTOR: {
								Doctor doctor = new Doctor();
								LOGGER.debug("Enter Username");
								doctor.setUsername(sc.nextLine());
								LOGGER.debug("Enter password");
								doctor.setPassword(sc.nextLine());
								LOGGER.debug("Enter specialisation");
								doctor.setDoctorSpecialisation(sc.nextLine());
								boolean result = new DoctorServices().addDoctor(doctor);
								if (result == true) {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB007D));
								} else {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB008D));
								}
							}
								break;
							default: {
								LOGGER.error(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB002E));
							}
							}
						}
							break;
						case VIEW: {
							LOGGER.debug("---View---\n1.Patient\n2.Doctor");
							int adminAddChoice = Integer.valueOf(sc.nextLine());
							switch (RolesEnum.valueOf(adminAddChoice)) {
							case PATIENT: {
								List<Patient> patientList = new PatientServices().viewPatient(branchId);
								if (patientList != null) {
									for (Patient patient : patientList) {
										LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB025D)
												+ String.valueOf(patient.getPatientId()));
										LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB026D)
												+ patient.getUsername());
										LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB027D)
												+ patient.getPatientDisease());
										LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB028D)
												+ String.valueOf(patient.getJoinedDate()));
									}
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB007D));
								} else {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB008D));
								}
							}
								break;
							case DOCTOR: {
								List<Doctor> doctorList = new DoctorServices().viewDoctor(branchId);
								if (doctorList != null) {
									for (Doctor doctor : doctorList) {
										LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB014D)
												+ String.valueOf(doctor.getDoctorId()));
										LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB017D)
												+ doctor.getUsername());
										LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB015D)
												+ doctor.getDoctorSpecialisation());
										LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB016D)
												+ String.valueOf(doctor.getAvailableTime()));
									}
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB007D));
								} else {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB008D));
								}
							}
								break;
							default: {
								LOGGER.error(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB002E));
							}
							}
						}
							break;
						case MODIFY: {
							LOGGER.debug("---Modify---\n1.Patient\n2.Doctor");

							int modifyAdminChoice = Integer.valueOf(sc.nextLine());
							LOGGER.debug("Enter the userId : ");
							int modifyUserId = Integer.valueOf(sc.nextLine());
							switch (RolesEnum.valueOf(modifyAdminChoice)) {
							case PATIENT: {
								PatientServices patientServices = new PatientServices();
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB009D));
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB010D));
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB011D));
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB022D));
								int updateChoice = Integer.parseInt(sc.nextLine());
								switch (updateChoice) {
								case 1: {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB012D));
									String newUsername = sc.nextLine();
									boolean result = patientServices.modifyPatient(updateChoice, modifyUserId,
											newUsername);
									if (result == true) {
										LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB007D));
									} else {
										LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB008D));
									}
								}
									break;
								case 2: {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB013D));
									String newPassword = sc.nextLine();
									boolean result = patientServices.modifyPatient(updateChoice, modifyUserId,
											newPassword);
									if (result == true) {
										LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB007D));
									} else {
										LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB008D));
									}
								}
									break;
								case 3: {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB023D));
									String newDisease = sc.nextLine();
									boolean result = patientServices.modifyPatient(updateChoice, modifyUserId,
											newDisease);
									if (result == true) {
										LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB007D));
									} else {
										LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB008D));
									}
								}
								}

							}
								break;
							case DOCTOR: {
								DoctorServices doctorServices = new DoctorServices();
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB009D));
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB010D));
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB011D));
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB015D));
								int updateChoice = Integer.parseInt(sc.nextLine());
								switch (updateChoice) {
								case 1: {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB012D));
									String newUsername = sc.nextLine();
									boolean result = doctorServices.modifyDoctor(updateChoice, modifyUserId,
											newUsername);
									if (result == true) {
										LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB007D));
									} else {
										LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB008D));
									}
								}
									break;
								case 2: {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB013D));
									String newPassword = sc.nextLine();
									boolean result = doctorServices.modifyDoctor(updateChoice, modifyUserId,
											newPassword);
									if (result == true) {
										LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB007D));
									} else {
										LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB008D));
									}
								}
									break;
								case 3: {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB029D));
									String newSpecialisation = sc.nextLine();
									boolean result = doctorServices.modifyDoctor(updateChoice, modifyUserId,
											newSpecialisation);
									if (result == true) {
										LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB007D));
									} else {
										LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB008D));
									}
								}
								}
							}
								break;
							default: {
								LOGGER.error(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB002E));
							}
							}

						}
							break;
						case DELETE: {
							LOGGER.debug("---Delete---\n1.Patient\n2.Doctor");
							int deleteAdminChoice = Integer.valueOf(sc.nextLine());
							switch (RolesEnum.valueOf(deleteAdminChoice)) {
							case PATIENT: {
								LOGGER.debug("Enter User Id");
								int deleteUserId = Integer.valueOf(sc.nextLine());
								boolean result = new PatientServices().deletePatient(deleteUserId);
								if (result == true) {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB007D));
								} else {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB008D));
								}
							}
								break;
							case DOCTOR: {
								LOGGER.debug("Enter User Id");
								int deleteUserId = Integer.valueOf(sc.nextLine());
								boolean result = new DoctorServices().deleteDoctor(deleteUserId);
								if (result == true) {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB007D));
								} else {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB008D));
								}
							}
								break;
							default: {
								LOGGER.error(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB002E));
							}
							}
						}
							break;
						case SIGN_OUT: {
							LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB024D));
							signedOut = true;
						}
							break;
						default: {
							LOGGER.error(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB002E));
						}
						}
					} while (adminChoice != 5);
				}
					break;
				case GLOBAL_ADMIN: {
					LOGGER.debug("--- GLobal Administrator Menu ---");

					LOGGER.debug("Choose operation to perform : \n1.Add\n2.View\n3.Modify\n4.Delete\n5.Sign Out");
					int adminChoice = Integer.valueOf(sc.nextLine());
					switch (AdminServicesEnum.valueOf(adminChoice)) {
					case ADD: {
						LOGGER.debug("---Add---\n1.Patient\n2.Doctor\n3.Branch");
						int adminAddChoice = Integer.valueOf(sc.nextLine());
						switch (RolesEnum.valueOf(adminAddChoice)) {
						case PATIENT: {
							Patient patient = new Patient();
							LOGGER.debug("Enter Username");
							patient.setUsername(sc.nextLine());
							LOGGER.debug("Enter password");
							patient.setPassword(sc.nextLine());
							LOGGER.debug("Enter disease");
							patient.setPatientDisease(sc.nextLine());
							boolean result = new PatientServices().addPatient(patient);
							if (result == true) {
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB007D));
							} else {
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB008D));
							}
						}
							break;
						case DOCTOR: {
							Doctor doctor = new Doctor();
							LOGGER.debug("Enter Username");
							doctor.setUsername(sc.nextLine());
							LOGGER.debug("Enter password");
							doctor.setPassword(sc.nextLine());
							LOGGER.debug("Enter specialisation");
							doctor.setDoctorSpecialisation(sc.nextLine());
							boolean result = new DoctorServices().addDoctor(doctor);
							if (result == true) {
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB007D));
							} else {
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB008D));
							}
						}
							break;

						default: {
							LOGGER.error(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB002E));
						}
						}
					}
						break;
					case VIEW: {

						LOGGER.debug("---View---\n1.Patient\n2.Doctor");
						int adminAddChoice = Integer.valueOf(sc.nextLine());
						LOGGER.debug("Enter a Branch Id : ");
						int branchId = Integer.valueOf(sc.nextLine());
						switch (RolesEnum.valueOf(adminAddChoice)) {
						case PATIENT: {
							List<Patient> patientList = new PatientServices().viewPatient(branchId);
							if (patientList != null) {
								for (Patient patient : patientList) {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB025D)
											+ String.valueOf(patient.getPatientId()));
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB026D)
											+ patient.getUsername());
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB027D)
											+ patient.getPatientDisease());
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB028D)
											+ String.valueOf(patient.getJoinedDate()));
								}
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB007D));
							} else {
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB008D));
							}
						}
							break;
						case DOCTOR: {
							List<Doctor> doctorList = new DoctorServices().viewDoctor(branchId);
							if (doctorList != null) {
								for (Doctor doctor : doctorList) {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB014D)
											+ String.valueOf(doctor.getDoctorId()));
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB017D)
											+ doctor.getUsername());
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB015D)
											+ doctor.getDoctorSpecialisation());
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB016D)
											+ String.valueOf(doctor.getAvailableTime()));
								}
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB007D));
							} else {
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB008D));
							}
						}
							break;
						default: {
							LOGGER.error(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB002E));
						}
						}
					}
						break;
					case MODIFY: {
						LOGGER.debug("---Modify---\n1.Patient\n2.Doctor");

						int modifyAdminChoice = Integer.valueOf(sc.nextLine());
						LOGGER.debug("Enter the userId : ");
						int modifyUserId = Integer.valueOf(sc.nextLine());
						switch (RolesEnum.valueOf(modifyAdminChoice)) {
						case PATIENT: {
							PatientServices patientServices = new PatientServices();
							LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB009D));
							LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB010D));
							LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB011D));
							LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB022D));
							int updateChoice = Integer.parseInt(sc.nextLine());
							switch (updateChoice) {
							case 1: {
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB012D));
								String newUsername = sc.nextLine();
								boolean result = patientServices.modifyPatient(updateChoice, modifyUserId, newUsername);
								if (result == true) {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB007D));
								} else {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB008D));
								}
							}
								break;
							case 2: {
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB013D));
								String newPassword = sc.nextLine();
								boolean result = patientServices.modifyPatient(updateChoice, modifyUserId, newPassword);
								if (result == true) {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB007D));
								} else {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB008D));
								}
							}
								break;
							case 3: {
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB023D));
								String newDisease = sc.nextLine();
								boolean result = patientServices.modifyPatient(updateChoice, modifyUserId, newDisease);
								if (result == true) {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB007D));
								} else {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB008D));
								}
							}
							}

						}
							break;
						case DOCTOR: {
							DoctorServices doctorServices = new DoctorServices();
							LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB009D));
							LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB010D));
							LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB011D));
							LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB015D));
							int updateChoice = Integer.parseInt(sc.nextLine());
							switch (updateChoice) {
							case 1: {
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB012D));
								String newUsername = sc.nextLine();
								boolean result = doctorServices.modifyDoctor(updateChoice, modifyUserId, newUsername);
								if (result == true) {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB007D));
								} else {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB008D));
								}
							}
								break;
							case 2: {
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB013D));
								String newPassword = sc.nextLine();
								boolean result = doctorServices.modifyDoctor(updateChoice, modifyUserId, newPassword);
								if (result == true) {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB007D));
								} else {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB008D));
								}
							}
								break;
							case 3: {
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB029D));
								String newSpecialisation = sc.nextLine();
								boolean result = doctorServices.modifyDoctor(updateChoice, modifyUserId,
										newSpecialisation);
								if (result == true) {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB007D));
								} else {
									LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB008D));
								}
							}
							}
						}
							break;
						default: {
							LOGGER.error(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB002E));
						}
						}
					}
						break;
					case DELETE: {
						LOGGER.debug("---Delete---\n1.Patient\n2.Doctor");
						int deleteAdminChoice = Integer.valueOf(sc.nextLine());
						switch (RolesEnum.valueOf(deleteAdminChoice)) {
						case PATIENT: {
							LOGGER.debug("Enter User Id");
							int deleteUserId = Integer.valueOf(sc.nextLine());
							boolean result = new PatientServices().deletePatient(deleteUserId);
							if (result == true) {
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB007D));
							} else {
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB008D));
							}
						}
							break;
						case DOCTOR: {
							LOGGER.debug("Enter User Id");
							int deleteUserId = Integer.valueOf(sc.nextLine());
							boolean result = new DoctorServices().deleteDoctor(deleteUserId);
							if (result == true) {
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB007D));
							} else {
								LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB008D));
							}
						}
							break;
						default: {
							LOGGER.error(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB002E));
						}
						}
					}
						break;
					case SIGN_OUT: {
						LOGGER.debug(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB024D));
						signedOut = true;
					}
						break;
					default: {
						LOGGER.error(DB_MESSAGES_BUNDLE.getString(DatabaseMessageConstants.HDB002E));
					}
					}
				}
				}

			} while (signedOut == true);
			sc.close();
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
		}

	}
}
