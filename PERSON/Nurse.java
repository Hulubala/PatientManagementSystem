package PERSON;

public class Nurse extends Person {
    private String role;

    public Nurse(String id, String name, String contact, String role) {
        super(id, name, contact);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Nurse ID: " + getId() + ", Name: " + getName() + ", Contact: " + getContact() + ", Role: " + role;
    }

    // Display method
    public void displayNurseInfo() {
        System.out.println("Nurse Details:");
        System.out.println("ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Contact: " + getContact());
        System.out.println("Role: " + role);
        System.out.println("------------------------");
    }
}
