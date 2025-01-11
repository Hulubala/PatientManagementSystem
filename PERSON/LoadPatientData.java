package PERSON;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class LoadPatientData {
    public static Patient loadPatient(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Patient) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading data: " + e.getMessage());
            return null;
        }
    }
}
