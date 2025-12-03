package cse213.badc.nusrat;

import com.example.farmer_seed_dealer_project.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.Objects;

public class Goal7Controller {

    // Inputs
    @FXML private ComboBox<String> categoryCombo;
    @FXML private ComboBox<String> cropCombo;
    @FXML private TextField        cropNameField;
    @FXML private ComboBox<String> districtCombo;
    @FXML private ComboBox<String> upazilaCombo;

    // Messages
    @FXML private Label loadMsg;
    @FXML private Label validateMsg;
    @FXML private Label fetchMsg;

    // Table
    @FXML private TableView<?>  calendarTable;
    @FXML private TableColumn<?, ?> colMonth;
    @FXML private TableColumn<?, ?> colActivity;
    @FXML private TableColumn<?, ?> colFertilizer;
    @FXML private TableColumn<?, ?> colIrrigation;
    @FXML private TableColumn<?, ?> colPest;

    // Techniques
    @FXML private TextArea techniquesArea;

    @FXML
    private void initialize() {
        clearMsgs();
        // Optionally preload categories/regions later from server
        // categoryCombo.getItems().setAll("Rice", "Wheat", "Maize", "Vegetables");
        // districtCombo.getItems().setAll(...); upazilaCombo depends on district
    }

    // === UI hooks (implement logic later) ===

    @FXML
    private void onLoadCrops() {
        clearMsgs();
        loadMsg.setText("Loading crops…");
        // DP: populate cropCombo by selected category
        // cropCombo.getItems().setAll(...);
        // loadMsg.setText("Loaded.");
    }

    @FXML
    private void onValidateSelection() {
        clearMsgs();
        // VL: ensure crop+region combination exists in DB
        // set validateMsg with errors if any
        validateMsg.setText("Validation complete (sample).");
    }

    @FXML
    private void onLoadCalendar() {
        clearMsgs();
        fetchMsg.setText("Fetching calendar & techniques…");
        // DP: fetch sowing/transplant/harvest, fertilizer, irrigation, pest tips
        // calendarTable.setItems(...);
        // techniquesArea.setText(...);
        // fetchMsg.setText("Done.");
    }

    @FXML
    private void onClear() {
        categoryCombo.getSelectionModel().clearSelection();
        cropCombo.getItems().clear();
        cropCombo.getSelectionModel().clearSelection();
        cropNameField.clear();
        districtCombo.getSelectionModel().clearSelection();
        upazilaCombo.getSelectionModel().clearSelection();
        calendarTable.getItems().clear();
        techniquesArea.clear();
        clearMsgs();
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

    private void clearMsgs() {
        if (loadMsg != null)     loadMsg.setText("");
        if (validateMsg != null) validateMsg.setText("");
        if (fetchMsg != null)    fetchMsg.setText("");
    }

    private void alert(String header, String content) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText(header);
        a.setContentText(content);
        a.showAndWait();
    }
}
