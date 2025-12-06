package cse213.badc.saad;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class createFarmerApplication
{
    @javafx.fxml.FXML
    private ComboBox<String> locationCB;
    @javafx.fxml.FXML
    private TextField applicationID;
    @javafx.fxml.FXML
    private TextField applicantID;
    @javafx.fxml.FXML
    private TextField mobileTF;
    @javafx.fxml.FXML
    private TextArea detailsTA;

    @javafx.fxml.FXML
    public void initialize() {
        locationCB.getItems().addAll("Dhaka", "Chattogram", "Rajshahi", "Khulna", "Sylhet", "Barishal", "Rangpur", "Mymensingh");
    }

    @javafx.fxml.FXML
    public void createOA(ActionEvent actionEvent) throws IOException {
        FarmerApplication fa = new FarmerApplication(
                applicationID.getText(),
                applicantID.getText(),
                locationCB.getValue(),
                mobileTF.getText(),
                detailsTA.getText()
        );

        Helper.writeInto("allApplications.bin", fa);
        Helper.showAlert("Writing Done");

    }
}