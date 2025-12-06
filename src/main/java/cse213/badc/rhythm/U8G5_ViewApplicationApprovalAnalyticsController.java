package cse213.badc.rhythm;

import cse213.badc.Helper;
import javafx.event.ActionEvent;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;

public class U8G5_ViewApplicationApprovalAnalyticsController {
    @javafx.fxml.FXML
    private ComboBox<String> regionComboBox;
    @javafx.fxml.FXML
    private TableView<ApplicationAnalytics> applicationsTableView;
    @javafx.fxml.FXML
    private TableColumn<ApplicationAnalytics, String> appIdTC;
    @javafx.fxml.FXML
    private TableColumn<ApplicationAnalytics, String> farmerNameTC;
    @javafx.fxml.FXML
    private TableColumn<ApplicationAnalytics, String> statusTC;
    @javafx.fxml.FXML
    private Label summaryLabel;

    private ArrayList<ApplicationAnalytics> allApplications;
    private ApplicationApprovalSummary summary;

    @javafx.fxml.FXML
    public void initialize() throws IOException {
        setupTableColumns();
        setupComboBox();
        loadApplicationData();
        displayApplications();
    }

    private void setupTableColumns() {
        appIdTC.setCellValueFactory(new PropertyValueFactory<>("applicationId"));
        farmerNameTC.setCellValueFactory(new PropertyValueFactory<>("farmerName"));
        statusTC.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void setupComboBox() {
        ArrayList<String> regions = new ArrayList<>();
        regions.add("All");
        regions.add("Dhaka");
        regions.add("Chittagong");
        regions.add("Khulna");
        regions.add("Rajshahi");
        regions.add("Sylhet");
        regionComboBox.getItems().addAll(regions);
        regionComboBox.setValue("All");
    }

    private void loadApplicationData() throws IOException {
        allApplications = new ArrayList<>();
        Helper.loadFrom("application_analytics.bin", allApplications);

        if (allApplications.isEmpty()) {
            addSampleData();
        }
    }

    private void addSampleData() {
        allApplications.add(new ApplicationAnalytics("APP-001", "Farmer A", "Approved", "Dhaka"));
        allApplications.add(new ApplicationAnalytics("APP-002", "Farmer B", "Approved", "Chittagong"));
        allApplications.add(new ApplicationAnalytics("APP-003", "Farmer C", "Pending", "Khulna"));
        allApplications.add(new ApplicationAnalytics("APP-004", "Farmer D", "Approved", "Rajshahi"));
        allApplications.add(new ApplicationAnalytics("APP-005", "Farmer E", "Rejected", "Sylhet"));
    }

    @javafx.fxml.FXML
    public void filterOA(ActionEvent actionEvent) {
        displayApplications();
    }

    private void displayApplications() {
        applicationsTableView.getItems().clear();
        String selectedRegion = regionComboBox.getValue();

        for (ApplicationAnalytics app: allApplications) {
            if (selectedRegion.equals("All") || app.getRegion().equals(selectedRegion)) {
                applicationsTableView.getItems().add(app);
            }
        }

        ArrayList<ApplicationAnalytics> displayedApps = new ArrayList<>(applicationsTableView.getItems());
        summary = new ApplicationApprovalSummary(displayedApps);
        ArrayList<String> report = summary.generateReport();

        String summaryText = "";
        for (String line: report) {
            summaryText += line + "\n";
        }
        summaryLabel.setText(summaryText);
    }

    @javafx.fxml.FXML
    public void exportReportOA(ActionEvent actionEvent) {
        ArrayList<String> report = summary.generateReport();
        String reportText = "";
        for (String line: report) {
            reportText += line + "\n";
        }

        if (Helper.appendTextFile("application_approval_report.txt", reportText)) {
            Helper.showAlert("Success", "Report exported successfully");
        }
    }

    @javafx.fxml.FXML
    public void backOA(ActionEvent actionEvent) {
        Helper.closeWindow(actionEvent);
    }

    @javafx.fxml.FXML
    public void backToDashboardOA(ActionEvent actionEvent) throws IOException {
        Helper.backToDashboardU8(actionEvent);
    }
}
