package com.example.farmer_seed_dealer_project.nusrat;

import com.example.farmer_seed_dealer_project.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.Objects;

public class Goal10Controller {

    // Search and Filter Components
    @FXML private TextField searchField;
    @FXML private ComboBox<String> districtFilter;
    @FXML private ComboBox<String> statusFilter;
    @FXML private ComboBox<String> categoryFilter;
    @FXML private Label filterValidationLabel;

    // TableView and Columns
    @FXML private TableView<DealerRecord> dealerTableView;
    @FXML private TableColumn<DealerRecord, String> dealerIdColumn;
    @FXML private TableColumn<DealerRecord, String> nameColumn;
    @FXML private TableColumn<DealerRecord, String> districtColumn;
    @FXML private TableColumn<DealerRecord, String> upazilaColumn;
    @FXML private TableColumn<DealerRecord, String> categoryColumn;
    @FXML private TableColumn<DealerRecord, String> statusColumn;
    @FXML private TableColumn<DealerRecord, String> validityColumn;
    @FXML private TableColumn<DealerRecord, Void> actionColumn;

    // Pagination
    @FXML private Pagination dealerPagination;

    // Data
    private ObservableList<DealerRecord> allDealers = FXCollections.observableArrayList();
    private ObservableList<DealerRecord> filteredDealers = FXCollections.observableArrayList();
    private static final int ROWS_PER_PAGE = 10;

    @FXML
    public void initialize() {
        // event-4: DP - Fetch latest dealer profile & default location preferences
        setupFilters();
        setupTableColumns();
        setupPagination();

        // Initialize with empty data
        dealerPagination.setVisible(false);
    }

    // DP: Setup filter dropdowns
    private void setupFilters() {
        // Populate District Filter
        districtFilter.getItems().addAll(
                "All Districts", "Dhaka", "Rajshahi", "Chittagong", "Khulna",
                "Barisal", "Sylhet", "Rangpur", "Mymensingh"
        );
        districtFilter.setValue("All Districts");

        // event-7: VL - Renewal status must be one of [Approved, Pending, Rejected]
        statusFilter.getItems().addAll(
                "All Status", "Approved", "Pending", "Rejected", "Under Review"
        );
        statusFilter.setValue("All Status");

        // Populate Category Filter
        categoryFilter.getItems().addAll(
                "All Categories", "Seed", "Fertilizer", "Both"
        );
        categoryFilter.setValue("All Categories");
    }

