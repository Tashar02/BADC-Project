package cse213.badc.rhythm;

import cse213.badc.BADCApplication;
import cse213.badc.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class U7G8_AdmitCardDisplayController {
    @FXML
    private TextArea admitCardTextArea;

    private ApprovedApplication approvedApplication;

    public void setApprovedApplication(ApprovedApplication app) {
        this.approvedApplication = app;
        displayAdmitCard();
    }

    private void displayAdmitCard() {
        String admitCardText = "==========================\n" +
                "               ADMIT CARD\n" +
                "==========================\n\n" +
                "Application ID: " + approvedApplication.getApplicationId() + "\n" +
                "Applicant Name: " + approvedApplication.getApplicantName() + "\n" +
                "National ID: " + approvedApplication.getNationalId() + "\n" +
                "Roll Number: " + approvedApplication.getRollNumber() + "\n" +
                "Circular: " + approvedApplication.getCircular() + "\n" +
                "Exam Date: " + approvedApplication.getExamDate() + "\n" +
                "Exam Time: " + approvedApplication.getExamTime() + "\n" +
                "Status: " + approvedApplication.getStatus() + "\n\n" +
                "==========================\n" +
                "Please carry this admit card to the exam.\n" +
                "==========================";

        admitCardTextArea.setText(admitCardText);
    }

    @FXML
    public void backToApplicationsListOA(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(BADCApplication.class.getResource("rhythm/U7G8_AdmitCardListView.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            Helper.showAlert("Error", "Could not load admit card list view");
        }
    }
}
