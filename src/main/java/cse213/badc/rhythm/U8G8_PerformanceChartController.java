package cse213.badc.rhythm;

import cse213.badc.Helper;
import javafx.event.ActionEvent;

import javafx.scene.chart.PieChart;

import java.util.ArrayList;

public class U8G8_PerformanceChartController {
    @javafx.fxml.FXML
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

    @javafx.fxml.FXML
    public void backToMainOA(ActionEvent actionEvent) {
        Helper.closeWindow(actionEvent);
    }
}
