package cse213.badc.nusrat;

import cse213.badc.BADCApplication;
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

    
    @FXML private TextField searchField;
    @FXML private ComboBox<String> districtFilter;
    @FXML private ComboBox<String> statusFilter;
    @FXML private ComboBox<String> categoryFilter;
    @FXML private Label filterValidationLabel;

    @FXML private TableView<DealerRecord> dealerTableView;
    @FXML private TableColumn<DealerRecord, String> dealerIdColumn;
    @FXML private TableColumn<DealerRecord, String> nameColumn;
    @FXML private TableColumn<DealerRecord, String> districtColumn;
    @FXML private TableColumn<DealerRecord, String> upazilaColumn;
    @FXML private TableColumn<DealerRecord, String> categoryColumn;
    @FXML private TableColumn<DealerRecord, String> statusColumn;
    @FXML private TableColumn<DealerRecord, String> validityColumn;
    @FXML private TableColumn<DealerRecord, Void> actionColumn;

    
    

    
    private ObservableList<DealerRecord> allDealers = FXCollections.observableArrayList();
    private ObservableList<DealerRecord> filteredDealers = FXCollections.observableArrayList();
    private static final int ROWS_PER_PAGE = 10;

    @FXML
    public void initialize() {
      
        setupFilters();
        setupTableColumns();  
      
    }

    private void setupFilters() {
       
        districtFilter.getItems().addAll(
                "All Districts", "Dhaka", "Rajshahi", "Chittagong", "Khulna",
                "Barisal", "Sylhet", "Rangpur", "Mymensingh"
        );
        districtFilter.setValue("All Districts");

        statusFilter.getItems().addAll(
                "All Status", "Approved", "Pending", "Rejected", "Under Review"
        );
        statusFilter.setValue("All Status");

      
        categoryFilter.getItems().addAll(
                "All Categories", "Seed", "Fertilizer", "Both"
        );
        categoryFilter.setValue("All Categories");
    }

    private void setupTableColumns() {
        dealerIdColumn.setCellValueFactory(new PropertyValueFactory<>("dealerId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        districtColumn.setCellValueFactory(new PropertyValueFactory<>("district"));
        upazilaColumn.setCellValueFactory(new PropertyValueFactory<>("upazila"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        validityColumn.setCellValueFactory(new PropertyValueFactory<>("validityPeriod"));

       
        statusColumn.setCellFactory(column -> new TableCell<DealerRecord, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                   
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

   
   
    private TableView<DealerRecord> createPage(int pageIndex) {
        int fromIndex = pageIndex * ROWS_PER_PAGE;
        int toIndex = Math.min(fromIndex + ROWS_PER_PAGE, filteredDealers.size());

        dealerTableView.setItems(FXCollections.observableArrayList(
                filteredDealers.subList(fromIndex, toIndex)
        ));

        return dealerTableView;
    }

    
    @FXML
    private void onLoadDealerData() {
        filterValidationLabel.setText("");

      
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

     

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Data Loaded");
        alert.setHeaderText("Dealer List Loaded Successfully");
        alert.setContentText("Total " + allDealers.size() + " dealer records loaded.");
        alert.showAndWait();
    }

   
    @FXML
    private void onApplyFilters() {
       
        if (!validateFilters()) {
            return;
        }

        filterValidationLabel.setText("");
        filteredDealers.clear();

        String searchText = searchField.getText().toLowerCase().trim();
        String selectedDistrict = districtFilter.getValue();
        String selectedStatus = statusFilter.getValue();
        String selectedCategory = categoryFilter.getValue();

      
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

     

        if (filteredDealers.isEmpty()) {
            filterValidationLabel.setText("No dealers found matching your search criteria.");
            filterValidationLabel.setTextFill(Color.ORANGE);
        }
    }

   
    private boolean validateFilters() {
       
        if (searchField.getText() != null && searchField.getText().trim().isEmpty() &&
                districtFilter.getValue().equals("All Districts") &&
                statusFilter.getValue().equals("All Status") &&
                categoryFilter.getValue().equals("All Categories")) {
         
            filteredDealers.clear();
            filteredDealers.addAll(allDealers);
          
            return false;
        }

       
        if (!districtFilter.getValue().equals("All Districts")) {
       
        }

       ]
        if (!statusFilter.getValue().equals("All Status")) {
          
        }

        return true;
    }

 
    @FXML
    private void onResetFilters() {
        searchField.clear();
        districtFilter.setValue("All Districts");
        statusFilter.setValue("All Status");
        categoryFilter.setValue("All Categories");
        filterValidationLabel.setText("");

      
        filteredDealers.clear();
        filteredDealers.addAll(allDealers);
       
    }

  

    @FXML
    private void onViewDealerDetails() {
        DealerRecord selected = dealerTableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a dealer from the table.");
            return;
        }
        viewDealerDetails(selected);
    }

  
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

    


    @FXML
    private void onExportReport() {
        if (allDealers.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "No Data", "Please load dealer data first.");
            return;
        }

    
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

   
    @FXML
    private void onBackToDashboard() {
        try {
            AnchorPane root = FXMLLoader.load(Objects.requireNonNull(
                    BADCApplication.class.getResource("nusrat/seedDealerDashboard.fxml")
            ));
            Scene scene = new Scene(root);
            BADCApplication.stage.setTitle("Seed Dealer Dashboard");
            BADCApplication.stage.setScene(scene);
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

      
        public String getDealerId() { return dealerId; }
        public String getName() { return name; }
        public String getDistrict() { return district; }
        public String getUpazila() { return upazila; }
        public String getCategory() { return category; }
        public String getStatus() { return status; }
        public String getValidityPeriod() { return validityPeriod; }
    }
}
