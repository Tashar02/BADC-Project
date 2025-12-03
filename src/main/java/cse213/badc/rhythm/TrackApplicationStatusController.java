package cse213.badc.rhythm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.io.FileWriter;

public class TrackApplicationStatusController {
    @FXML
    private TextField searchApplicationIDTextField;
    @FXML
    private TextField applicantNameTextField;
    @FXML
    private TextField applicationIDTextField;
    @FXML
    private TextField circularIDTextField;
    @FXML
    private DatePicker submissionDateDatePicker;
    @FXML
    private TextField currentStatusTextField;
    @FXML
    private TextArea remarksTextArea;
    @FXML
    private TableView<StatusUpdate> statusHistoryTableView;
    @FXML
    private TableColumn<StatusUpdate, String> updateDateTC;
    @FXML
    private TableColumn<StatusUpdate, String> statusTC;
    @FXML
    private TableColumn<StatusUpdate, String> descriptionTC;
    @FXML
    private Label messageLabel;

    private ArrayList<ApplicationStatus> applicationStatusList;
    private ArrayList<StatusHistory> statusHistoryList;

    @FXML
    public void initialize() {
        updateDateTC.setCellValueFactory(new PropertyValueFactory<>("updateDate"));
        statusTC.setCellValueFactory(new PropertyValueFactory<>("status"));
        descriptionTC.setCellValueFactory(new PropertyValueFactory<>("description"));

        applicationStatusList = new ArrayList<>();
        statusHistoryList = new ArrayList<>();

        applicationStatusList.add(new ApplicationStatus("APP1", "C001", "Md. Abdur Rahim", "01-12-2025", "Under Review", "Application received and under initial review"));
        applicationStatusList.add(new ApplicationStatus("APP2", "C002", "Reshma Sultana", "02-12-2025", "Shortlisted", "Candidate shortlisted for interview"));
        applicationStatusList.add(new ApplicationStatus("APP3", "C003", "Ikhtiar Khan", "03-12-2025", "Interview Scheduled", "Interview scheduled for 15-12-2025"));

        StatusHistory history1 = new StatusHistory("APP1");
        history1.addUpdate(new StatusUpdate("01-12-2025", "Submitted", "Application submitted successfully"));
        history1.addUpdate(new StatusUpdate("02-12-2025", "Under Review", "Application under initial review"));
        statusHistoryList.add(history1);

        StatusHistory history2 = new StatusHistory("APP2");
        history2.addUpdate(new StatusUpdate("02-12-2025", "Submitted", "Application submitted successfully"));
        history2.addUpdate(new StatusUpdate("03-12-2025", "Under Review", "Application under review"));
        history2.addUpdate(new StatusUpdate("03-12-2025", "Shortlisted", "Candidate shortlisted for interview"));
        statusHistoryList.add(history2);

        StatusHistory history3 = new StatusHistory("APP3");
        history3.addUpdate(new StatusUpdate("03-12-2025", "Submitted", "Application submitted successfully"));
        history3.addUpdate(new StatusUpdate("04-12-2025", "Interview Scheduled", "Interview scheduled for 15-12-2025"));
        statusHistoryList.add(history3);
    }

    @FXML
    public void searchApplicationStatusOA(ActionEvent actionEvent) {
        String appId = searchApplicationIDTextField.getText().trim();

        if (appId.isEmpty()) {
            showAlert("Input Error", "Please enter an Application ID");
            return;
        }

        ApplicationStatus status = findApplicationStatus(appId);

        if (status != null) {
            displayApplicationDetails(status);
            displayStatusHistory(appId);
            messageLabel.setText("Application found successfully");
        } else {
            showAlert("Not Found", "Application ID not found");
            clearFields();
        }
    }

    private ApplicationStatus findApplicationStatus(String appId) {
        for (ApplicationStatus status: applicationStatusList) {
            if (status.getApplicationId().equals(appId)) {
                return status;
            }
        }
        return null;
    }

    private void displayApplicationDetails(ApplicationStatus status) {
        applicantNameTextField.setText(status.getApplicantName());
        applicationIDTextField.setText(status.getApplicationId());
        circularIDTextField.setText(status.getCircularId());
        submissionDateDatePicker.setValue(LocalDate.parse(status.getSubmissionDate()));
        currentStatusTextField.setText(status.getStatus());
        remarksTextArea.setText(status.getRemarks());
    }

    private void displayStatusHistory(String appId) {
        statusHistoryTableView.getItems().clear();

        for (StatusHistory history: statusHistoryList) {
            if (history.getApplicationId().equals(appId)) {
                statusHistoryTableView.getItems().addAll(history.getUpdates());
                return;
            }
        }
    }

    @FXML
    public void updateStatusOA(ActionEvent actionEvent) {
        String appId = applicationIDTextField.getText().trim();

        if (appId.isEmpty()) {
            showAlert("Input Error", "Please search for an application first");
            return;
        }

        String updatedName = applicantNameTextField.getText().trim();
        String updatedStatus = currentStatusTextField.getText().trim();
        String updatedRemarks = remarksTextArea.getText().trim();

        ApplicationStatus status = findApplicationStatus(appId);

        if (status != null) {
            status.setApplicantName(updatedName);
            status.setStatus(updatedStatus);
            status.setRemarks(updatedRemarks);

            saveToFile(status);
            messageLabel.setText("Status updated successfully");
        } else {
            showAlert("Error", "Failed to update status");
        }
    }

    private void saveToFile(ApplicationStatus status) {
        try {
            FileWriter writer = new FileWriter("application_status_updates.txt", true);
            writer.write(status.toString() + "\n");
            writer.close();
        } catch (Exception e) {
            showAlert("File Error", "Failed to save status update");
        }
    }

    @FXML
    public void clearSearchOA(ActionEvent actionEvent) {
        clearFields();
    }

    private void clearFields() {
        searchApplicationIDTextField.clear();
        applicantNameTextField.clear();
        applicationIDTextField.clear();
        circularIDTextField.clear();
        submissionDateDatePicker.setValue(null);
        currentStatusTextField.clear();
        remarksTextArea.clear();
        statusHistoryTableView.getItems().clear();
        messageLabel.setText("");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
