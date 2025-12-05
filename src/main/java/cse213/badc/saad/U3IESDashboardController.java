package com.example.mainproject.saad;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class U3IESDashboardController
{

    @javafx.fxml.FXML
    public void initialize() {
    }

    Supplier currentUser;
    public void passIESDashboard(Supplier s){
        currentUser = s;
    }

    @javafx.fxml.FXML
    public void submitReportOA(ActionEvent actionEvent) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("saad/U3SubmitMaintenanceReportView.fxml"));

         Scene scene = new Scene(loader.load());

         U3SubmitMaintenanceReportController controller = loader.getController();
         controller.passMainReportInterface(currentUser);

         Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
         stage.setScene(scene);
         stage.setTitle("Maintenance Report");
         stage.show();

    }

    @javafx.fxml.FXML
    public void checkPaymentOA(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("saad/U3PaymentStatusView.fxml"));

        Scene scene = new Scene(loader.load());

        U3PaymentStatusController controller = loader.getController();
        controller.passPaymentInterface(currentUser);

        Stage stage = (Stage) ((Node) (actionEvent.getSource())).getScene().getWindow();

        stage.setScene(scene);
        stage.setTitle("Earning Status");

        stage.show();
    }

    @javafx.fxml.FXML
    public void viewStatusOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("saad/U3ViewVerificationStatusView.fxml"));

        Scene scene = new Scene(loader.load());

        U3ViewVerificationStatusController controller = loader.getController();
        controller.passStatusInterface(currentUser);

        Stage stage = (Stage) ((Node) (actionEvent.getSource())).getScene().getWindow();

        stage.setScene(scene);
        stage.setTitle("Status");

        stage.show();

    }

    @javafx.fxml.FXML
    public void viewInstallationOrdersOA(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("saad/U3ViewAssignedInstallationOrdersView.fxml"));

        Scene scene = new Scene(loader.load());

        U3ViewAssignedInstallationOrdersController controller = loader.getController();
        controller.passAssignedOrderInterface(currentUser);

        Stage stage = (Stage) ((Node) (actionEvent.getSource())).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("assigned Tasks");
        stage.show();

    }

    @javafx.fxml.FXML
    public void logOutOA(ActionEvent actionEvent) {
//        Helper.changeScene(actionEvent, "logIn.fxml", "Log in page");
    }

    @javafx.fxml.FXML
    public void uploadPumpCatalogOA(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("saad/U3UploadPumpCatalogView.fxml"));

        Scene scene = new Scene(loader.load());

        U3UploadPumpCatalogController controller = loader.getController();
        controller.passPumpInterface(currentUser);

        Stage stage = (Stage) ((Node) (actionEvent.getSource())).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Pump Catalog Upload");
        stage.show();

    }

    @javafx.fxml.FXML
    public void viewTendersOA(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("saad/U3ViewTendersView.fxml"));

        Scene scene = new Scene(loader.load());

        U3ViewTendersController controller = loader.getController();
        controller.passTenderInterface(currentUser);

        Stage stage = (Stage) ((Node) (actionEvent.getSource())).getScene().getWindow();

        stage.setScene(scene);
        stage.setTitle("Tenders");

        stage.show();


    }

    @javafx.fxml.FXML
    public void registerOA(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("saad/U3ContractorRegistrationView.fxml"));

        Scene scene = new Scene(loader.load());

        U3ContractorRegistrationController controller = loader.getController();
        controller.passRegInterface(currentUser);

        Stage stage = (Stage) ((Node) (actionEvent.getSource())).getScene().getWindow();

        stage.setScene(scene);
        stage.setTitle("Register");

        stage.show();

    }

    @javafx.fxml.FXML
    public void trainingSessionsOA(ActionEvent actionEvent) {
    }
}