package cse213.badc.nusrat;

import com.example.farmer_seed_dealer_project.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Random;

public class Goal15Controller {

    // OP: Available Supplies Table
    @FXML private TableView<SupplyItem> suppliesTableView;
    @FXML private TableColumn<SupplyItem, String> itemColumn;
    @FXML private TableColumn<SupplyItem, String> currentLocationColumn;
    @FXML private TableColumn<SupplyItem, Integer> quantityColumn;
    @FXML private TableColumn<SupplyItem, String> unitColumn;

    // UID: Form Fields
    @FXML private ComboBox<String> supplyItemCombo;
    @FXML private TextField quantityField;
    @FXML private Label unitLabel;
    @FXML private ComboBox<String> currentLocationCombo;
    @FXML private ComboBox<String> desiredLocationCombo;
    @FXML private TextArea justificationArea;

    // VL, OP: Validation and Confirmation
    @FXML private Label validationLabel;
    @FXML private VBox confirmationBox;
    @FXML private Label requestIdLabel;
    @FXML private Label submissionDateLabel;
    @FXML private Label statusLabel;

    private ObservableList<SupplyItem> supplies = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // OP: Show list of supplies eligible for reallocation
        setupSuppliesTable();
        loadSupplies();
        setupFormFields();
    }

    private void setupSuppliesTable() {
        itemColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        currentLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        unitColumn.setCellValueFactory(new PropertyValueFactory<>("unit"));
    }

    private void loadSupplies() {
        supplies.add(new SupplyItem("BRRI Dhan 29 Seed", "Dhaka Warehouse", 5000, "KG"));
        supplies.add(new SupplyItem("Urea Fertilizer", "Rajshahi Depot", 10000, "KG"));
        supplies.add(new SupplyItem("TSP Fertilizer", "Chittagong Depot", 8000, "KG"));
        supplies.add(new SupplyItem("Hybrid Corn Seed", "Sylhet Warehouse", 2000, "KG"));
        supplies.add(new SupplyItem("DAP Fertilizer", "Khulna Depot", 6000, "KG"));

        suppliesTableView.setItems(supplies);
    }

    private void setupFormFields() {
        // Populate supply items
        supplyItemCombo.getItems().addAll(
                "BRRI Dhan 29 Seed", "Urea Fertilizer", "TSP Fertilizer",
                "Hybrid Corn Seed", "DAP Fertilizer"
        );

        // Populate locations
        ObservableList<String> locations = FXCollections.observableArrayList(
                "Dhaka Warehouse", "Rajshahi Depot", "Chittagong Depot",
                "Sylhet Warehouse", "Khulna Depot", "Barisal Warehouse",
                "Rangpur Depot", "Mymensingh Warehouse"
        );
        currentLocationCombo.setItems(locations);
        desiredLocationCombo.setItems(locations);

        // Update unit label when item selected
        supplyItemCombo.setOnAction(e -> updateUnitLabel());
    }

    private void updateUnitLabel() {
        String selected = supplyItemCombo.getValue();
        if (selected != null) {
            unitLabel.setText("KG");
        }
    }

    // VL, DP, OP: Submit reallocation request
    @FXML
    private void onSubmitRequest() {
        validationLabel.setText("");
        confirmationBox.setVisible(false);

        // VL: Validate all required fields
        if (!validateForm()) {
            return;
        }

        // DP: Record request in database and generate request ID
        String requestId = generateRequestId();
        String submissionDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a"));

        System.out.println("=== Supply Reallocation Request ===");
        System.out.println("Request ID: " + requestId);
        System.out.println("Supply Item: " + supplyItemCombo.getValue());
        System.out.println("Quantity: " + quantityField.getText());
        System.out.println("From: " + currentLocationCombo.getValue());
        System.out.println("To: " + desiredLocationCombo.getValue());

        // OP: Show confirmation message with request ID and status
        requestIdLabel.setText("Request ID: " + requestId);
        submissionDateLabel.setText("Submission Date: " + submissionDate);
        statusLabel.setText("Status: Pending Approval");
        confirmationBox.setVisible(true);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Request Submitted");
        alert.setHeaderText("Reallocation Request Submitted Successfully!");
        alert.setContentText("Request ID: " + requestId + "\n\nYour request is pending approval.");
        alert.showAndWait();
    }

    // VL: Validate form
    private boolean validateForm() {
        if (supplyItemCombo.getValue() == null) {
            validationLabel.setText("❌ Please select a supply item.");
            return false;
        }

        if (quantityField.getText() == null || quantityField.getText().trim().isEmpty()) {
            validationLabel.setText("❌ Please enter quantity.");
            return false;
        }

        // VL: Check if quantity is available
        try {
            int requestedQty = Integer.parseInt(quantityField.getText().trim());
            if (requestedQty <= 0) {
                validationLabel.setText("❌ Quantity must be greater than 0.");
                return false;
            }

            // Check availability
            String selectedItem = supplyItemCombo.getValue();
            String selectedLocation = currentLocationCombo.getValue();
            SupplyItem supply = supplies.stream()
                    .filter(s -> s.getItemName().equals(selectedItem) && s.getLocation().equals(selectedLocation))
                    .findFirst()
                    .orElse(null);

            if (supply != null && requestedQty > supply.getQuantity()) {
                validationLabel.setText("❌ Requested quantity exceeds available stock (" + supply.getQuantity() + " KG).");
                return false;
            }
        } catch (NumberFormatException e) {
            validationLabel.setText("❌ Quantity must be a valid number.");
            return false;
        }

        // VL: Check locations are valid
        if (currentLocationCombo.getValue() == null) {
            validationLabel.setText("❌ Please select current location.");
            return false;
        }

        if (desiredLocationCombo.getValue() == null) {
            validationLabel.setText("❌ Please select desired location.");
            return false;
        }

        if (currentLocationCombo.getValue().equals(desiredLocationCombo.getValue())) {
            validationLabel.setText("❌ Current and desired locations cannot be the same.");
            return false;
        }

        return true;
    }

    // DP: Generate request ID
    private String generateRequestId() {
        String prefix = "RA";
        String year = String.valueOf(LocalDateTime.now().getYear());
        String randomNum = String.format("%06d", new Random().nextInt(999999));
        return prefix + "-" + year + "-" + randomNum;
    }

    @FXML
    private void onClearForm() {
        supplyItemCombo.setValue(null);
        quantityField.clear();
        currentLocationCombo.setValue(null);
        desiredLocationCombo.setValue(null);
        justificationArea.clear();
        validationLabel.setText("");
        confirmationBox.setVisible(false);
    }

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

    @FXML
    private void onViewMyRequests() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("My Requests");
        alert.setHeaderText("View Reallocation Requests");
        alert.setContentText("This will show all your submitted reallocation requests with their status.\n(Feature to be implemented)");
        alert.showAndWait();
    }

    // Inner class for Supply Item
    public static class SupplyItem {
        private String itemName;
        private String location;
        private int quantity;
        private String unit;

        public SupplyItem(String itemName, String location, int quantity, String unit) {
            this.itemName = itemName;
            this.location = location;
            this.quantity = quantity;
            this.unit = unit;
        }

        public String getItemName() { return itemName; }
        public String getLocation() { return location; }
        public int getQuantity() { return quantity; }
        public String getUnit() { return unit; }
    }
}
