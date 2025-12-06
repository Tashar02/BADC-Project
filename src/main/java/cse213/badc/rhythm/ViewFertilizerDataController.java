package cse213.badc.rhythm;

import cse213.badc.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class ViewFertilizerDataController {
    @FXML
    private ComboBox<String> fertilizerTypeComboBox;
    @FXML
    private ComboBox<String> fiscalYearComboBox;
    @FXML
    private TableView<FertilizerImport> fertilizerDataTableView;
    @FXML
    private TableColumn<FertilizerImport, String> importIdTC;
    @FXML
    private TableColumn<FertilizerImport, String> fertilizerTypeTC;
    @FXML
    private TableColumn<FertilizerImport, Float> quantityTC;
    @FXML
    private TableColumn<FertilizerImport, Float> costTC;
    @FXML
    private TableColumn<FertilizerImport, LocalDate> importDateTC;
    @FXML
    private TableColumn<FertilizerImport, String> statusTC;
    @FXML
    private Label totalQuantityLabel;
    @FXML
    private Label totalCostLabel;

    private ArrayList<FertilizerImport> allFertilizerImports;
    private ArrayList<FertilizerImport> filteredImports;

    @FXML
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

    @FXML
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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
