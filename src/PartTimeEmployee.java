import java.util.Scanner;

class PartTimeEmployee extends Employee {
    private String employeeCategory;
    private int numberOfHoursWorked;
    private double baseHourlyRate;

    //Default Constructor
    public PartTimeEmployee() {
        super();
        employeeCategory = "Part-Time";
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
    public double calculateMonthlySalary() {
        return (numberOfHoursWorked * baseHourlyRate);
    }

    @Override
    public String report() {
        // super refers to the parent class
        // hence super.report() means call the `report` method in parent class
        return super.report() +
                "Type of Employee: " + employeeCategory + "\n"  +
                "Monthly Salary: " + "$" + calculateMonthlySalary() + "\n";
    }

    @Override
    public void edit() {
        super.edit();

        System.out.println();
        System.out.print("Type of Employee: " + employeeCategory);

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of hours worked: ");
        int newHours = sc.nextInt();
        this.numberOfHoursWorked = newHours;

        System.out.print("Enter basic hourly rate: ");
        double newRate = sc.nextDouble();
        this.baseHourlyRate = newRate;
    }
}
