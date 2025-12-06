package cse213.badc.saad;

import cse213.badc.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class createFieldOfficerController
{
    @javafx.fxml.FXML
    private TextField idTF;
    FieldOfficer fo;
    @javafx.fxml.FXML
    private TextField passTF;
    @javafx.fxml.FXML
    private ComboBox<String> regionCB;

    @javafx.fxml.FXML
    public void initialize() {
        regionCB.getItems().addAll("Dhaka", "Chattogram", "Rajshahi", "Khulna", "Sylhet", "Barishal", "Rangpur", "Mymensingh");
    }


    @javafx.fxml.FXML
    public void createOA(ActionEvent actionEvent) throws IOException {
        fo = new FieldOfficer(idTF.getText(), regionCB.getValue(), passTF.getText());
        Helper.writeInto("allFieldOfficers.bin", fo);
        Helper.showAlert("Writing success");
    }

    @javafx.fxml.FXML
    public void goLogInOA(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("saad/logInbySaad.fxml"));

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Log IN");

        stage.show();

    }
}