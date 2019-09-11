package sample;

import javafx.beans.property.SimpleStringProperty;

public class Table {
    private final SimpleStringProperty sno;
    private final SimpleStringProperty bgroup;

    public Table(String sno, String bgroup) {
        this.sno = new SimpleStringProperty(sno);
        this.bgroup = new SimpleStringProperty(bgroup);
    }

    public String getSNO() {
        return this.sno.get();
    }

    public void setSNO(String sno) {
        this.sno.set(sno);
    }

    public String getBgroup() {
        return this.bgroup.get();
    }

    public void setBgroup(String bgroup) {
        this.bgroup.set(bgroup);
    }
}
