package com.groupthree.group3_bookfairsimulator;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import static com.groupthree.group3_bookfairsimulator.User.userList;

public class LoginController
{
    @javafx.fxml.FXML
    private PasswordField newPassInput;
    @javafx.fxml.FXML
    private PasswordField checkNewPass;
    @javafx.fxml.FXML
    private CheckBox showPass;
    @javafx.fxml.FXML
    private TextField newUserInput;
    @javafx.fxml.FXML
    private Label createAccountLabel;
    @javafx.fxml.FXML
    private ComboBox<String> userInput;
    @javafx.fxml.FXML
    private Label loginLabel;
    @javafx.fxml.FXML
    private PasswordField passInput;

    @javafx.fxml.FXML
    public void initialize() {
        userInput.getItems().addAll("Author", "Publisher", "Visitor", "Event Manager", "Stall Owner", "Maintenance Officer", "Security Officer", "Help Desk Officer");
    }

    @javafx.fxml.FXML
    public void createAccount(ActionEvent actionEvent) {
        if (newUserInput.getText().isEmpty() || newPassInput.getText().isEmpty()){
            createAccountLabel.setText("Enter Username and password!");
            return;
        }
        if (!newPassInput.getText().equals(checkNewPass.getText())){
            createAccountLabel.setText("Password doesn't match!");
            return;
        }
        for (User u:userList) {
            if (u.getUserName().equals(newUserInput.getText())) {
                createAccountLabel.setText("This user already exists");
                return;
            }
        }

        User user = new User(
                newUserInput.getText(),
                newPassInput.getText()
        );
        userList.add(user);
        userInput.getItems().add(newUserInput.getText());
        createAccountLabel.setText("New account created successfully!");
    }

    @javafx.fxml.FXML
    public void login(ActionEvent actionEvent) throws IOException {
        if ((userInput.getValue() == null) || (passInput.getText().isEmpty())){
            loginLabel.setText("Choose user and enter password!");
            return;
        }

        for (User u : userList) {
            if ((u.getUserName().equals(userInput.getValue())) && (u.getPassword().equals(passInput.getText()))){
                AnchorPane root = FXMLLoader.load(HelloApplication.class.getResource("UserDashboard.fxml"));
                Scene scene = new Scene(root);
                HelloApplication.stage.setTitle("User Dashboard");
                HelloApplication.stage.setScene(scene);
                return;
            }
        }

        if ((userInput.getValue().equals("Author")) && (passInput.getText().equals("2310717"))){
            AnchorPane root = FXMLLoader.load(HelloApplication.class.getResource("Sami/AuthorDashboard.fxml"));
            Scene scene = new Scene(root);
            HelloApplication.stage.setTitle("Author Dashboard");
            HelloApplication.stage.setScene(scene);

        } else if ((userInput.getValue().equals("Publisher")) && (passInput.getText().equals("2310717"))){
            AnchorPane root = FXMLLoader.load(HelloApplication.class.getResource("Sami/PublisherDashboard.fxml"));
            Scene scene = new Scene(root);
            HelloApplication.stage.setTitle("Publisher Dashboard");
            HelloApplication.stage.setScene(scene);

        } else if ((userInput.getValue().equals("Stall Owner")) && (passInput.getText().equals("12345"))){
            AnchorPane root = FXMLLoader.load(HelloApplication.class.getResource("AbdullahMohammadSadman/StallOwnerDashboard.fxml"));
            Scene scene = new Scene(root);
            HelloApplication.stage.setTitle("Stall Owner Dashboard");
            HelloApplication.stage.setScene(scene);

        } else if ((userInput.getValue().equals("Maintenance Officer")) && (passInput.getText().equals("2330322"))){
            AnchorPane root = FXMLLoader.load(HelloApplication.class.getResource("AbdullahMohammadSadman/MaintenanceOfficerDashboard.fxml"));
            Scene scene = new Scene(root);
            HelloApplication.stage.setTitle("Maintenance Officer Dashboard");
            HelloApplication.stage.setScene(scene);

        } else if ((userInput.getValue().equals("Security Officer")) && (passInput.getText().equals("2211312"))){
            AnchorPane root = FXMLLoader.load(HelloApplication.class.getResource("Reya/securityOfficerDashboard.fxml"));
            Scene scene = new Scene(root);
            HelloApplication.stage.setTitle("Security Officer Dashboard");
            HelloApplication.stage.setScene(scene);

        } else if ((userInput.getValue().equals("Help Desk Officer")) && (passInput.getText().equals("2211312"))){
            AnchorPane root = FXMLLoader.load(HelloApplication.class.getResource("Reya/HelpDeskOfficerDasboard.fxml"));
            Scene scene = new Scene(root);
            HelloApplication.stage.setTitle("Help Desk Officer Dashboard");
            HelloApplication.stage.setScene(scene);
        }
        else if ((userInput.getValue().equals("Visitor")) && (passInput.getText().equals("12345"))){
            AnchorPane root = FXMLLoader.load(HelloApplication.class.getResource("Fahim/visitor.fxml"));
            Scene scene = new Scene(root);
            HelloApplication.stage.setScene(scene);
        }
        else if ((userInput.getValue().equals("Event Manager")) && (passInput.getText().equals("12345"))){
            AnchorPane root = FXMLLoader.load(HelloApplication.class.getResource("Fahim/EventManager.fxml"));
            Scene scene = new Scene(root);
            HelloApplication.stage.setScene(scene);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText("Invalid credentials");
            alert.setContentText("Please check your ID and password.");
            alert.showAndWait();
        }

    }

    @javafx.fxml.FXML
    public void forgotPass(ActionEvent actionEvent) {
        loginLabel.setText("Try to remember your password!");
    }

    @javafx.fxml.FXML
    public void showPassword(ActionEvent actionEvent) {
        if (showPass.isSelected()){
            if (passInput.getText().isEmpty()){
                loginLabel.setText("Enter Password!");
                return;
            }
            loginLabel.setText(passInput.getText());
        } else {
            loginLabel.setText("");
        }
    }
}