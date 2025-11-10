package com.example.farmer_seed_dealer_project.nusrat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class FarmerDashboardController {

    @FXML
    private AnchorPane contentPane;

    // Helper: load a child FXML into the content pane
    private void loadView(String fxmlResource) {
        try {
            Node view = FXMLLoader.load(getClass().getResource(fxmlResource));
            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);
            contentPane.getChildren().setAll(view);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Failed to open screen");
            alert.setContentText("Could not load: " + fxmlResource + "\n" + e.getMessage());
            alert.showAndWait();
        }
    }

    // Goal button handlers (replace with real names later)
    @FXML private void onGoal1() { loadView("/com/app/farmer/goal1.fxml"); }
    @FXML private void onGoal2() { loadView("/com/app/farmer/goal2.fxml"); }
    @FXML private void onGoal3() { loadView("/com/app/farmer/goal3.fxml"); }
    @FXML private void onGoal4() { loadView("/com/app/farmer/goal4.fxml"); }
    @FXML private void onGoal5() { loadView("/com/app/farmer/goal5.fxml"); }
    @FXML private void onGoal6() { loadView("/com/app/farmer/goal6.fxml"); }
    @FXML private void onGoal7() { loadView("/com/app/farmer/goal7.fxml"); }
    @FXML private void onGoal8() { loadView("/com/app/farmer/goal8.fxml"); }

    // Optional footer actions
    @FXML
    private void onLogout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Logout");
        alert.setContentText("Implement logout in your auth flow.");
        alert.showAndWait();
    }

    @FXML
    private void onClose() {
        Stage stage = (Stage) contentPane.getScene().getWindow();
        stage.close();
    }
}
