package cse213.badc.rhythm;

import cse213.badc.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.Node;
import javafx.stage.Stage;
import java.util.ArrayList;

public class U8G8_YieldChartController {
    @FXML
    private PieChart yieldPieChart;

    private ArrayList<SeedTrial> trials;

    public void setTrials(ArrayList<SeedTrial> trials) {
        this.trials = trials;
        generateChart();
    }

    private void generateChart() {
        yieldPieChart.getData().clear();

        for (SeedTrial trial: trials) {
            float yield = trial.getAverageYield();
            PieChart.Data slice = new PieChart.Data(trial.getSeedName(), yield);
            yieldPieChart.getData().add(slice);
        }

        yieldPieChart.setTitle("");
        yieldPieChart.setLegendVisible(true);
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
