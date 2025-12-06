package cse213.badc.nusrat;

import cse213.badc.BADCApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.util.Objects;

public class Goal6Controller {

    // Table
    @FXML private TableView<?> trainingTable;
    @FXML private TableColumn<?, ?> colTopic;
    @FXML private TableColumn<?, ?> colDate;
    @FXML private TableColumn<?, ?> colVenue;
    @FXML private TableColumn<?, ?> colOrganizer;
    @FXML private TableColumn<?, ?> colSeats;
    @FXML private TableColumn<?, ?> colStatus;

    // Load message
    @FXML private Label loadMsg;
    @FXML private Label selectMsg;

    // Form fields
    @FXML private TextField nameField;
    @FXML private TextField farmerIdField;
    @FXML private TextField contactField;
    @FXML private ComboBox<String> districtCombo;
    @FXML private DatePicker preferredDatePicker;
    @FXML private ComboBox<String> slotCombo;

    // Errors
    @FXML private Label nameError;
    @FXML private Label farmerIdError;
    @FXML private Label contactError;
    @FXML private Label districtError;
    @FXML private Label scheduleError;

    // Submit/feedback
    @FXML private Label submitMsg;

    @FXML
    private void initialize() {
        // Pre-fill simple slot choices; replace with server-driven later
        if (slotCombo != null) {
            slotCombo.getItems().setAll("Morning", "Afternoon", "Evening");
        }
        if (preferredDatePicker != null) {
            preferredDatePicker.setValue(LocalDate.now());
        }
        clearMessages();
    }

    // ====== UI hooks (implement DP/VL later) ======

    @FXML
    private void onLoadTrainings() {
        loadMsg.setText("Loading trainings…");
        // DP: fetch sessions and set trainingTable items
        // trainingTable.setItems(...);
        // loadMsg.setText("Loaded.");
    }

    @FXML
    private void onUseSelected() {
        // OP/UIE: read selected table row to prefill date/slot/district if desired
        selectMsg.setText("Selected row will be used (sample).");
    }

    @FXML
    private void onValidate() {
        clearErrors();
        // VL: ensure fields present, farmer registered, seat availability etc.
        // nameError.setText("..."), etc. as needed
        submitMsg.setText("Validation complete (sample).");
    }

    @FXML
    private void onRegister() {
        clearErrors();
        submitMsg.setText("Registering…");
        // DP: save registration, get registration_id
        // OP: submitMsg.setText("Registered. ID: REG-2025-0012. See date/venue details.");
    }

    @FXML
    private void onTrack() {
        submitMsg.setText("Tracking registration… (sample)");
        // DP: fetch by last reg id / selected row and display status
    }

    @FXML
    private void onClear() {
        nameField.clear();
        farmerIdField.clear();
        contactField.clear();
        districtCombo.getSelectionModel().clearSelection();
        preferredDatePicker.setValue(LocalDate.now());
        slotCombo.getSelectionModel().clearSelection();
        trainingTable.getItems().clear();
        clearMessages();
    }

    @FXML
    private void onBackToDashboard() {
        try {
            AnchorPane root = FXMLLoader.load(
                    Objects.requireNonNull(
                            BADCApplication.class.getResource("nusrat/farmerDashboard.fxml")
                    )
            );
            Scene scene = new Scene(root);
            BADCApplication.stage.setTitle("Farmer Dashboard");
            BADCApplication.stage.setScene(scene);
        } catch (Exception e) {
            alert("Navigation error", "Could not return to dashboard.\n" + e.getMessage());
        }
    }

    // ====== Helpers ======

    private void clearErrors() {
        if (nameError != null)     nameError.setText("");
        if (farmerIdError != null) farmerIdError.setText("");
        if (contactError != null)  contactError.setText("");
        if (districtError != null) districtError.setText("");
        if (scheduleError != null) scheduleError.setText("");
    }

    private void clearMessages() {
        clearErrors();
        if (loadMsg != null)   loadMsg.setText("");
        if (selectMsg != null) selectMsg.setText("");
        if (submitMsg != null) submitMsg.setText("");
    }

    private void alert(String header, String content) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText(header);
        a.setContentText(content);
        a.showAndWait();
    }
}
