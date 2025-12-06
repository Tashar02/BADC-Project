package cse213.badc.rhythm;

import cse213.badc.Helper;
import cse213.badc.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;

public class U8_DevelopmentPartnerDashboardController {
    @javafx.fxml.FXML
    public void goToGoal1(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("U8G1_ViewFieldReportsView.fxml"));
        Scene scene = new Scene(loader.load());
        Helper.setScene(actionEvent, scene);
    }

    @javafx.fxml.FXML
    public void goToGoal2(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("U8G2_ViewEquipmentPerformanceView.fxml"));
        Scene scene = new Scene(loader.load());
        Helper.setScene(actionEvent, scene);
    }

    @javafx.fxml.FXML
    public void goToGoal3(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("U8G3_ViewTendersView.fxml"));
        Scene scene = new Scene(loader.load());
        Helper.setScene(actionEvent, scene);
    }

    @javafx.fxml.FXML
    public void goToGoal4(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("U8G4_UploadProposalView.fxml"));
        Scene scene = new Scene(loader.load());
        Helper.setScene(actionEvent, scene);
    }

    @javafx.fxml.FXML
    public void goToGoal5(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("U8G5_ViewApplicationApprovalAnalyticsView.fxml"));
        Scene scene = new Scene(loader.load());
        Helper.setScene(actionEvent, scene);
    }

    @javafx.fxml.FXML
    public void goToGoal6(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("U8G6_ViewFertilizerDataView.fxml"));
        Scene scene = new Scene(loader.load());
        Helper.setScene(actionEvent, scene);
    }

    @javafx.fxml.FXML
    public void goToGoal7(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("U8G7_ViewPoliciesView.fxml"));
        Scene scene = new Scene(loader.load());
        Helper.setScene(actionEvent, scene);
    }

    @javafx.fxml.FXML
    public void goToGoal8(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("U8G8_ViewSeedPerformanceView.fxml"));
        Scene scene = new Scene(loader.load());
        Helper.setScene(actionEvent, scene);
    }

    @javafx.fxml.FXML
    public void logOut(ActionEvent actionEvent) throws IOException {
        User.clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
        Scene scene = new Scene(loader.load());
        Helper.setScene(actionEvent, scene);
    }
}
