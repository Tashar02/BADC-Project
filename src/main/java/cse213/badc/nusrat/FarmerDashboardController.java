package cse213.badc.nusrat;

import com.example.farmer_seed_dealer_project.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class FarmerDashboardController {

    @FXML
    private AnchorPane contentPane; // still used as owner reference

    // === Navigation helpers ===
    private void gotoView(String resourcePath, String title) {
        try {
            AnchorPane root = FXMLLoader.load(Objects.requireNonNull(
                    HelloApplication.class.getResource(resourcePath)
            ));
            Scene scene = new Scene(root);
            HelloApplication.stage.setTitle(title);
            HelloApplication.stage.setScene(scene);
        } catch (IOException | NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Failed to open screen");
            alert.setContentText("Could not load: " + resourcePath + "\n" + e.getMessage());
            alert.showAndWait();
        }
    }

    // === Goal buttons -> replace the whole scene on the same Stage ===
    // Make sure goal1.fxml ... goal8.fxml live under resources/com/example/farmer_seed_dealer_project/nusrat/
    @FXML private void onGoal1() { gotoView("nusrat/goal1.fxml", "Goal 1 — Local Availability"); }
    @FXML private void onGoal2() { gotoView("nusrat/goal2.fxml", "Goal 2"); }
    @FXML private void onGoal3() { gotoView("nusrat/goal3.fxml", "Goal 3"); }
    @FXML private void onGoal4() { gotoView("nusrat/goal4.fxml", "Goal 4"); }
    @FXML private void onGoal5() { gotoView("nusrat/goal5.fxml", "Goal 5"); }
    @FXML private void onGoal6() { gotoView("nusrat/goal6.fxml", "Goal 6"); }
    @FXML private void onGoal7() { gotoView("nusrat/goal7.fxml", "Goal 7"); }
    @FXML private void onGoal8() { gotoView("nusrat/goal8.fxml", "Goal 8"); }

    @FXML
    private void onLogout() {
        gotoView("Login.fxml", "Login");
    }

    @FXML
    private void onClose() {
        HelloApplication.stage.close();
    }
}
