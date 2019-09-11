package sample;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import connectivity.ConnectionClass;
import javafx.stage.Stage;

public class FXMLDonorController implements Initializable {
    @FXML
    public ComboBox bloodgroup;
    @FXML
    private TextField tDID;
    @FXML
    private TextField tName;
    @FXML
    private TextField tContact;

    @FXML
    private TextField tEmail;
    @FXML
    private RadioButton rMale;
    @FXML
    private ToggleGroup gender;
    @FXML
    private RadioButton rFemale;
    @FXML
    private DatePicker dDOB;
    @FXML
    private TextArea tAddress;

    @FXML
    private Button bSave;
    @FXML
    private Button bDelete;
    @FXML
    private Button bLoadData;
    @FXML
    private Button bNewEntry;
    @FXML
    private TableColumn<Donors, String> cDID;
    @FXML
    private TableColumn<Donors, String> cName;
    @FXML
    private TableColumn<Donors, String> cGender;
    @FXML
    private TableColumn<Donors, String> cDOB;
    @FXML
    private TableColumn<Donors, String> cContact;
    @FXML
    private TableColumn<Donors, String> cEmail;
    @FXML
    private TableColumn<Donors, String> cAddress;
    @FXML
    private TableColumn<Donors, String> cBlood;

    ObservableList<Donors> data;
    @FXML
    private TableView<Donors> tDonors;
    @FXML
    private Button bUpdate;

