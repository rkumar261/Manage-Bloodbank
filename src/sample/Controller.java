package sample;
import java.io.IOException;
import java.sql.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import java.util.ResourceBundle;
import javax.swing.*;
import connectivity.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class Controller implements Initializable {
    @FXML
    public TextField tUsername;
    @FXML
    public PasswordField tPassword;

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public Controller() {
        connection = ConnectionClass.connectdb();
    }

    public void loginButtonPressed(ActionEvent event) {
        String uname = tUsername.getText().toString();
        String password = tPassword.getText().toString();

//        String sql = "SELECT * FROM register WHERE u_uname = ? and u_pass = ?";
        String sql = "SELECT * FROM `register` WHERE `u_uname` =? AND `u_pass` =?";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, uname);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
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
            else{
                JOptionPane.showMessageDialog(null, "Incorrect Username Or Password", "Login Failed", 2);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }


    public void newUserButton(ActionEvent event) {

        Parent tableViewParent = null;
        try {
            tableViewParent = FXMLLoader.load(getClass().getResource("NewUser.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene newUserScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newUserScene);
        window.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

