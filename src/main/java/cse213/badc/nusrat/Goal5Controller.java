package cse213.badc.nusrat;

import com.example.farmer_seed_dealer_project.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Objects;

public class Goal5Controller {

    // Inputs
    @FXML private ComboBox<String> complaintTypeCombo;
    @FXML private TextField productNameField;
    @FXML private TextField orderIdField;
    @FXML private TextField dealerNameField;
    @FXML private TextArea  descriptionArea;

    // Validation / info labels
    @FXML private Label typeError;
    @FXML private Label productError;
    @FXML private Label orderError;
    @FXML private Label dealerMsg;
    @FXML private Label descError;
    @FXML private Label fileLabel;
    @FXML private Label submitMsg;

    // Table
    @FXML private TableView<?> complaintsTable;
    @FXML private TableColumn<?, ?> colCid;
    @FXML private TableColumn<?, ?> colCDate;
    @FXML private TableColumn<?, ?> colCType;
    @FXML private TableColumn<?, ?> colCStatus;
    @FXML private TableColumn<?, ?> colCItem;

    // File chosen
    private File attachmentFile;

    @FXML
    private void initialize() {
        // Prefill type list; you can override with server-provided list later.
        // complaintTypeCombo.getItems().setAll("Product Quality Issue", "Delivery Delay", "Other");
        clearMessages();
        fileLabel.setText("No file selected");
    }

    // === UI actions (business logic to be added later) ===

    @FXML
    private void onChooseFile() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select attachment");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg"),
                new FileChooser.ExtensionFilter("Documents", "*.pdf")
        );
        File f = chooser.showOpenDialog(HelloApplication.stage);
        if (f != null) {
            attachmentFile = f;
            fileLabel.setText(f.getName());
        } else {
            attachmentFile = null;
            fileLabel.setText("No file selected");
        }
    }

    @FXML
    private void onValidate() {
        clearMessages();
        // VL: implement field checks later (type/product/description non-empty, order exists…)
        // Show specific errors into: typeError, productError, orderError, descError
        submitMsg.setText("Validation complete (sample).");
    }

    @FXML
    private void onSubmitComplaint() {
        clearMessages();
        submitMsg.setText("Submitting…");
        // DP: package inputs (+attachmentFile if present), send to server, receive complaint_id + status
        // OP: set message like: submitMsg.setText("Submitted. Complaint ID: CMP-2025-0123");
        // Optionally add a row to complaintsTable.
    }

    @FXML
    private void onTrackComplaint() {
        // OP/DP: open a track view or query by ID (from table/last submission)
        submitMsg.setText("Tracking complaint… (sample)");
    }

    @FXML
    private void onClear() {
        complaintTypeCombo.getSelectionModel().clearSelection();
        productNameField.clear();
        orderIdField.clear();
        dealerNameField.clear();
        descriptionArea.clear();
        attachmentFile = null;
        fileLabel.setText("No file selected");
        clearMessages();
        complaintsTable.getItems().clear();
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

    private void clearMessages() {
        if (typeError != null)   typeError.setText("");
        if (productError != null) productError.setText("");
        if (orderError != null)   orderError.setText("");
        if (dealerMsg != null)    dealerMsg.setText("");
        if (descError != null)    descError.setText("");
        if (submitMsg != null)    submitMsg.setText("");
    }

    private void alert(String header, String content) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText(header);
        a.setContentText(content);
        a.showAndWait();
    }
}
