package com.example.mainproject.saad;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class U3ContractorRegistrationController
{
    @javafx.fxml.FXML
    private TextField licenseTF;
    @javafx.fxml.FXML
    private TextField areaTF;
    @javafx.fxml.FXML
    private TextField comNameTF;

    @javafx.fxml.FXML
    public void initialize() {
    }

    Supplier currentUser;
    public void passRegInterface(Supplier s){
        currentUser = s;
    }

    @javafx.fxml.FXML
    public void cancelOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("U3IESDashboardView.fxml"));

        Scene scene = new Scene(loader.load());

        U3IESDashboardController controller = loader.getController();
        controller.passIESDashboard(currentUser);

        Stage stage = (Stage) ((Node) (actionEvent.getSource())).getScene().getWindow();

        stage.setScene(scene);
        stage.setTitle("IES Dashboard");

        stage.show();
    }

    @javafx.fxml.FXML
    public void submitOA(ActionEvent actionEvent) throws IOException {

        if (currentUser.isApplied()){
            Helper.showAlert("Already Submitted");
            return;
        }

        currentUser.setCompanyName(comNameTF.getText());
        currentUser.setServiceArea(areaTF.getText());
        currentUser.setTradeLicenseNo(Long.parseLong(licenseTF.getText()));
        currentUser.setStatus("Pending");
        currentUser.setDateOfRegistration(LocalDate.now());
        currentUser.setApplied(true);

        Helper.writeInto("supplierApplicant.bin", currentUser);
        Helper.showAlert("Successfully Registered. Go back to dashboard");



    }
}