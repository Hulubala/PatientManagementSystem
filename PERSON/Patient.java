package PERSON;

import java.io.NotSerializableException;
import java.io.Serializable;

@SuppressWarnings("unused")
public class Patient extends Person implements Serializable {
    private static final long serialVersionUID = 1L; 

    private int age;
    private String gender;
    private String address;

    public Patient(String id, String name, String contact, int age, String gender, String address) {
        super(id, name, contact);
        this.age = age;
        this.gender = gender;
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public void displayPatientInfo() {
        System.out.println("Patient ID: " + getId() +
                ", Name: " + getName() +
                ", Age: " + age +
                ", Gender: " + gender +
                ", Address: " + address +
                ", Contact: " + getContact());
    }

    public String getPatientID() {
        return getId(); 
    }
}