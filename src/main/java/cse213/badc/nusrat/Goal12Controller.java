package cse213.badc.nusrat;

import cse213.badc.BADCApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;

public class Goal12Controller {

    // Download Status Components
    @FXML private VBox downloadStatusBox;
    @FXML private Label downloadStatusLabel;
    @FXML private Label downloadMessageLabel;
    @FXML private ProgressIndicator downloadProgress;
    @FXML private Label validationLabel;

    @FXML
    public void initialize() {
        // Initialize controller
        hideDownloadStatus();
    }

    // ====== APPLICATION FORMS ======

    // UID, VL, DP, OP: Download New Application Form
    @FXML
    private void onDownloadNewApplicationForm() {
        downloadDocument("New Dealership Application Form", "new_dealership_application_form.pdf", 245);
    }

    @FXML
    private void onPreviewNewApplicationForm() {
        previewDocument("New Dealership Application Form");
    }

    // UID, VL, DP, OP: Download Renewal Form
    @FXML
    private void onDownloadRenewalForm() {
        downloadDocument("Dealership Renewal Form", "dealership_renewal_form.pdf", 198);
    }

    @FXML
    private void onPreviewRenewalForm() {
        previewDocument("Dealership Renewal Form");
    }

    // ====== GUIDELINES & REGULATIONS ======

    // UID, VL, DP, OP: Download Dealer Guidelines
    @FXML
    private void onDownloadDealerGuidelines() {
        downloadDocument("BADC Dealer Guidelines 2025", "badc_dealer_guidelines_2025.pdf", 1200);
    }

    @FXML
    private void onPreviewDealerGuidelines() {
        previewDocument("BADC Dealer Guidelines 2025");
    }

    // UID, VL, DP, OP: Download Seed Regulations
    @FXML
    private void onDownloadSeedRegulations() {
        downloadDocument("Seed Distribution Regulations 2025", "seed_distribution_regulations_2025.pdf", 890);
    }

    @FXML
    private void onPreviewSeedRegulations() {
        previewDocument("Seed Distribution Regulations 2025");
    }

    // UID, VL, DP, OP: Download Fertilizer Regulations
    @FXML
    private void onDownloadFertilizerRegulations() {
        downloadDocument("Fertilizer Distribution Regulations 2025", "fertilizer_distribution_regulations_2025.pdf", 756);
    }

    @FXML
    private void onPreviewFertilizerRegulations() {
        previewDocument("Fertilizer Distribution Regulations 2025");
    }

    // ====== POLICY DOCUMENTS ======

    // UID, VL, DP, OP: Download Agriculture Policy
    @FXML
    private void onDownloadAgriculturePolicy() {
        downloadDocument("National Agriculture Policy 2024", "national_agriculture_policy_2024.pdf", 2100);
    }

    @FXML
    private void onPreviewAgriculturePolicy() {
        previewDocument("National Agriculture Policy 2024");
    }

    // UID, VL, DP, OP: Download Code of Conduct
    @FXML
    private void onDownloadCodeOfConduct() {
        downloadDocument("Dealer Code of Conduct", "dealer_code_of_conduct.pdf", 345);
    }

    @FXML
    private void onPreviewCodeOfConduct() {
        previewDocument("Dealer Code of Conduct");
    }

    // ====== HELPER METHODS ======

    // VL, DP, OP: Download document method
    private void downloadDocument(String documentName, String fileName, int fileSizeKB) {
        hideValidation();

        // VL: Check if the requested file exists and user has access permission
        if (!validateDownloadAccess(documentName)) {
            showValidationError("Access denied. Please login or register as a dealer to download this document.");
            return;
        }

        // VL: Check if file exists (simulated)
        if (!checkFileExists(fileName)) {
            showValidationError("File not found. Please contact support.");
            return;
        }

        // DP: Process the user's request and fetch the corresponding file
        System.out.println("=== Download Request ===");
        System.out.println("Document: " + documentName);
        System.out.println("File: " + fileName);
        System.out.println("Size: " + fileSizeKB + " KB");

        // OP: Show download status
        showDownloadStatus(documentName, fileName);

        // Simulate actual file download (to be implemented with real file I/O)
        // In real implementation, this would:
        // 1. Fetch file from database/document server
        // 2. Save to user's download directory
        // 3. Update progress indicator
    }

    // VL: Validate download access (simulated)
    private boolean validateDownloadAccess(String documentName) {
        // In real implementation, check:
        // - User is logged in
        // - User is registered dealer or applicant
        // - User has permission to access this document
        return true; // Simulated - always allow for demo
    }

    // VL: Check if file exists (simulated)
    private boolean checkFileExists(String fileName) {
        // In real implementation, check if file exists in:
        // - Database
        // - File system
        // - Document server
        return true; // Simulated - always exists for demo
    }

    // OP: Show download status message
    private void showDownloadStatus(String documentName, String fileName) {
        downloadStatusLabel.setText("✓ Download Successful!");
        downloadMessageLabel.setText(String.format(
                "File: %s\nDocument: %s\n\nYour file is downloading to your default download folder.",
                fileName, documentName
        ));
        downloadStatusBox.setVisible(true);

        // Also show alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Download Started");
        alert.setHeaderText("Document Download");
        alert.setContentText("Downloading: " + documentName + "\n\nFile will be saved to your download folder.");
        alert.showAndWait();
    }

    // OP: Preview document (opens in new window or shows preview)
    private void previewDocument(String documentName) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Document Preview");
        alert.setHeaderText("Preview: " + documentName);
        alert.setContentText("Document preview will open in a new window.\n(Feature to be implemented with PDF viewer)");
        alert.showAndWait();
    }

    // VL: Show validation error
    private void showValidationError(String message) {
        validationLabel.setText("❌ " + message);
        validationLabel.setVisible(true);
        downloadStatusBox.setVisible(false);
    }

    private void hideValidation() {
        validationLabel.setVisible(false);
    }

    private void hideDownloadStatus() {
        downloadStatusBox.setVisible(false);
        validationLabel.setVisible(false);
    }

    // ====== BULK ACTIONS ======

    // UIE: Download all forms at once
    @FXML
    private void onDownloadAllForms() {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Download All Forms");
        confirmAlert.setHeaderText("Download All Documents");
        confirmAlert.setContentText("This will download all available forms and documents (Total: ~6 MB).\nContinue?");

        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                downloadStatusLabel.setText("Downloading Multiple Files...");
                downloadMessageLabel.setText("Downloading all forms and documents.\nPlease wait...");
                downloadProgress.setVisible(true);
                downloadStatusBox.setVisible(true);

                // Simulate bulk download
                Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                infoAlert.setTitle("Bulk Download");
                infoAlert.setHeaderText("Download Started");
                infoAlert.setContentText("All forms and documents are being downloaded.\nTotal files: 8\nTotal size: ~6 MB");
                infoAlert.showAndWait();

                downloadProgress.setVisible(false);
            }
        });
    }

    // UIE: Help & Support
    @FXML
    private void onHelpSupport() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help & Support");
        alert.setHeaderText("Document Download Support");
        alert.setContentText(
                "Having trouble downloading documents?\n\n" +
                        "Common Issues:\n" +
                        "• File not downloading - Check your internet connection\n" +
                        "• Access denied - Ensure you are logged in\n" +
                        "• Corrupted file - Try downloading again\n\n" +
                        "Contact Support:\n" +
                        "Phone: 09678000123\n" +
                        "Email: support@badc.gov.bd\n" +
                        "Website: www.badc.gov.bd/support"
        );
        alert.showAndWait();
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
}
