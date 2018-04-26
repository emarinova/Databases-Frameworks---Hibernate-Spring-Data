package p07_Mainkind;

public class Student extends Human{
    private String facultyNumber;

    public Student(String firstName, String lastName, String facultyNumber) {
        super(firstName, lastName);
        this.setFacultyNumber(facultyNumber);
    }

    private void setFacultyNumber(String facultyNumber) {
        if (facultyNumber.length() < 5 || facultyNumber.length() > 10) {
            throw new IllegalArgumentException("Invalid faculty number!");
        }
        this.facultyNumber = facultyNumber;
    }

    private String getFacultyNumber() {
        return this.facultyNumber;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("First Name: ").append(super.getFirstName()).append(System.lineSeparator());
        builder.append("Last Name: ").append(super.getLastName()).append(System.lineSeparator());
        builder.append("Faculty number: ").append(getFacultyNumber());
        return builder.toString();
    }
}