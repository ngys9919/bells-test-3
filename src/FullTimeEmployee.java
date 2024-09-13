import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

class FullTimeEmployee extends Employee {
    private String employeeCategory;
    private double baseMonthlySalary;

    //Default Constructor
    public FullTimeEmployee() {
        super();
        employeeCategory = "Full-Time";
        baseMonthlySalary = 0;
    }

    //Overloaded Constructor
    public FullTimeEmployee(int employeeID, String nameOfEmployee, String employeeDesignation, double baseMonthlySalary) {
        super(employeeID, nameOfEmployee, employeeDesignation);
        this.baseMonthlySalary = baseMonthlySalary;
    }

    //Getter
    public double getBaseMonthlySalary() {
        return baseMonthlySalary;
    }

    //Setter
    public void setBaseMonthlySalary(double baseMonthlySalary) {
        this.baseMonthlySalary = baseMonthlySalary;
    }

    @Override
    public String employeeCategory() {
        return ("Full-Time");
    }

    @Override
    public double calculateMonthlySalary() {
        return (0.8 * baseMonthlySalary);
    }

    @Override
    public String formattedReport() {
        String str = super.formattedReport();
        return String.format(str + "\t%13s\t$%.2f", employeeCategory(), calculateMonthlySalary());
    }

    @Override
    public String report() {
        // super refers to the parent class
        // hence super.report() means call the `report` method in parent class
        return super.report() +
                "Type of Employee: " + employeeCategory() + "\n" +
                "Nett Monthly Salary: " + "$" + calculateMonthlySalary() + "\n";
    }

    @Override
    public void edit() {
        super.edit();

        System.out.println();
        System.out.println("Type of Employee: " + employeeCategory());

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter new basic monthly salary (up to 2 decimal places): ");
        String newSalary = sc.nextLine();
        newSalary = newSalary.trim();
        validateBaseMonthlySalary(newSalary);
    }

    @Override
    public void validateBaseMonthlySalary(String newSalary) {
        //regular expression to check if the input is a whole number or float number with up to 2 decimal places
        //. means allow any single character that matches the pattern after the dot
        //two dots matches any two characters with pattern after the dot
        //a dot operator normally stops only for a line terminator (a new line, a carriage return, or combination of both)
        //* means allow any number of that type of character (zero or more iterations)
        //+ means allow at least one number of that type of character (one or more iterations)
        //? means allow exactly zero or one of that type of character (zero or one iteration)
        //you use + ( which means one or more) instead of * (which means zero or more)
        //if you need to make the dot as mandatory you remove the ? mark
        //[0-9] matches digits in the range from 0 to 9
        //[.] matches decimal point //['.']
        //Pattern.matches("[0-9]*['.']?[0-9][0-9]", newSalary) is true for any numeric inputs with or without decimal point (up to 2 decimal places)
        //newSalary_isIntegerOrDecimal = Pattern.matches("[0-9]+[.]?|[0-9]*[.]?[0-9]|[0-9]*[.]?[0-9][0-9]", newSalary)
        boolean newSalary_isIntegerOrDecimal = Pattern.matches("[0-9]+[.]?|[0-9]*[.]?[0-9]|[0-9]*[.]?[0-9][0-9]", newSalary);

        String[] newSalary_split = {"0", "00"};
        if (newSalary_isIntegerOrDecimal) {
            try {
                // Attempt to parse the string as an integer
                Integer.parseInt(newSalary);

                // If successful, it is integer
                newSalary_split[1] = String.valueOf(0);

            } catch (NumberFormatException e) {
                // If parsing fails, it is decimal
                String regex = "[.]"; //[,.\\s] matches comma (,), dot (.) or whitespace (\s)
                newSalary_split = newSalary.split(regex);
                if (false) {
                    System.out.println("newSalary_split: ");
                    for (String s : newSalary_split) {
                        System.out.println(s);
                    }
                }
            }
        }

        if (false) {
            String s1 = Boolean.toString(newSalary_isIntegerOrDecimal);
            System.out.format("newSalary is %s\n", newSalary);
            System.out.format("newSalary_isIntegerOrDecimal is %s\n", s1);
        }

        if ((newSalary_isIntegerOrDecimal) && ((Double.parseDouble(newSalary)) != 0) && (!newSalary.isEmpty())) {
            this.baseMonthlySalary = Double.parseDouble(newSalary);
        } else if (newSalary.isEmpty()) {
            throw new NullPointerException("Basic monthly salary must not be null!");
        } else if (!newSalary_isIntegerOrDecimal) {
            throw new NumberFormatException("Basic monthly salary must be integer or decimal numbers (up to 2 decimal places)!");
        } else {
            if (((newSalary_split[0]).equals("0")) && (newSalary_split[1].isEmpty())) {
                throw new NumberFormatException("Basic monthly salary must not be 0!");
            }
            if (!newSalary_split[1].isEmpty()) {
                if (((newSalary_split[0]).equals("0")) && ((newSalary_split[1]).equals("0"))) {
                    throw new NumberFormatException("Basic monthly salary must not be 0!");
                } else if (((newSalary_split[0]).equals("0")) && ((newSalary_split[1]).equals("00"))) {
                    throw new NumberFormatException("Basic monthly salary must not be 0!");
                } else if (!((newSalary_split[1].length() == 1) || (newSalary_split[1].length() == 2))) {
                    throw new NumberFormatException("Basic monthly salary must be decimal number with up to 2 decimal places!");
                }
            }
            if (newSalary_split[0].isEmpty()) {
                if (newSalary_split[1].equals("0")) {
                    throw new NumberFormatException("Basic monthly salary must not be 0!");
                } else if (newSalary_split[1].equals("00")) {
                    throw new NumberFormatException("Basic monthly salary must not be 0!");
                } else if (!((newSalary_split[1].length() == 1) || (newSalary_split[1].length() == 2))) {
                    throw new NumberFormatException("Basic monthly salary must be decimal number with up to 2 decimal places!");
                }
            }
        }
    }
}