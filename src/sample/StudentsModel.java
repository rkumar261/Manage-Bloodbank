package sample;



import javafx.beans.property.SimpleStringProperty;

public class StudentsModel {

    private SimpleStringProperty studentId;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;

    public StudentsModel(String studentId, String firstName, String lastName) {
        this.studentId = new SimpleStringProperty(studentId);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
    }

    public String getStudentId() {
        return studentId.get();
    }

    public void setStudentId(String studentId) {
        this.studentId = new SimpleStringProperty(studentId);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName = new SimpleStringProperty(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName = new SimpleStringProperty(lastName);
    }
}