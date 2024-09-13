import java.util.Scanner;
import java.util.regex.Pattern;

class PartTimeEmployee extends Employee {
    private int numberOfHoursWorked;
    private double baseHourlyRate;

    //Default Constructor
    public PartTimeEmployee() {
        super();
        numberOfHoursWorked = 0;
        baseHourlyRate = 12;
    }

    //Overloaded Constructor
    public PartTimeEmployee(int employeeID, String nameOfEmployee, String employeeDesignation, int numberOfHoursWorked, double baseHourlyRate) {
        super(employeeID, nameOfEmployee, employeeDesignation);
        this.numberOfHoursWorked = numberOfHoursWorked;
        this.baseHourlyRate = baseHourlyRate;
    }

    //Getters and Setters
    public int getNumberOfHoursWorked() {
        return numberOfHoursWorked;
    }

    public void setNumberOfHoursWorked(int numberOfHoursWorked) {
        this.numberOfHoursWorked = numberOfHoursWorked;
    }

    public double getBaseHourlyRate() {
        return baseHourlyRate;
    }

    public void setBaseHourlyRate(double baseHourlyRate) {
        this.baseHourlyRate = baseHourlyRate;
    }

    @Override
    public String employeeCategory() {
        return ("Part-Time");
    }

    @Override
    public double calculateMonthlySalary() {
        return (numberOfHoursWorked * baseHourlyRate);
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
                "Type of Employee: " + employeeCategory() + "\n"  +
                "Nett Monthly Salary: " + "$" + calculateMonthlySalary() + "\n";
    }

    @Override
    public void edit() {
        super.edit();

        System.out.println();
        System.out.println("Type of Employee: " + employeeCategory());

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter new number of hours worked (whole numbers): ");
        String newHours = sc.nextLine();
        newHours = newHours.trim();
        validateNumberOfHoursWorked(newHours);

        System.out.print("Enter new basic hourly rate (up to 2 decimal places): ");
        String newRate = sc.nextLine();
        newRate = newRate.trim();
        validateBaseHourlyRate(newRate);
    }

    @Override
    public void validateNumberOfHoursWorked(String newHours) {
        //regular expression to check if the input is a whole number
        //Pattern.matches("\\d+", newHours) is true for any numeric inputs that is at least 1 digit or more
        //newHours_isInteger = Pattern.matches("\\d+", newHours)
        boolean newHours_isInteger = Pattern.matches("\\d+", newHours);
        if (false) {
            String s1 = Boolean.toString(newHours_isInteger);
            System.out.format("newHours is %s\n", newHours);
            System.out.format("newHours_isInteger is %s\n", s1);
        }

        if ((newHours_isInteger) && ((Integer.parseInt(newHours))!=0) && (!newHours.isEmpty())) {
            this.numberOfHoursWorked = Integer.parseInt(newHours);
        } else if (newHours.isEmpty()) {
            throw new NullPointerException("Number of hours worked must not be null!");
        } else if (!newHours_isInteger) {
            throw new NumberFormatException("Number of hours worked must be integer digits!");
        } else if ((Integer.parseInt(newHours))==0) {
            throw new NumberFormatException("Number of hours worked must not be 0!");
        }
    }

    @Override
    public void validateBaseHourlyRate(String newRate) {
        //regular expression to check if the input is a whole number or float number with up to 2 decimal places
        //Pattern.matches("[0-9]*['.']?[0-9][0-9]", newRate) is true for any numeric inputs with or without decimal point (up to 2 decimal places)
        //newRate_isIntegerOrDecimal = Pattern.matches("[0-9]+[.]?|[0-9]*[.]?[0-9]|[0-9]*[.]?[0-9][0-9]", newRate)
        boolean newRate_isIntegerOrDecimal = Pattern.matches("[0-9]+[.]?|[0-9]*[.]?[0-9]|[0-9]*[.]?[0-9][0-9]", newRate);

        String[] newRate_split = {"0", "00"};
        if (newRate_isIntegerOrDecimal) {
            try {
                // Attempt to parse the string as an integer
                Integer.parseInt(newRate);

                // If successful, it is integer
                newRate_split[1] = String.valueOf(0);

            } catch (NumberFormatException e) {
                // If parsing fails, it is decimal
                String regex = "[.]"; //[,.\\s] matches comma (,), dot (.) or whitespace (\s)
                newRate_split = newRate.split(regex);
                if (false) {
                    System.out.println("newSalary_split: ");
                    for (String s : newRate_split) {
                        System.out.println(s);
                    }
                }
            }
        }

        if (false) {
            String s2 = Boolean.toString(newRate_isIntegerOrDecimal);
            System.out.format("newRate is %s\n", newRate);
            System.out.format("newRate_isIntegerOrDecimal is %s\n", s2);
        }

        if ((newRate_isIntegerOrDecimal) && ((Double.parseDouble(newRate)) != 0) && (!newRate.isEmpty())) {
            this.baseHourlyRate = Double.parseDouble(newRate);
        } else if (newRate.isEmpty()) {
            throw new NullPointerException("Basic hourly rate must not be null!");
        } else if (!newRate_isIntegerOrDecimal) {
            throw new NumberFormatException("Basic hourly rate must be integer or decimal numbers (up to 2 decimal places)!");
        } else {
            if (((newRate_split[0]).equals("0")) && (newRate_split[1].isEmpty())) {
                throw new NumberFormatException("Basic hourly rate must not be 0!");
            }
            if (!newRate_split[1].isEmpty()) {
                if (((newRate_split[0]).equals("0")) && ((newRate_split[1]).equals("0"))) {
                    throw new NumberFormatException("Basic hourly rate must not be 0!");
                } else if (((newRate_split[0]).equals("0")) && ((newRate_split[1]).equals("00"))) {
                    throw new NumberFormatException("Basic hourly rate must not be 0!");
                } else if (!((newRate_split[1].length() == 1) || (newRate_split[1].length() == 2))) {
                    throw new NumberFormatException("Basic hourly rate must be decimal number with up to 2 decimal places!");
                }
            }
            if (newRate_split[0].isEmpty()) {
                if (newRate_split[1].equals("0")) {
                    throw new NumberFormatException("Basic hourly rate must not be 0!");
                } else if (newRate_split[1].equals("00")) {
                    throw new NumberFormatException("Basic hourly rate must not be 0!");
                } else if (!((newRate_split[1].length() == 1) || (newRate_split[1].length() == 2))) {
                    throw new NumberFormatException("Basic hourly rate must be decimal number with up to 2 decimal places!");
                }
            }
        }
    }
}
