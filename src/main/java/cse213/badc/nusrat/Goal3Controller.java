package cse213.badc.nusrat;

import com.example.farmer_seed_dealer_project.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.Objects;

public class Goal3Controller {

    // Mode (Rent / Purchase)
    @FXML private RadioButton rentRadio;
    @FXML private RadioButton purchaseRadio;
    @FXML private ToggleGroup modeGroup;

    // User inputs
    @FXML private TextField nameField;
    @FXML private TextField farmerIdField;
    @FXML private ComboBox<String> districtCombo;
    @FXML private ComboBox<String> upazilaCombo;
    @FXML private TextField areaField;

    // Validation labels
    @FXML private Label nameError;
    @FXML private Label farmerIdError;
    @FXML private Label districtError;
    @FXML private Label upazilaError;
    @FXML private Label areaError;
    @FXML private Label validationMsg;

    // Load feedback
    @FXML private Label loadMsg;

    // Table
    @FXML private TableView<?> pumpsTable;
    @FXML private TableColumn<?, ?> colPumpId;
    @FXML private TableColumn<?, ?> colPumpType;
    @FXML private TableColumn<?, ?> colCapacity;
    @FXML private TableColumn<?, ?> colRatePrice;
    @FXML private TableColumn<?, ?> colDealer;
    @FXML private TableColumn<?, ?> colContact;
    @FXML private TableColumn<?, ?> colAvailability;

    // Submit feedback
    @FXML private Label submitMsg;

    @FXML
    private void initialize() {
        // default mode: Rent
        if (modeGroup == null) {
            modeGroup = new ToggleGroup();
            rentRadio.setToggleGroup(modeGroup);
            purchaseRadio.setToggleGroup(modeGroup);
        }
        rentRadio.setSelected(true);

        // you will populate these from server later
        // districtCombo.getItems().setAll(...);
        // upazilaCombo.getItems().setAll(...);
    }

    // === UI Events (hook business logic later) ===

    @FXML
    private void onModeChanged() {
        // You may adjust labels or columns based on mode (rent vs purchase)
        // For now: clear messages
        submitMsg.setText("");
        validationMsg.setText("");
    }

    @FXML
    private void onLoadOptions() {
        // DP: fetch list of pumps for selected location + mode
        loadMsg.setText("Loading nearby options…");
        // After load: loadMsg.setText("Loaded.");
        // pumpsTable.setItems(...);
    }

    @FXML
    private void onSubmitRequest() {
        // VL -> DP -> OP pipeline will be implemented here later
        validationMsg.setText("");
        submitMsg.setText("Submitting request…");
        // After success: submitMsg.setText("Request submitted for confirmation.");
    }

    @FXML
    private void onClearForm() {
        nameField.clear();
        farmerIdField.clear();
        districtCombo.getSelectionModel().clearSelection();
        upazilaCombo.getSelectionModel().clearSelection();
        areaField.clear();
        clearErrors();
        submitMsg.setText("");
        validationMsg.setText("");
        loadMsg.setText("");
        pumpsTable.getItems().clear();
        rentRadio.setSelected(true);
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
        if (nameError != null)     nameError.setText("");
        if (farmerIdError != null) farmerIdError.setText("");
        if (districtError != null) districtError.setText("");
        if (upazilaError != null)  upazilaError.setText("");
        if (areaError != null)     areaError.setText("");
    }

    private void alert(String header, String content) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText(header);
        a.setContentText(content);
        a.showAndWait();
    }
}
