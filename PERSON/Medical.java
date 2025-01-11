package PERSON;

import java.io.Serializable;

public class Medical implements Serializable {
    private static final long serialVersionUID = 1L;

    private String recordID;
    private String patientID;
    private String doctorID;
    private String diagnosis;
    private String prescription;

    public Medical(String recordID, String patientID, String doctorID, String diagnosis, String prescription) {
        this.recordID = recordID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
    }

    public String getRecordID() {
        return recordID;
    }

    public String getPatientID() {
        return patientID;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getPrescription() {
        return prescription;
    }

    public void displayRecord() {
        System.out.println("Record ID: " + recordID);
        System.out.println("Patient ID: " + patientID);
        System.out.println("Doctor ID: " + doctorID);
        System.out.println("Diagnosis: " + diagnosis);
        System.out.println("Prescription: " + prescription);
        System.out.println("------------------------");
    }
}
