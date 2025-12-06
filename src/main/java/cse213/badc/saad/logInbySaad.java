package cse213.badc.saad;

import cse213.badc.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class logInbySaad
{
    @javafx.fxml.FXML
    private TextField passTF;
    @javafx.fxml.FXML
    private TextField idTF;
    @javafx.fxml.FXML
    private ComboBox<String> userType;

    @javafx.fxml.FXML
    public void initialize() {

        userType.getItems().addAll("Supplier", "BADC");

    }

    @javafx.fxml.FXML
    public void logOA(ActionEvent actionEvent) throws IOException {

        String id = idTF.getText();
        String password = passTF.getText();

        if (userType.getValue().equals("Supplier")){
            ArrayList<Supplier> supList = new ArrayList<>();
            Helper.loadFrom("allSuppliers.bin", supList);
            for (Supplier s : supList){
                if (s.getSupplierID().equals(id) && (s.getPassword()).equals(password)){
                    FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("saad/U3IESDashboardView.fxml"));

                    Scene scene = new Scene(loader.load());

                    U3IESDashboardController controller = loader.getController();
                    controller.passIESDashboard(s);

                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

                    stage.setTitle("IES Dashboard");
                    stage.setScene(scene);
                    stage.show();
                } else {
                    Helper.showAlert("No Supplier user found");
                }
            }

        } else if (userType.getValue().equals("BADC")){
            ArrayList<FieldOfficer> foList = new ArrayList<>();
            Helper.loadFrom("allFieldOfficers.bin", foList);
            for (FieldOfficer fo : foList){
                if (fo.getOfficerId().equals(id) && (fo.getPassword()).equals(password)){
                    FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("saad/U4BFODashboardView.fxml"));

                    Scene scene = new Scene(loader.load());

                    U4BFODashboardController controller = loader.getController();
                    controller.passBFODashboard(fo);

                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

                    stage.setTitle("BFO Dashboard");
                    stage.setScene(scene);
                    stage.show();
                } else {
                    Helper.showAlert("No Officer user found");
                }
            }
        }


    }
}