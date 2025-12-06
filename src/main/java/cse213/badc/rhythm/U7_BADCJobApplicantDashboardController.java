package cse213.badc.rhythm;

import cse213.badc.BADCApplication;
import cse213.badc.Helper;
import cse213.badc.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;

public class U7_BADCJobApplicantDashboardController {
    @javafx.fxml.FXML
    public void goToGoal1(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("U7G1_JobCircularBrowseView.fxml"));
        Scene scene = new Scene(loader.load());
        Helper.setScene(actionEvent, scene);
    }

    @javafx.fxml.FXML
    public void goToGoal2(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("U7G2_TrackApplicationStatusView.fxml"));
        Scene scene = new Scene(loader.load());
        Helper.setScene(actionEvent, scene);
    }

    @javafx.fxml.FXML
    public void goToGoal3(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("U7G3_ReviewEligibilityView.fxml"));
        Scene scene = new Scene(loader.load());
        Helper.setScene(actionEvent, scene);
    }

    @javafx.fxml.FXML
    public void goToGoal4(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("U7G4_HelpdeskView.fxml"));
        Scene scene = new Scene(loader.load());
        Helper.setScene(actionEvent, scene);
    }

    @javafx.fxml.FXML
    public void goToGoal5(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("U7G5_WithdrawApplicationView.fxml"));
        Scene scene = new Scene(loader.load());
        Helper.setScene(actionEvent, scene);
    }

    @javafx.fxml.FXML
    public void goToGoal6(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("U7G6_NotificationPreferencesView.fxml"));
        Scene scene = new Scene(loader.load());
        Helper.setScene(actionEvent, scene);
    }

    @javafx.fxml.FXML
    public void goToGoal7(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("U7G7_RecruitmentQueriesView.fxml"));
        Scene scene = new Scene(loader.load());
        Helper.setScene(actionEvent, scene);
    }

    @javafx.fxml.FXML
    public void goToGoal8(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("U7G8_AdmitCardListView.fxml"));
        Scene scene = new Scene(loader.load());
        Helper.setScene(actionEvent, scene);
    }

    @javafx.fxml.FXML
    public void logOut(ActionEvent actionEvent) throws IOException {
        User.clear();
        FXMLLoader loader = new FXMLLoader(BADCApplication.class.getResource("LoginView.fxml"));
        Scene scene = new Scene(loader.load());
        Helper.setScene(actionEvent, scene);
    }
}
