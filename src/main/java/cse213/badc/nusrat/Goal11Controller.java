package cse213.badc.nusrat;

import com.example.farmer_seed_dealer_project.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Random;

public class Goal11Controller {

    // Radio Buttons for Application Type
    @FXML private RadioButton newDealershipRadio;
    @FXML private RadioButton renewDealershipRadio;
    @FXML private ToggleGroup applicationTypeGroup;
    @FXML private TextArea eligibilityInfoArea;

    // Form Fields
    @FXML private TextField applicantNameField;
    @FXML private TextField businessNameField;
    @FXML private Label licenseNumberLabel;
    @FXML private TextField licenseNumberField;
    @FXML private ComboBox<String> categoryCombo;
    @FXML private ComboBox<String> districtCombo;
    @FXML private ComboBox<String> upazilaCombo;
    @FXML private TextField unionField;
    @FXML private TextArea addressArea;
    @FXML private TextField contactNumberField;
    @FXML private TextField emailField;
    @FXML private TextField nidNumberField;

    // Document Upload Fields
    @FXML private TextField tradeLicensePathField;
    @FXML private Button clearTradeLicenseBtn;
    @FXML private TextField nidCopyPathField;
    @FXML private Button clearNIDCopyBtn;
    @FXML private TextField premisesPhotoPathField;
    @FXML private Button clearPremisesPhotoBtn;
    @FXML private TextField bankStatementPathField;
    @FXML private Button clearBankStatementBtn;
    @FXML private HBox previousLicenseBox;
    @FXML private TextField previousLicensePathField;
    @FXML private Button clearPreviousLicenseBtn;

    // Validation and Confirmation
    @FXML private Label validationLabel;
    @FXML private VBox confirmationBox;
    @FXML private Label applicationIdLabel;
    @FXML private Label submissionDateLabel;
    @FXML private TextArea nextStepsArea;

    // File storage
    private File tradeLicenseFile;
    private File nidCopyFile;
    private File premisesPhotoFile;
    private File bankStatementFile;
    private File previousLicenseFile;

    @FXML
    public void initialize() {
        // OP: Display eligibility criteria and required documents
        setupEligibilityInfo();
        setupFormFields();

        // UIE: Listen for application type changes
        newDealershipRadio.setOnAction(e -> onApplicationTypeChanged());
        renewDealershipRadio.setOnAction(e -> onApplicationTypeChanged());

        // Set initial state
        onApplicationTypeChanged();
    }

    // OP: Setup eligibility information based on application type
    private void setupEligibilityInfo() {
        String newDealerInfo = "New Dealership Eligibility:\n" +
                "• Must have valid Trade License\n" +
                "• Must have physical business premises\n" +
                "• Must provide NID and business documents\n" +
                "• Minimum 3 years of agricultural business experience recommended\n" +
                "\nRequired Documents: Trade License, NID Copy, Business Premises Photo, Bank Statement (optional)";

        eligibilityInfoArea.setText(newDealerInfo);
    }

    private void setupFormFields() {
        // Populate Category ComboBox
        categoryCombo.getItems().addAll("Seed Only", "Fertilizer Only", "Seed and Fertilizer");

        // Populate District ComboBox
        districtCombo.getItems().addAll(
                "Dhaka", "Rajshahi", "Chittagong", "Khulna", "Barisal",
                "Sylhet", "Rangpur", "Mymensingh"
        );

        // Populate Upazila ComboBox (sample data)
        upazilaCombo.getItems().addAll(
                "Savar", "Paba", "Rangunia", "Dumuria", "Companiganj",
                "Badarganj", "Bakerganj", "Trishal", "Keraniganj", "Godagari"
        );
    }

    // UIE: Handle application type change
    private void onApplicationTypeChanged() {
        boolean isRenewal = renewDealershipRadio.isSelected();

        // Show/hide license number field for renewal
        licenseNumberLabel.setVisible(isRenewal);
        licenseNumberField.setVisible(isRenewal);

        // Show/hide previous license upload for renewal
        previousLicenseBox.setVisible(isRenewal);

        // Update eligibility info
        if (isRenewal) {
            String renewalInfo = "Renewal Eligibility:\n" +
                    "• Must have existing valid or recently expired BADC dealership license\n" +
                    "• No major violations or complaints in previous license period\n" +
                    "• Updated business information and documents required\n" +
                    "\nRequired Documents: Previous License Copy, Trade License, NID Copy, Business Premises Photo, Bank Statement (optional)";
            eligibilityInfoArea.setText(renewalInfo);
        } else {
            setupEligibilityInfo();
        }
    }

    // UIE: File chooser methods for document uploads
    @FXML
    private void onChooseTradeLicense() {
        tradeLicenseFile = chooseFile("Select Trade License", "PDF Files", "*.pdf", "Image Files", "*.jpg", "*.png");
        if (tradeLicenseFile != null) {
            tradeLicensePathField.setText(tradeLicenseFile.getName());
            clearTradeLicenseBtn.setVisible(true);
        }
    }

