package PERSON;

public class Billing {
    private String billID;
    private String patientID;
    private double totalAmount;
    private boolean status; 

    public Billing(String billID, String patientID, double totalAmount, boolean status) {
        this.billID = billID;
        this.patientID = patientID;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public String getBillID() {
        return billID;
    }

    public String getPatientID() {
        return patientID;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public boolean getStatus() {
        return status;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setStatus(boolean status) { 
        this.status = status;
    }

    public void displayBillingInfo() {
        System.out.println("Bill ID: " + billID +
                ", Patient ID: " + patientID +
                ", Total Amount: $" + totalAmount +
                ", Status: " + (status ? "Paid" : "Unpaid")); 
    }
}
