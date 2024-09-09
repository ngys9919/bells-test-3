// Staff Management System
// In this assessment, you'll create a simple Staff Management System
// using the concepts you learned in the Java Lab for Inventory Management System.
// Instead of managing inventory (like physical and digital products),you'll be managing employees in a company.

// C = Create: adding new data to the database
// R = Read: get existing information
// U = Update: update existing information in the database
// D = Delete: remove existing information from the database

//

// represent our database
// - ArrayList<Employee>: represents the entire database
// - Employee class: represents the parent of all employees
// -- FullTimeEmployee class: represents the child of full-time employees
// -- PartTimeEmployee class: represents the child of part-time employees

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<Employee> staff = new ArrayList<>();

        while (true) {
            displayMenu();
            int choice = getMenuChoice();
            boolean keepRunning = processChoice(choice, staff);
            if (!keepRunning) {
                break;
            }
        }
     
    }

    public static void displayMenu() {
        System.out.println();
        System.out.println("Staff Management System Menu");
        System.out.println("1. List all employees"); // Read
        System.out.println("2. Add new employee"); // Create
        System.out.println("3. Edit existing employee"); // Update
        System.out.println("4. Remove employee record"); // Delete
        System.out.println("5. Quit");
        System.out.println();
    }

    // get the choice that the user has entered
    public static int getMenuChoice() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        if (choice >= 1 && choice <= 5) {
            return choice;
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
        return 0;
    }

    // depending on the user has entered at the menu, do something
    // if return true, keep running the program
    // if return false, quit the program
    public static boolean processChoice(int choice, ArrayList<Employee> staff) {
        if (choice == 1) {
            displayAllEmployees(staff);
        }
        if (choice == 2) {
            addNewEmployee(staff);
        }
        if (choice == 3) {
            editEmployee(staff);
        }
        if (choice == 4) {
            removeEmployee(staff);
        }
        if (choice == 5) {
            return false;
        }
        return true;
    }

    public static void displayAllEmployees(ArrayList<Employee> staff) {
        System.out.println("===== Staff List Summary =====");
        for (Employee e : staff) {
            System.out.println(e.report());
        }
    }

    public static void addNewEmployee(ArrayList<Employee> staff) {
        System.out.println("===== Add New Employee =====");
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter employee type to create: ");
        System.out.println("F for full-time or P for part-time");

        String employeeType = sc.nextLine();
        while (!employeeType.equalsIgnoreCase("F") && !employeeType.equalsIgnoreCase("P")) {
            System.out.println("Enter employee type to create: ");
            System.out.println("F for full-time or P for part-time");
            employeeType = sc.nextLine();
        }

        // ask the user to enter details for parent Employee class
        System.out.print("Enter ID of the new employee: ");
        int employeeID = sc.nextInt();
        sc.nextLine(); // consume the buffered new line

        System.out.print("Enter name of the new employee: ");
        String nameOfEmployee = sc.nextLine();

        System.out.print("Enter job title of the new employee: ");
        String employeeDesignation = sc.nextLine();

        // create a placeholder and set it to empty
        Employee newEmployee = null;

        if (employeeType.equalsIgnoreCase("F")) {
            // ask the user for information pertaining to the FullTimeEmployee
            System.out.print("Enter basic monthly salary: ");
            double baseMonthlySalary = sc.nextDouble();

            newEmployee = new FullTimeEmployee(employeeID, nameOfEmployee, employeeDesignation, baseMonthlySalary);
        } else if (employeeType.equalsIgnoreCase("P")) {
            // ask the user for the information needed to create PartTimeEmployee
            System.out.print("Enter number of hours worked: ");
            int numberOfHoursWorked = sc.nextInt();

            System.out.print("Enter basic hourly rate: ");
            double baseHourlyRate = sc.nextDouble();

            newEmployee = new PartTimeEmployee(employeeID, nameOfEmployee, employeeDesignation, numberOfHoursWorked, baseHourlyRate);

        }
        staff.add(newEmployee);
    }


    public static void editEmployee(ArrayList<Employee> staff) {
        System.out.println("===== Edit Existing Employee =====");

        // 1. ask the user to select which employee to edit
        for (int i = 0; i < staff.size(); i++) {
            System.out.println(i + ": " + staff.get(i).getNameOfEmployee());
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter index to edit: ");
        int staffIndex = sc.nextInt();

        // 2. base on the selection, we will ask the questions for
        // the new values for the employee
        Employee staffToEdit = staff.get(staffIndex);
        staffToEdit.edit();

    }

    public static void removeEmployee(ArrayList<Employee> staff) {
        System.out.println("===== Remove Employee Record =====");

        // 1. ask the user to select which employee to remove
        for (int i = 0; i < staff.size(); i++) {
            System.out.println(i + ": " + staff.get(i).getNameOfEmployee());
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter index to delete: ");
        int employeeIndex = sc.nextInt();

        // 2. base on the selection, we will delete
        // the record for the employee
        Employee employeeToRemove = staff.get(employeeIndex);
        //employeeToRemove.delete();

    }
}