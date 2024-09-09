import java.util.Scanner;

//public abstract class Employee implements PayableStaff {
public abstract class Employee {
    protected int employeeID;
    protected String nameOfEmployee;
    protected String employeeDesignation;
    public abstract double calculateMonthlySalary();

    //Default Constructor
    public Employee() {
        employeeID = 0;
        nameOfEmployee = "N/A";
        employeeDesignation = "N/A";
    }

    //Overloaded Constructor
    public Employee(int employeeID, String nameOfEmployee, String employeeDesignation) {
        this.employeeID = employeeID;
        this.nameOfEmployee = nameOfEmployee;
        this.employeeDesignation = employeeDesignation;
    }

    //Getters and Setters
    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getNameOfEmployee() {
        return nameOfEmployee;
    }

    public void setNameOfEmployee(String nameOfEmployee) {
        this.nameOfEmployee = nameOfEmployee;
    }

    public String getEmployeeDesignation() {
        return employeeDesignation;
    }

    public void setEmployeeDesignation(String employeeDesignation) {
        this.employeeDesignation = employeeDesignation;
    }

    public String report() {
        return "Employee ID: " + employeeID + "\n"
                + "Employee Name: " + nameOfEmployee + "\n"
                + "Job Designation: " + employeeDesignation + "\n";
    }

    public void edit() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter ID of the new employee: ");
        int newID = sc.nextInt();
        sc.nextLine(); // consume the buffered new line
        this.employeeID = newID;

        System.out.print("Enter name of the new employee: ");
        String newName = sc.nextLine();
        this.nameOfEmployee = newName;

        System.out.print("Enter job title of the new employee: ");
        String newDesignation = sc.nextLine();
        this.employeeDesignation = newDesignation;
    }
}
