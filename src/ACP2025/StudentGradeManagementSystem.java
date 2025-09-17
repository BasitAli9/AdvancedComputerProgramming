package ACP2025;
import java.util.Scanner;


public class StudentGradeManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int maxStudents = 50;
        int[] rollNo = new int[maxStudents];
        String[] name = new String[maxStudents];
        int[][] marks = new int[maxStudents][3];
        int count = 0;

        int choice;
        do {
            System.out.println("\n===== Student Grade Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Update Marks");
            System.out.println("3. Remove Student");
            System.out.println("4. View All Students");
            System.out.println("5. Search Student");
            System.out.println("6. Highest Scorer");
            System.out.println("7. Class Average");
            System.out.println("8. Exit");
            System.out.print("Choose option: ");
            choice = sc.nextInt();

            if (choice == 1) { // Add Student
                if (count >= maxStudents) {
                    System.out.println("Error: Cannot add more than 50 students.");
                } else {
                    System.out.print("Enter Roll No: ");
                    int roll = sc.nextInt();
                    boolean exist = false;

                    for (int i = 0; i < count; i++) {
                        if (rollNo[i] == roll) {
                            exist = true;
                            break;
                        }
                    }

                    if (exist) {
                        System.out.println("Error: Roll number already exists!");
                    } else {
                        rollNo[count] = roll;
                        sc.nextLine(); // buffer clear
                        System.out.print("Enter Name: ");
                        name[count] = sc.nextLine();

                        // Marks input
                        for (int j = 0; j < 3; j++) {
                            int m;
                            do {
                                System.out.print("Enter Marks in Subject " + (j + 1) + " (0-100): ");
                                m = sc.nextInt();
                                if (m < 0 || m > 100) {
                                    System.out.println("Invalid input! Marks must be between 0 and 100.");
                                }
                            } while (m < 0 || m > 100);
                            marks[count][j] = m;
                        }

                        count++;
                        System.out.println("Student added successfully!");
                    }
                }
            }


            else if (choice == 2) { // Update Marks
                System.out.print("Enter Roll No: ");
                int roll = sc.nextInt();
                boolean found = false;

                // Student search
                for (int i = 0; i < count; i++) {
                    if (rollNo[i] == roll) {
                        found = true; // if student found
                        System.out.println("Student found: " + name[i]);

                        // Update marks
                        for (int j = 0; j < 3; j++) {
                            int m;
                            do {
                                System.out.print("Enter new Marks in Subject " + (j + 1) + " (0-100): ");
                                m = sc.nextInt();
                                if (m < 0 || m > 100) {
                                    System.out.println("Invalid input! Marks must be between 0 and 100.");
                                }
                            } while (m < 0 || m > 100);

                            marks[i][j] = m;
                        }

                        System.out.println("Marks updated successfully!");
                        break; // break loop if student found
                    }
                }

                if (!found) {
                    System.out.println("Error: Student not found!");
                }
            }


            else if (choice == 3) { // Remove Student
                System.out.print("Enter Roll No: ");
                int roll = sc.nextInt();
                boolean removed = false;

                for (int i = 0; i < count; i++) {
                    if (rollNo[i] == roll) {
                        System.out.println("Student found: " + name[i]);

                        // Shift arrays left to remove the student
                        for (int j = i; j < count - 1; j++) {
                            rollNo[j] = rollNo[j + 1];
                            name[j] = name[j + 1];

                            // Copy marks (since it's a 2D array)
                            for (int k = 0; k < 3; k++) {
                                marks[j][k] = marks[j + 1][k];
                            }
                        }

                        count--;
                        removed = true;
                        System.out.println("Student removed successfully!");
                        break;
                    }
                }

                if (!removed) {
                    System.out.println("Error: Student not found!");
                }
            }


            else if (choice == 4) { // View All
                if (count == 0) {
                    System.out.println("No students available.");
                } else {
                    System.out.println("-------------------------------------------------------------");
                    System.out.printf("%-6s %-12s %-6s %-6s %-6s %-7s %-6s\n", "Roll", "Name", "Sub1", "Sub2", "Sub3",
                            "Total", "Avg");
                    System.out.println("-------------------------------------------------------------");

                    for (int i = 0; i < count; i++) {
                        int total = marks[i][0] + marks[i][1] + marks[i][2];
                        double avg = total / 3.0;

                        System.out.printf("%-6d %-12s %-6d %-6d %-6d %-7d %-6.2f\n", rollNo[i], name[i], marks[i][0],
                                marks[i][1], marks[i][2], total, avg);
                    }

                    System.out.println("-------------------------------------------------------------");
                }
            }
            else if (choice == 5) { // Search Student
                System.out.print("Enter Roll No: ");
                int roll = sc.nextInt();
                boolean found = false;

                for (int i = 0; i < count; i++) {
                    if (rollNo[i] == roll) {
                        found = true;

                        int total = marks[i][0] + marks[i][1] + marks[i][2];
                        double avg = total / 3.0;

                        System.out.println("-------------------------------------------------------------");
                        System.out.printf("%-6s %-12s %-6s %-6s %-6s %-7s %-6s\n", "Roll", "Name", "Sub1", "Sub2",
                                "Sub3", "Total", "Avg");
                        System.out.println("-------------------------------------------------------------");

                        System.out.printf("%-6d %-12s %-6d %-6d %-6d %-7d %-6.2f\n",
                                rollNo[i], name[i],
                                marks[i][0], marks[i][1], marks[i][2],
                                total, avg);

                        System.out.println("-------------------------------------------------------------");
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Error: Student not found!");
                }
            }

            else if (choice == 6) { // Highest Scorer
                if (count == 0) {
                    System.out.println("No students available.");
                } else {
                    int best = 0;
                    int bestTotal = marks[0][0] + marks[0][1] + marks[0][2];

                    for (int i = 1; i < count; i++) {
                        int total = marks[i][0] + marks[i][1] + marks[i][2];
                        if (total > bestTotal) {
                            best = i;
                            bestTotal = total;
                        }
                    }

                    double avg = bestTotal / 3.0;

                    System.out.println("-------------------------------------------------------------");
                    System.out.printf("%-6s %-12s %-6s %-6s %-6s %-7s %-6s\n",
                            "Roll", "Name", "Sub1", "Sub2", "Sub3", "Total", "Avg");
                    System.out.println("-------------------------------------------------------------");

                    System.out.printf("%-6d %-12s %-6d %-6d %-6d %-7d %-6.2f\n",
                            rollNo[best], name[best],
                            marks[best][0], marks[best][1], marks[best][2],
                            bestTotal, avg);

                    System.out.println("-------------------------------------------------------------");
                    System.out.println(" Highest Scorer: " + name[best] + " (Roll " + rollNo[best] + ")");
                }
            }

            else if (choice == 7) { // Class Average
                if (count == 0) {
                    System.out.println("No students available.");
                } else {
                    int sum = 0;
                    for (int i = 0; i < count; i++) {
                        sum += marks[i][0] + marks[i][1] + marks[i][2];
                    }
                    double classAvg = sum / (count * 3.0);

                    System.out.println("-----------------------------------------------------------");
                    System.out.printf("%-20s %-15s\n", "Total Students", "Class Average");
                    System.out.println("-----------------------------------------------------------");
                    System.out.printf("%-20d %-15.2f\n", count, classAvg);
                    System.out.println("-----------------------------------------------------------");
                }
            }


            else if (choice == 8) { // Exit
                System.out.println("Exiting... Total Students = " + count);
                if (count > 0) {
                    int sum = 0;
                    for (int i = 0; i < count; i++) {
                        sum += marks[i][0] + marks[i][1] + marks[i][2];
                    }
                    double classAvg = sum / (count * 3.0);
                    System.out.println("Final Class Average = " + classAvg);
                }
            }

            else {
                System.out.println("Invalid choice! Please enter 1–8.");
            }

        } while (choice != 8);

        sc.close();
    }
}
