package PERSON;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class PatientManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Patient> patients = new ArrayList<>();
        ArrayList<Doctor> doctors = new ArrayList<>();
        ArrayList<Nurse> nurses = new ArrayList<>();
        ArrayList<Medical> medicalRecords = new ArrayList<>();
        ArrayList<Appointment> appointments = new ArrayList<>();
        ArrayList<Billing> bills = new ArrayList<>();

        HashMap<String, String> doctorCredentials = new HashMap<>();
        HashMap<String, String> nurseCredentials = new HashMap<>();
        HashMap<String, String> patientCredentials = new HashMap<>();
        HashMap<String, String> adminCredentials = new HashMap<>();

        doctorCredentials.put("doctor1", "password1");
        nurseCredentials.put("nurse1", "password1");
        patientCredentials.put("patient1", "password1");
        adminCredentials.put("admin", "admin123");

        loadDefaultData(patients, doctors, nurses, medicalRecords, appointments, bills);
        loadData(patients, doctors, nurses, medicalRecords, appointments, bills);

        System.out.println("--- Debug: Default Patients ---");
        for (Patient patient : patients) {
            System.out.println("Patient ID: " + patient.getPatientID());
            System.out.println("Name: " + patient.getName());
            System.out.println("Contact: " + patient.getContact());
            System.out.println("Age: " + patient.getAge());
            System.out.println("Gender: " + patient.getGender());
            System.out.println("Address: " + patient.getAddress());
            System.out.println("------------------------");
        }

        int choice;

        do {
            System.out.println("\nHospital Management System - Login:");
            System.out.println("1. Doctor Login");
            System.out.println("2. Nurse Login");
            System.out.println("3. Patient Login");
            System.out.println("4. Admin Login");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1: // Doctor Login
                    String doctorID = loginUser(scanner, doctorCredentials);
                    if (doctorID != null) {
                        doctorMenu(scanner, appointments, medicalRecords, patients, doctors, nurses, bills);
                    } else {
                        System.out.println("Invalid credentials! Returning to main menu.");
                    }
                    break;
            
                case 2: // Nurse Login
                    String nurseID = loginUser(scanner, nurseCredentials);
                    if (nurseID != null) {
                        nurseMenu(scanner, patients);
                    } else {
                        System.out.println("Invalid credentials! Returning to main menu.");
                    }
                    break;
            
                case 3: // Patient Login
                    String patientID = loginUser(scanner, patientCredentials);
                    if (patientID != null) {
                        patientMenu(scanner, medicalRecords, patientID);
                    } else {
                        System.out.println("Invalid credentials! Returning to main menu.");
                    }
                    break;
            
                case 4: // Admin Login
                    String adminID = loginUser(scanner, adminCredentials);
                    if (adminID != null) {
                        adminMenu(scanner, patients, doctors, nurses, medicalRecords, appointments, bills);
                    } else {
                        System.out.println("Invalid credentials! Returning to main menu.");
                    }
                    break;
            
                case 5: // Exit
                    System.out.println("Exiting the Hospital Management System. Goodbye!");
                    break;
            
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static String loginUser(Scanner scanner, HashMap<String, String> credentials) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
    
        if (credentials.containsKey(username) && credentials.get(username).equals(password)) {
            return username; 
        }
        return null;
    }
    
    private static void doctorMenu(Scanner scanner, ArrayList<Appointment> appointments, ArrayList<Medical> medicalRecords, 
                                ArrayList<Patient> patients, ArrayList<Doctor> doctors, ArrayList<Nurse> nurses, ArrayList<Billing> bills) {
        int choice;
        do {
            System.out.println("\n--- Doctor Menu ---");
            System.out.println("1. View Appointments");
            System.out.println("2. Add Medical Record");
            System.out.println("3. View All Medical Records");
            System.out.println("4. View Patient Medical Records");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.println("\n--- View Appointments ---");
                    for (Appointment app : appointments) {
                        app.displayAppointmentInfo();
                    }
                    break;

                case 2:
                    System.out.println("\n--- Add Medical Record ---");
                    System.out.print("Enter Record ID: ");
                    String recordID = scanner.nextLine();
                    System.out.print("Enter Patient ID: ");
                    String medicalPatientID = scanner.nextLine();
                    System.out.print("Enter Doctor ID: ");
                    String doctorID = scanner.nextLine();
                    System.out.print("Enter Diagnosis: ");
                    String diagnosis = scanner.nextLine();
                    System.out.print("Enter Prescription: ");
                    String prescription = scanner.nextLine();
                    medicalRecords.add(new Medical(recordID, medicalPatientID, doctorID, diagnosis, prescription));
                    System.out.println("Medical Record Added Successfully!");
                    saveData(patients, doctors, nurses, medicalRecords, appointments, bills);
                    break;

                case 3:
                    System.out.println("\n--- View All Medical Records ---");
                    for (Medical record : medicalRecords) {
                        record.displayRecord();
                    }
                    break;

                case 4:
                    System.out.println("\n--- View Patient Medical Records ---");
                    System.out.print("Enter Patient ID: ");
                    String searchPatientID = scanner.nextLine();
                    boolean found = false;
                    for (Medical record : medicalRecords) {
                        if (record.getPatientID().trim().equalsIgnoreCase(searchPatientID.trim())) {
                            record.displayRecord();
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("No medical records found for Patient ID: " + searchPatientID);
                    }
                    break;

                case 5:
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 5);
    }

    private static void nurseMenu(Scanner scanner, ArrayList<Patient> patients) {
        int choice;
        do {
            System.out.println("\n--- Nurse Menu ---");
            System.out.println("1. View Patient List");
            System.out.println("2. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.println("\n--- Patient List ---");
                    if (patients.isEmpty()) {
                        System.out.println("No patients registered yet.");
                    } else {
                        for (Patient patient : patients) {
                            patient.displayPatientInfo();
                        }
                    }
                    break;

                case 2:
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 2);
    }

    private static void patientMenu(Scanner scanner, ArrayList<Medical> medicalRecords, String loggedInPatientID) {
        int choice;
        do {
            System.out.println("\n--- Patient Menu ---");
            System.out.println("1. View Your Medical Records");
            System.out.println("2. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.println("\n--- View Your Medical Records ---");
                    System.out.print("Enter Patient ID: ");
                    String searchPatientID = scanner.nextLine();
                    boolean found = false;
                    for (Medical record : medicalRecords) {
                        if (record.getPatientID().trim().equalsIgnoreCase(searchPatientID.trim())) {
                            record.displayRecord();
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("No medical records found for Patient ID: " + searchPatientID);
                    }
                    break;

                case 2:
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 2);
    }

    private static void adminMenu(Scanner scanner, ArrayList<Patient> patients, ArrayList<Doctor> doctors, ArrayList<Nurse> nurses,
                               ArrayList<Medical> medicalRecords, ArrayList<Appointment> appointments, ArrayList<Billing> bills) {
        int choice;
        do {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Register New Patient");
            System.out.println("2. Add New Doctor");
            System.out.println("3. Add New Nurse");
            System.out.println("4. View All Data");
            System.out.println("5. Add Appointment");
            System.out.println("6. View Appointments");
            System.out.println("7. Update Billing Status");
            System.out.println("8. View Bills");
            System.out.println("9. Search Patient by ID");
            System.out.println("10. Sort and View Patients by Name");
            System.out.println("11. Save Data to File");
            System.out.println("12. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1: // Register New Patient
                    System.out.println("\n--- Register New Patient ---");
                    System.out.print("Enter Patient ID: ");
                    String patientID = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String patientName = scanner.nextLine();
                    System.out.print("Enter Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Gender: ");
                    String gender = scanner.nextLine();
                    System.out.print("Enter Address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter Contact: ");
                    String contact = scanner.nextLine();
                    patients.add(new Patient(patientID, patientName, contact, age, gender, address));
                    System.out.println("Patient Registered Successfully!");
                    break;

                case 2: // Add New Doctor
                    System.out.println("\n--- Add New Doctor ---");
                    System.out.print("Enter Doctor ID: ");
                    String doctorID = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String doctorName = scanner.nextLine();
                    System.out.print("Enter Contact: ");
                    String doctorContact = scanner.nextLine();
                    System.out.print("Enter Specialization: ");
                    String specialization = scanner.nextLine();
                    doctors.add(new Doctor(doctorID, doctorName, doctorContact, specialization));
                    System.out.println("Doctor Added Successfully!");
                    break;

                case 3: // Add New Nurse
                    System.out.println("\n--- Add New Nurse ---");
                    System.out.print("Enter Nurse ID: ");
                    String nurseID = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String nurseName = scanner.nextLine();
                    System.out.print("Enter Role: ");
                    String role = scanner.nextLine();
                    System.out.print("Enter Contact: ");
                    String nurseContact = scanner.nextLine();
                    nurses.add(new Nurse(nurseID, nurseName, nurseContact, role));
                    System.out.println("Nurse Added Successfully!");
                    break;

                case 4: // View All Data
                    System.out.println("\n--- View All Data ---");
                    System.out.println("Patients:");
                    for (Patient patient : patients) {
                        patient.displayPatientInfo();
                    }
                    System.out.println("\nDoctors:");
                    for (Doctor doctor : doctors) {
                        doctor.displayDoctorInfo();
                    }
                    System.out.println("\nNurses:");
                    for (Nurse nurse : nurses) {
                        nurse.displayNurseInfo();
                    }
                    break;

                case 5: // Add Appointment
                    System.out.println("\n--- Add Appointment ---");
                    System.out.print("Enter Appointment ID: ");
                    String appointmentID = scanner.nextLine();
                    System.out.print("Enter Patient ID: ");
                    String appointmentPatientID = scanner.nextLine();
                    System.out.print("Enter Doctor ID: ");
                    String appointmentDoctorID = scanner.nextLine();
                    System.out.print("Enter Date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    System.out.print("Enter Time (HH:MM): ");
                    String time = scanner.nextLine();

                    appointments.add(new Appointment(appointmentID, appointmentPatientID, appointmentDoctorID, date, time));
                    System.out.println("Appointment Added Successfully!");
                    break;

                case 6: // View Appointments
                    System.out.println("\n--- View Appointments ---");
                    if (appointments.isEmpty()) {
                        System.out.println("No appointments scheduled.");
                    } else {
                        for (Appointment app : appointments) {
                            app.displayAppointmentInfo();
                        }
                    }
                    break;

                case 7: // Update Billing Status
                    System.out.println("\n--- Update Billing Status ---");
                    System.out.print("Enter Bill ID: ");
                    String billID = scanner.nextLine();
                    System.out.print("Enter Patient ID: ");
                    String billingPatientID = scanner.nextLine();
                    System.out.print("Enter Amount: ");
                    double amount = scanner.nextDouble();
                    System.out.print("Enter Status (1 for Paid, 0 for Unpaid): ");
                    boolean status = scanner.nextInt() == 1;

                    bills.add(new Billing(billID, billingPatientID, amount, status));
                    System.out.println("Billing Status Updated Successfully!");
                    break;

                case 8: // View Bills
                    System.out.println("\n--- View Bills ---");
                    if (bills.isEmpty()) {
                        System.out.println("No bills recorded.");
                    } else {
                        for (Billing bill : bills) {
                            System.out.println("Bill ID: " + bill.getBillID());
                            System.out.println("Patient ID: " + bill.getPatientID());
                            System.out.println("Amount: $" + bill.getTotalAmount());
                            System.out.println("Status: " + (bill.getStatus() ? "Paid" : "Unpaid"));
                            System.out.println("------------------------");
                        }
                    }
                    break;

                case 9: // Search Patient by ID
                    System.out.println("\n--- Search Patient by ID ---");
                    System.out.print("Enter Patient ID: ");
                    String searchID = scanner.nextLine();
                    boolean found = false;
                    for (Patient patient : patients) {
                        if (patient.getPatientID().equals(searchID)) {
                            patient.displayPatientInfo();
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Patient not found.");
                    }
                    break;

                case 10: // Sort and View Patients by Name
                    System.out.println("\n--- Sorted Patient List ---");
                    ArrayList<Patient> sortedPatients = new ArrayList<>(patients);
                    sortedPatients.sort((p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()));
                    for (Patient patient : sortedPatients) {
                        patient.displayPatientInfo();
                    }
                    break;

                case 11: // Save Data
                    saveData(patients, doctors, nurses, medicalRecords, appointments, bills);
                    break;

                case 12: // Logout
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 12);
    }

    private static void saveData(ArrayList<Patient> patients, ArrayList<Doctor> doctors, ArrayList<Nurse> nurses,
                             ArrayList<Medical> medicalRecords, ArrayList<Appointment> appointments, ArrayList<Billing> bills) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("hospital_data.dat"))) {
            oos.writeObject(patients);
            oos.writeObject(doctors);
            oos.writeObject(nurses);
            oos.writeObject(medicalRecords);
            oos.writeObject(appointments);
            oos.writeObject(bills);
            System.out.println("Data Saved Successfully!");
        } catch (NotSerializableException e) {
            System.err.println("Serialization error: " + e.getMessage() + ". Ensure all classes implement Serializable.");
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage() + ". Please check file permissions and path.");
        }
    }

    private static void loadDefaultData(ArrayList<Patient> patients, ArrayList<Doctor> doctors, 
                                      ArrayList<Nurse> nurses, ArrayList<Medical> medicalRecords, 
                                      ArrayList<Appointment> appointments, ArrayList<Billing> bills) {
        patients.clear();
        medicalRecords.clear();
        doctors.clear();
        
        patients.add(new Patient("AI123456", "Peter", "0123456789", 21, "Male", "UTHM"));
        
        medicalRecords.add(new Medical("R001", "patient1", "D001", "Flu Diagnosis", "Rest and hydration"));
        
        doctors.add(new Doctor("D001", "Dr. Smith", "0123456789", "General Medicine"));
    }

    @SuppressWarnings("unchecked")
    private static void loadData(ArrayList<Patient> patients, ArrayList<Doctor> doctors, ArrayList<Nurse> nurses,
                             ArrayList<Medical> medicalRecords, ArrayList<Appointment> appointments, ArrayList<Billing> bills) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("hospital_data.dat"))) {
            patients.clear();
            doctors.clear();
            nurses.clear();
            medicalRecords.clear();
            appointments.clear();
            bills.clear();
            
            patients.addAll((ArrayList<Patient>) ois.readObject());
            doctors.addAll((ArrayList<Doctor>) ois.readObject());
            nurses.addAll((ArrayList<Nurse>) ois.readObject());
            medicalRecords.addAll((ArrayList<Medical>) ois.readObject());
            appointments.addAll((ArrayList<Appointment>) ois.readObject());
            bills.addAll((ArrayList<Billing>) ois.readObject());
            System.out.println("Data Loaded Successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("No previous data found. Starting with default data.");
            loadDefaultData(patients, doctors, nurses, medicalRecords, appointments, bills);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
            System.out.println("Loading default data instead.");
            loadDefaultData(patients, doctors, nurses, medicalRecords, appointments, bills);
        }
    }
}