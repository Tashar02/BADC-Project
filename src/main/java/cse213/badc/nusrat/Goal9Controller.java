package cse213.badc.nusrat;

import com.example.farmer_seed_dealer_project.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class Goal9Controller {

    @FXML private ComboBox<String> itemTypeCombo;
    @FXML private TextField productNameField;
    @FXML private Label seedTypeLabel;
    @FXML private ComboBox<String> seedTypeCombo;
    @FXML private Label fertilizerTypeLabel;
    @FXML private ComboBox<String> fertilizerTypeCombo;
    @FXML private TextField quantityField;
    @FXML private TextField priceField;
    @FXML private DatePicker uploadDatePicker;
    @FXML private TextArea notesArea;
    @FXML private Label validationLabel;
    @FXML private Label successLabel;

    @FXML
    public void initialize() {
        // DP: Fetch stock update form template

        // Populate Item Type ComboBox
        itemTypeCombo.getItems().addAll("Seed", "Fertilizer");

        // Populate Seed Type ComboBox
        seedTypeCombo.getItems().addAll(
                "Rice Seed", "Wheat Seed", "Corn Seed", "Vegetable Seed", "Pulse Seed"
        );

        // Populate Fertilizer Type ComboBox
        fertilizerTypeCombo.getItems().addAll(
                "Urea", "TSP", "DAP", "MOP", "NPK", "Organic Fertilizer"
        );

        // Set default date to today
        uploadDatePicker.setValue(LocalDate.now());

        // UIE: Listen for item type selection to show/hide specific fields
        itemTypeCombo.setOnAction(event -> {
            String selectedType = itemTypeCombo.getValue();
            if ("Seed".equals(selectedType)) {
                seedTypeLabel.setVisible(true);
                seedTypeCombo.setVisible(true);
                fertilizerTypeLabel.setVisible(false);
                fertilizerTypeCombo.setVisible(false);
            } else if ("Fertilizer".equals(selectedType)) {
                seedTypeLabel.setVisible(false);
                seedTypeCombo.setVisible(false);
                fertilizerTypeLabel.setVisible(true);
                fertilizerTypeCombo.setVisible(true);
            }
        });
    }

    // UIE, VL, DP, OP: Submit stock update
    @FXML
    private void onSubmitStockUpdate() {
        // Clear previous messages
        validationLabel.setText("");
        successLabel.setText("");

        // VL: Validate required fields
        if (!validateForm()) {
            return;
        }

        // DP: Process and upload data to server (simulated here)
        // In real implementation, this would save to database or file
        System.out.println("=== Stock Update Submission ===");
        System.out.println("Item Type: " + itemTypeCombo.getValue());
        System.out.println("Product Name: " + productNameField.getText());

        if ("Seed".equals(itemTypeCombo.getValue())) {
            System.out.println("Seed Type: " + seedTypeCombo.getValue());
        } else {
            System.out.println("Fertilizer Type: " + fertilizerTypeCombo.getValue());
        }

        System.out.println("Quantity: " + quantityField.getText());
        System.out.println("Price: " + priceField.getText());
        System.out.println("Date: " + uploadDatePicker.getValue());
        System.out.println("Notes: " + notesArea.getText());

        // OP: Display success message
        successLabel.setText("✓ Stock update successful!");

        // Clear form after successful submission
        clearFormFields();
    }

    // VL: Validation method
    private boolean validateForm() {
        // Check if item type is selected
        if (itemTypeCombo.getValue() == null || itemTypeCombo.getValue().isEmpty()) {
            validationLabel.setText("Please select an item type.");
            return false;
        }

        // Check product name
        if (productNameField.getText() == null || productNameField.getText().trim().isEmpty()) {
            validationLabel.setText("Please enter a product name.");
            return false;
        }

        // Check specific type selection
        if ("Seed".equals(itemTypeCombo.getValue())) {
            if (seedTypeCombo.getValue() == null || seedTypeCombo.getValue().isEmpty()) {
                validationLabel.setText("Please select a seed type.");
                return false;
            }
        } else if ("Fertilizer".equals(itemTypeCombo.getValue())) {
            if (fertilizerTypeCombo.getValue() == null || fertilizerTypeCombo.getValue().isEmpty()) {
                validationLabel.setText("Please select a fertilizer type.");
                return false;
            }
        }

        // VL: Validate quantity (must be a valid number)
        if (quantityField.getText() == null || quantityField.getText().trim().isEmpty()) {
            validationLabel.setText("Please enter available quantity.");
            return false;
        }

        try {
            double quantity = Double.parseDouble(quantityField.getText().trim());
            if (quantity <= 0) {
                validationLabel.setText("Quantity must be greater than 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            validationLabel.setText("Quantity must be a valid number.");
            return false;
        }

        // VL: Validate price (must be a valid number)
        if (priceField.getText() == null || priceField.getText().trim().isEmpty()) {
            validationLabel.setText("Please enter price per unit.");
            return false;
        }

        try {
            double price = Double.parseDouble(priceField.getText().trim());
            if (price <= 0) {
                validationLabel.setText("Price must be greater than 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            validationLabel.setText("Price must be a valid number.");
            return false;
        }

        // Check upload date
        if (uploadDatePicker.getValue() == null) {
            validationLabel.setText("Please select an upload date.");
            return false;
        }

        return true;
    }

    // UIE: Clear form
    @FXML
    private void onClearForm() {
        clearFormFields();
        successLabel.setText("");
        validationLabel.setText("");
    }

    private void clearFormFields() {
        itemTypeCombo.setValue(null);
        productNameField.clear();
        seedTypeCombo.setValue(null);
        fertilizerTypeCombo.setValue(null);
        quantityField.clear();
        priceField.clear();
        uploadDatePicker.setValue(LocalDate.now());
        notesArea.clear();

        // Hide conditional fields
        seedTypeLabel.setVisible(false);
        seedTypeCombo.setVisible(false);
        fertilizerTypeLabel.setVisible(false);
        fertilizerTypeCombo.setVisible(false);
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

    // UIE: View stock list (placeholder for future implementation)
    @FXML
    private void onViewStockList() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("View Stock List");
        alert.setHeaderText("Stock List Feature");
        alert.setContentText("This will navigate to the stock list view (to be implemented).");
        alert.showAndWait();
    }
}
