package cse213.badc.saad;

import cse213.badc.Helper;
import cse213.badc.BADCApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class U4VerifyComplaintsController
{
    @javafx.fxml.FXML
    private TableColumn<Complaints, String> idCol;
    @javafx.fxml.FXML
    private TableColumn<Complaints, LocalDate> dateCol;
    @javafx.fxml.FXML
    private Label detailsLabel;
    @javafx.fxml.FXML
    private ComboBox<String> verificationComboBox;
    @javafx.fxml.FXML
    private TextArea remarkTA;
    @javafx.fxml.FXML
    private TableView<Complaints> complaintTableView;
    Complaints c;

    @javafx.fxml.FXML
    public void initialize() {
        verificationComboBox.getItems().addAll("Approved", "Rejected");

        idCol.setCellValueFactory(new PropertyValueFactory<>("complaintId"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("dateOfComplaint"));
    }

    FieldOfficer currentUser;
    public void passVerifyComplaintsInterface(FieldOfficer fo){
        currentUser = fo;
    }

    @javafx.fxml.FXML
    public void detailsOA(ActionEvent actionEvent) {
        c = complaintTableView.getSelectionModel().getSelectedItem();
        detailsLabel.setText(c.toString());

    }

    @javafx.fxml.FXML
    public void loadTableOA(ActionEvent actionEvent) throws IOException {
        ArrayList<Complaints> compLst = new ArrayList<>();
        Helper.loadFrom("allComplaints.bin", compLst);
        for ( Complaints c : compLst){
            if (c.getStatus().equals("Pending")){
                complaintTableView.getItems().add(c);
            }
        }
    }

    @javafx.fxml.FXML
    public void verifyOA(ActionEvent actionEvent) throws IOException {
        c.setStatus(verificationComboBox.getValue());
        c.setVarified(true);
        c.setVerificationRemark(remarkTA.getText());
        complaintTableView.getItems().remove(c);

        ArrayList<Complaints> coList = new ArrayList<>();
        Helper.loadFrom("allComplaints.bin", coList);
        for (Complaints co : coList){
            if (c.getComplaintId().equals(co.getComplaintId())){
                co.setStatus(verificationComboBox.getValue());
                co.setVarified(true);
                co.setVerificationRemark(remarkTA.getText());
            }
        }
        Helper.deleteFile("allComplaints.bin");
        for (Complaints co : coList){
            Helper.writeInto("allComplaints.bin", co);
        }


    }

    @javafx.fxml.FXML
    public void goBackOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(BADCApplication.class.getResource("saad/U4BFODashboardView.fxml"));
        Scene scene = new Scene(loader.load());
        U4BFODashboardController controller = loader.getController();
        controller.passBFODashboard(currentUser);
        Stage stage =   (Stage)  ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("BFO Dashboard");
        stage.setScene(scene);
        stage.show();

    }
}