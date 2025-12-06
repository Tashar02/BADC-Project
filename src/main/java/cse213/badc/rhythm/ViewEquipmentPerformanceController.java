package cse213.badc.rhythm;

import cse213.badc.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;

public class ViewEquipmentPerformanceController {
    @FXML
    private ComboBox<String> regionComboBox;
    @FXML
    private TableView<EquipmentAnalytics> equipmentTableView;
    @FXML
    private TableColumn<EquipmentAnalytics, String> pumpIdTC;
    @FXML
    private TableColumn<EquipmentAnalytics, String> locationTC;
    @FXML
    private TableColumn<EquipmentAnalytics, Double> uptimeTC;
    @FXML
    private TableColumn<EquipmentAnalytics, String> statusTC;
    @FXML
    private Label summaryLabel;

    private ArrayList<EquipmentAnalytics> allEquipment;
    private EquipmentPerformanceSummary summary;

    @FXML
    public void initialize() throws IOException {
        setupTableColumns();
        setupComboBox();
        loadEquipmentData();
        displayEquipment();
    }

    private void setupTableColumns() {
        pumpIdTC.setCellValueFactory(new PropertyValueFactory<>("pumpId"));
        locationTC.setCellValueFactory(new PropertyValueFactory<>("location"));
        uptimeTC.setCellValueFactory(new PropertyValueFactory<>("uptimePercentage"));
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

    private void loadEquipmentData() throws IOException {
        allEquipment = new ArrayList<>();
        Helper.loadFrom("equipment_analytics.bin", allEquipment);

        if (allEquipment.isEmpty()) {
            addSampleData();
        }
    }

    private void addSampleData() {
        allEquipment.add(new EquipmentAnalytics("PUMP-001", "Dhaka", 94.5f, "Dhaka"));
        allEquipment.add(new EquipmentAnalytics("PUMP-002", "Chittagong", 87.2f, "Chittagong"));
        allEquipment.add(new EquipmentAnalytics("PUMP-003", "Khulna", 82.1f, "Khulna"));
        allEquipment.add(new EquipmentAnalytics("PUMP-004", "Rajshahi", 91.3f, "Rajshahi"));
        allEquipment.add(new EquipmentAnalytics("PUMP-005", "Sylhet", 79.8f, "Sylhet"));
    }

    @FXML
    public void filterOA(ActionEvent actionEvent) {
        displayEquipment();
    }

    private void displayEquipment() {
        equipmentTableView.getItems().clear();
        String selectedRegion = regionComboBox.getValue();

        for (EquipmentAnalytics eq: allEquipment) {
            if (selectedRegion.equals("All") || eq.getRegion().equals(selectedRegion)) {
                equipmentTableView.getItems().add(eq);
            }
        }

        ArrayList<EquipmentAnalytics> displayedEquipment = new ArrayList<>(equipmentTableView.getItems());
        summary = new EquipmentPerformanceSummary(displayedEquipment);
        ArrayList<String> report = summary.generateReport();
        summaryLabel.setText(String.join("\n", report));
    }

    @FXML
    public void exportReportOA(ActionEvent actionEvent) {
        ArrayList<String> report = summary.generateReport();
        if (Helper.appendTextFile("equipment_performance_report.txt", String.join("\n", report))) {
            Helper.showAlert("Success", "Report exported successfully");
        }
    }

    @FXML
    public void backOA(ActionEvent actionEvent) {
        Helper.closeWindow(actionEvent);
    }
}
