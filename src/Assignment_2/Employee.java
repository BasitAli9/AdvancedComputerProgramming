package Assignment_2;
public abstract class Employee {
    // Data members (
    private String empId;
    private String name;
    protected double basicSalary; //  Protected for direct access in tax/salary calculations

    // Constructor to initialize the values
    public Employee(String empId, String name, double basicSalary) {
        this.empId = empId;
        this.name = name;
        this.basicSalary = basicSalary;
    }

    // Abstract method
    public abstract double calculateTax();

    // Getters and Setters (Encapsulation)

    public String getEmpId() { return empId; }
    public String getName() { return name; }
    public double getBasicSalary() { return basicSalary; }

    // Setters (Example: to update salary)
    public void setBasicSalary(double basicSalary) { this.basicSalary = basicSalary; }
    // No setters for empId and name since they are usually set only during creation.

    // A utility method to get employee type, useful for tabular display
    public abstract String getEmployeeType();
}
