package cse213.badc.saad;

import cse213.badc.BADCApplication;
import cse213.badc.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class U3ContractorRegistrationController
{
    @javafx.fxml.FXML
    private TextField licenseTF;
    @javafx.fxml.FXML
    private TextField comNameTF;
    @javafx.fxml.FXML
    private ComboBox<String> serviceAreaCB;

    @javafx.fxml.FXML
    public void initialize() {
        serviceAreaCB.getItems().addAll("Dhaka", "Chattogram", "Rajshahi", "Khulna", "Sylhet", "Barishal", "Rangpur", "Mymensingh");
    }

    Supplier currentUser;
    public void passRegInterface(Supplier s){
        currentUser = s;
    }

    @javafx.fxml.FXML
    public void cancelOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(BADCApplication.class.getResource("saad/U3IESDashboardView.fxml"));

        Scene scene = new Scene(loader.load());

        U3IESDashboardController controller = loader.getController();
        controller.passIESDashboard(currentUser);

        Stage stage = (Stage) ((Node) (actionEvent.getSource())).getScene().getWindow();

        stage.setScene(scene);
        stage.setTitle("IES Dashboard");

        stage.show();
    }

    @javafx.fxml.FXML
    public void submitOA(ActionEvent actionEvent) throws IOException {

        if (currentUser.isApplied()){
            Helper.showAlert("Already Submitted");
            return;
        }

        try {
            long id = Long.parseLong(licenseTF.getText());
        } catch (Exception e){
            Helper.showAlert(e.getMessage());

        }


        currentUser.setCompanyName(comNameTF.getText());
        currentUser.setServiceArea(serviceAreaCB.getValue());
        currentUser.setTradeLicenseNo(Long.parseLong(licenseTF.getText()));
        currentUser.setStatus("Pending");
        currentUser.setDateOfRegistration(LocalDate.now());
        currentUser.setApplied(true);

        Helper.writeInto("supplierApplicant.bin", currentUser);
        Helper.showAlert("Successfully Registered. Go back to dashboard");

        ArrayList<Supplier> allsaMainList = new ArrayList<>();
        Helper.loadFrom("allSuppliers.bin", allsaMainList);
        for (Supplier asMain : allsaMainList){
            if (asMain.getSupplierID().equals(currentUser.getSupplierID())){
                asMain.setCompanyName(comNameTF.getText());
                asMain.setServiceArea(serviceAreaCB.getValue());
                asMain.setTradeLicenseNo(Long.parseLong(licenseTF.getText()));
                asMain.setStatus("Pending");
                asMain.setDateOfRegistration(LocalDate.now());
                asMain.setApplied(true);

            }
        }

        Helper.deleteFile("allSuppliers.bin");
        for (Supplier asMain : allsaMainList){
            Helper.writeInto("allSuppliers.bin", asMain);
        }



    }
}