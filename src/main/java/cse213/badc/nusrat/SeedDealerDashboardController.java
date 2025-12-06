package cse213.badc.nusrat;

import cse213.badc.BADCApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class SeedDealerDashboardController {

    @FXML
    private AnchorPane contentPane; // still used as owner reference

    // === Navigation helpers ===
    private void gotoView(String resourcePath, String title) {
        try {
            AnchorPane root = FXMLLoader.load(Objects.requireNonNull(
                    BADCApplication.class.getResource(resourcePath)
            ));
            Scene scene = new Scene(root);
            BADCApplication.stage.setTitle(title);
            BADCApplication.stage.setScene(scene);
        } catch (IOException | NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Failed to open screen");
            alert.setContentText("Could not load: " + resourcePath + "\n" + e.getMessage());
            alert.showAndWait();
        }
    }

    // === Goal buttons -> replace the whole scene on the same Stage ===
    // Make sure goal9.fxml ... goal16.fxml live under resources/com/example/farmer_seed_dealer_project/nusrat/
    @FXML private void onGoal9() { gotoView("nusrat/goal9.fxml", "Goal 9 — Seed Inventory"); }
    @FXML private void onGoal10() { gotoView("nusrat/goal10.fxml", "Goal 10"); }
    @FXML private void onGoal11() { gotoView("nusrat/goal11.fxml", "Goal 11"); }
    @FXML private void onGoal12() { gotoView("nusrat/goal12.fxml", "Goal 12"); }
    @FXML private void onGoal13() { gotoView("nusrat/goal13.fxml", "Goal 13"); }
    @FXML private void onGoal14() { gotoView("nusrat/goal14.fxml", "Goal 14"); }
    @FXML private void onGoal15() { gotoView("nusrat/goal15.fxml", "Goal 15"); }
    @FXML private void onGoal16() { gotoView("nusrat/goal16.fxml", "Goal 16"); }

    @FXML
    private void onLogout() {
        gotoView("Login.fxml", "Login");
    }

    @FXML
    private void onClose() {
        BADCApplication.stage.close();
    }
}
