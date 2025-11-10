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

    // Static password
    private static final String STATIC_PASSWORD = "1234";

    @javafx.fxml.FXML
    public void initialize() {
        // Only one role for now
        userInput.getItems().setAll("Farmer");
        userInput.getSelectionModel().selectFirst();
    }

    @javafx.fxml.FXML
    public void login(ActionEvent actionEvent) throws IOException {
        if (userInput.getValue() == null || passInput.getText().isEmpty()) {
            showError("Choose user and enter password!");
            return;
        }

        // Validate credentials
        if ("Farmer".equals(userInput.getValue()) && STATIC_PASSWORD.equals(passInput.getText())) {
            // Navigate to Farmer Dashboard
            AnchorPane root = FXMLLoader.load(Objects.requireNonNull(
                    HelloApplication.class.getResource("nusrat/farmerDashboard.fxml")
            ));
            Scene scene = new Scene(root);
            HelloApplication.stage.setTitle("Farmer Dashboard");
            HelloApplication.stage.setScene(scene);
            return;
        }

        // Otherwise invalid
        showError("Invalid credentials. Please check your user and password.");
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Failed");
        alert.setHeaderText("Invalid credentials");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
