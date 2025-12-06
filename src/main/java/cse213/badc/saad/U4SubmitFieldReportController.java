package cse213.badc.saad;

import cse213.badc.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class U4SubmitFieldReportController
{
    @javafx.fxml.FXML
    private Label applicationLabel;
    @javafx.fxml.FXML
    private TextArea observationTA;
    @javafx.fxml.FXML
    private Label complaintLabel;
    @javafx.fxml.FXML
    private TextArea challengesTA;
    @javafx.fxml.FXML
    private Label appAplicationsLabel;
    @javafx.fxml.FXML
    private Label appReportsLabel;
    @javafx.fxml.FXML
    private Label reportLabel;
    @javafx.fxml.FXML
    private Label appComplaintsLabel;
    ArrayList<MaintenanceReport> mrList;
    ArrayList<Complaints> compList;
    ArrayList<FarmerApplication> faList;
    private int appApp, appCom, appRep;
    @javafx.fxml.FXML
    private TextField idTF;
    @javafx.fxml.FXML
    private TextArea summaryTA;

    @javafx.fxml.FXML
    public void initialize() throws IOException {
        Helper.loadFrom("allApplications.bin", faList);
        Helper.loadFrom("allComplaints.bin", compList);
        Helper.loadFrom("allMaintenanceReports.bin", mrList);
    }

    FieldOfficer currentUser;
    public void passRegionalReportInterface(FieldOfficer fo){
        currentUser = fo;
    }


    @javafx.fxml.FXML
    public void applicationOA(ActionEvent actionEvent) {
        applicationLabel.setText(Integer.toString(faList.size()));
    }

    @javafx.fxml.FXML
    public void submitOA(ActionEvent actionEvent) throws IOException {
        if (appApp == 0 | appCom == 0 | appRep == 0){
            Helper.showAlert("Please load first");
            return;
        }


        RegionalReport rr = new RegionalReport(
                idTF.getText(),
                currentUser.getOfficerId(),
                summaryTA.getText(),
                faList.size(),
                appApp,
                compList.size(),
                appCom,
                mrList.size(),
                appRep,
                observationTA.getText(),
                challengesTA.getText()
        );

        Helper.writeInto("allRegionalReports.bin", rr);


    }


    @javafx.fxml.FXML
    public void appReportsOA(ActionEvent actionEvent) {
        for (MaintenanceReport mr : mrList){
            if (mr.getStatus().equals("Approved")){
                appRep += 1;
            }
        }

        appAplicationsLabel.setText(Integer.toString(appRep));

    }

    @javafx.fxml.FXML
    public void appComplaintsOA(ActionEvent actionEvent) {
        for (Complaints c : compList){
            if (c.getStatus().equals("Approved")){
                appCom += 1;
            }
        }

        appComplaintsLabel.setText(Integer.toString(appCom));
    }

    @javafx.fxml.FXML
    public void backOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("saad/U4BFODashboardView.fxml"));
        Scene scene = new Scene(loader.load());
        U4BFODashboardController controller = loader.getController();
        controller.passBFODashboard(currentUser);
        Stage stage =   (Stage)  ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("BFO Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void appApplicationsOA(ActionEvent actionEvent) {
        for (FarmerApplication app : faList){
            if (app.getStatus().equals("Approved")){
                appApp += 1;
            }
        }

        appAplicationsLabel.setText(Integer.toString(appApp));
    }

    @javafx.fxml.FXML
    public void reportsOA(ActionEvent actionEvent) {
        reportLabel.setText(Integer.toString(mrList.size()));
    }

    @javafx.fxml.FXML
    public void complaintOA(ActionEvent actionEvent) {
        complaintLabel.setText(Integer.toString(compList.size()));
    }
}