    @FXML
    private void onClearTradeLicense() {
        tradeLicenseFile = null;
        tradeLicensePathField.clear();
        clearTradeLicenseBtn.setVisible(false);
    }

    @FXML
    private void onChooseNIDCopy() {
        nidCopyFile = chooseFile("Select NID Copy", "PDF Files", "*.pdf", "Image Files", "*.jpg", "*.png");
        if (nidCopyFile != null) {
            nidCopyPathField.setText(nidCopyFile.getName());
            clearNIDCopyBtn.setVisible(true);
        }
    }

    @FXML
    private void onClearNIDCopy() {
        nidCopyFile = null;
        nidCopyPathField.clear();
        clearNIDCopyBtn.setVisible(false);
    }

    @FXML
    private void onChoosePremisesPhoto() {
        premisesPhotoFile = chooseFile("Select Business Premises Photo", "Image Files", "*.jpg", "*.png", "*.jpeg");
        if (premisesPhotoFile != null) {
            premisesPhotoPathField.setText(premisesPhotoFile.getName());
            clearPremisesPhotoBtn.setVisible(true);
        }
    }

    @FXML
    private void onClearPremisesPhoto() {
        premisesPhotoFile = null;
        premisesPhotoPathField.clear();
        clearPremisesPhotoBtn.setVisible(false);
    }

    @FXML
    private void onChooseBankStatement() {
        bankStatementFile = chooseFile("Select Bank Statement", "PDF Files", "*.pdf", "Image Files", "*.jpg", "*.png");
        if (bankStatementFile != null) {
            bankStatementPathField.setText(bankStatementFile.getName());
            clearBankStatementBtn.setVisible(true);
        }
    }

    @FXML
    private void onClearBankStatement() {
        bankStatementFile = null;
        bankStatementPathField.clear();
        clearBankStatementBtn.setVisible(false);
    }

    @FXML
    private void onChoosePreviousLicense() {
        previousLicenseFile = chooseFile("Select Previous License Copy", "PDF Files", "*.pdf", "Image Files", "*.jpg", "*.png");
        if (previousLicenseFile != null) {
            previousLicensePathField.setText(previousLicenseFile.getName());
            clearPreviousLicenseBtn.setVisible(true);
        }
    }

    @FXML
    private void onClearPreviousLicense() {
        previousLicenseFile = null;
        previousLicensePathField.clear();
        clearPreviousLicenseBtn.setVisible(false);
    }

