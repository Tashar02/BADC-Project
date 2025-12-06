package cse213.badc.saad;

import cse213.badc.BADCApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class U4BFODashboardController {

    @FXML
    private BorderPane rootPane;

    @javafx.fxml.FXML
    public void initialize() {
    }

    FieldOfficer currentUser;
    public void passBFODashboard(FieldOfficer fo){
        currentUser = fo;
    }



    @javafx.fxml.FXML
    public void supplierAssignmentOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(BADCApplication.class.getResource("saad/U4AssignSupplierView.fxml"));
        Scene scene = new Scene(loader.load());
        U4AssignSupplierController controller = loader.getController();
        controller.passAssignInterface(currentUser);
        Stage stage =  (Stage) rootPane.getScene().getWindow();
        stage.setTitle("Assign Supplier");
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void supplierReportOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(BADCApplication.class.getResource("saad/U4VerifySupplierReportView.fxml"));
        Scene scene = new Scene(loader.load());
        U4VerifySupplierReportController controller = loader.getController();
        controller.passVerifySupplierReportInterface(currentUser);
        Stage stage =  (Stage) rootPane.getScene().getWindow();
        stage.setTitle("Verify Supplier report");
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void complaintsOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(BADCApplication.class.getResource("saad/U4VerifyComplaintsView.fxml"));
        Scene scene = new Scene(loader.load());
        U4VerifyComplaintsController controller = loader.getController();
        controller.passVerifyComplaintsInterface(currentUser);
        Stage stage =  (Stage) rootPane.getScene().getWindow();
        stage.setTitle("Complaints");
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void logOutOA(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(BADCApplication.class.getResource("saad/logInbySaad.fxml"));

        Stage stage = (Stage) rootPane.getScene().getWindow();

        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Log IN");

        stage.show();

    }

//    @javafx.fxml.FXML
//    public void publishTrainingOA(ActionEvent actionEvent) throws IOException {
//        FXMLLoader loader = new FXMLLoader(BADCApplication.class.getResource("saad/U4VPublishTrainingSessionsView.fxml"));
//        Scene scene = new Scene(loader.load());
//        U4NoticeCreationController controller = loader.getController();
//        controller.passNoticeInterface(currentUser);
//        Stage stage =  (Stage)   ((Node) actionEvent.getSource()).getScene().getWindow();
//        stage.setTitle("Notice");
//        stage.setScene(scene);
//        stage.show();
//    }



    @javafx.fxml.FXML
    public void farmerApplicationOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(BADCApplication.class.getResource("saad/U4VerifyFarmerApplicationsView.fxml"));
        Scene scene = new Scene(loader.load());
        U4VerifyFarmerApplicationsController controller = loader.getController();
        controller.passVerifyFarmerApplicationInterface(currentUser);
        Stage stage =  (Stage) rootPane.getScene().getWindow();
        stage.setTitle("Verify Farmer Applications");
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void contractorRegistrationOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(BADCApplication.class.getResource("saad/U4VerifyContractorRegistrationView.fxml"));
        Scene scene = new Scene(loader.load());
        U4VerifyContractorRegistrationController controller = loader.getController();
        controller.passVerifyConRegistrationInterface(currentUser);
        Stage stage =  (Stage) rootPane.getScene().getWindow();
        stage.setTitle("Verify Contractor Registration");
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void submitRegionalReportOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(BADCApplication.class.getResource("saad/U4SubmitFieldReportView.fxml"));
        Scene scene = new Scene(loader.load());
        U4SubmitFieldReportController controller = loader.getController();
        controller.passRegionalReportInterface(currentUser);
        Stage stage =  (Stage) rootPane.getScene().getWindow();
        stage.setTitle("Regional Report");
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void pumpRuntimeOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(BADCApplication.class.getResource("saad/U4PumpRuntimeAnalysisView.fxml"));
        Scene scene = new Scene(loader.load());
        U4PumpRuntimeAnalysisController controller = loader.getController();
        controller.passPumpRuntimeInterface(currentUser);
        Stage stage =  (Stage) rootPane.getScene().getWindow();
        stage.setTitle("Pump Runtime Chart");
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void noticeOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(BADCApplication.class.getResource("saad/U4NoticeCreationView.fxml"));
        Scene scene = new Scene(loader.load());
        U4NoticeCreationController controller = loader.getController();
        controller.passNoticeInterface(currentUser);
        Stage stage =  (Stage) rootPane.getScene().getWindow();
        stage.setTitle("Notice");
        stage.setScene(scene);
        stage.show();
    }
}