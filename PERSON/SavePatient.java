package PERSON;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SavePatient {
    public static void savePatient(Patient patient, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(patient);
            System.out.println("Patient data saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error saving data: " + e.getMessage());
        }
    }
}