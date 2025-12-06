package cse213.badc.nusrat;

import cse213.badc.BADCApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.Objects;

public class Goal8Controller {

    // Filters
    @FXML private ComboBox<String> categoryCombo;
    @FXML private ComboBox<String> fertilizerCombo;
    @FXML private TextField        fertilizerNameField;
    @FXML private ComboBox<String> dealerCombo;
    @FXML private ComboBox<String> warehouseCombo;
    @FXML private ComboBox<String> districtCombo;
    @FXML private ComboBox<String> upazilaCombo;

    // Messages
    @FXML private Label loadMsg;
    @FXML private Label validateMsg;
    @FXML private Label fetchMsg;
    @FXML private Label actionMsg;

    // Table
    @FXML private TableView<?> stockTable;
    @FXML private TableColumn<?, ?> colFertilizer;
    @FXML private TableColumn<?, ?> colCategory;
    @FXML private TableColumn<?, ?> colAvailQty;
    @FXML private TableColumn<?, ?> colUnit;
    @FXML private TableColumn<?, ?> colLastRestock;
    @FXML private TableColumn<?, ?> colDealer;
    @FXML private TableColumn<?, ?> colContact;
    @FXML private TableColumn<?, ?> colLevel;
    @FXML private TableColumn<?, ?> colNextSupply;

    @FXML
    private void initialize() {
        clearMsgs();
        // Optional: pre-fill known categories
        // categoryCombo.getItems().setAll("Urea","TSP","DAP","MOP");
        // dealerCombo / warehouseCombo / districtCombo to be loaded from server later
    }

    // === UI hooks (add logic later) ===

    @FXML
    private void onLoadFilters() {
        clearMsgs();
        loadMsg.setText("Loading filters…");
        // DP: load dealer list, warehouses, and fertilizer list by category
        // dealerCombo.getItems().setAll(...); warehouseCombo.getItems().setAll(...);
        // fertilizerCombo.getItems().setAll(...);
        // loadMsg.setText("Loaded.");
    }

    @FXML
    private void onValidateQuery() {
        clearMsgs();
        // VL: check fertilizer (from list or typed) + district/upazila exist
        // if invalid -> validateMsg.setText("...specific error...");
        validateMsg.setText("Validation complete (sample).");
    }

    @FXML
    private void onFetchUpdates() {
        clearMsgs();
        fetchMsg.setText("Fetching latest stock data…");
        // DP: query inventory -> set stockTable items
        // stockTable.setItems(...);
        // fetchMsg.setText("Done.");
    }

    @FXML
    private void onSubscribeAlerts() {
        actionMsg.setText("Subscribed to alerts (sample).");
        // DP: save subscription for fertilizer/region to receive updates
    }

    @FXML
    private void onClear() {
        categoryCombo.getSelectionModel().clearSelection();
        fertilizerCombo.getItems().clear();
        fertilizerCombo.getSelectionModel().clearSelection();
        fertilizerNameField.clear();
        dealerCombo.getSelectionModel().clearSelection();
        warehouseCombo.getSelectionModel().clearSelection();
        districtCombo.getSelectionModel().clearSelection();
        upazilaCombo.getSelectionModel().clearSelection();
        stockTable.getItems().clear();
        clearMsgs();
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

    // === Helpers ===

    private void clearMsgs() {
        if (loadMsg != null)     loadMsg.setText("");
        if (validateMsg != null) validateMsg.setText("");
        if (fetchMsg != null)    fetchMsg.setText("");
        if (actionMsg != null)   actionMsg.setText("");
    }

    private void alert(String header, String content) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText(header);
        a.setContentText(content);
        a.showAndWait();
    }
}
