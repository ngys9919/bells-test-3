import java.util.Scanner;

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
    public double calculateMonthlySalary() {
        return (0.8 * baseMonthlySalary);
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
        System.out.print("Enter basic monthly salary: ");
        double newSalary = sc.nextDouble();
        this.baseMonthlySalary = newSalary;
    }

}
