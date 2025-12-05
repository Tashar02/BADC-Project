package cse213.badc.rhythm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.Node;
import javafx.stage.Stage;
import java.util.ArrayList;

public class YieldChartController {
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
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            showAlert("Error", "Could not close window");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
