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
//        return String.format("%11d\t%-13s\t%-15s", employeeID, nameOfEmployee, employeeDesignation);
        return String.format("%11d\t%-25s\t%-25s", employeeID, nameOfEmployee, employeeDesignation);
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
        boolean valid_NewID = validateEmployeeID(newID);
        String answerType = "N";
        boolean unique_employeeID = false;
        while ((valid_NewID) && (answerType.equalsIgnoreCase("N")) && !(unique_employeeID)) {
            //First Validation: check for same input as existing record
            if (Integer.parseInt(newID) == getEmployeeID()) {
                //newID is a valid input and same as existing Employee ID
                System.out.println();
                System.out.println("Employee ID remains the same?");
                System.out.println("Y for Yes or N for No");
                answerType = sc.nextLine();
                while (!answerType.equalsIgnoreCase("Y")
                        && !answerType.equalsIgnoreCase("N")) {
                    System.out.println("Invalid answer. Please try again.");
                    System.out.println();
                    System.out.println("Employee ID remains the same?");
                    System.out.println("Y for Yes or N for No");
                    answerType = sc.nextLine();
                }
                if (answerType.equalsIgnoreCase("N")) {
                    //if answerType is N (change Employee ID), then proceed the next step for newID input
                    System.out.println("Please enter a unique Employee ID.");
                    System.out.println();
                    System.out.print("Enter new ID (between 1 and 4 digits) of the employee: ");
                    newID = sc.nextLine();
                    newID = newID.trim();
                    valid_NewID = validateEmployeeID(newID);
                    //unique_employeeID = Main.verifyEmployeeID(newID);
                } else {
                    //if answerType is Y (Employee ID remains), then proceed the next step for newName input
                    unique_employeeID = true;
                }
            } else {
                //newID is a valid input and different Employee ID is entered
                //Proceed to verification of Employee ID for uniqueness
                unique_employeeID = Main.verifyEmployeeID(newID);
                //Break from the while loop for different newID
                if (unique_employeeID) {
                    answerType = "Y";
                } else {
                    //Employee ID exists in database
                    //Proceed to the next step for newID input
                    System.out.println();
                    System.out.println("Please enter a unique Employee ID.");
                    System.out.println();
                    System.out.print("Enter new ID (between 1 and 4 digits) of the employee: ");
                    newID = sc.nextLine();
                    newID = newID.trim();
                    valid_NewID = validateEmployeeID(newID);
                    //unique_employeeID = Main.verifyEmployeeID(newID);
                }
            }
        }
        this.employeeID = Integer.parseInt(newID);

        //if answerType is Y, then continue with the next step for newName input
        //Or the answerType is N, but user enter unique Employee ID with another newID input
        System.out.print("Enter new name (alphabets, whitespace only, max 25 letters) of the employee: ");
        String newName = sc.nextLine();
        newName = newName.trim();
        validateNameOfEmployee(newName);

        System.out.print("Enter new job title (alphanumeric, whitespace only, max 25 characters) of the employee: ");
        String newDesignation = sc.nextLine();
        newDesignation = newDesignation.trim();
        validateEmployeeDesignation(newDesignation);
    }



    public boolean validateEmployeeID(String newID) {
        //regular expression to check if the input is a whole number (up to 4 digits)
        //"" matches directly (character for character) in the string pattern
        //this is the simplest form of regular expression that is plain, literal text, which has no special meaning
        //\d matches any of the digits from 0 to 9, with \D does the inverse, matching all characters except digits
        //[] matches your own character classes using square brackets around the characters you want
        //^ matches any character except those included in the brackets
        //you use by placing a caret (^) as the first character inside the brackets to invert the character class
        //x-y matches a range notation between x and y, it can be used as shorthand for consecutive runs of alphanumeric characters
        //{x,y} specifies a precise range to match between a lower bound (x) and an upper bound (y)
        //{x,} specifies at least x or more iterations to match between a lower bound (x) and an infinite upper bound
        //if y is infinite, it is a way to specify a minimum of occurrences with no maximum
        //{1,4} matches digits with 1, 2, 3, or 4 digits length
        //Pattern.matches("\\d{1,4}", newID) is true for any numeric inputs that are between 1 and 4 digits
        //newID_isInteger = Pattern.matches("\\d{1,4}", newID)
        boolean valid_NewID = false;
        boolean newID_isInteger = Pattern.matches("\\d{1,4}", newID);
        if (false) {
            String s1 = Boolean.toString(newID_isInteger);
            System.out.format("newID is %s\n", newID);
            System.out.format("newID_isInteger is %s\n", s1);
        }

        if ((newID_isInteger) && ((Integer.parseInt(newID))!=0) && (!newID.isEmpty())) {
                //this.employeeID = Integer.parseInt(newID);
                //newID is a valid input, then verify for uniqueness
                valid_NewID = true;
        } else if (newID.isEmpty()) {
            throw new NullPointerException("Employee ID must not be null!");
        } else if ((!newID_isInteger) && ((Integer.parseInt(newID))<=9999)) {
            throw new NumberFormatException("Employee ID must be integer digits!");
        } else if ((Integer.parseInt(newID))==0) {
            throw new NumberFormatException("Employee ID must not be 0!");
        } else if ((Integer.parseInt(newID))>9999) {
            throw new NumberFormatException("Employee ID must be within 1 and 4 digits!");
        }
        return valid_NewID;
    }

    public void validateNameOfEmployee(String newName) {
        //regular expression to check if the input is an alphabet string
        //^ (caret) is placed as the first character inside the square brackets to invert the character class
        //[^0-9] matches any character except those included in the square brackets, that is, it isn't a digit
        //position characters allow you to designate the relative location of a match within a line
        //with the most important position characters are ^ and $, which match the beginning and end of a line, respectively
        //for example,
        //The phrase "A rose is a rose is a rose"
        //the pattern "[Aa] rose" matches an included upper- or lowercase A followed by the word rose
        //it matches 3 times in the phrase
        //the pattern "^[Aa] rose" matches an included upper- or lowercase A followed by the word rose at the beginning of line
        //it matches "A rose" in the phrase at the beginning of line
        //the pattern "[Aa] rose$" matches an included upper- or lowercase A followed by the word rose at end of line
        //it matches "a rose" in the phrase at end of line
        //the position markers \b and \B match a word boundary (whitespace, punctuation, or the beginning or end of a line),
        //or a non-word boundary (the middle of a word), respectively.
        //for example,
        //the pattern "\brose"
        //it matches "rose" and "rosemary" and "roses", but not "primrose"
        //the pattern "\Brose"
        //it matches "primrose" and "prose", but not "rose" or "rosemary"
        //Pattern.matches("[^0-9]{1,25}", newName) is true for any characters except digits
        //Pattern.matches("[A-Z[a-z]\\s]{1,25}", newName)
        //newName_isAlphabet = Pattern.matches("[A-Za-z\\s]{1,25}", newName)
        boolean newName_isAlphabet = Pattern.matches("[A-Za-z\\s]{1,25}", newName);
        if (false) {
            String s2 = Boolean.toString(newName_isAlphabet);
            System.out.format("newName is %s\n", newName);
            System.out.format("newName_isAlphabet is %s\n", s2);
            String s = Boolean.toString(newName.isEmpty());
            System.out.format("newName.isEmpty is %s\n", s);
        }

        if ((newName_isAlphabet) && (!newName.isEmpty())) {
            this.nameOfEmployee = newName;
        } else {
            if (newName.isEmpty()) {
                throw new NullPointerException("Employee name must not be empty string!");
            } else { //if (!newName_isAlphabet) {
                throw new NumberFormatException("Employee name must be alphabets!");
            }
        }
    }

    public void validateEmployeeDesignation(String newDesignation) {
        //regular expression to check if the input is an alphanumeric string
        //\w matches characters typically found in "words",
        // such as A-Z (uppercase letters), a-z (lowercase letters), 0-9 (digits) and _ (underscore),
        // with \W matches everything except those characters
        //\s matches whitespace characters include any characters relate to visual space in text or that marks the end of line,
        // such as <space>, \t (tab), \r (carriage return), \n (newline), and \f (form feed),
        // with \S matches any character that is not whitespace
        //Pattern.matches("\\w\\s{1,25}", newDesignation) is true for the above character classes
        //Pattern.matches("[[A-Z][a-z][0-9]\\s]{1,25}", newDesignation)
        //newDesignation_isAlphanumeric = Pattern.matches("[A-Za-z0-9\\s]{1,25}", newDesignation)
        boolean newDesignation_isAlphanumeric = Pattern.matches("[A-Za-z0-9\\s]{1,25}", newDesignation);
        if (false) {
            String s3 = Boolean.toString(newDesignation_isAlphanumeric);
            System.out.format("newDesignation is %s\n", newDesignation);
            System.out.format("newDesignation_isAlphanumeric is %s\n", s3);
        }

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

    //Method for FullTimeEmployee
    public void validateBaseMonthlySalary(String baseMonthlySalary) {
    }

    //Method for PartTimeEmployee
    public void validateNumberOfHoursWorked(String newHours) {
    }

    //Method for PartTimeEmployee
    public void validateBaseHourlyRate(String newRate) {
    }
}
