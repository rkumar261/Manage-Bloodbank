package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.StudentsModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class List implements Initializable {

    @FXML
    private TableView<StudentsModel> tbData;
    @FXML
    public TableColumn<StudentsModel, Integer> studentId;

    @FXML
    public TableColumn<StudentsModel, String> firstName;

    @FXML
    public TableColumn<StudentsModel, String> lastName;



    @Override
    public void initialize(URL location, ResourceBundle resources) {


        loadStudents();
    }



    private ObservableList<StudentsModel> studentsModels = FXCollections.observableArrayList(
            new StudentsModel("A+","10 Units", ""),
            new StudentsModel("A-","12 Units", "458793245"),
            new StudentsModel("B+","13 Units", "5584596216"),
            new StudentsModel("B-","17 Units", "458521"),
            new StudentsModel("AB+","11 Units", "458521"),
            new StudentsModel("AB-","15 Units", "45565521"),
            new StudentsModel("O+","10 Units", "4585455521"),
            new StudentsModel("O-","12 Units", "665458521")

    );


    private void loadStudents()
    {
        studentId.setCellValueFactory(new PropertyValueFactory<>("StudentId"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        tbData.setItems(studentsModels);
    }

    public void backbuttonPressed(ActionEvent event) {
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
