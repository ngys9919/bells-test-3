import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class Employee implements PayablePerson {
//public abstract class Employee {
    protected int employeeID;
    protected String nameOfEmployee;
    protected String employeeDesignation;
    public abstract double calculateMonthlySalary();
    public abstract String employeeCategory();

    //Default Constructor
    public Employee() {
        employeeID = 9999;
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

    public String formattedReport() {
        return String.format("%11d\t%-13s\t%-15s", employeeID, nameOfEmployee, employeeDesignation);
    }

    public String report() {
        return "Employee ID: " + employeeID + "\n"
                + "Employee Name: " + nameOfEmployee + "\n"
                + "Job Designation: " + employeeDesignation + "\n";
    }

    public void edit() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter new ID (between 1 and 4 digits) of the employee: ");
        String newID = sc.nextLine();
        newID = newID.trim();
        //regular expression to check if the input is a digit number
        //\d matches any of the digits from 0 to 9
        //{1,4} matches digits with 1, 2, 3, or 4 digits length
        //Pattern.matches("\\d{1,4}", newID) is true for any numeric inputs that are between 1 and 4 digits
        boolean newID_isInteger = Pattern.matches("\\d{1,4}", newID);
        String s1 = Boolean.toString(newID_isInteger);
//        int newIntID = Integer.parseInt(newID);
        System.out.format("newID is %s\n", newID);
        System.out.format("newID_isInteger is %s\n", s1);
        if ((newID_isInteger) && ((Integer.parseInt(newID))!=0) && (newID.isEmpty())) {
            this.employeeID = Integer.parseInt(newID);
        } else if (newID.isEmpty()) {
            throw new NullPointerException("Employee ID must not be null!");
        } else if ((!newID_isInteger) && ((Integer.parseInt(newID))<=9999)) {
            throw new NumberFormatException("Employee ID must be integer digits!");
        } else if ((Integer.parseInt(newID))==0) {
            throw new NumberFormatException("Employee ID must not be 0!");
        } else if ((Integer.parseInt(newID))>9999) {
            throw new NumberFormatException("Employee ID must be within 1 and 4 digits!");
        }

        System.out.print("Enter new name (alphabets, whitespace only, max 25 letters) of the employee: ");
        String newName = sc.nextLine();
        newName = newName.trim();
        //regular expression to check if the input is an alphabet string
        //^ (caret) is placed as the first character inside the square brackets to invert the character class
        //[^0-9] matches any character except those included in the square brackets, that is, it isn't a digit
        //Pattern.matches("[^0-9]{1,25}", newName) is true for any characters except digits
        //Pattern.matches("[A-Z[a-z]\\s]{1,25}", newName);
        boolean newName_isAlphabet = Pattern.matches("[A-Z[a-z]\\s]{1,25}", newName);
        String s2 = Boolean.toString(newName_isAlphabet);
        System.out.format("newName is %s\n", newName);
        System.out.format("newName_isAlphabet is %s\n", s2);
        String s = Boolean.toString(newName.isEmpty());
        System.out.format("newName.isEmpty is %s\n", s);
        if ((newName_isAlphabet) && (!newName.isEmpty())) {
            this.nameOfEmployee = newName;
        } else {
            if (newName.isEmpty()) {
                throw new NullPointerException("Employee name must not be empty string!");
            } else { //if (!newName_isAlphabet) {
                throw new NumberFormatException("Employee name must be alphabets!");
            }
        }

        System.out.print("Enter new job title (alphanumeric, whitespace only, max 25 characters) of the employee: ");
        String newDesignation = sc.nextLine();
        newDesignation = newDesignation.trim();
        //regular expression to check if the input is an alphanumeric string
        //\w matches characters typically found in "words",
        // such as A-Z (uppercase letters), a-z (lowercase letters), 0-9 (digits) and _ (underscore).
        //\s matches whitespace characters include any characters relate to visual space in text or that marks the end of line,
        // such as <space>, \t (tab), \r (carriage return), \n (newline), and \f (form feed).
        //Pattern.matches("\\w\\s{1,25}", newDesignation) is true for the above character classes
        //Pattern.matches("[[A-Z][a-z][0-9]\\s]{1,25}", newDesignation);
        boolean newDesignation_isAlphanumeric = Pattern.matches("[[A-Z][a-z][0-9]\\s]{1,25}", newDesignation);
        String s3 = Boolean.toString(newDesignation_isAlphanumeric);
        System.out.format("newDesignation is %s\n", newDesignation);
        System.out.format("newDesignation_isAlphanumeric is %s\n", s3);
        if ((newDesignation_isAlphanumeric) && (!newDesignation.isEmpty())) {
            this.employeeDesignation = newDesignation;
        } else {
            if (newDesignation.isEmpty()) {
                throw new NullPointerException("Employee designation must not be empty string!");
            } else { //if (!newDesignation_isAlphanumeric) {
                throw new NumberFormatException("Employee designation must be alphanumeric!");
            }
        }
    }
}
