package Assignment_2;

public class ContractEmployee extends Employee implements Payable {
    private int contractDuration; // Additional data member (in months)

    // Constructor
    public ContractEmployee(String empId, String name, double basicSalary, int contractDuration) {
        super(empId, name, basicSalary);
        this.contractDuration = contractDuration;
    }

    // Getter and Setter for contractDuration
    public int getContractDuration() { return contractDuration; }
    public void setContractDuration(int contractDuration) { this.contractDuration = contractDuration; }

    // Implements abstract method from Employee
    @Override
    public double calculateTax() {
        // 5% of basicSalary
        return 0.05 * basicSalary;
    }

    // Implements method from Payable interface
    @Override
    public double calculateNetSalary() {
        // basicSalary - calculateTax()
        return basicSalary - calculateTax();
    }

    // Implements method from Payable interface
    @Override
    public void generatePayslip() {
        // Displays employee details and contract period
        System.out.println("\n--- CONTRACT EMPLOYEE PAYSLIP ---");
        System.out.println("ID: " + getEmpId());
        System.out.println("Name: " + getName());
        System.out.println("Type: Contract");
        System.out.println("Basic Salary: " + String.format("%.2f", basicSalary));
        System.out.println("Contract Duration: " + contractDuration + " months");
        System.out.println("Tax Deducted (5%): " + String.format("%.2f", calculateTax()));
        System.out.println("NET SALARY: " + String.format("%.2f", calculateNetSalary()));
        System.out.println("----------------------------------\n");
    }

    // Implements abstract method from Employee
    @Override
    public String getEmployeeType() {
        return "Contract";
}
}