package cse213.badc.saad;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class U3SupplierSignUpViewController
{
    @javafx.fxml.FXML
    private TextField nameTF;
    @javafx.fxml.FXML
    private TextField emailTF;
    @javafx.fxml.FXML
    private TextField idTF;
    @javafx.fxml.FXML
    private TextField mobileTF;
    Supplier s;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void dashboardOA(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("saad/U3IESDashboardView.fxml"));

        Scene scene = new Scene(loader.load());

        U3IESDashboardController controller = loader.getController();
        controller.passIESDashboard(s);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        stage.setTitle("IES Dashboard");
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void createOA(ActionEvent actionEvent) throws IOException {

        // String supplierID, String name, String mobileNo, String email

        s = new Supplier(idTF.getText(), nameTF.getText(), mobileTF.getText(), emailTF.getText());
        Helper.writeInto("allSuppliers.bin", s);

    }
}