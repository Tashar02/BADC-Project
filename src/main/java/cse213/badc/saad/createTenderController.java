package cse213.badc.saad;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class createTenderController
{
    @javafx.fxml.FXML
    private ComboBox<String> regionComboBox;
    @javafx.fxml.FXML
    private TextArea detailsTA;
    @javafx.fxml.FXML
    private TextField idTF;
    @javafx.fxml.FXML
    private TextField titleTF;

    @javafx.fxml.FXML
    public void initialize() {
        regionComboBox.getItems().addAll("Dhaka", "Chattogram", "Rajshahi", "Khulna", "Sylhet", "Barishal", "Rangpur", "Mymensingh");
    }

    @javafx.fxml.FXML
    public void createOA(ActionEvent actionEvent) throws IOException {
        Tender t = new Tender(
                idTF.getText(),
                titleTF.getText(),
                detailsTA.getText(),
                regionComboBox.getValue()
        );

        Helper.writeInto("allTenders.bin", t);
    }
}