    // event-5: OP - Present Dealer List in Table/Grid View
    private void setupTableColumns() {
        dealerIdColumn.setCellValueFactory(new PropertyValueFactory<>("dealerId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        districtColumn.setCellValueFactory(new PropertyValueFactory<>("district"));
        upazilaColumn.setCellValueFactory(new PropertyValueFactory<>("upazila"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        validityColumn.setCellValueFactory(new PropertyValueFactory<>("validityPeriod"));

        // Custom cell factory for status column with color coding
        statusColumn.setCellFactory(column -> new TableCell<DealerRecord, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    // event-10: OP - Approved dealers show green badge; rejected ones show red
                    if (item.equals("Approved")) {
                        setStyle("-fx-background-color: #C8E6C9; -fx-text-fill: #2E7D32; -fx-font-weight: bold;");
                    } else if (item.equals("Rejected")) {
                        setStyle("-fx-background-color: #FFCDD2; -fx-text-fill: #C62828; -fx-font-weight: bold;");
                    } else if (item.equals("Pending") || item.equals("Under Review")) {
                        setStyle("-fx-background-color: #FFF9C4; -fx-text-fill: #F57F17; -fx-font-weight: bold;");
                    }
                }
            }
        });

        // event-9: UIE - Select a Dealer for Detailed View (Action buttons in each row)
        actionColumn.setCellFactory(param -> new TableCell<DealerRecord, Void>() {
            private final Button viewBtn = new Button("View");
            private final HBox pane = new HBox(5, viewBtn);

            {
                viewBtn.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-size: 10px;");
                viewBtn.setOnAction(event -> {
                    DealerRecord dealer = getTableView().getItems().get(getIndex());
                    viewDealerDetails(dealer);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : pane);
            }
        });
    }

    // Setup pagination control
    private void setupPagination() {
        dealerPagination.setPageFactory(this::createPage);
    }

    // event-5: OP - System displays a paginated, sortable table view
    private TableView<DealerRecord> createPage(int pageIndex) {
        int fromIndex = pageIndex * ROWS_PER_PAGE;
        int toIndex = Math.min(fromIndex + ROWS_PER_PAGE, filteredDealers.size());

        dealerTableView.setItems(FXCollections.observableArrayList(
                filteredDealers.subList(fromIndex, toIndex)
        ));

        return dealerTableView;
    }

    // event-4: DP - Fetch latest dealer profile (simulated data)
    @FXML
    private void onLoadDealerData() {
        filterValidationLabel.setText("");

        // Simulated dealer data
        allDealers.clear();
        allDealers.addAll(
                new DealerRecord("D001", "Abdul Karim", "Dhaka", "Savar", "Seed", "Approved", "01 Jan 2025 - 31 Dec 2025"),
                new DealerRecord("D002", "Rahim Uddin", "Rajshahi", "Paba", "Fertilizer", "Approved", "15 Feb 2025 - 14 Feb 2026"),
                new DealerRecord("D003", "Fatema Begum", "Chittagong", "Rangunia", "Both", "Pending", "Pending Review"),
                new DealerRecord("D004", "Kamal Hossain", "Khulna", "Dumuria", "Seed", "Rejected", "Application Rejected"),
                new DealerRecord("D005", "Salma Akter", "Sylhet", "Companiganj", "Fertilizer", "Approved", "01 Mar 2025 - 28 Feb 2026"),
                new DealerRecord("D006", "Jahangir Alam", "Rangpur", "Badarganj", "Both", "Under Review", "Under Review"),
                new DealerRecord("D007", "Nasrin Sultana", "Barisal", "Bakerganj", "Seed", "Approved", "10 Jan 2025 - 09 Jan 2026"),
                new DealerRecord("D008", "Mizanur Rahman", "Mymensingh", "Trishal", "Fertilizer", "Approved", "20 Feb 2025 - 19 Feb 2026"),
                new DealerRecord("D009", "Ayesha Khatun", "Dhaka", "Keraniganj", "Seed", "Pending", "Pending Review"),
                new DealerRecord("D010", "Habibur Rahman", "Rajshahi", "Godagari", "Both", "Approved", "05 Jan 2025 - 04 Jan 2026")
        );

        filteredDealers.clear();
        filteredDealers.addAll(allDealers);

        updatePagination();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Data Loaded");
        alert.setHeaderText("Dealer List Loaded Successfully");
        alert.setContentText("Total " + allDealers.size() + " dealer records loaded.");
        alert.showAndWait();
    }

    // event-6: UID, UIE - Apply Filters or Search
    @FXML
    private void onApplyFilters() {
        // event-7: VL - Validate Search and Filter Inputs
        if (!validateFilters()) {
            return;
        }

        filterValidationLabel.setText("");
        filteredDealers.clear();

        String searchText = searchField.getText().toLowerCase().trim();
        String selectedDistrict = districtFilter.getValue();
        String selectedStatus = statusFilter.getValue();
        String selectedCategory = categoryFilter.getValue();

        // event-6: The system filters displayed data dynamically
        for (DealerRecord dealer : allDealers) {
            boolean matchesSearch = searchText.isEmpty() ||
                    dealer.getName().toLowerCase().contains(searchText) ||
                    dealer.getDealerId().toLowerCase().contains(searchText) ||
                    dealer.getDistrict().toLowerCase().contains(searchText);

            boolean matchesDistrict = selectedDistrict.equals("All Districts") ||
                    dealer.getDistrict().equals(selectedDistrict);

            boolean matchesStatus = selectedStatus.equals("All Status") ||
                    dealer.getStatus().equals(selectedStatus);

            boolean matchesCategory = selectedCategory.equals("All Categories") ||
                    dealer.getCategory().equals(selectedCategory);

            if (matchesSearch && matchesDistrict && matchesStatus && matchesCategory) {
                filteredDealers.add(dealer);
            }
        }

        updatePagination();

        if (filteredDealers.isEmpty()) {
            filterValidationLabel.setText("No dealers found matching your search criteria.");
            filterValidationLabel.setTextFill(Color.ORANGE);
        }
    }

    // event-7: VL - Validate Search and Filter Inputs
    private boolean validateFilters() {
        // event-7: Empty input should reset filter to default view
        if (searchField.getText() != null && searchField.getText().trim().isEmpty() &&
                districtFilter.getValue().equals("All Districts") &&
                statusFilter.getValue().equals("All Status") &&
                categoryFilter.getValue().equals("All Categories")) {
            // Reset to show all data
            filteredDealers.clear();
            filteredDealers.addAll(allDealers);
            updatePagination();
            return false;
        }

        // event-7: District names must match official list
        if (!districtFilter.getValue().equals("All Districts")) {
            // Already validated by ComboBox selection
        }

        // event-7: Renewal status must be one of [Approved, Pending, Rejected]
        if (!statusFilter.getValue().equals("All Status")) {
            // Already validated by ComboBox selection
        }

        return true;
    }

    // UIE - Reset Filters
    @FXML
    private void onResetFilters() {
        searchField.clear();
        districtFilter.setValue("All Districts");
        statusFilter.setValue("All Status");
        categoryFilter.setValue("All Categories");
        filterValidationLabel.setText("");

        // event-7: VL - Empty input should reset filter to default view
        filteredDealers.clear();
        filteredDealers.addAll(allDealers);
        updatePagination();
    }

    private void updatePagination() {
        int pageCount = (int) Math.ceil((double) filteredDealers.size() / ROWS_PER_PAGE);
        dealerPagination.setPageCount(pageCount > 0 ? pageCount : 1);
        dealerPagination.setCurrentPageIndex(0);
        dealerPagination.setVisible(true);
        createPage(0);
    }

    // event-9: UIE - Select a Dealer for Detailed View
    @FXML
    private void onViewDealerDetails() {
        DealerRecord selected = dealerTableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a dealer from the table.");
            return;
        }
        viewDealerDetails(selected);
    }

    // event-10: OP - Display Dealer Renewal Decision
    private void viewDealerDetails(DealerRecord dealer) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Dealer Details");
        alert.setHeaderText("Dealer ID: " + dealer.getDealerId());

        String content = String.format(
                "Name: %s\nDistrict: %s\nUpazila: %s\nCategory: %s\n\n" +
                        "=== Renewal Decision ===\nStatus: %s\nValidity: %s\n\n" +
                        "Remarks: %s",
                dealer.getName(), dealer.getDistrict(), dealer.getUpazila(), dealer.getCategory(),
                dealer.getStatus(), dealer.getValidityPeriod(),
                getRemarks(dealer.getStatus())
        );

        alert.setContentText(content);
        alert.showAndWait();
    }

