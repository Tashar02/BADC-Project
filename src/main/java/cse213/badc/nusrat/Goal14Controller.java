package cse213.badc.nusrat;

import com.example.farmer_seed_dealer_project.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Goal14Controller {

    // Search Components
    @FXML private TextField searchField;

    // Category Buttons
    @FXML private Button allCategoriesBtn;
    @FXML private Button dealerConductBtn;
    @FXML private Button distributionPoliciesBtn;
    @FXML private Button complianceGuidelinesBtn;
    @FXML private Button penaltiesBtn;
    @FXML private Button pricingBtn;

    // Filter Checkboxes
    @FXML private CheckBox recentOnlyCheckbox;
    @FXML private CheckBox importantOnlyCheckbox;

    // Content Display
    @FXML private VBox circularsContainer;
    @FXML private Label validationLabel;
    @FXML private Label noResultsLabel;

    // Data
    private List<CircularData> allCirculars = new ArrayList<>();
    private String selectedCategory = "All";

    @FXML
    public void initialize() {
        // OP: Display categorized list of circulars
        loadSampleCirculars();
        displayCirculars(allCirculars);
    }

    // Load sample circular data (simulated database)
    private void loadSampleCirculars() {
        allCirculars.add(new CircularData(
                "Dealer Conduct",
                "Code of Conduct for BADC Authorized Dealers - 2025",
                "Ministry of Agriculture",
                "15 Jan 2025",
                "Establishes ethical standards and professional conduct requirements for all BADC authorized seed and fertilizer dealers. Mandatory compliance.",
                true
        ));

        allCirculars.add(new CircularData(
                "Distribution Policies",
                "Updated Distribution Guidelines for Agricultural Inputs",
                "BADC Head Office",
                "10 Jan 2025",
                "New guidelines for distribution of seeds and fertilizers across all districts. Includes storage, transportation, and delivery protocols.",
                false
        ));

        allCirculars.add(new CircularData(
                "Compliance Guidelines",
                "Quality Assurance and Compliance Standards 2025",
                "BADC Compliance Division",
                "05 Jan 2025",
                "Comprehensive quality standards for seed and fertilizer handling. Includes inspection procedures and certification requirements.",
                true
        ));

        allCirculars.add(new CircularData(
                "Penalties",
                "Penalties for Non-Compliance and Violations",
                "Ministry of Agriculture",
                "20 Dec 2024",
                "Details penalties, fines, and license suspension procedures for dealers violating regulations. Includes appeal process.",
                true
        ));

        allCirculars.add(new CircularData(
                "Pricing Regulations",
                "Maximum Retail Price (MRP) Guidelines for Seeds",
                "BADC Pricing Committee",
                "15 Dec 2024",
                "Updated MRP list for all approved seed varieties. Dealers must display prices and maintain records of transactions.",
                false
        ));

        allCirculars.add(new CircularData(
                "Dealer Conduct",
                "Customer Service Standards for Dealers",
                "BADC Consumer Affairs",
                "10 Dec 2024",
                "Mandatory customer service standards including complaint handling, record keeping, and farmer assistance protocols.",
                false
        ));

        allCirculars.add(new CircularData(
                "Distribution Policies",
                "Subsidy Distribution Mechanism for Fertilizers",
                "Ministry of Agriculture",
                "01 Dec 2024",
                "Guidelines for distribution of subsidized fertilizers. Includes eligibility criteria and documentation requirements.",
                true
        ));

        allCirculars.add(new CircularData(
                "Compliance Guidelines",
                "Environmental Compliance for Dealer Premises",
                "BADC Environment Cell",
                "25 Nov 2024",
                "Environmental safety standards for storage and handling of agricultural inputs. Waste disposal and safety measures.",
                false
        ));
    }

    // UID: Select category
    @FXML
    private void onSelectAllCategories() {
        selectedCategory = "All";
        updateCategoryButtons();
        applyFiltersAndDisplay();
    }

    @FXML
    private void onSelectDealerConduct() {
        selectedCategory = "Dealer Conduct";
        updateCategoryButtons();
        applyFiltersAndDisplay();
    }

    @FXML
    private void onSelectDistributionPolicies() {
        selectedCategory = "Distribution Policies";
        updateCategoryButtons();
        applyFiltersAndDisplay();
    }

    @FXML
    private void onSelectComplianceGuidelines() {
        selectedCategory = "Compliance Guidelines";
        updateCategoryButtons();
        applyFiltersAndDisplay();
    }

    @FXML
    private void onSelectPenalties() {
        selectedCategory = "Penalties";
        updateCategoryButtons();
        applyFiltersAndDisplay();
    }

    @FXML
    private void onSelectPricing() {
        selectedCategory = "Pricing Regulations";
        updateCategoryButtons();
        applyFiltersAndDisplay();
    }

    private void updateCategoryButtons() {
        // Reset all buttons
        resetButtonStyle(allCategoriesBtn);
        resetButtonStyle(dealerConductBtn);
        resetButtonStyle(distributionPoliciesBtn);
        resetButtonStyle(complianceGuidelinesBtn);
        resetButtonStyle(penaltiesBtn);
        resetButtonStyle(pricingBtn);

        // Highlight selected category
        Button selectedBtn = null;
        switch (selectedCategory) {
            case "All": selectedBtn = allCategoriesBtn; break;
            case "Dealer Conduct": selectedBtn = dealerConductBtn; break;
            case "Distribution Policies": selectedBtn = distributionPoliciesBtn; break;
            case "Compliance Guidelines": selectedBtn = complianceGuidelinesBtn; break;
            case "Penalties": selectedBtn = penaltiesBtn; break;
            case "Pricing Regulations": selectedBtn = pricingBtn; break;
        }

        if (selectedBtn != null) {
            selectedBtn.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-alignment: CENTER_LEFT;");
        }
    }

    private void resetButtonStyle(Button btn) {
        if (btn != allCategoriesBtn) {
            btn.setStyle("-fx-alignment: CENTER_LEFT;");
        }
    }

    // UID, VL: Search circulars by keyword
    @FXML
    private void onSearch() {
        String keyword = searchField.getText();

        // VL: Check if entered keywords are valid
        if (keyword == null || keyword.trim().isEmpty()) {
            validationLabel.setText("Please enter a search keyword.");
            validationLabel.setVisible(true);
            return;
        }

        validationLabel.setVisible(false);

        // DP: Fetch all circulars matching the search query
        List<CircularData> results = filterCirculars(keyword);

        if (results.isEmpty()) {
            showNoResults();
        } else {
            displayCirculars(results);
        }
    }

    @FXML
    private void onClearSearch() {
        searchField.clear();
        validationLabel.setVisible(false);
        selectedCategory = "All";
        updateCategoryButtons();
        recentOnlyCheckbox.setSelected(false);
        importantOnlyCheckbox.setSelected(false);
        displayCirculars(allCirculars);
    }

    @FXML
    private void onApplyFilters() {
        applyFiltersAndDisplay();
    }

    // DP, VL: Filter circulars based on category, search, and filters
    private void applyFiltersAndDisplay() {
        validationLabel.setVisible(false);

        List<CircularData> filtered = new ArrayList<>();

        for (CircularData circular : allCirculars) {
            // Filter by category
            if (!selectedCategory.equals("All") && !circular.getCategory().equals(selectedCategory)) {
                continue;
            }

            // Filter by search keyword
            String keyword = searchField.getText();
            if (keyword != null && !keyword.trim().isEmpty()) {
                String keywordLower = keyword.toLowerCase();
                if (!circular.getTitle().toLowerCase().contains(keywordLower) &&
                        !circular.getSummary().toLowerCase().contains(keywordLower) &&
                        !circular.getCategory().toLowerCase().contains(keywordLower)) {
                    continue;
                }
            }

            // Filter by recent only
            if (recentOnlyCheckbox.isSelected()) {
                LocalDate circularDate = LocalDate.parse(circular.getPublicationDate(),
                        DateTimeFormatter.ofPattern("dd MMM yyyy"));
                if (circularDate.isBefore(LocalDate.now().minusDays(30))) {
                    continue;
                }
            }

            // Filter by important only
            if (importantOnlyCheckbox.isSelected() && !circular.isImportant()) {
                continue;
            }

            filtered.add(circular);
        }

        if (filtered.isEmpty()) {
            showNoResults();
        } else {
            displayCirculars(filtered);
        }
    }

    private List<CircularData> filterCirculars(String keyword) {
        List<CircularData> results = new ArrayList<>();
        String keywordLower = keyword.toLowerCase();

        for (CircularData circular : allCirculars) {
            if (circular.getTitle().toLowerCase().contains(keywordLower) ||
                    circular.getSummary().toLowerCase().contains(keywordLower) ||
                    circular.getCategory().toLowerCase().contains(keywordLower) ||
                    circular.getIssuingAuthority().toLowerCase().contains(keywordLower)) {
                results.add(circular);
            }
        }

        return results;
    }

    // OP: Display circulars with title, summary, publication date, and view/download options
    private void displayCirculars(List<CircularData> circulars) {
        circularsContainer.getChildren().clear();
        noResultsLabel.setVisible(false);

        for (CircularData circular : circulars) {
            VBox circularCard = createCircularCard(circular);
            circularsContainer.getChildren().add(circularCard);
        }
    }

    private VBox createCircularCard(CircularData circular) {
        VBox card = new VBox(8);
        card.setStyle("-fx-background-color: white; -fx-border-color: #ddd; -fx-border-width: 1; " +
                "-fx-border-radius: 5; -fx-background-radius: 5; -fx-padding: 15;");
        card.setPrefWidth(590);

        // Title
        Label titleLabel = new Label(circular.getTitle());
        titleLabel.setFont(Font.font("System Bold", 15));
        titleLabel.setWrapText(true);

        // Category and Important Badge
        HBox badgeBox = new HBox(8);
        Label categoryBadge = new Label(circular.getCategory());
        categoryBadge.setStyle("-fx-background-color: #E3F2FD; -fx-text-fill: #1976D2; " +
                "-fx-padding: 3 8 3 8; -fx-background-radius: 3;");
        categoryBadge.setFont(Font.font(11));
        badgeBox.getChildren().add(categoryBadge);

        if (circular.isImportant()) {
            Label importantBadge = new Label("IMPORTANT");
            importantBadge.setStyle("-fx-background-color: #FFCDD2; -fx-text-fill: #C62828; " +
                    "-fx-padding: 3 8 3 8; -fx-background-radius: 3; -fx-font-weight: bold;");
            importantBadge.setFont(Font.font(11));
            badgeBox.getChildren().add(importantBadge);
        }

        // Publication info
        Label infoLabel = new Label("Issued by: " + circular.getIssuingAuthority() +
                " | Date: " + circular.getPublicationDate());
        infoLabel.setFont(Font.font(12));
        infoLabel.setStyle("-fx-text-fill: #666;");

        // Summary
        Label summaryLabel = new Label(circular.getSummary());
        summaryLabel.setFont(Font.font(13));
        summaryLabel.setWrapText(true);
        summaryLabel.setStyle("-fx-text-fill: #333;");

        // Action buttons
        HBox buttonBox = new HBox(10);
        Button viewBtn = new Button("View Full Document");
        viewBtn.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        viewBtn.setOnAction(e -> onViewCircular(circular));

        Button downloadBtn = new Button("Download PDF");
        downloadBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        downloadBtn.setOnAction(e -> onDownloadCircular(circular));

        buttonBox.getChildren().addAll(viewBtn, downloadBtn);

        card.getChildren().addAll(titleLabel, badgeBox, infoLabel, summaryLabel, buttonBox);

        return card;
    }

    private void showNoResults() {
        circularsContainer.getChildren().clear();
        noResultsLabel.setVisible(true);
    }

    // OP: View circular
    private void onViewCircular(CircularData circular) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("View Circular");
        alert.setHeaderText(circular.getTitle());
        alert.setContentText(
                "Category: " + circular.getCategory() + "\n" +
                        "Issued by: " + circular.getIssuingAuthority() + "\n" +
                        "Date: " + circular.getPublicationDate() + "\n\n" +
                        "Summary:\n" + circular.getSummary() + "\n\n" +
                        "Full document will open in a new window.\n(Feature to be implemented with document viewer)"
        );
        alert.showAndWait();
    }

    // OP: Download circular
    private void onDownloadCircular(CircularData circular) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Download Circular");
        alert.setHeaderText("Downloading: " + circular.getTitle());
        alert.setContentText("The document will be downloaded to your default download folder.");
        alert.showAndWait();
    }

    @FXML
    private void onDownloadAll() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Download All");
        alert.setHeaderText("Download All Circulars");
        alert.setContentText("This will download " + allCirculars.size() + " circulars. Continue?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Download Started");
                info.setContentText("All circulars are being downloaded.");
                info.showAndWait();
            }
        });
    }

    @FXML
    private void onSubscribeUpdates() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Subscribe to Updates");
        alert.setHeaderText("Circular Update Notifications");
        alert.setContentText("Subscribe to receive email/SMS notifications when new circulars are published.\n(Feature to be implemented)");
        alert.showAndWait();
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

    // Inner class for Circular Data
    public static class CircularData {
        private String category;
        private String title;
        private String issuingAuthority;
        private String publicationDate;
        private String summary;
        private boolean important;

        public CircularData(String category, String title, String issuingAuthority,
                            String publicationDate, String summary, boolean important) {
            this.category = category;
            this.title = title;
            this.issuingAuthority = issuingAuthority;
            this.publicationDate = publicationDate;
            this.summary = summary;
            this.important = important;
        }

        public String getCategory() { return category; }
        public String getTitle() { return title; }
        public String getIssuingAuthority() { return issuingAuthority; }
        public String getPublicationDate() { return publicationDate; }
        public String getSummary() { return summary; }
        public boolean isImportant() { return important; }
    }
}
