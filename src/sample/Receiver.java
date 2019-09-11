package sample;


import javafx.beans.property.SimpleStringProperty;

public class Receiver {
    private final SimpleStringProperty RID;
    private final SimpleStringProperty name;
    private final SimpleStringProperty contactno;
    private final SimpleStringProperty gender;
    private final SimpleStringProperty DOB;
    private final SimpleStringProperty bgroup;
    private final SimpleStringProperty units;
    private final SimpleStringProperty city;

    public Receiver(String RID, String name, String contactno, String gender, String DOB, String bgroup, String units, String city) {
        this.RID = new SimpleStringProperty(RID);
        this.name = new SimpleStringProperty(name);
        this.contactno = new SimpleStringProperty(contactno);
        this.gender = new SimpleStringProperty(gender);
        this.DOB = new SimpleStringProperty(DOB);
        this.bgroup = new SimpleStringProperty(bgroup);
        this.units = new SimpleStringProperty(units);
        this.city = new SimpleStringProperty(city);
    }

    public String getRID() {
        return this.RID.get();
    }

    public void setRID(String RID) {
        this.RID.set(RID);
    }

    public String getName() {
        return this.name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getContactno() {
        return this.contactno.get();
    }

    public void setContactno(String contactno) {
        this.contactno.set(contactno);
    }

    public String getGender() {
        return this.gender.get();
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getDOB() {
        return this.DOB.get();
    }

    public void setDOB(String DOB) {
        this.DOB.set(DOB);
    }

    public String getBgroup() {
        return this.bgroup.get();
    }

    public void setBgroup(String bgroup) {
        this.bgroup.set(bgroup);
    }

    public String getUnits() {
        return this.units.get();
    }

    public void setUnits(String units) {
        this.units.set(units);
    }

    public String getCity() {
        return this.city.get();
    }

    public void setCity(String city) {
        this.city.set(city);
    }
}