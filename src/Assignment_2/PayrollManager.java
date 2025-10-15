package Assignment_2;

import java.util.Scanner;
import java.util.ArrayList;

public class PayrollManager {
    // Array to store employees (up to 5 max) [cite: 49, 71]
    private ArrayList<Employee> employees;
    private static final int MAX_EMPLOYEES = 5;

    public PayrollManager() {
        employees = new ArrayList<>();
    }

    // method to check for unique ID
    private boolean isIdUnique(String empId) {
        for (Employee emp : employees) {
            if (emp.getEmpId().equalsIgnoreCase(empId)) {
                return false;
            }
        }
        return true;
    }

    // 1. Add Employee
    public void addEmployee(Scanner scanner) {
        if (employees.size() >= MAX_EMPLOYEES) {
            System.out.println("Error: Maximum of " + MAX_EMPLOYEES + " employees allowed. Cannot add more."); // [cite: 49, 71]
            return;
        }

        System.out.print("Enter Employee Type (Permanent/Contract): "); // [cite: 45]
        String type = scanner.nextLine().trim();

        System.out.print("Enter ID: ");
        String id = scanner.nextLine().trim();

        if (!isIdUnique(id)) {
            System.out.println("Error: Employee ID must be unique. Employee with ID " + id + " already exists."); // [cite: 73, 74]
            return;
        }

        System.out.print("Enter Name: "); // [cite: 89]
        String name = scanner.nextLine().trim();

        double basicSalary = -1;
        while (basicSalary <= 0) {
            System.out.print("Enter Basic Salary: "); // [cite: 90]
            try {
                basicSalary = Double.parseDouble(scanner.nextLine());
                if (basicSalary <= 0) {
                    System.out.println("Error: Salary value must be positive."); // [cite: 72, 74]
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter a numerical value for salary."); // [cite: 74]
            }
        }

        if (type.equalsIgnoreCase("Permanent")) { // [cite: 87]
            double bonus = -1;
            while (bonus < 0) {
                System.out.print("Enter Bonus: "); // [cite: 91]
                try {
                    bonus = Double.parseDouble(scanner.nextLine());
                    if (bonus < 0) {
                        System.out.println("Error: Bonus value must be positive (or zero)."); // [cite: 72, 74]
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid input. Please enter a numerical value for bonus."); // [cite: 74]
                }
            }
            employees.add(new PermanentEmployee(id, name, basicSalary, bonus));
            System.out.println("Employee added successfully!"); // [cite: 92, 67]

        } else if (type.equalsIgnoreCase("Contract")) {
            int duration = -1;
            while (duration <= 0) {
                System.out.print("Enter Contract Duration (in months): ");
                try {
                    duration = Integer.parseInt(scanner.nextLine());
                    if (duration <= 0) {
                        System.out.println("Error: Contract duration must be a positive number."); // [cite: 74]
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid input. Please enter an integer for duration."); // [cite: 74]
                }
            }
            employees.add(new ContractEmployee(id, name, basicSalary, duration));
            System.out.println("Employee added successfully!"); // [cite: 67]
        } else {
            System.out.println("Error: Invalid employee type entered. Employee not added."); // [cite: 74]
        }
    }

    // 2. View All Employees
    public void viewAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees in the system.");
            return;
        }

        // Display details in tabular format [cite: 51, 68]
        System.out.println("\n--- ALL EMPLOYEES ---");
        System.out.printf("%-5s | %-15s | %-10s | %-15s | %-15s | %-10s | %-15s%n",
                "ID", "Name", "Type", "Basic Salary", "Bonus/Duration", "Tax", "Net Salary"); // [cite: 52]
        System.out.println("--------------------------------------------------------------------------------------------------");

        for (Employee emp : employees) {
            String bonusOrDuration;
            String type = emp.getEmployeeType();

            if (emp instanceof PermanentEmployee) {
                PermanentEmployee pEmp = (PermanentEmployee) emp;
                bonusOrDuration = String.format("%.2f", pEmp.getBonus());
            } else if (emp instanceof ContractEmployee) {
                ContractEmployee cEmp = (ContractEmployee) emp;
                bonusOrDuration = cEmp.getContractDuration() + " months";
            } else {
                bonusOrDuration = "N/A";
            }

            System.out.printf("%-5s | %-15s | %-10s | %-15.2f | %-15s | %-10.2f | %-15.2f%n",
                    emp.getEmpId(), emp.getName(), type, emp.getBasicSalary(),
                    bonusOrDuration, emp.calculateTax(), ((Payable) emp).calculateNetSalary());
        }
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    // Helper method to find an employee
    private Employee findEmployeeById(String empId) {
        for (Employee emp : employees) {
            if (emp.getEmpId().equalsIgnoreCase(empId)) {
                return emp;
            }
        }
        return null; // ID not found [cite: 74]
    }

    // 3. Search Employee
    public void searchEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID to search: "); // [cite: 55]
        String id = scanner.nextLine();
        Employee emp = findEmployeeById(id);

        if (emp != null) {
            System.out.println("\n--- EMPLOYEE DETAILS ---");
            // Display details in tabular format [cite: 55, 68]
            System.out.printf("%-5s | %-15s | %-10s | %-15s | %-15s | %-10s | %-15s%n",
                    "ID", "Name", "Type", "Basic Salary", "Bonus/Duration", "Tax", "Net Salary"); // [cite: 52]
            System.out.println("--------------------------------------------------------------------------------------------------");

            String bonusOrDuration;
            String type = emp.getEmployeeType();

            if (emp instanceof PermanentEmployee) {
                PermanentEmployee pEmp = (PermanentEmployee) emp;
                bonusOrDuration = String.format("%.2f", pEmp.getBonus());
            } else if (emp instanceof ContractEmployee) {
                ContractEmployee cEmp = (ContractEmployee) emp;
                bonusOrDuration = cEmp.getContractDuration() + " months";
            } else {
                bonusOrDuration = "N/A";
            }

            System.out.printf("%-5s | %-15s | %-10s | %-15.2f | %-15s | %-10.2f | %-15.2f%n",
                    emp.getEmpId(), emp.getName(), type, emp.getBasicSalary(),
                    bonusOrDuration, emp.calculateTax(), ((Payable) emp).calculateNetSalary());
            System.out.println("--------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("Error: Employee with ID " + id + " not found. (ID not found)"); // [cite: 74]
        }
    }

    // 4. Highest Net Salary [cite: 56]
    public void findHighestNetSalary() {
        if (employees.isEmpty()) {
            System.out.println("No employees to compare.");
            return;
        }

        Employee highestPaid = employees.get(0); // Initialize with the first employee
        double maxNetSalary = ((Payable) employees.get(0)).calculateNetSalary();

        // Find employee with the highest net salary
        for (int i = 1; i < employees.size(); i++) {
            Employee currentEmp = employees.get(i);
            double currentNetSalary = ((Payable) currentEmp).calculateNetSalary();
            if (currentNetSalary > maxNetSalary) {
                maxNetSalary = currentNetSalary;
                highestPaid = currentEmp;
            }
        }

        System.out.println("\n--- EMPLOYEE WITH HIGHEST NET SALARY ---");
        // Display details (can reuse search logic for tabular display)
        System.out.printf("%-5s | %-15s | %-10s | %-15s | %-15s | %-10s | %-15s%n",
                "ID", "Name", "Type", "Basic Salary", "Bonus/Duration", "Tax", "Net Salary");
        System.out.println("--------------------------------------------------------------------------------------------------");

        String bonusOrDuration;
        String type = highestPaid.getEmployeeType();

        if (highestPaid instanceof PermanentEmployee) {
            PermanentEmployee pEmp = (PermanentEmployee) highestPaid;
            bonusOrDuration = String.format("%.2f", pEmp.getBonus());
        } else if (highestPaid instanceof ContractEmployee) {
            ContractEmployee cEmp = (ContractEmployee) highestPaid;
            bonusOrDuration = cEmp.getContractDuration() + " months";
        } else {
            bonusOrDuration = "N/A";
        }

        System.out.printf("%-5s | %-15s | %-10s | %-15.2f | %-15s | %-10.2f | %-15.2f%n",
                highestPaid.getEmpId(), highestPaid.getName(), type, highestPaid.getBasicSalary(),
                bonusOrDuration, highestPaid.calculateTax(), ((Payable) highestPaid).calculateNetSalary());
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    // 5. Average Salary [cite: 58]
    public double calculateAndDisplayAverageSalary() {
        if (employees.isEmpty()) {
            System.out.println("No employees to calculate average salary.");
            return 0.0;
        }

        double totalNetSalary = 0;
        for (Employee emp : employees) {
            totalNetSalary += ((Payable) emp).calculateNetSalary();
        }

        double averageNetSalary = totalNetSalary / employees.size(); // Calculate average net salary
        System.out.println("\n--- AVERAGE NET SALARY ---");
        System.out.println("Total Employees: " + employees.size());
        System.out.println("Overall Average Net Salary: " + String.format("%.2f", averageNetSalary));
        return averageNetSalary;
    }

    // 6. Generate Payslip
    public void generatePayslipForEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID to generate payslip: ");
        String id = scanner.nextLine();
        Employee emp = findEmployeeById(id);

        if (emp != null) {
            // Print the payslip using the generatePayslip() method
            ((Payable) emp).generatePayslip();
        } else {
            System.out.println("Error: Employee with ID " + id + " not found. (ID not found)");
        }
    }

    // 7. Exit and Summary
    public void exitProgram() {
        System.out.println("\n--- EXITING PROGRAM ---");
        System.out.println("Summary of total employees and overall average salary:"); // [cite: 65]
        System.out.println("Total Employees in System: " + employees.size()); // [cite: 65]
        calculateAndDisplayAverageSalary(); // Calculate and display average salary [cite: 65]
        System.out.println("Thank you for using the Employee Payroll Management System.");
    }

    // Main menu loop
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PayrollManager manager = new PayrollManager();
        int option = -1;

        System.out.println("Welcome to Employee Payroll Management System"); // [cite: 78]

        while (option != 7) {
            // Menu Display [cite: 79, 80, 81, 82, 83, 84, 85]
            System.out.println("\n----------------------------------");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Search Employee");
            System.out.println("4. Highest Net Salary");
            System.out.println("5. Average Salary");
            System.out.println("6. Generate Payslip");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter a number between 1 and 7.");
                continue;
            }

            switch (option) {
                case 1:
                    manager.addEmployee(scanner);
                    break;
                case 2:
                    manager.viewAllEmployees();
                    break;
                case 3:
                    manager.searchEmployee(scanner);
                    break;
                case 4:
                    manager.findHighestNetSalary();
                    break;
                case 5:
                    manager.calculateAndDisplayAverageSalary();
                    break;
                case 6:
                    manager.generatePayslipForEmployee(scanner);
                    break;
                case 7:
                    manager.exitProgram();
                    break;
                default:
                    System.out.println("Invalid option. Please choose a number between 1 and 7.");
            }
        }
        scanner.close();
    }
}