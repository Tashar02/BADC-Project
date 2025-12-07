package cse213.badc.saad;

import cse213.badc.BADCApplication;
import cse213.badc.Helper;
import cse213.badc.rhythm.U8G3_ViewTendersController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class U3IESDashboardController {
    @FXML
    private BorderPane rootPane;

    // Current logged-in user
    private Supplier currentUser;

    @FXML
    public void initialize() {
        // Any initialization logic if needed
    }

    // Pass the Supplier object to this dashboard
    public void passIESDashboard(Supplier s) {
        currentUser = s;
    }

    @FXML
    public void submitReportOA(ActionEvent actionEvent) throws IOException {
        if (!currentUser.isVerified()) {
            Helper.showAlert("You don't have any task to report");
            return;
        }
        FXMLLoader loader = new FXMLLoader(
                BADCApplication.class.getResource("saad/U3SubmitMaintenanceReportView.fxml")
        );
        Scene scene = new Scene(loader.load());
        U3SubmitMaintenanceReportController controller = loader.getController();
        controller.passMainReportInterface(currentUser);

        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Maintenance Report");
        stage.show();
    }

    @FXML
    public void checkPaymentOA(ActionEvent actionEvent) throws IOException {
        if (!currentUser.isVerified()) {
            Helper.showAlert("You are not allowed for this section");
            return;
        }
        FXMLLoader loader = new FXMLLoader(
                BADCApplication.class.getResource("saad/U3PaymentStatusView.fxml")
        );
        Scene scene = new Scene(loader.load());
        U3PaymentStatusController controller = loader.getController();
        controller.passPaymentInterface(currentUser);

        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Earning Status");
        stage.show();
    }

    @FXML
    public void viewStatusOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                BADCApplication.class.getResource("saad/U3ViewVerificationStatusView.fxml")
        );
        Scene scene = new Scene(loader.load());
        U3ViewVerificationStatusController controller = loader.getController();
        controller.passStatusInterface(currentUser);

        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Status");
        stage.show();
    }

    @FXML
    public void viewInstallationOrdersOA(ActionEvent actionEvent) throws IOException {
        if (!currentUser.isVerified()) {
            Helper.showAlert("You are not allowed for this section");
            return;
        }
        FXMLLoader loader = new FXMLLoader(
                BADCApplication.class.getResource("saad/U3ViewAssignedInstallationOrdersView.fxml")
        );
        Scene scene = new Scene(loader.load());
        U3ViewAssignedInstallationOrdersController controller = loader.getController();
        controller.passAssignedOrderInterface(currentUser);

        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Assigned Tasks");
        stage.show();
    }

    @FXML
    public void logOutOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                BADCApplication.class.getResource("LoginView.fxml")
        );
        Stage stage = (Stage) rootPane.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Log In");
        stage.show();
    }

    @FXML
    public void uploadPumpCatalogOA(ActionEvent actionEvent) throws IOException {
        if (!currentUser.isVerified()) {
            Helper.showAlert("You are not allowed for this section");
            return;
        }
        FXMLLoader loader = new FXMLLoader(
                BADCApplication.class.getResource("saad/U3UploadPumpCatalogView.fxml")
        );
        Scene scene = new Scene(loader.load());
        U3UploadPumpCatalogController controller = loader.getController();
        controller.passPumpInterface(currentUser);

        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Pump Catalog Upload");
        stage.show();
    }

    @FXML
    public void viewTendersOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                BADCApplication.class.getResource("saad/U3ViewTendersView.fxml")
        );
        Scene scene = new Scene(loader.load());

        U3ViewTendersController controller = loader.getController();
        controller.passTenderInterface(currentUser);

        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Tenders");
        stage.show();
    }

    @FXML
    public void registerOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                BADCApplication.class.getResource("saad/U3ContractorRegistrationView.fxml")
        );
        Scene scene = new Scene(loader.load());
        U3ContractorRegistrationController controller = loader.getController();
        controller.passRegInterface(currentUser);

        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Register");
        stage.show();
    }

    @FXML
    public void noticeOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                BADCApplication.class.getResource("saad/U3ViewNotice.fxml")
        );
        Scene scene = new Scene(loader.load());
        U3ViewNoticeController controller = loader.getController();
        controller.passViewNoticeInterface(currentUser);

        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("View Notice");
        stage.show();
    }
}
