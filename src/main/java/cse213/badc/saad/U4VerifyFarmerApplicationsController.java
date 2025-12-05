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

public class U4VerifyFarmerApplicationsController
{
    @javafx.fxml.FXML
    private TableColumn<FarmerApplication, LocalDate> dateCol;
    @javafx.fxml.FXML
    private TableColumn<FarmerApplication, String> idCol;
    @javafx.fxml.FXML
    private ComboBox<String> verificationCB;
    @javafx.fxml.FXML
    private Label detailsLabel;
    @javafx.fxml.FXML
    private TableView<FarmerApplication> tableView;
    @javafx.fxml.FXML
    private TextArea remarkTA;
    FarmerApplication f;

    @javafx.fxml.FXML
    public void initialize() throws IOException {
        verificationCB.getItems().addAll("Approved", "Pending");

        idCol.setCellValueFactory(new PropertyValueFactory<>("applicationId"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("applicationDate"));

        ArrayList<FarmerApplication> faList = new ArrayList<>();
        Helper.loadFrom("allApplications.bin", faList);
        for (FarmerApplication fa : faList){
            if (fa.getStatus().equals("Pending")){
                tableView.getItems().add(fa);
            }
        }
    }

    FieldOfficer currentUser;
    public void passVerifyFarmerApplicationInterface(FieldOfficer fo){
        currentUser = fo;
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
    public void verifyOA(ActionEvent actionEvent) {

        if (f == null){
            Helper.showAlert("Load first");
            return;
        }

        f.setStatus(verificationCB.getValue());
        f.setApplicationRemark(remarkTA.getText());
        tableView.getItems().remove(f);




    }

    @javafx.fxml.FXML
    public void loadOA(ActionEvent actionEvent) {
        f = tableView.getSelectionModel().getSelectedItem();
        detailsLabel.setText(f.toString());
    }
}