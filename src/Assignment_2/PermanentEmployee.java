package Assignment_2;

public class PermanentEmployee extends Employee implements Payable {
    private double bonus; // Additional data member

    // Constructor
    public PermanentEmployee(String empId, String name, double basicSalary, double bonus) {
        super(empId, name, basicSalary);
        this.bonus = bonus;
    }

    // Getter and Setter for bonus
    public double getBonus() { return bonus; }
    public void setBonus(double bonus) { this.bonus = bonus; }

    // Implements abstract method from Employee
    @Override
    public double calculateTax() {
        // 10% of (basicSalary + bonus)
        return 0.10 * (basicSalary + bonus);
    }

    // Implements method from Payable interface
    @Override
    public double calculateNetSalary() {
        // (basicSalary + bonus) - calculateTax()
        return (basicSalary + bonus) - calculateTax();
    }

    // Implements method from Payable interface
    @Override
    public void generatePayslip() {
        // Displays employee details and salary breakdown
        System.out.println("\n--- PERMANENT EMPLOYEE PAYSLIP ---");
        System.out.println("ID: " + getEmpId());
        System.out.println("Name: " + getName());
        System.out.println("Type: Permanent");
        System.out.println("Basic Salary: " + String.format("%.2f", basicSalary));
        System.out.println("Bonus: " + String.format("%.2f", bonus));
        System.out.println("Total Earning: " + String.format("%.2f", (basicSalary + bonus)));
        System.out.println("Tax Deducted (10%): " + String.format("%.2f", calculateTax()));
        System.out.println("NET SALARY: " + String.format("%.2f", calculateNetSalary()));
        System.out.println("----------------------------------\n");
    }

    // Implements abstract method from Employee
    @Override
    public String getEmployeeType() {
        return "Permanent";
    }
}