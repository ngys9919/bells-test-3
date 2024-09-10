// Staff Management System
// In this assessment, you'll create a simple Staff Management System
// using the concepts you learned in the Java Lab for Inventory Management System.
// Instead of managing inventory (like physical and digital products),you'll be managing employees in a company.

// C = Create: adding new data to the database
// R = Read: get existing information
// U = Update: update existing information in the database
// D = Delete: remove existing information from the database

//

// Using ArrayList to store our database
// - ArrayList<Employee>: represents the entire staff database
// - Employee abstract class: represents the parent of staff employees
// -- FullTimeEmployee class: represents the child of full-time employees
// -- PartTimeEmployee class: represents the child of part-time employees

// - ArrayList<Contractor>: represents the contractor database
// - Contractor class: represents the contract workers

// - ArrayList<PayablePerson>: represents the payable database
// - PayablePerson interface: represents the salaried persons for full-time/part-time/contract

import java.util.ArrayList;
//import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<Employee> staffList = new ArrayList<>();
        ArrayList<Contractor> contractList = new ArrayList<>();

        while (true) {
            displayMenu();
            int choice = getMenuChoice();
            boolean keepRunning = processChoice(choice, staffList, contractList);
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
        System.out.println("5. Display monthly salary");
        System.out.println("6. Quit");
        System.out.println();
    }

    // get the choice that the user has entered
    public static int getMenuChoice() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        System.out.println();
        if (choice >= 1 && choice <= 6) {
            return choice;
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
        return 0;
    }

    // depending on the user has entered at the menu, do something
    // if return true, keep running the program
    // if return false, quit the program
    public static boolean processChoice(int choice, ArrayList<Employee> staffList, ArrayList<Contractor> contractList) {
        if (choice == 1) {
            displayAllEmployees(staffList, contractList);
        } else if (choice == 2) {
            addNewEmployee(staffList, contractList);
        } else if (choice == 3) {
            editEmployee(staffList);
        } else if (choice == 4) {
            removeEmployee(staffList, contractList);
        } else if (choice == 5) {
            displaySalaryItems(staffList, contractList);
        } else if (choice == 6) {
            System.out.println("Exiting...");
            return false;
        }
        return true;
    }

    public static void displayAllEmployees(ArrayList<Employee> staffList, ArrayList<Contractor> contractList) {
        System.out.println("===== Staff List Summary =====");
        System.out.println();
        if (staffList.isEmpty() && contractList.isEmpty()) {
            System.out.println("There is no employee record to display.");
            return;
        }

        if (false) {
            for (Employee e : staffList) {
                System.out.println(e.report());
            }
        }

        if (!staffList.isEmpty()) {
            String strHeader = "Employee Records for Full-time/Part-time Staff";
            System.out.println(strHeader);
            System.out.println("==============================================");
            String strTableHeader = "Employee ID\tEmployee Name\tJob Designation\tEmployee Type\tNett Monthly Salary";
            System.out.println(strTableHeader);
            for (Employee e : staffList) {
                System.out.println(e.formattedReport());
            }
            System.out.println();
        }

        if (!contractList.isEmpty()) {
            String strHeader2 = "Employee Records for Contract Worker(s)";
            System.out.println(strHeader2);
            System.out.println("=======================================");
            for (Contractor c : contractList) {
                System.out.format("Employee ID: %d Contract Duration: %d days Nett Monthly Salary: $%.2f\n", c.getEmployeeID(), c.getDurationOfContract(), c.getBasicMonthlySalary());
            }
        }
    }

    public static void addNewEmployee(ArrayList<Employee> staffList, ArrayList<Contractor> contractList) {
        System.out.println("===== Add New Employee =====");
        System.out.println();
        System.out.println("Enter employee type to create: ");
        System.out.println("F for full-time or P for part-time or C for contract");

        Scanner sc = new Scanner(System.in);
        String employeeType = sc.nextLine();

        while (!employeeType.equalsIgnoreCase("F")
                && !employeeType.equalsIgnoreCase("P")
                && !employeeType.equalsIgnoreCase("C")) {
            System.out.println("Invalid employee type. Please try again.");
            System.out.println();
            System.out.println("Enter employee type to create: ");
            System.out.println("F for full-time or P for part-time or C for contract");
            employeeType = sc.nextLine();
        }

        if (employeeType.equalsIgnoreCase("C")) {
            // ask the user for information pertaining to the Contractor class
            addNewContractEmployee(contractList);
            return;
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
        staffList.add(newEmployee);
        System.out.println("New employee successfully added!");
    }


    public static void editEmployee(ArrayList<Employee> staffList) {
        System.out.println("===== Edit Existing Employee =====");
        System.out.println();
        if (staffList.isEmpty()) {
            System.out.println("There is no employee record to edit.");
            return;
        }

        // 1. ask the user to select which employee to edit
        for (int i = 0; i < staffList.size(); i++) {
            System.out.println("index " + (i + 1) + ": " + staffList.get(i).getNameOfEmployee());
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter index to edit: ");
        int staffIndex = sc.nextInt() - 1;

        // 2. base on the selection, we will ask the questions for
        // the new values for the employee
        if (staffIndex >= 0 && staffIndex < staffList.size()) {
            Employee staffToEdit = staffList.get(staffIndex);
            staffToEdit.edit();
            System.out.println("Employee successfully edited!");
        } else {
            System.out.println("Invalid index.");
        }
    }

    public static void removeEmployee(ArrayList<Employee> staffList, ArrayList<Contractor> contractList) {
        System.out.println("===== Remove Employee Record =====");
        System.out.println();
        if (staffList.isEmpty()) {
            System.out.println("There is no employee record to delete.");
            return;
        }

        // 1. ask the user to select which employee to remove
        for (int i = 0; i < staffList.size(); i++) {
            System.out.println("index " + (i + 1) + ": " + staffList.get(i).getNameOfEmployee());
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter index to delete: ");
        int employeeIndex = sc.nextInt() - 1;

        // 2. base on the selection, we will delete
        // the record for the employee
        if (employeeIndex >= 0 && employeeIndex < staffList.size()) {
            staffList.remove(employeeIndex);
            System.out.println("Employee successfully deleted!");
            displayAllEmployees(staffList, contractList);
        } else {
            System.out.println("Invalid index.");
        }
    }

    private static void addNewContractEmployee(ArrayList<Contractor> contractList) {
        // create a placeholder and set it to empty
        Contractor newContractEmployee = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ID of the contract worker: ");
        int employeeID = sc.nextInt();
        System.out.print("Enter basic monthly salary: ");
        double basicMonthlySalary = sc.nextDouble();
        System.out.print("Enter contractual period (in days): ");
        int durationOfContract = sc.nextInt();

        newContractEmployee = new Contractor(employeeID, basicMonthlySalary, durationOfContract);
        contractList.add(newContractEmployee);

        System.out.println("New contract worker successfully added!");
    }

    private static void displaySalaryItems(ArrayList<Employee> staffList, ArrayList<Contractor> contractList) {
        ArrayList<PayablePerson> salaryItems = new ArrayList<>();

        // Add all Employee instances to the salaryItems list
        for (Employee e : staffList) {
            salaryItems.add(e);
        }

        // Add all Contractor instances to the salaryItems list
        for (Contractor c : contractList) {
            salaryItems.add(c);
        }

        if (salaryItems.isEmpty()) {
            System.out.println("There is no salary items to display.");
            return;
        }

        // Display details of each taxable item
        System.out.println("Salary Items:");
        System.out.println("-------------");
        for (PayablePerson item : salaryItems) {
            System.out.format("Employee ID: %d Nett Monthly Salary: $%.2f\n", item.getEmployeeID(), item.calculateMonthlySalary());
        }
    }
}