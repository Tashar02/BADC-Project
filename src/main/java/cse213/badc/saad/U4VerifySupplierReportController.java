package cse213.badc.saad;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class U4VerifySupplierReportController
{
    @javafx.fxml.FXML
    private TableColumn<MaintenanceReport, String> taskCol;
    @javafx.fxml.FXML
    private TableColumn<MaintenanceReport, LocalDate> dateCol;
    @javafx.fxml.FXML
    private TableView<MaintenanceReport> reportTableView;
    @javafx.fxml.FXML
    private ComboBox<String> statusComboBox;
    @javafx.fxml.FXML
    private TextArea remarksTA;
    @javafx.fxml.FXML
    private Label detailsLabel;
    MaintenanceReport report;

    @javafx.fxml.FXML
    public void initialize() throws IOException {
        statusComboBox.getItems().addAll("Approved", "Rejected");

        taskCol.setCellValueFactory(new PropertyValueFactory<>("taskId"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("reportDate"));

        ArrayList<MaintenanceReport> mrLst = new ArrayList<>();
        Helper.loadFrom("allMaintenanceReports.bin", mrLst);
        for (MaintenanceReport mr : mrLst){
            if (mr.getStatus().equals("Pending")){
                reportTableView.getItems().add(mr);
            }
        }

    }

    FieldOfficer currentUser;
    public void passVerifySupplierReportInterface(FieldOfficer fo){
        currentUser = fo;
    }


    @javafx.fxml.FXML
    public void loadDetailsOA(ActionEvent actionEvent) {

        report = reportTableView.getSelectionModel().getSelectedItem();
        detailsLabel.setText(report.toString());

    }

    @javafx.fxml.FXML
    public void goBackOA(ActionEvent actionEvent) throws IOException {
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
    public void verifyOA(ActionEvent actionEvent) {
        report.setStatus(statusComboBox.getValue());
        report.setVerificationRemark(remarksTA.getText());
        reportTableView.getItems().remove(report);
    }
}