package p07_Mainkind;

public class Worker extends Human{
    private double weekSalary;
    private double workingHours;

    public Worker(String firstName, String lastName, double weekSalary, double workingHours) {
        super(firstName, lastName);
        this.setWeekSalary(weekSalary);
        this.setWorkingHours(workingHours);
    }

    @Override
    protected void setLastName(String lastName) {
        if (lastName.length() < 3) {
            throw new IllegalArgumentException("Expected length more than 3 symbols!Argument: lastName");
        }
        super.setLastName(lastName);
    }

    private void setWeekSalary(double weekSalary) {
        if (weekSalary < 10) {
            throw new IllegalArgumentException("Expected value mismatch!Argument: weekSalary");
        }
        this.weekSalary = weekSalary;
    }

    private double getWeekSalary() {
        return this.weekSalary;
    }

    private void setWorkingHours(double workingHours) {
        if (workingHours < 1 || workingHours > 12) {
            throw new IllegalArgumentException("Expected value mismatch!Argument: workHoursPerDay");
        }
        this.workingHours = workingHours;
    }

    private double getWorkingHours() {
        return this.workingHours;
    }

    private double getSalaryPerHour () {
        return (this.weekSalary / 7) / this.workingHours;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("First Name: ").append(super.getFirstName()).append(System.lineSeparator());
        builder.append("Last Name: ").append(super.getLastName()).append(System.lineSeparator());
        builder.append(String.format("Week Salary: %.2f" , this.getWeekSalary()).replace(",", ".")).append(System.lineSeparator());
        builder.append(String.format("Hours per day: %.2f", this.getWorkingHours()).replace(",", ".")).append(System.lineSeparator());
        builder.append(String.format("Salary per hour: %.2f", this.getSalaryPerHour()).replace(",", "."));
        return builder.toString();
    }
}
