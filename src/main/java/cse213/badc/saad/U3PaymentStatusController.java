package cse213.badc.saad;

import cse213.badc.Helper;
import cse213.badc.BADCApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class U3PaymentStatusController {
    @javafx.fxml.FXML
    private TableView<MaintenanceReport> earningTableView;
    @javafx.fxml.FXML
    private TableColumn<MaintenanceReport, Integer> earningCol;
    @javafx.fxml.FXML
    private TableColumn<MaintenanceReport, LocalDate> dateCol;
    @javafx.fxml.FXML
    private Label totalEarningLabel;
    @javafx.fxml.FXML
    private Label totalEarningLabel1;
    @javafx.fxml.FXML
    private TableColumn<MaintenanceReport, String> idCol;

    ArrayList<MaintenanceReport> mrLst = new ArrayList<>();
    int tearning = 0;


    Supplier currentUser;

    public void passPaymentInterface(Supplier s) {
        currentUser = s;
    }

    @javafx.fxml.FXML
    public void initialize() throws IOException {


        idCol.setCellValueFactory(new PropertyValueFactory<>("taskId"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("reportDate"));
        earningCol.setCellValueFactory(new PropertyValueFactory<>("cost"));




    }

    @javafx.fxml.FXML
    public void backOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(BADCApplication.class.getResource("saad/U3IESDashboardView.fxml"));
        Scene scene = new Scene(loader.load());

        U3IESDashboardController controller = loader.getController();
        controller.passIESDashboard(currentUser);

        Stage stage =   (Stage)  ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("IES Dashboard");
        stage.show();
    }

    @javafx.fxml.FXML
    public void loadOA(ActionEvent actionEvent) throws IOException {
        Helper.loadFrom("allMaintenanceReports.bin", mrLst);

        for (MaintenanceReport mr : mrLst) {
            if (mr.getAuthorId().equals(currentUser.getSupplierID())) {
                if (mr.getStatus().equals("Approved")) {
                    earningTableView.getItems().add(mr);
                    tearning = tearning + mr.getCost();
                }
            }

            totalEarningLabel.setText(Integer.toString(tearning));


        }
    }
}