    // Helper method for file chooser
    private File chooseFile(String title, String... extensions) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);

        // Add extension filters
        for (int i = 0; i < extensions.length; i += 2) {
            if (i + 1 < extensions.length) {
                fileChooser.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter(extensions[i], extensions[i + 1])
                );
            }
        }

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        return fileChooser.showOpenDialog(HelloApplication.stage);
    }

    // VL, DP, OP: Submit Application
    @FXML
    private void onSubmitApplication() {
        validationLabel.setText("");
        confirmationBox.setVisible(false);

        // VL: Validate all mandatory fields
        if (!validateForm()) {
            return;
        }

        // DP: Process the submitted application data
        String applicationType = newDealershipRadio.isSelected() ? "New Dealership" : "Renewal";
        String applicationId = generateApplicationId();
        String submissionDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a"));

        // Simulate database storage
        System.out.println("=== Application Submission ===");
        System.out.println("Application Type: " + applicationType);
        System.out.println("Application ID: " + applicationId);
        System.out.println("Applicant Name: " + applicantNameField.getText());
        System.out.println("Business Name: " + businessNameField.getText());
        System.out.println("Category: " + categoryCombo.getValue());
        System.out.println("District: " + districtCombo.getValue());
        System.out.println("Contact: " + contactNumberField.getText());
        System.out.println("Email: " + emailField.getText());
        System.out.println("Documents uploaded: " + getUploadedDocumentCount());

        // OP: Display confirmation message with application ID, submission date, and next steps
        applicationIdLabel.setText("Application ID: " + applicationId);
        submissionDateLabel.setText("Submission Date: " + submissionDate);

        String nextSteps = "1. Your application is under review by BADC officials.\n" +
                "2. Document verification will be completed within 7-10 working days.\n" +
                "3. You will receive SMS and Email notifications about your application status.\n" +
                "4. Field inspection may be scheduled for business premises verification.\n" +
                "5. Final decision will be communicated within 15-20 working days.";
        nextStepsArea.setText(nextSteps);

        confirmationBox.setVisible(true);

        // Show success alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Application Submitted");
        alert.setHeaderText("Your application has been submitted successfully!");
        alert.setContentText("Application ID: " + applicationId + "\n\nPlease save this ID for future reference.");
        alert.showAndWait();
    }

    // VL: Validate form fields
    private boolean validateForm() {
        // Check applicant name
        if (applicantNameField.getText() == null || applicantNameField.getText().trim().isEmpty()) {
            validationLabel.setText("❌ Please enter applicant name.");
            return false;
        }

        // Check business name
        if (businessNameField.getText() == null || businessNameField.getText().trim().isEmpty()) {
            validationLabel.setText("❌ Please enter business name.");
            return false;
        }

        // Check license number for renewal
        if (renewDealershipRadio.isSelected()) {
            if (licenseNumberField.getText() == null || licenseNumberField.getText().trim().isEmpty()) {
                validationLabel.setText("❌ Please enter existing license number for renewal.");
                return false;
            }
        }

        // Check category
        if (categoryCombo.getValue() == null || categoryCombo.getValue().isEmpty()) {
            validationLabel.setText("❌ Please select business category.");
            return false;
        }

        // Check district
        if (districtCombo.getValue() == null || districtCombo.getValue().isEmpty()) {
            validationLabel.setText("❌ Please select district.");
            return false;
        }

        // Check upazila
        if (upazilaCombo.getValue() == null || upazilaCombo.getValue().isEmpty()) {
            validationLabel.setText("❌ Please select upazila.");
            return false;
        }

        // Check address
        if (addressArea.getText() == null || addressArea.getText().trim().isEmpty()) {
            validationLabel.setText("❌ Please enter full address.");
            return false;
        }

        // Check contact number
        if (contactNumberField.getText() == null || contactNumberField.getText().trim().isEmpty()) {
            validationLabel.setText("❌ Please enter contact number.");
            return false;
        }

        // Validate contact number format (11 digits)
        String contact = contactNumberField.getText().trim();
        if (!contact.matches("\\d{11}")) {
            validationLabel.setText("❌ Contact number must be 11 digits.");
            return false;
        }

        // Check email
        if (emailField.getText() == null || emailField.getText().trim().isEmpty()) {
            validationLabel.setText("❌ Please enter email address.");
            return false;
        }

        // Validate email format
        String email = emailField.getText().trim();
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            validationLabel.setText("❌ Please enter a valid email address.");
            return false;
        }

        // Check NID number
        if (nidNumberField.getText() == null || nidNumberField.getText().trim().isEmpty()) {
            validationLabel.setText("❌ Please enter NID number.");
            return false;
        }

        // VL: Check mandatory documents are uploaded in valid formats
        if (tradeLicenseFile == null) {
            validationLabel.setText("❌ Please upload Trade License document.");
            return false;
        }

        if (nidCopyFile == null) {
            validationLabel.setText("❌ Please upload NID Copy.");
            return false;
        }

        if (premisesPhotoFile == null) {
            validationLabel.setText("❌ Please upload Business Premises Photo.");
            return false;
        }

        // Check previous license for renewal
        if (renewDealershipRadio.isSelected() && previousLicenseFile == null) {
            validationLabel.setText("❌ Please upload Previous License Copy for renewal.");
            return false;
        }

        return true;
    }

    // DP: Generate unique tracking or application ID
    private String generateApplicationId() {
        String prefix = newDealershipRadio.isSelected() ? "NEW" : "REN";
        String year = String.valueOf(LocalDateTime.now().getYear());
        String randomNum = String.format("%06d", new Random().nextInt(999999));
        return prefix + "-" + year + "-" + randomNum;
    }

    private int getUploadedDocumentCount() {
        int count = 0;
        if (tradeLicenseFile != null) count++;
        if (nidCopyFile != null) count++;
        if (premisesPhotoFile != null) count++;
        if (bankStatementFile != null) count++;
        if (previousLicenseFile != null) count++;
        return count;
    }

    // UIE: Save as Draft
    @FXML
    private void onSaveAsDraft() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Save as Draft");
        alert.setHeaderText("Application Saved as Draft");
        alert.setContentText("Your application has been saved. You can complete and submit it later from 'My Applications'.");
        alert.showAndWait();
    }

    // UIE: Clear Form
    @FXML
    private void onClearForm() {
        applicantNameField.clear();
        businessNameField.clear();
        licenseNumberField.clear();
        categoryCombo.setValue(null);
        districtCombo.setValue(null);
        upazilaCombo.setValue(null);
        unionField.clear();
        addressArea.clear();
        contactNumberField.clear();
        emailField.clear();
        nidNumberField.clear();

        onClearTradeLicense();
        onClearNIDCopy();
        onClearPremisesPhoto();
        onClearBankStatement();
        onClearPreviousLicense();

        validationLabel.setText("");
        confirmationBox.setVisible(false);

        newDealershipRadio.setSelected(true);
        onApplicationTypeChanged();
    }

    // UIE: Navigate back to dashboard
    @FXML
    private void onBackToDashboard() {
        try {
            AnchorPane root = FXMLLoader.load(Objects.requireNonNull(
                    HelloApplication.class.getResource("nusrat/seedDealerDashboard.fxml")
            ));
            Scene scene = new Scene(root);
            HelloApplication.stage.setTitle("Seed Dealer Dashboard");
            HelloApplication.stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // UIE: View my applications (placeholder)
    @FXML
    private void onViewMyApplications() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("My Applications");
        alert.setHeaderText("View Applications");
        alert.setContentText("This will show all your submitted applications with their status.\n(Feature to be implemented)");
        alert.showAndWait();
    }
}
