package cse213.badc.rhythm;

import cse213.badc.BADCApplication;
import cse213.badc.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class AdmitCardListController {
    @FXML
    private TableView<ApprovedApplication> approvedApplicationsTableView;
    @FXML
    private TableColumn<ApprovedApplication, String> applicationIdTC;
    @FXML
    private TableColumn<ApprovedApplication, String> applicantNameTC;
    @FXML
    private TableColumn<ApprovedApplication, String> circularTC;
    @FXML
    private TableColumn<ApprovedApplication, String> statusTC;

    private ArrayList<ApprovedApplication> approvedApplications;

    @FXML
    public void initialize() {
        applicationIdTC.setCellValueFactory(new PropertyValueFactory<>("applicationId"));
        applicantNameTC.setCellValueFactory(new PropertyValueFactory<>("applicantName"));
        circularTC.setCellValueFactory(new PropertyValueFactory<>("circular"));
        statusTC.setCellValueFactory(new PropertyValueFactory<>("status"));

        approvedApplications = new ArrayList<>();
        loadApprovedApplicationsFromFile();
        refreshApprovedApplicationsTable();
    }

    private void loadApprovedApplicationsFromFile() {
        try {
            File file = new File("applications.txt");

            if (!file.exists()) {
                return;
            }

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.startsWith("JobApplication")) {
                    String applicationId = extractField(line, "applicationId='");
                    String fullName = extractField(line, "fullName='");
                    String nationalId = extractField(line, "nationalId='");
                    String circular = extractField(line, "circularId='");

                    if (applicationId != null && fullName != null && nationalId != null && circular != null) {
                        String rollNumber = "BADC" + applicationId;
                        String examDate = "08-12-2025";
                        String examTime = "10:00 AM";

                        ApprovedApplication app = new ApprovedApplication(
                                applicationId,
                                fullName,
                                nationalId,
                                circular,
                                rollNumber,
                                examDate,
                                examTime,
                                "Approved"
                        );

                        approvedApplications.add(app);
                    }
                }
            }

            scanner.close();
        } catch (Exception e) {
            Helper.showAlert("File Error", "Could not read applications file");
        }
    }

    private String extractField(String line, String fieldMarker) {
        int markerPos = line.indexOf(fieldMarker);
        if (markerPos != -1) {
            int start = markerPos + fieldMarker.length();
            int end = line.indexOf("'", start);
            if (end != -1) {
                return line.substring(start, end);
            }
        }
        return null;
    }

    private void refreshApprovedApplicationsTable() {
        approvedApplicationsTableView.getItems().clear();
        for (ApprovedApplication app: approvedApplications) {
            approvedApplicationsTableView.getItems().add(app);
        }
    }

    @FXML
    public void viewAdmitCardOA(ActionEvent actionEvent) {
        if (approvedApplicationsTableView.getItems().isEmpty()) {
            Helper.showAlert("Error", "No applications available");
            return;
        }

        ApprovedApplication selectedApp = null;
        for (ApprovedApplication app: approvedApplicationsTableView.getItems()) {
            selectedApp = app;
            break;
        }

        if (selectedApp == null) {
            Helper.showAlert("Error", "No application found");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(BADCApplication.class.getResource("rhythm/AdmitCardDisplayView.fxml"));
            Scene scene = new Scene(loader.load());
            AdmitCardDisplayController controller = loader.getController();
            controller.setApprovedApplication(selectedApp);

            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            Helper.showAlert("Error", "Could not load admit card view");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
