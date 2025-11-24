package com.example.farmer_seed_dealer_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class LoginController {

    @javafx.fxml.FXML
    private ComboBox<String> userInput;

    @javafx.fxml.FXML
    private PasswordField passInput;

    // Static password for both roles
    private static final String STATIC_PASSWORD = "1234";

    @javafx.fxml.FXML
    public void initialize() {
        // Two roles available
        userInput.getItems().setAll("Farmer", "Seed Dealer");
        userInput.getSelectionModel().selectFirst();
    }

    @javafx.fxml.FXML
    public void login(ActionEvent actionEvent) throws IOException {
        if (userInput.getValue() == null || passInput.getText().isEmpty()) {
            showError("Choose user and enter password!");
            return;
        }

        // Validate credentials and navigate based on role
        if (!STATIC_PASSWORD.equals(passInput.getText())) {
            showError("Invalid credentials. Please check your user and password.");
            return;
        }

        // Navigate based on user role
        String selectedRole = userInput.getValue();
        AnchorPane root;
        String title;

        if ("Farmer".equals(selectedRole)) {
            root = FXMLLoader.load(Objects.requireNonNull(
                    HelloApplication.class.getResource("nusrat/farmerDashboard.fxml")
            ));
            title = "Farmer Dashboard";
        } else if ("Seed Dealer".equals(selectedRole)) {
            root = FXMLLoader.load(Objects.requireNonNull(
                    HelloApplication.class.getResource("nusrat/seedDealerDashboard.fxml")
            ));
            title = "Seed Dealer Dashboard";
        } else {
            showError("Invalid user role selected.");
            return;
        }

        Scene scene = new Scene(root);
        HelloApplication.stage.setTitle(title);
        HelloApplication.stage.setScene(scene);
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Failed");
        alert.setHeaderText("Invalid credentials");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
