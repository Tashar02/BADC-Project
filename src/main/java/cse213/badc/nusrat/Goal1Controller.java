package cse213.badc.nusrat;

import com.example.farmer_seed_dealer_project.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Objects;

public class Goal1Controller {

    // Filters
    @FXML private ComboBox<String> districtCombo;
    @FXML private ComboBox<String> upazilaCombo;
    @FXML private ComboBox<String> unionCombo;

    @FXML private RadioButton seedRadio;
    @FXML private RadioButton fertilizerRadio;
    @FXML private ToggleGroup inputTypeGroup; // auto-wired via FXML ($inputTypeGroup)
    @FXML private ComboBox<String> cropCodeCombo;
    @FXML private ComboBox<String> fertGradeCombo;

    @FXML private Label validationMsg;

    // Inventory
    @FXML private TableView<?> inventoryTable;
    @FXML private TableColumn<?, ?> colItemId;
    @FXML private TableColumn<?, ?> colName;
    @FXML private TableColumn<?, ?> colVarOrGrade;
    @FXML private TableColumn<?, ?> colAvailQty;
    @FXML private TableColumn<?, ?> colUnit;
    @FXML private TableColumn<?, ?> colUnitPrice;
    @FXML private TableColumn<?, ?> colSupplier;
    @FXML private TableColumn<?, ?> colLastUpdated;

    @FXML private ComboBox<String> sortCombo;
    @FXML private Label inventoryMsg;

    // Details
    @FXML private TitledPane detailsPane;
    @FXML private Label detailItemName;
    @FXML private Label detailSupplier;
    @FXML private Label detailContacts;
    @FXML private Label detailStockPerDepot;
    @FXML private Label detailDelivery;

    // Actions
    @FXML private Button callSupplierBtn;
    @FXML private Button reserveBtn;
    @FXML private Button notifyBtn;

    // Reserve/Notify inputs
    @FXML private TextField reserveQtyField;
    @FXML private TextField preferredPickupDateField;
    @FXML private TextField notifyPhoneField;
    @FXML private TextField notifyEmailField;

    @FXML private Label actionMsg;

    @FXML
    private void initialize() {
        if (inputTypeGroup == null) {        // fallback if FXML group missing
            inputTypeGroup = new ToggleGroup();
            seedRadio.setToggleGroup(inputTypeGroup);
            fertilizerRadio.setToggleGroup(inputTypeGroup);
        }
        seedRadio.setSelected(true);

        cropCodeCombo.setDisable(false);
        fertGradeCombo.setDisable(true);

        sortCombo.getItems().setAll("price asc", "price desc");
    }


    // ==== Workflow hooks (UI only; logic to be implemented later) ====

    // event-6: Load district/upazila/union lists
    @FXML private void onFetchLocationLists() {
        validationMsg.setText(""); // clear
        // populate later from server; placeholder UX:
        // districtCombo.getItems().setAll(...); etc.
    }

    // event-8: input type toggled -> enable/disable subtype controls
    @FXML private void onInputTypeChanged() {
        boolean isSeed = seedRadio.isSelected();
        cropCodeCombo.setDisable(!isSeed);
        fertGradeCombo.setDisable(isSeed);
    }

    // event-9: validate filters
    @FXML private void onValidateFilters() {
        validationMsg.setText(""); // set validation result later
    }

    // event-10: request inventory
    @FXML private void onRequestInventory() {
        inventoryMsg.setText("Loading…"); // replace with server call later
    }

    // event-12: apply price sort
    @FXML private void onApplySort() {
        // will sort table items later
    }

    // event-13/14/15: open selected item details
    @FXML private void onOpenSelectedDetails() {
        detailsPane.setExpanded(true);
    }

    // event-16: action choices
    @FXML private void onCallSupplier() {
        // placeholder; implement dialer/OS hook or display modal later
        info("Call supplier", "Will call supplier contact (to implement).");
    }
    @FXML private void onReserveSelected() {
        // show reserve fields (already visible)
    }
    @FXML private void onNotifySelected() {
        // show notify fields (already visible)
    }

    // event-18/19/20: validate & submit Reserve/Notify
    @FXML private void onValidateAndSubmit() {
        actionMsg.setText("Submitting…"); // replace with validation + server submission later
    }

    // event-21: back to inventory or dashboard
    @FXML private void onBackToDashboard() {
        // handled by dashboard nav; no-op here (or fire event up)
        Window w = actionMsg.getScene().getWindow();
        // no navigation here to avoid coupling; dashboard already provides Back button
    }

    @FXML private void onOpenMyRequests() {
        // navigate to another dashboard tab/screen if you add it later
    }

    @FXML
    private void onBack() throws IOException {
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(
                HelloApplication.class.getResource("nusrat/farmerDashboard.fxml")
        ));
        Scene scene = new Scene(root);
        HelloApplication.stage.setTitle("Farmer Dashboard");
        HelloApplication.stage.setScene(scene);

    }

    // ==== helpers ====
    private void info(String title, String content) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(title);
        a.setContentText(content);
        a.showAndWait();
    }
}
