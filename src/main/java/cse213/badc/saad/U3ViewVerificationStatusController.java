package cse213.badc.saad;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class U3ViewVerificationStatusController
{
    @javafx.fxml.FXML
    private ComboBox<String> filterStatusComboBox;
    @javafx.fxml.FXML
    private TableColumn<MaintenanceReport, LocalDate> dateCol;
    @javafx.fxml.FXML
    private TableColumn<MaintenanceReport, String> statusCol;
    @javafx.fxml.FXML
    private TableView<MaintenanceReport> statusTableView;
    @javafx.fxml.FXML
    private Label registrationStatusLabel;
    @javafx.fxml.FXML
    private Label registrationRemarkLabel;
    @javafx.fxml.FXML
    private TableColumn<MaintenanceReport, String> idCol;

    @javafx.fxml.FXML
    public void initialize() {
        filterStatusComboBox.getItems().addAll("Pending", "Rejected", "Approved");

        idCol.setCellValueFactory(new PropertyValueFactory<>("reportId"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("reportDate"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        registrationStatusLabel.setText(currentUser.getStatus());
        registrationRemarkLabel.setText(currentUser.getVerificationRemark());


    }

    Supplier currentUser;
    public void passStatusInterface(Supplier s){
        currentUser = s;
    }

    @javafx.fxml.FXML
    public void showStatusOA(ActionEvent actionEvent) throws IOException {

        statusTableView.getItems().clear();
        ArrayList<MaintenanceReport> mrLst = new ArrayList<>();
        Helper.loadFrom("allMaintenanceReports.bin", mrLst);
        for (MaintenanceReport mr : mrLst){
            if ((mr.getAuthorId().equals(currentUser.getSupplierID())) && (filterStatusComboBox.getValue().equals(mr.getStatus()))){
                statusTableView.getItems().add(mr);
            }
        }
    }

    @javafx.fxml.FXML
    public void cancelOA(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("saad/U3IESDashboardView.fxml"));
        Scene scene = new Scene(loader.load());

        U3IESDashboardController controller = loader.getController();
        controller.passIESDashboard(currentUser);

        Stage stage =   (Stage) ((Node)  actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.setTitle("IES Dashboard");
        stage.show();

    }
}