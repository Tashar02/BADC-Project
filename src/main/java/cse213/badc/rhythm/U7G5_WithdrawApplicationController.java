package cse213.badc.rhythm;

import cse213.badc.Helper;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class U7G5_WithdrawApplicationController {
    @javafx.fxml.FXML
    private TableView<JobApplication> applicationsTable;
    @javafx.fxml.FXML
    private TableColumn<JobApplication, String> applicationIdTC;
    @javafx.fxml.FXML
    private TableColumn<JobApplication, String> circularIdTC;
    @javafx.fxml.FXML
    private TableColumn<JobApplication, String> statusTC;
    @javafx.fxml.FXML
    private TableColumn<JobApplication, String> submissionDateTC;

    private JobApplication selectedApplication;
    private ArrayList<String> allLines;

    @javafx.fxml.FXML
    public void initialize() {
        applicationIdTC.setCellValueFactory(new PropertyValueFactory<>("applicationId"));
        circularIdTC.setCellValueFactory(new PropertyValueFactory<>("circularId"));
        statusTC.setCellValueFactory(new PropertyValueFactory<>("status"));
        submissionDateTC.setCellValueFactory(new PropertyValueFactory<>("submissionDate"));

        loadApplications();
    }

    @javafx.fxml.FXML
    public void onTableSelectionChanged() {
        selectedApplication = applicationsTable.getSelectionModel().getSelectedItem();
    }

    private void loadApplications() {
        ArrayList<JobApplication> applications = new ArrayList<>();
        allLines = new ArrayList<>();

        try {
            File file = new File("applications.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                allLines.add(line);

                if (line.startsWith("JobApplication")) {
                    String appId = extractValue(line, "applicationId='");
                    String circularId = extractValue(line, "circularId='");
                    String submissionDate = extractValue(line, "submissionDate='");
                    String status = extractValue(line, "status='");

                    JobApplication app = new JobApplication(appId, circularId, "", "", "", "", "", "", null, null, submissionDate, status);
                    applications.add(app);
                }
            }

            scanner.close();
        } catch (Exception e) {
            Helper.showAlert("File Error", "Could not read applications file");
        }

        applicationsTable.getItems().clear();
        for (JobApplication app: applications) {
            if (!app.getStatus().equals("Withdrawn")) {
                applicationsTable.getItems().add(app);
            }
        }
    }

    private String extractValue(String line, String marker) {
        int pos = line.indexOf(marker);
        if (pos != -1) {
            int start = pos + marker.length();
            int end = line.indexOf("'", start);
            if (end != -1) {
                return line.substring(start, end);
            }
        }
        return "";
    }

    @javafx.fxml.FXML
    public void withdrawOA(ActionEvent actionEvent) {
        if (selectedApplication == null) {
            Helper.showAlert("Please select an application to withdraw");
            return;
        }

        if (selectedApplication.getStatus().equals("Approved") ||
                selectedApplication.getStatus().equals("Rejected")) {
            Helper.showAlert("Cannot withdraw application with status: " + selectedApplication.getStatus());
            return;
        }

        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Confirm Withdrawal");
        confirmDialog.setHeaderText("Withdraw Application?");
        confirmDialog.setContentText("Are you sure you want to withdraw this application?\n\nApplication ID: " +
                selectedApplication.getApplicationId() + "\nPosition: " + selectedApplication.getCircularId() +
                "\n\nThis action is permanent and cannot be undone.");

        if (confirmDialog.showAndWait().get() == ButtonType.OK) {
            updateStatus(selectedApplication.getApplicationId());
            Helper.showAlert("Success", "Application withdrawn successfully.");
            loadApplications();
        }
    }

    private void updateStatus(String appId) {
        ArrayList<String> updatedLines = new ArrayList<>();

        for (String line : allLines) {
            if (line.startsWith("JobApplication") && line.contains("applicationId='" + appId + "'")) {
                int statusPos = line.indexOf("status='");
                int statusEnd = line.indexOf("'", statusPos + 8);
                line = line.substring(0, statusPos + 8) + "Withdrawn" + line.substring(statusEnd);
            }
            updatedLines.add(line);
        }

        try {
            FileWriter writer = new FileWriter("applications.txt");
            for (String line : updatedLines) {
                writer.write(line + "\n");
            }
            writer.close();
        } catch (IOException e) {
            Helper.showAlert("File Error", "Could not write to applications file");
        }
    }
}
