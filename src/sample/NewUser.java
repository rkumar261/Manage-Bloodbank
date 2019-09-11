package sample;

import connectivity.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class NewUser implements Initializable {
    @FXML
    public TextField Name;
    @FXML
    public TextField UserName;
    @FXML
    public TextField Email;
    @FXML
    public TextField Password;
    @FXML
    public TextField PhoneNo;
    @FXML
    public ComboBox bloodgroup;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.bloodgroup.getItems().addAll(new String[]{"A+", "A-", "B+", "B-", "AB+", "AB-", "O+","O-"});
    }

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public NewUser() {
        connection = ConnectionClass.connectdb();
    }

    public void BacktoLogin(ActionEvent event){
        String name = Name.getText().toString();
        String uname = UserName.getText().toString();
        String email = Email.getText().toString();
        String pass = Password.getText().toString();
        String pno = PhoneNo.getText().toString();
        String bgroup = (String) bloodgroup.getSelectionModel().getSelectedItem();

        String query = "INSERT INTO `register`(`u_name`, `u_uname`,`u_email`, `u_pass`,`u_pno`,`u_bgroup`) VALUES (?,?,?,?,?,?)";
        if (this.UserName.getText().isEmpty()) {
            Data.showMessage("Donor Id can't be left blank");
        } else if (!Data.isValidContactNo(PhoneNo.getText())) {
            Data.showMessage("Enter Valid Contact No.");
        } else if (!Data.isValidEmailId(Email.getText())) {
            Data.showMessage("Enter valid email Id");
        }else if (Name.getText().isEmpty()) {
            Data.showMessage("Please Enter Name Of Donor");
        }else if (Name.getText().isEmpty()){
            Data.showMessage("Password field can not be empty");
        }else {
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, uname);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, pass);
                preparedStatement.setString(5, pno);
                preparedStatement.setString(6, bgroup);
                if(preparedStatement.executeUpdate() > 0) {
                    Parent tableViewParent = null;
                    try {
                        tableViewParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene newUserScene = new Scene(tableViewParent);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(newUserScene);
                    window.show();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void backbuttonpressed(ActionEvent event) {
        Parent tableViewParent = null;
        try {
            tableViewParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene newUserScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newUserScene);
        window.show();
    }

}