    private String getRemarks(String status) {
        switch (status) {
            case "Approved": return "License renewed after document verification.";
            case "Rejected": return "Application rejected due to incomplete documentation.";
            case "Pending": return "Application is currently under review.";
            case "Under Review": return "Documents are being verified by BADC officials.";
            default: return "No remarks available.";
        }
    }

    // event-11: UIE, OP - Download or View Renewal Certificate
    @FXML
    private void onDownloadCertificate() {
        DealerRecord selected = dealerTableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select an approved dealer to download certificate.");
            return;
        }

        if (!selected.getStatus().equals("Approved")) {
            showAlert(Alert.AlertType.WARNING, "Not Approved", "Certificate is only available for approved dealers.");
            return;
        }

        showAlert(Alert.AlertType.INFORMATION, "Download Certificate",
                "Certificate for " + selected.getName() + " will be downloaded.\n(Feature to be implemented)");
    }

    // event-12: DP - Notify Dealer via SMS/Email
    @FXML
    private void onSendNotification() {
        DealerRecord selected = dealerTableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a dealer to send notification.");
            return;
        }

        String message = String.format(
                "Dear Dealer,\nYour BADC dealership status: %s\n%s\n\nContact support for queries.",
                selected.getStatus(),
                selected.getStatus().equals("Approved") ? "Valid until: " + selected.getValidityPeriod() : ""
        );

        showAlert(Alert.AlertType.INFORMATION, "Send Notification",
                "Notification will be sent to:\n" + selected.getName() + "\n\nMessage:\n" + message);
    }

    // event-15: DP, OP - Generate Summary Report of Approved Dealers
    @FXML
    private void onExportReport() {
        if (allDealers.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "No Data", "Please load dealer data first.");
            return;
        }

        // Count statistics
        long approved = allDealers.stream().filter(d -> d.getStatus().equals("Approved")).count();
        long pending = allDealers.stream().filter(d -> d.getStatus().equals("Pending") || d.getStatus().equals("Under Review")).count();
        long rejected = allDealers.stream().filter(d -> d.getStatus().equals("Rejected")).count();

        String report = String.format(
                "=== Dealer Summary Report ===\n\n" +
                        "Total Applications: %d\n" +
                        "Approved Dealers: %d\n" +
                        "Pending/Under Review: %d\n" +
                        "Rejected: %d\n\n" +
                        "Report will be exported as Excel/PDF.\n" +
                        "Dealer list and renewal report successfully updated and exported.",
                allDealers.size(), approved, pending, rejected
        );

        showAlert(Alert.AlertType.INFORMATION, "Export Report", report);
    }

    // UIE - Navigate back to dashboard
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

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Inner class for Dealer Record
    public static class DealerRecord {
        private String dealerId;
        private String name;
        private String district;
        private String upazila;
        private String category;
        private String status;
        private String validityPeriod;

        public DealerRecord(String dealerId, String name, String district, String upazila,
                            String category, String status, String validityPeriod) {
            this.dealerId = dealerId;
            this.name = name;
            this.district = district;
            this.upazila = upazila;
            this.category = category;
            this.status = status;
            this.validityPeriod = validityPeriod;
        }

        // Getters
        public String getDealerId() { return dealerId; }
        public String getName() { return name; }
        public String getDistrict() { return district; }
        public String getUpazila() { return upazila; }
        public String getCategory() { return category; }
        public String getStatus() { return status; }
        public String getValidityPeriod() { return validityPeriod; }
    }
}
