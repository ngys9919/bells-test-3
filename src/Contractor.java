class Contractor implements PayablePerson {
//class Contractor {
    private int employeeID;
    private double basicMonthlySalary;
    private int durationOfContract; // number of months

    //Default Constructor
    public Contractor() {
        employeeID = 9999;
        basicMonthlySalary = 0;
        durationOfContract = 0;
    }

    //Overloaded Constructor
    public Contractor(int employeeID, double basicMonthlySalary, int durationOfContract) {
        this.employeeID = employeeID;
        this.basicMonthlySalary = basicMonthlySalary;
        this.durationOfContract = durationOfContract;
    }

    //Getters and Setters
    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public double getBasicMonthlySalary() {
        return basicMonthlySalary;
    }

    public void setBasicMonthlySalary(double basicMonthlySalary) {
        this.basicMonthlySalary = basicMonthlySalary;
    }

    public int getDurationOfContract() {
        return durationOfContract;
    }

    public void setDurationOfContract(int durationOfContract) {
        this.durationOfContract = durationOfContract;
    }

    @Override
    public double calculateMonthlySalary() {
        return (basicMonthlySalary);
    }
}