    public FXMLDonorController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.data = FXCollections.observableArrayList();
        this.bloodgroup.getItems().addAll(new String[]{"A+", "A-", "B+", "B-", "AB+", "AB-", "O+","O-"});
        this.cDID.setCellValueFactory(new PropertyValueFactory("DID"));
        this.cName.setCellValueFactory(new PropertyValueFactory("Name"));
        this.cGender.setCellValueFactory(new PropertyValueFactory("Gender"));
        this.cDOB.setCellValueFactory(new PropertyValueFactory("DOB"));
        this.cContact.setCellValueFactory(new PropertyValueFactory("Contactno"));
        this.cEmail.setCellValueFactory(new PropertyValueFactory("Emailid"));
        this.cAddress.setCellValueFactory(new PropertyValueFactory("Address"));
        this.cBlood.setCellValueFactory(new PropertyValueFactory("City"));
    }

    @FXML
    private void save(ActionEvent event) {
        if (this.tDID.getText().isEmpty()) {
            Data.showMessage("Donor Id can't be left blank");
        } else if (!Data.isValidContactNo(this.tContact.getText())) {
            Data.showMessage("Enter Valid Contact No.");
        } else if (!Data.isValidEmailId(this.tEmail.getText())) {
            Data.showMessage("Enter valid email Id");
        } else if (!this.rMale.isSelected() && !this.rFemale.isSelected()) {
            Data.showMessage("Please Select either Male or Female!");
        } else if (this.tName.getText().isEmpty()) {
            Data.showMessage("Please Enter Name Of Donor");
        } else if (this.tAddress.getText().isEmpty()) {
            Data.showMessage("Address cannot be left blank");
        } else if (this.dDOB.getEditor().getText().isEmpty()) {
            Data.showMessage("Please enter Date Of Birth");
        } else {
            try {
                Connection con = ConnectionClass.connectdb();
                PreparedStatement ps = con.prepareStatement("Insert into donor values(?,?,?,?,?,?,?,?)");
                ps.setString(1, this.tDID.getText());
                ps.setString(2, this.tName.getText());
                if (this.rMale.isSelected()) {
                    ps.setString(3, "Male");
                } else {
                    ps.setString(3, "Female");
                }

                ps.setString(4, ((LocalDate)this.dDOB.getValue()).format(DateTimeFormatter.ISO_LOCAL_DATE));
                ps.setString(5, this.tContact.getText());
                ps.setString(6, this.tEmail.getText());
                ps.setString(7, this.tAddress.getText());
                ps.setString(8, (String)this.bloodgroup.getSelectionModel().getSelectedItem());
                int count = ps.executeUpdate();
                if (count > 0) {
                    Data.did = Integer.parseInt(this.tDID.getText());
                    Alert a = new Alert(AlertType.INFORMATION);
                    a.setTitle("Donors");
                    a.setContentText("Record Saved Successfully");
                    a.show();
                }

                con.close();
            } catch (Exception var6) {
                Alert a = new Alert(AlertType.ERROR);
                a.setTitle("Error in Project Donors");
                a.setContentText(var6.toString());
                a.show();
                System.out.println(var6);
            }

        }
    }

    @FXML
    private void delete(ActionEvent event) {
        try {
            Connection con = ConnectionClass.connectdb();
            PreparedStatement ps = con.prepareStatement("delete from donor where did=? ");
            ps.setString(1, this.tDID.getText());
            int count = ps.executeUpdate();
            if (count > 0) {
                Alert a = new Alert(AlertType.INFORMATION);
                a.setTitle("Donors");
                a.setContentText("Record has been Deleted");
                a.show();
            }

            con.close();
        } catch (Exception var6) {
            Alert a = new Alert(AlertType.ERROR);
            a.setTitle("Error in Project Donors");
            a.setContentText(var6.toString());
            a.show();
            System.out.println(var6);
        }

    }

    @FXML
    private void loadData(ActionEvent event) {
        try {
            this.data.clear();
            Connection con = ConnectionClass.connectdb();
            PreparedStatement ps = con.prepareStatement("Select * from donor");
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                this.data.add(new Donors(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }

            this.tDonors.setItems(this.data);
        } catch (Exception var5) {
            System.out.println(var5);
        }

    }

    @FXML
    private void newEntry(ActionEvent event) {
        try {
            this.data.clear();
            Connection con = ConnectionClass.connectdb();
            PreparedStatement ps = con.prepareStatement("select Max(did) from donor");
            ResultSet rs = ps.executeQuery();
            int did = 0;
            if (rs.next()) {
                did = rs.getInt(1);
            }

            ++did;
            this.tDID.setText(String.valueOf(did));
            con.close();
        } catch (Exception var6) {
            System.out.println(var6);
        }

    }

    @FXML
    private void uploadData(MouseEvent event) {
        Donors s = (Donors)this.tDonors.getSelectionModel().getSelectedItem();
        this.tDID.setText(s.getDID());
        Data.did = Integer.parseInt(this.tDID.getText());
        this.tName.setText(s.getName());
        if (s.getGender().equals("Male")) {
            this.rMale.setSelected(true);
        } else {
            this.rFemale.setSelected(true);
        }

        this.dDOB.setValue(LocalDate.parse(s.getDOB(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        this.tContact.setText(s.getContactno());
        this.tEmail.setText(s.getEmailid());
        this.tAddress.setText(s.getAddress());
        this.bloodgroup.getSelectionModel().select(s.getCity());
    }

    @FXML
    private void update(ActionEvent event) {
        try {
            Connection con = ConnectionClass.connectdb();
            PreparedStatement ps = con.prepareStatement("update donor set name=?,gender=?,dob=?,contactno=?,emailid=?,address=?,bloodgroup=? where did=? ");
            ps.setString(8, this.tDID.getText());
            ps.setString(1, this.tName.getText());
            if (this.rMale.isSelected()) {
                ps.setString(2, "Male");
            } else {
                ps.setString(2, "Female");
            }

            ps.setString(3, ((LocalDate)this.dDOB.getValue()).format(DateTimeFormatter.ISO_LOCAL_DATE));
            ps.setString(4, this.tContact.getText());
            ps.setString(5, this.tEmail.getText());
            ps.setString(6, this.tAddress.getText());
            ps.setString(7, (String)this.bloodgroup.getSelectionModel().getSelectedItem());
            int count = ps.executeUpdate();
            if (count > 0) {
                Alert m = new Alert(AlertType.INFORMATION);
                m.setTitle("Donors");
                m.setContentText("Record has been Updated");
                m.show();
            }

            con.close();
        } catch (Exception var6) {
            Alert a = new Alert(AlertType.ERROR);
            a.setTitle("Error in Project Donors");
            a.setContentText(var6.toString());
            a.show();
            System.out.println(var6);
        }
    }

    public void backbuttonpressed(ActionEvent event) {
        Parent tableViewParent = null;
        try {
            tableViewParent = FXMLLoader.load(getClass().getResource("Entry.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene newUserScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newUserScene);
        window.show();
    }
}
