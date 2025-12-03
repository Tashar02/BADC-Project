package cse213.badc.nusrat;

import com.example.farmer_seed_dealer_project.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.util.Objects;

public class Goal2Controller {

    // Location (display only)
    @FXML private Label districtLabel;
    @FXML private Label upazilaLabel;
    @FXML private Label unionLabel;

    // Server-driven lists
    @FXML private ComboBox<String> irrigationTypeCombo;  // Deep Tube Well / Surface Pump / Canal
    @FXML private ComboBox<String> waterSourceCombo;     // Sources per union
    @FXML private ComboBox<String> scheduleSlotCombo;    // Available slots
    @FXML private Label loadMsgLabel;

    // Inputs
    @FXML private TextField cultivationAreaField;
    @FXML private DatePicker preferredStartDatePicker;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;
    @FXML private TextArea remarksArea;

    // Validation messages
    @FXML private Label irrigationTypeError;
    @FXML private Label areaError;
    @FXML private Label dateError;
    @FXML private Label phoneError;
    @FXML private Label emailMsg;
    @FXML private Label scheduleMsg;
    @FXML private Label waterSourceMsg;

    // Submit feedback
    @FXML private Label submitMsg;

    // My Applications table
    @FXML private TableView<?> applicationsTable;
    @FXML private TableColumn<?, ?> colAppId;
    @FXML private TableColumn<?, ?> colAppDate;
    @FXML private TableColumn<?, ?> colAppType;
    @FXML private TableColumn<?, ?> colStatus;

    @FXML
    private void initialize() {
        // Prefill any UI bits; real data wiring will be implemented by you later.
        // Example placeholders:
        // irrigationTypeCombo.getItems().setAll("Deep Tube Well", "Surface Pump", "Canal Supply");
        // scheduleSlotCombo.getItems().setAll("Morning", "Afternoon", "Evening");
        // waterSourceCombo.getItems().setAll("Union-1 Source A", "Union-1 Source B");

        // Optional: default date = today
        if (preferredStartDatePicker != null) {
            preferredStartDatePicker.setValue(LocalDate.now());
        }
    }

    // === Event handlers (no business logic yet; plug your DP/VR/VL later) ===

    @FXML
    private void onLoadServerLists() {
        // DP: fetch lists based on district/upazila/union (populate combos)
        loadMsgLabel.setText("Loading…");
        // after load, set loadMsgLabel.setText("Loaded.");
    }

    @FXML
    private void onValidate() {
        // VL: set specific errors beside fields
        clearErrors();
        // implement validation later
        submitMsg.setText("Validation complete (sample).");
    }

    @FXML
    private void onSubmitApplication() {
        // DP: package inputs, send to server, receive app_id & status
        submitMsg.setText("Submitting…");
        // after success set: submitMsg.setText("Submitted. Application ID: IRR-2025-0456");
        // enable "Track Application" if you wish (handled in FXML as a normal Button)
    }

    @FXML
    private void onTrackApplication() {
        // OP: open a track view or reuse table; fetch latest status by Application ID
        submitMsg.setText("Tracking… (sample)");
    }

    @FXML
    private void onRefreshMyApplications() {
        // DP: repopulate applicationsTable from local store/server
    }

    @FXML
    private void onApplyAnother() {
        // Clear inputs for a fresh application
        irrigationTypeCombo.getSelectionModel().clearSelection();
        waterSourceCombo.getSelectionModel().clearSelection();
        scheduleSlotCombo.getSelectionModel().clearSelection();
        cultivationAreaField.clear();
        preferredStartDatePicker.setValue(LocalDate.now());
        phoneField.clear();
        emailField.clear();
        remarksArea.clear();
        clearErrors();
        submitMsg.setText("");
    }

    @FXML
    private void onBackToDashboard() {
        try {
            AnchorPane root = FXMLLoader.load(
                    Objects.requireNonNull(
                            HelloApplication.class.getResource("nusrat/farmerDashboard.fxml")
                    )
            );
            Scene scene = new Scene(root);
            HelloApplication.stage.setTitle("Farmer Dashboard");
            HelloApplication.stage.setScene(scene);
        } catch (Exception e) {
            alert("Navigation error", "Could not return to dashboard.\n" + e.getMessage());
        }
    }

    // === Helpers ===
    private void clearErrors() {
        if (irrigationTypeError != null) irrigationTypeError.setText("");
        if (areaError != null)           areaError.setText("");
        if (dateError != null)           dateError.setText("");
        if (phoneError != null)          phoneError.setText("");
        if (emailMsg != null)            emailMsg.setText("");
        if (scheduleMsg != null)         scheduleMsg.setText("");
        if (waterSourceMsg != null)      waterSourceMsg.setText("");
    }

    private void alert(String header, String content) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText(header);
        a.setContentText(content);
        a.showAndWait();
    }
}
