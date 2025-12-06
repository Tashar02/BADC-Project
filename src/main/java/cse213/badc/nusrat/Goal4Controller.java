package cse213.badc.nusrat;

import cse213.badc.BADCApplication;
import cse213.badc.BADCApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.Objects;

public class Goal4Controller {

    // Inputs
    @FXML private ComboBox<String> categoryCombo;
    @FXML private ComboBox<String> cropCombo;
    @FXML private TextField cropNameField;

    // Messages
    @FXML private Label loadMsg;
    @FXML private Label validateMsg;
    @FXML private Label fetchMsg;
    @FXML private Label actionMsg;

    // Table
    @FXML private TableView<?> seedTable;
    @FXML private TableColumn<?, ?> colVariety;
    @FXML private TableColumn<?, ?> colUnit;
    @FXML private TableColumn<?, ?> colPrice;
    @FXML private TableColumn<?, ?> colSupplier;
    @FXML private TableColumn<?, ?> colStock;
    @FXML private TableColumn<?, ?> colSeason;

    @FXML
    private void initialize() {
        // Prefill categories later from server or keep a simple default list:
        // categoryCombo.getItems().setAll("Rice", "Wheat", "Maize", "Vegetables");
        clearMsgs();
    }

    // === UI event hooks (implement logic later) ===

    @FXML
    private void onLoadCrops() {
        clearMsgs();
        loadMsg.setText("Loading crops…");
        // DP: populate cropCombo based on categoryCombo
        // cropCombo.getItems().setAll(...);
        // loadMsg.setText("Loaded.");
    }

    @FXML
    private void onValidateCrop() {
        clearMsgs();
        // VL: ensure cropCombo selection OR cropNameField text is valid (exists in DB)
        // Set validateMsg with specific errors if needed
        validateMsg.setText("Validation complete (sample).");
    }

    @FXML
    private void onFetchSeedInfo() {
        clearMsgs();
        fetchMsg.setText("Fetching seed information…");
        // DP: query varieties/prices/season/stock and set items to seedTable
        // seedTable.setItems(...);
        // fetchMsg.setText("Done.");
    }

    @FXML
    private void onOpenSelected() {
        actionMsg.setText("");
        // OP: show details or open a modal with the selected variety
        // var item = seedTable.getSelectionModel().getSelectedItem();
        // if (item == null) { actionMsg.setText("Select a row first."); return; }
        actionMsg.setText("Details view (sample).");
    }

    @FXML
    private void onClear() {
        categoryCombo.getSelectionModel().clearSelection();
        cropCombo.getItems().clear();
        cropCombo.getSelectionModel().clearSelection();
        cropNameField.clear();
        seedTable.getItems().clear();
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
