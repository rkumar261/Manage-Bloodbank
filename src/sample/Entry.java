package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Entry {
    public void resisterNewDoner(ActionEvent event) {
        Parent tableViewParent = null;
        try {
            tableViewParent = FXMLLoader.load(getClass().getResource("FXMLDonor.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene newUserScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newUserScene);
        window.show();
    }

    public void bloodInBank(ActionEvent event) {
        Parent tableViewParent = null;
        try {
            tableViewParent = FXMLLoader.load(getClass().getResource("list.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene newUserScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newUserScene);
        window.show();
    }

    public void donerList(ActionEvent event) {
        Parent tableViewParent = null;
        try {
            tableViewParent = FXMLLoader.load(getClass().getResource("FXMLDonor.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene newUserScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newUserScene);
        window.show();
    }

    public void donationCamps(ActionEvent event) {
        Parent tableViewParent = null;
        try {
            tableViewParent = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene newUserScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newUserScene);
        window.show();
    }

    public void newreciever(ActionEvent event) {
        Parent tableViewParent = null;
        try {
            tableViewParent = FXMLLoader.load(getClass().getResource("FXMLReceiver.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene newUserScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newUserScene);
        window.show();
    }

    public void bloodInformation(ActionEvent event) {
        Parent tableViewParent = null;
        try {
            tableViewParent = FXMLLoader.load(getClass().getResource("FXMLBlood.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene newUserScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newUserScene);
        window.show();
    }

    public void taketotabe(ActionEvent event) {
        Parent tableViewParent = null;
        try {
            tableViewParent = FXMLLoader.load(getClass().getResource("FXMLTable2.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene newUserScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newUserScene);
        window.show();
    }
}
