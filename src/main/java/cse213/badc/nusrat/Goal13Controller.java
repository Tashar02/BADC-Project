package cse213.badc.nusrat;

import cse213.badc.BADCApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Goal13Controller {

    // Form Fields
    @FXML private DatePicker startDatePicker;
    @FXML private DatePicker endDatePicker;
    @FXML private ComboBox<String> productCategoryCombo;
    @FXML private TextField productNameField;
    @FXML private TextField quantitySoldField;
    @FXML private ComboBox<String> unitCombo;
    @FXML private TextField revenueField;
    @FXML private TextField costField;
    @FXML private TextField transactionsField;
    @FXML private TextField targetAreaField;
    @FXML private TextArea remarksArea;

    // Document Upload
    @FXML private TextField documentsPathField;
    @FXML private Button clearDocumentsBtn;
    @FXML private ListView<String> filesListView;

    // Validation and Confirmation
    @FXML private Label validationLabel;
    @FXML private VBox confirmationBox;
    @FXML private Label submissionIdLabel;
    @FXML private Label submissionDateLabel;
    @FXML private Label feedbackLabel;

    // File storage
    private List<File> uploadedFiles;

    @FXML
    public void initialize() {
        // OP: Display sales report form with required fields
        setupFormFields();
    }

    private void setupFormFields() {
        // Populate Product Category ComboBox
        productCategoryCombo.getItems().addAll(
                "Seed", "Fertilizer", "Pesticide", "Agricultural Equipment", "Other"
        );

        // Populate Unit ComboBox
        unitCombo.getItems().addAll(
                "KG", "Bags", "Ton", "Liters", "Pieces", "Units"
        );

        // Set default date range (current month)
        LocalDate now = LocalDate.now();
        startDatePicker.setValue(now.withDayOfMonth(1));
        endDatePicker.setValue(now);
    }

    // UIE, UID: Choose documents to upload
    @FXML
    private void onChooseDocuments() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Supporting Documents");

        // Add extension filters
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf"),
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        // Allow multiple file selection
        uploadedFiles = fileChooser.showOpenMultipleDialog(BADCApplication.stage);

        if (uploadedFiles != null && !uploadedFiles.isEmpty()) {
            documentsPathField.setText(uploadedFiles.size() + " file(s) selected");
            clearDocumentsBtn.setVisible(true);

            // Display file names in ListView
            ObservableList<String> fileNames = FXCollections.observableArrayList();
            for (File file : uploadedFiles) {
                fileNames.add(file.getName() + " (" + formatFileSize(file.length()) + ")");
            }
            filesListView.setItems(fileNames);
            filesListView.setVisible(true);
        }
    }

    @FXML
    private void onClearDocuments() {
        uploadedFiles = null;
        documentsPathField.clear();
        clearDocumentsBtn.setVisible(false);
        filesListView.setVisible(false);
        filesListView.getItems().clear();
    }

    private String formatFileSize(long bytes) {
        if (bytes < 1024) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(1024));
        String pre = "KMGTPE".charAt(exp - 1) + "";
        return String.format("%.1f %sB", bytes / Math.pow(1024, exp), pre);
    }

    // VL, DP, OP: Submit sales report
    @FXML
    private void onSubmitReport() {
        validationLabel.setText("");
        confirmationBox.setVisible(false);

        // VL: Validate all mandatory fields
        if (!validateForm()) {
            return;
        }

        // DP: Process the submitted data and generate submission ID
        String submissionId = generateSubmissionId();
        String submissionDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a"));

        // Simulate database update
        System.out.println("=== Sales Report Submission ===");
        System.out.println("Submission ID: " + submissionId);
        System.out.println("Period: " + startDatePicker.getValue() + " to " + endDatePicker.getValue());
        System.out.println("Category: " + productCategoryCombo.getValue());
        System.out.println("Product: " + productNameField.getText());
        System.out.println("Quantity: " + quantitySoldField.getText() + " " + unitCombo.getValue());
        System.out.println("Revenue: " + revenueField.getText());
        System.out.println("Documents: " + (uploadedFiles != null ? uploadedFiles.size() : 0) + " file(s)");

        // OP: Display confirmation message with submission ID and date
        submissionIdLabel.setText("Submission ID: " + submissionId);
        submissionDateLabel.setText("Submission Date: " + submissionDate);
        feedbackLabel.setText("Thank you for submitting your sales report. You will receive a confirmation email shortly.");
        confirmationBox.setVisible(true);

        // Show success alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Report Submitted");
        alert.setHeaderText("Sales Report Submitted Successfully!");
        alert.setContentText("Submission ID: " + submissionId + "\n\nYour report has been recorded and will be reviewed by BADC officials.");
        alert.showAndWait();
    }

    // VL: Validate form fields
    private boolean validateForm() {
        // Check sales period
        if (startDatePicker.getValue() == null) {
            validationLabel.setText("❌ Please select start date for sales period.");
            return false;
        }

        if (endDatePicker.getValue() == null) {
            validationLabel.setText("❌ Please select end date for sales period.");
            return false;
        }

        // VL: Check date range is valid
        if (startDatePicker.getValue().isAfter(endDatePicker.getValue())) {
            validationLabel.setText("❌ Start date cannot be after end date.");
            return false;
        }

        // Check product category
        if (productCategoryCombo.getValue() == null || productCategoryCombo.getValue().isEmpty()) {
            validationLabel.setText("❌ Please select product category.");
            return false;
        }

        // Check product name
        if (productNameField.getText() == null || productNameField.getText().trim().isEmpty()) {
            validationLabel.setText("❌ Please enter product name/type.");
            return false;
        }

        // VL: Check quantity sold (must be valid number)
        if (quantitySoldField.getText() == null || quantitySoldField.getText().trim().isEmpty()) {
            validationLabel.setText("❌ Please enter quantity sold.");
            return false;
        }

        try {
            double quantity = Double.parseDouble(quantitySoldField.getText().trim());
            if (quantity <= 0) {
                validationLabel.setText("❌ Quantity sold must be greater than 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            validationLabel.setText("❌ Quantity sold must be a valid number.");
            return false;
        }

        // Check unit
        if (unitCombo.getValue() == null || unitCombo.getValue().isEmpty()) {
            validationLabel.setText("❌ Please select unit of measurement.");
            return false;
        }

        // VL: Check revenue (must be valid number within expected range)
        if (revenueField.getText() == null || revenueField.getText().trim().isEmpty()) {
            validationLabel.setText("❌ Please enter revenue generated.");
            return false;
        }

        try {
            double revenue = Double.parseDouble(revenueField.getText().trim());
            if (revenue <= 0) {
                validationLabel.setText("❌ Revenue must be greater than 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            validationLabel.setText("❌ Revenue must be a valid number.");
            return false;
        }

        // VL: Validate cost if provided
        if (costField.getText() != null && !costField.getText().trim().isEmpty()) {
            try {
                double cost = Double.parseDouble(costField.getText().trim());
                if (cost < 0) {
                    validationLabel.setText("❌ Cost cannot be negative.");
                    return false;
                }
            } catch (NumberFormatException e) {
                validationLabel.setText("❌ Cost must be a valid number.");
                return false;
            }
        }

        // VL: Validate transactions if provided
        if (transactionsField.getText() != null && !transactionsField.getText().trim().isEmpty()) {
            try {
                int transactions = Integer.parseInt(transactionsField.getText().trim());
                if (transactions < 0) {
                    validationLabel.setText("❌ Number of transactions cannot be negative.");
                    return false;
                }
            } catch (NumberFormatException e) {
                validationLabel.setText("❌ Number of transactions must be a valid integer.");
                return false;
            }
        }

        // VL: Check uploaded documents are in valid formats
        if (uploadedFiles != null && !uploadedFiles.isEmpty()) {
            for (File file : uploadedFiles) {
                String fileName = file.getName().toLowerCase();
                if (!fileName.endsWith(".pdf") && !fileName.endsWith(".jpg") &&
                        !fileName.endsWith(".jpeg") && !fileName.endsWith(".png")) {
                    validationLabel.setText("❌ Invalid file format: " + file.getName() + ". Only PDF and image files are allowed.");
                    return false;
                }
            }
        }

        return true;
    }

    // DP: Generate unique submission ID
    private String generateSubmissionId() {
        String prefix = "SR";
        String year = String.valueOf(LocalDateTime.now().getYear());
        String month = String.format("%02d", LocalDateTime.now().getMonthValue());
        String randomNum = String.format("%05d", new Random().nextInt(99999));
        return prefix + "-" + year + month + "-" + randomNum;
    }

    // UIE: Save as Draft
    @FXML
    private void onSaveAsDraft() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Save as Draft");
        alert.setHeaderText("Report Saved as Draft");
        alert.setContentText("Your sales report has been saved. You can complete and submit it later from 'Past Reports'.");
        alert.showAndWait();
    }

    // UIE: Clear Form
    @FXML
    private void onClearForm() {
        LocalDate now = LocalDate.now();
        startDatePicker.setValue(now.withDayOfMonth(1));
        endDatePicker.setValue(now);
        productCategoryCombo.setValue(null);
        productNameField.clear();
        quantitySoldField.clear();
        unitCombo.setValue(null);
        revenueField.clear();
        costField.clear();
        transactionsField.clear();
        targetAreaField.clear();
        remarksArea.clear();

        onClearDocuments();

        validationLabel.setText("");
        confirmationBox.setVisible(false);
    }

    // UIE: Navigate back to dashboard
    @FXML
    private void onBackToDashboard() {
        try {
            AnchorPane root = FXMLLoader.load(Objects.requireNonNull(
                    BADCApplication.class.getResource("nusrat/seedDealerDashboard.fxml")
            ));
            Scene scene = new Scene(root);
            BADCApplication.stage.setTitle("Seed Dealer Dashboard");
            BADCApplication.stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // UIE: View past reports (placeholder)
    @FXML
    private void onViewPastReports() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Past Reports");
        alert.setHeaderText("View Past Sales Reports");
        alert.setContentText("This will show all your previously submitted sales reports with their status.\n(Feature to be implemented)");
        alert.showAndWait();
    }
}
