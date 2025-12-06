package cse213.badc.saad;

import cse213.badc.Helper;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class createComplaint
{
    @javafx.fxml.FXML
    private TextArea detailsTA;
    @javafx.fxml.FXML
    private TextField comIDTF;
    @javafx.fxml.FXML
    private TextField farIDTF;
    @javafx.fxml.FXML
    private TextField accIDTF;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void craeteOA(ActionEvent actionEvent) throws IOException {
        Complaints c = new Complaints(
                comIDTF.getText(),
                farIDTF.getText(),
                accIDTF.getText(),
                detailsTA.getText()
        );

        Helper.writeInto("allComplaints.bin", c);
    }
}