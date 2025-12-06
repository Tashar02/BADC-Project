package cse213.badc.rhythm;

import cse213.badc.Helper;
import javafx.event.ActionEvent;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class U8G6_ViewFertilizerDataController {
    @javafx.fxml.FXML
    private ComboBox<String> fertilizerTypeComboBox;
    @javafx.fxml.FXML
    private ComboBox<String> fiscalYearComboBox;
    @javafx.fxml.FXML
    private TableView<FertilizerImport> fertilizerDataTableView;
    @javafx.fxml.FXML
    private TableColumn<FertilizerImport, String> importIdTC;
    @javafx.fxml.FXML
    private TableColumn<FertilizerImport, String> fertilizerTypeTC;
    @javafx.fxml.FXML
    private TableColumn<FertilizerImport, Float> quantityTC;
    @javafx.fxml.FXML
    private TableColumn<FertilizerImport, Float> costTC;
    @javafx.fxml.FXML
    private TableColumn<FertilizerImport, LocalDate> importDateTC;
    @javafx.fxml.FXML
    private TableColumn<FertilizerImport, String> statusTC;
    @javafx.fxml.FXML
    private Label totalQuantityLabel;
    @javafx.fxml.FXML
    private Label totalCostLabel;

    private ArrayList<FertilizerImport> allFertilizerImports;
    private ArrayList<FertilizerImport> filteredImports;

    @javafx.fxml.FXML
    public void initialize() {
        importIdTC.setCellValueFactory(new PropertyValueFactory<>("reportId"));
        fertilizerTypeTC.setCellValueFactory(new PropertyValueFactory<>("fertilizerType"));
        quantityTC.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        costTC.setCellValueFactory(new PropertyValueFactory<>("cost"));
        importDateTC.setCellValueFactory(new PropertyValueFactory<>("importDate"));
        statusTC.setCellValueFactory(new PropertyValueFactory<>("status"));

        allFertilizerImports = new ArrayList<>();
        filteredImports = new ArrayList<>();

        setupComboBoxes();
        loadTestData();
        refreshTable();
    }

    private void setupComboBoxes() {
        fertilizerTypeComboBox.getItems().addAll("All", "Urea", "DAP", "TSP", "MOP");
        fertilizerTypeComboBox.setValue("All");

        fiscalYearComboBox.getItems().addAll("2023", "2024", "2025");
        fiscalYearComboBox.setValue("2025");
    }

    private void loadTestData() {
        try {
            File file = new File("fertilizerImports.bin");

            if (!file.exists()) {
                addSampleData();
                return;
            }

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

            while (true) {
                try {
                    FertilizerImport imp = (FertilizerImport)ois.readObject();
                    allFertilizerImports.add(imp);
                } catch (Exception e) {
                    Helper.showAlert("File error", "Failed to load import data");
                    break;
                }
            }

            ois.close();
        } catch (Exception e) {
            addSampleData();
        }
    }

    private void addSampleData() {
        allFertilizerImports.add(new FertilizerImport("IMP001", "MOF001", "Urea", 5000f, 450000f, LocalDate.of(2025, 1, 10)));
        allFertilizerImports.add(new FertilizerImport("IMP002", "MOF001", "DAP", 3000f, 600000f, LocalDate.of(2025, 1, 15)));
        allFertilizerImports.add(new FertilizerImport("IMP003", "MOF001", "TSP", 2000f, 300000f, LocalDate.of(2025, 1, 20)));
        allFertilizerImports.add(new FertilizerImport("IMP004", "MOF001", "Urea", 4500f, 405000f, LocalDate.of(2025, 2, 5)));
        allFertilizerImports.add(new FertilizerImport("IMP005", "MOF001", "MOP", 2500f, 375000f, LocalDate.of(2025, 2, 10)));

        for (FertilizerImport imp: allFertilizerImports) {
            imp.setStatus("Approved");
        }
    }

    @javafx.fxml.FXML
    public void filterOA(ActionEvent actionEvent) {
        String selectedType = fertilizerTypeComboBox.getValue();

        filteredImports.clear();
        for (FertilizerImport imp: allFertilizerImports) {
            if (selectedType.equals("All") || imp.getFertilizerType().equals(selectedType)) {
                filteredImports.add(imp);
            }
        }

        refreshTable();
    }

    private void refreshTable() {
        fertilizerDataTableView.getItems().clear();
        for (FertilizerImport imp: filteredImports) {
            fertilizerDataTableView.getItems().add(imp);
        }

        calculateTotals();
    }

    private void calculateTotals() {
        float totalQuantity = 0;
        float totalCost = 0;

        for (FertilizerImport imp: filteredImports) {
            totalQuantity = totalQuantity + imp.getQuantity();
            totalCost = totalCost + imp.getCost();
        }

        totalQuantityLabel.setText(totalQuantity + " Tons");
        totalCostLabel.setText(String.format("%.2f", totalCost));
    }

    @javafx.fxml.FXML
    public void backToDashboardOA(ActionEvent actionEvent) throws IOException {
        Helper.backToDashboardU8(actionEvent);
    }
}
