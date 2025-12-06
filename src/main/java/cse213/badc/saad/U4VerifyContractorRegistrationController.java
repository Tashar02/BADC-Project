package cse213.badc.saad;

import cse213.badc.Helper;
import cse213.badc.BADCApplication;
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

public class U4VerifyContractorRegistrationController
{
    @javafx.fxml.FXML
    private TableColumn<Supplier, String> idCol;
    @javafx.fxml.FXML
    private Label detailsLabel;
    @javafx.fxml.FXML
    private TableView<Supplier> applicationsTableView;
    @javafx.fxml.FXML
    private TextArea remarksTF;
    @javafx.fxml.FXML
    private TableColumn<Supplier, String> statusCol;
    @javafx.fxml.FXML
    private TableColumn<Supplier, LocalDate> reportDateCol;


    @javafx.fxml.FXML
    public void initialize() throws IOException {
        idCol.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        reportDateCol.setCellValueFactory(new PropertyValueFactory<>("dateOfRegistration"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        ArrayList<Supplier> saMainList = new ArrayList<>();
        Helper.loadFrom("supplierApplicant.bin", saMainList);
    }

    FieldOfficer currentUser;
    public void passVerifyConRegistrationInterface(FieldOfficer fo){
        currentUser = fo;
    }



    @javafx.fxml.FXML
    public void detailsOA(ActionEvent actionEvent) {
        Supplier s = applicationsTableView.getSelectionModel().getSelectedItem();
        detailsLabel.setText(s.toString());

    }

    @javafx.fxml.FXML
    public void cancelOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(BADCApplication.class.getResource("saad/U4BFODashboardView.fxml"));
        Scene scene = new Scene(loader.load());
        U4BFODashboardController controller = loader.getController();
        controller.passBFODashboard(currentUser);
        Stage stage =   (Stage)  ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("BFO Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void loadAllOA(ActionEvent actionEvent) throws IOException {
        applicationsTableView.getItems().clear();
        ArrayList<Supplier> saLst = new ArrayList<>();
        Helper.loadFrom("supplierApplicant.bin", saLst);
        applicationsTableView.getItems().addAll(saLst);
        Helper.showAlert("loaded All");
    }

    @javafx.fxml.FXML
    public void approveOA(ActionEvent actionEvent) throws IOException {
        Supplier s = applicationsTableView.getSelectionModel().getSelectedItem();
        ArrayList<Supplier> saMainList = new ArrayList<>();
        Helper.loadFrom("supplierApplicant.bin", saMainList);
        for (Supplier sMain : saMainList){
            if (sMain.getSupplierID().equals(s.getSupplierID())){
                sMain.setVerificationRemark(remarksTF.getText());
                sMain.setStatus("Approved");
                sMain.setVerified(true);
            }
        }

        Helper.deleteFile("supplierApplicant.bin");
        for (Supplier sMain : saMainList){
            Helper.writeInto("supplierApplicant.bin", sMain);
        }

        ArrayList<Supplier> allsaMainList = new ArrayList<>();
        Helper.loadFrom("allSuppliers.bin", allsaMainList);
        for (Supplier asMain : allsaMainList){
            if (asMain.getSupplierID().equals(s.getSupplierID())){
                asMain.setVerificationRemark(remarksTF.getText());
                asMain.setStatus("Approved");
                asMain.setVerified(true);
            }
        }

        Helper.deleteFile("allSuppliers.bin");
        for (Supplier asMain : allsaMainList){
            Helper.writeInto("allSuppliers.bin", asMain);
        }



    }

    @javafx.fxml.FXML
    public void rejectOA(ActionEvent actionEvent) throws IOException {

        Supplier s = applicationsTableView.getSelectionModel().getSelectedItem();
//        s.setVerificationRemark(remarksTF.getText());
//        s.setStatus("Rejected");
//        s.setVerified(true);

        ArrayList<Supplier> saMainList = new ArrayList<>();
        Helper.loadFrom("supplierApplicant.bin", saMainList);
        for (Supplier sMain : saMainList){
            if (sMain.getSupplierID().equals(s.getSupplierID())){
                sMain.setVerificationRemark(remarksTF.getText());
                sMain.setStatus("Rejected");
                sMain.setVerified(true);
            }
        }

        Helper.deleteFile("supplierApplicant.bin");
        for (Supplier sMain : saMainList){
            Helper.writeInto("supplierApplicant.bin", sMain);
            }



        ArrayList<Supplier> allsaMainList = new ArrayList<>();
        Helper.loadFrom("allSuppliers.bin", allsaMainList);
        for (Supplier asMain : saMainList){
            if (asMain.getSupplierID().equals(s.getSupplierID())){
                asMain.setVerificationRemark(remarksTF.getText());
                asMain.setStatus("Rejected");
                asMain.setVerified(true);
            }
        }

        Helper.deleteFile("allSuppliers.bin");
        for (Supplier asMain : allsaMainList){
            Helper.writeInto("allSuppliers.bin", asMain);
        }

    }

    @javafx.fxml.FXML
    public void loadPendingOA(ActionEvent actionEvent) throws IOException {
        applicationsTableView.getItems().clear();
        ArrayList<Supplier> saLst = new ArrayList<>();
        Helper.loadFrom("supplierApplicant.bin", saLst);
        for (Supplier s : saLst) {
            if (s.getStatus().equals("Pending")) {
                applicationsTableView.getItems().add(s);
            }
        }
        Helper.showAlert("Loaded Pendings");
    }
}