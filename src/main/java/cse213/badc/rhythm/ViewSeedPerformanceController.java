package cse213.badc.rhythm;

import cse213.badc.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ViewSeedPerformanceController {
    @FXML
    private TableView<SeedTrial> seedPerformanceTableView;
    @FXML
    private TableColumn<SeedTrial, String> trialIdTC;
    @FXML
    private TableColumn<SeedTrial, String> seedNameTC;
    @FXML
    private TableColumn<SeedTrial, String> cropTypeTC;
    @FXML
    private TableColumn<SeedTrial, Float> testAreaTC;
    @FXML
    private TableColumn<SeedTrial, Float> averageYieldTC;
    @FXML
    private TableColumn<SeedTrial, Float> germinationRateTC;
    @FXML
    private Label totalTrialsLabel;
    @FXML
    private Label avgYieldLabel;
    @FXML
    private Label avgGerminationLabel;

    private ArrayList<SeedTrial> seedTrials;

    @FXML
    public void initialize() {
        trialIdTC.setCellValueFactory(new PropertyValueFactory<>("trialId"));
        seedNameTC.setCellValueFactory(new PropertyValueFactory<>("seedName"));
        cropTypeTC.setCellValueFactory(new PropertyValueFactory<>("cropType"));
        testAreaTC.setCellValueFactory(new PropertyValueFactory<>("testArea"));
        averageYieldTC.setCellValueFactory(new PropertyValueFactory<>("averageYield"));
        germinationRateTC.setCellValueFactory(new PropertyValueFactory<>("germinationRate"));

        seedTrials = new ArrayList<>();
        loadSeedTrials();
        refreshTable();
    }

    private void loadSeedTrials() {
        try {
            File file = new File("seedTrials.bin");

            if (!file.exists()) {
                addSampleData();
                return;
            }

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

            while (true) {
                try {
                    SeedTrial trial = (SeedTrial) ois.readObject();
                    if (trial.isValidPerformance()) {
                        seedTrials.add(trial);
                    }
                } catch (Exception e) {
                    break;
                }
            }

            ois.close();
        } catch (Exception e) {
            addSampleData();
        }
    }

    private void addSampleData() {
        seedTrials.add(new SeedTrial("TRIAL-001", "BRRI Dhan28", "Rice", 2.5f, 5800f, 94.2f));
        seedTrials.add(new SeedTrial("TRIAL-002", "BRRI Dhan29", "Rice", 2.8f, 6200f, 92.8f));

        seedTrials.add(new SeedTrial("TRIAL-003", "BARI Gom25", "Wheat", 3.0f, 4500f, 89.5f));
        seedTrials.add(new SeedTrial("TRIAL-004", "BARI Gom26", "Wheat", 2.9f, 4800f, 91.2f));

        seedTrials.add(new SeedTrial("TRIAL-005", "BARI Bhutta9", "Maize", 2.2f, 7200f, 93.6f));
        seedTrials.add(new SeedTrial("TRIAL-006", "BARI Bhutta11", "Maize", 2.1f, 6800f, 90.7f));

        seedTrials.add(new SeedTrial("TRIAL-007", "BARI Masur11", "Lentil", 1.8f, 2100f, 86.3f));
        seedTrials.add(new SeedTrial("TRIAL-008", "BARI Masur13", "Lentil", 1.9f, 2250f, 88.9f));

        seedTrials.add(new SeedTrial("TRIAL-009", "BARI Chola9", "Chickpea", 2.0f, 1900f, 85.2f));
        seedTrials.add(new SeedTrial("TRIAL-010", "BARI Chola10", "Chickpea", 2.1f, 2050f, 87.6f));
    }

    private void refreshTable() {
        seedPerformanceTableView.getItems().clear();
        for (SeedTrial trial: seedTrials) {
            seedPerformanceTableView.getItems().add(trial);
        }

        calculateSummary();
    }

    private void calculateSummary() {
        if (seedTrials.isEmpty()) {
            totalTrialsLabel.setText("0");
            avgYieldLabel.setText("0");
            avgGerminationLabel.setText("0");
            return;
        }

        float totalYield = 0;
        float totalGermination = 0;

        for (SeedTrial trial: seedTrials) {
            totalYield = totalYield + trial.getAverageYield();
            totalGermination = totalGermination + trial.getGerminationRate();
        }

        float avgYield = totalYield / seedTrials.size();
        float avgGermination = totalGermination / seedTrials.size();

        totalTrialsLabel.setText(String.valueOf(seedTrials.size()));
        avgYieldLabel.setText(String.format("%.2f kg/ha", avgYield));
        avgGerminationLabel.setText(String.format("%.2f %%", avgGermination));
    }

    @FXML
    public void viewPerformanceChartOA(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PerformanceChartView.fxml"));
            Parent root = loader.load();
            PerformanceChartController controller = loader.getController();
            controller.setTrials(seedTrials);

            Stage stage = new Stage();
            stage.setTitle("Performance Distribution Chart");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            Helper.showAlert("Error", "Could not open chart view");
        }
    }

    @FXML
    public void viewYieldChartOA(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("YieldChartView.fxml"));
            Parent root = loader.load();

            YieldChartController controller = loader.getController();
            controller.setTrials(seedTrials);

            Stage stage = new Stage();
            stage.setTitle("Yield Distribution Chart");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            Helper.showAlert("Error", "Could not open chart view");
        }
    }
}
