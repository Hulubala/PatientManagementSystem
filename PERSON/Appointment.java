package PERSON;

public class Appointment {
    private String appointmentID;
    private String patientID;
    private String doctorID;
    private String date;
    private String time;

    public Appointment(String appointmentID, String patientID, String doctorID, String date, String time) {
        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.date = date;
        this.time = time;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public String getPatientID() {
        return patientID;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public void displayAppointmentInfo() {
        System.out.println("Appointment ID: " + appointmentID +
                ", Patient ID: " + patientID +
                ", Doctor ID: " + doctorID +
                ", Date: " + date +
                ", Time: " + time);
    }
}

