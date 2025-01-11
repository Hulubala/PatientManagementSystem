package PERSON;

public class Doctor extends Person {
    private String specialization;

    public Doctor(String id, String name, String contact, String specialization) {
        super(id, name, contact);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void displayDoctorInfo() {
        System.out.println("Doctor ID: " + getId() +
                ", Name: " + getName() +
                ", Specialization: " + specialization +
                ", Contact: " + getContact());
    }
}

