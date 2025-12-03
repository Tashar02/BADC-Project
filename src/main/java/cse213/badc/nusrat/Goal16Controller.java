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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Goal16Controller {

    @FXML private TextField searchField;

    // Category Buttons
    @FXML private Button allBtn;
    @FXML private Button importBtn;
    @FXML private Button priceBtn;
    @FXML private Button guidelinesBtn;
    @FXML private Button marketBtn;

    // Content Display
    @FXML private VBox announcementsContainer;
    @FXML private Label validationLabel;
    @FXML private Label noResultsLabel;

    private List<Announcement> allAnnouncements = new ArrayList<>();
    private String selectedCategory = "All";

    @FXML
    public void initialize() {
        // OP: Show categories
        loadAnnouncements();
        displayAnnouncements(allAnnouncements);
    }

    private void loadAnnouncements() {
        allAnnouncements.add(new Announcement(
                "Import Notifications",
                "Urea Fertilizer Import - 50,000 MT",
                "10 Nov 2025",
                "Ministry of Agriculture",
                "New shipment of 50,000 MT Urea fertilizer arriving from UAE. Distribution begins 15 Nov 2025.",
                "Price: ৳22/kg | Effective: 15 Nov 2025"
        ));

        allAnnouncements.add(new Announcement(
                "Price Updates",
                "TSP Fertilizer Price Reduction",
                "08 Nov 2025",
                "BADC Pricing Committee",
                "TSP fertilizer price reduced by ৳3 per kg effective immediately.",
                "New Price: ৳25/kg | Previous: ৳28/kg"
        ));

        allAnnouncements.add(new Announcement(
                "Government Guidelines",
                "Fertilizer Storage Guidelines 2025",
                "05 Nov 2025",
                "Department of Agriculture",
                "Updated guidelines for proper storage and handling of chemical fertilizers.",
                "Effective: 01 Dec 2025"
        ));

        allAnnouncements.add(new Announcement(
                "Market Alerts",
                "DAP Fertilizer Shortage Alert",
                "02 Nov 2025",
                "BADC Market Monitoring",
                "Temporary shortage of DAP fertilizer in Rajshahi division. Alternative supplies arranged.",
                "Status: Resolving | Expected: 20 Nov 2025"
        ));

        allAnnouncements.add(new Announcement(
                "Import Notifications",
                "MOP Fertilizer Import - 30,000 MT",
                "28 Oct 2025",
                "Ministry of Agriculture",
                "MOP fertilizer shipment of 30,000 MT from Canada.",
                "Price: ৳35/kg | Effective: 05 Nov 2025"
        ));
    }

    // UID: Select category
    @FXML
    private void onSelectAll() {
        selectedCategory = "All";
        updateButtons();
        displayAnnouncements(allAnnouncements);
    }

    @FXML
    private void onSelectImport() {
        selectedCategory = "Import Notifications";
        updateButtons();
        filterAndDisplay();
    }

    @FXML
    private void onSelectPrice() {
        selectedCategory = "Price Updates";
        updateButtons();
        filterAndDisplay();
    }

    @FXML
    private void onSelectGuidelines() {
        selectedCategory = "Government Guidelines";
        updateButtons();
        filterAndDisplay();
    }

    @FXML
    private void onSelectMarket() {
        selectedCategory = "Market Alerts";
        updateButtons();
        filterAndDisplay();
    }

    private void updateButtons() {
        resetButton(allBtn);
        resetButton(importBtn);
        resetButton(priceBtn);
        resetButton(guidelinesBtn);
        resetButton(marketBtn);

        Button selectedBtn = null;
        switch (selectedCategory) {
            case "All": selectedBtn = allBtn; break;
            case "Import Notifications": selectedBtn = importBtn; break;
            case "Price Updates": selectedBtn = priceBtn; break;
            case "Government Guidelines": selectedBtn = guidelinesBtn; break;
            case "Market Alerts": selectedBtn = marketBtn; break;
        }

        if (selectedBtn != null) {
            selectedBtn.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-alignment: CENTER_LEFT;");
        }
    }

    private void resetButton(Button btn) {
        if (btn != allBtn) {
            btn.setStyle("-fx-alignment: CENTER_LEFT;");
        }
    }

    // UID, VL: Search
    @FXML
    private void onSearch() {
        String keyword = searchField.getText();

        // VL: Validate keyword
        if (keyword == null || keyword.trim().isEmpty()) {
            validationLabel.setText("Please enter a search keyword.");
            validationLabel.setVisible(true);
            return;
        }

        validationLabel.setVisible(false);

        // DP: Fetch matching announcements
        List<Announcement> results = new ArrayList<>();
        String keywordLower = keyword.toLowerCase();

        for (Announcement a : allAnnouncements) {
            if (a.getTitle().toLowerCase().contains(keywordLower) ||
                    a.getDetails().toLowerCase().contains(keywordLower) ||
                    a.getCategory().toLowerCase().contains(keywordLower)) {
                results.add(a);
            }
        }

        if (results.isEmpty()) {
            showNoResults();
        } else {
            displayAnnouncements(results);
        }
    }

    private void filterAndDisplay() {
        validationLabel.setVisible(false);

        List<Announcement> filtered = new ArrayList<>();
        for (Announcement a : allAnnouncements) {
            if (a.getCategory().equals(selectedCategory)) {
                filtered.add(a);
            }
        }

        if (filtered.isEmpty()) {
            showNoResults();
        } else {
            displayAnnouncements(filtered);
        }
    }

    // OP: Display announcements with view/download options
    private void displayAnnouncements(List<Announcement> announcements) {
        announcementsContainer.getChildren().clear();
        noResultsLabel.setVisible(false);

        for (Announcement a : announcements) {
            announcementsContainer.getChildren().add(createAnnouncementCard(a));
        }
    }

    private VBox createAnnouncementCard(Announcement a) {
        VBox card = new VBox(8);
        card.setStyle("-fx-background-color: white; -fx-border-color: #ddd; -fx-border-width: 1; " +
                "-fx-border-radius: 5; -fx-background-radius: 5; -fx-padding: 15;");
        card.setPrefWidth(600);

        Label titleLabel = new Label(a.getTitle());
        titleLabel.setFont(Font.font("System Bold", 15));

        Label categoryBadge = new Label(a.getCategory());
        categoryBadge.setStyle("-fx-background-color: #E3F2FD; -fx-text-fill: #1976D2; " +
                "-fx-padding: 3 8 3 8; -fx-background-radius: 3;");
        categoryBadge.setFont(Font.font(11));

        Label infoLabel = new Label("Date: " + a.getDate() + " | Authority: " + a.getAuthority());
        infoLabel.setFont(Font.font(12));
        infoLabel.setStyle("-fx-text-fill: #666;");

        Label detailsLabel = new Label(a.getDetails());
        detailsLabel.setWrapText(true);
        detailsLabel.setFont(Font.font(13));

        Label priceLabel = new Label(a.getPriceInfo());
        priceLabel.setFont(Font.font("System Bold", 12));
        priceLabel.setStyle("-fx-text-fill: #2E7D32;");

        HBox buttonBox = new HBox(10);
        Button viewBtn = new Button("View Full Notice");
        viewBtn.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        viewBtn.setOnAction(e -> onViewNotice(a));

        Button downloadBtn = new Button("Download PDF");
        downloadBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        downloadBtn.setOnAction(e -> onDownloadNotice(a));

        buttonBox.getChildren().addAll(viewBtn, downloadBtn);

        card.getChildren().addAll(titleLabel, categoryBadge, infoLabel, detailsLabel, priceLabel, buttonBox);
        return card;
    }

    private void showNoResults() {
        announcementsContainer.getChildren().clear();
        noResultsLabel.setVisible(true);
    }

    private void onViewNotice(Announcement a) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("View Notice");
        alert.setHeaderText(a.getTitle());
        alert.setContentText("Date: " + a.getDate() + "\nAuthority: " + a.getAuthority() +
                "\n\n" + a.getDetails() + "\n\n" + a.getPriceInfo());
        alert.showAndWait();
    }

    private void onDownloadNotice(Announcement a) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Download");
        alert.setHeaderText("Downloading: " + a.getTitle());
        alert.setContentText("The document will be downloaded.");
        alert.showAndWait();
    }

    @FXML
    private void onSubscribe() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Subscribe");
        alert.setContentText("Subscribe to receive updates.\n(Feature to be implemented)");
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

    // Data class
    public static class Announcement {
        private String category, title, date, authority, details, priceInfo;

        public Announcement(String category, String title, String date, String authority,
                            String details, String priceInfo) {
            this.category = category;
            this.title = title;
            this.date = date;
            this.authority = authority;
            this.details = details;
            this.priceInfo = priceInfo;
        }

        public String getCategory() { return category; }
        public String getTitle() { return title; }
        public String getDate() { return date; }
        public String getAuthority() { return authority; }
        public String getDetails() { return details; }
        public String getPriceInfo() { return priceInfo; }
    }
}
