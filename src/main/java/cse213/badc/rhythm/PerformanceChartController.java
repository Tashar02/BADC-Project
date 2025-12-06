package cse213.badc.rhythm;

import cse213.badc.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.Node;
import javafx.stage.Stage;
import java.util.ArrayList;

public class PerformanceChartController {
    @FXML
    private PieChart performancePieChart;

    private ArrayList<SeedTrial> trials;

    public void setTrials(ArrayList<SeedTrial> trials) {
        this.trials = trials;
        generateChart();
    }

    private void generateChart() {
        performancePieChart.getData().clear();

        for (SeedTrial trial: trials) {
            float performanceScore = trial.getPerformanceScore();
            PieChart.Data slice = new PieChart.Data(trial.getSeedName(), performanceScore);
            performancePieChart.getData().add(slice);
        }

        performancePieChart.setTitle("");
        performancePieChart.setLegendVisible(true);
    }

    @FXML
    public void backToMainOA(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            Helper.showAlert("Error", "Could not close window");
        }
    }
}
