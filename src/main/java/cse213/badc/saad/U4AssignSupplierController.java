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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class U4AssignSupplierController
{
    @javafx.fxml.FXML
    private TableView<Supplier> supplierTableView;
    @javafx.fxml.FXML
    private TextField taskIdTF;
    @javafx.fxml.FXML
    private TextArea detailsTA;
    @javafx.fxml.FXML
    private TableView<FarmerApplication> farmerTableView;
    @javafx.fxml.FXML
    private TableColumn<Supplier, String> supplierIdCol;
    @javafx.fxml.FXML
    private TableColumn<FarmerApplication, String>farmerIdCol;
    @javafx.fxml.FXML
    private Label appLabel;
    @javafx.fxml.FXML
    private Label supLabel;
    Supplier s;
    FarmerApplication f;

    @javafx.fxml.FXML
    public void initialize() throws IOException {
        farmerIdCol.setCellValueFactory(new PropertyValueFactory<>("applicantId"));
        supplierIdCol.setCellValueFactory(new PropertyValueFactory<>("supplierID"));



    }

    FieldOfficer currentUser;
    public void passAssignInterface(FieldOfficer fo){
        currentUser = fo;
    }



    @javafx.fxml.FXML
    public void assignOa(ActionEvent actionEvent) throws IOException {

        File file = new File("allTasks.bin");
        if (file.exists()){
            ArrayList<Task> nList = new ArrayList<>();
            Helper.loadFrom("allTasks.bin", nList);
            for (Task n : nList){
                if (n.getTaskId().equals(taskIdTF.getText())){
                    Helper.showAlert("Cant duplicate  id");
                    return;
                }
            }
        }


        // taskId, String assignedSupplier, String applicantId, String applicantMobileNo, String details
        Task t = new Task(taskIdTF.getText(), s.getSupplierID(), f.getApplicantId(), f.getApplicantMobileNo(), detailsTA.getText());
        Helper.writeInto("allTasks.bin", t);
        s.setAssigned(true);
        f.setAssigned(true);
        farmerTableView.getItems().remove(f);
        supplierTableView.getItems().removeAll(s);

        ArrayList<FarmerApplication> appList = new ArrayList<>();
        Helper.loadFrom("allApplications.bin", appList);
        for (FarmerApplication app : appList){
            if (f.getApplicationId().equals(app.getApplicationId())){
                app.setAssigned(true);
            }
        }
        Helper.deleteFile("allApplications.bin");
        for (FarmerApplication app : appList){
            Helper.writeInto("allApplications.bin", app);
        }

        ArrayList<Supplier> supList = new ArrayList<>();
        Helper.loadFrom("allSuppliers.bin", supList);
        for (Supplier sup : supList){
            if (s.getSupplierID().equals(sup.getSupplierID())){
                sup.setAssigned(true);
            }
        }
        Helper.deleteFile("allSuppliers.bin");
        for (Supplier sup : supList){
            Helper.writeInto("allSuppliers.bin", sup);
        }







    }

    @javafx.fxml.FXML
    public void backOA(ActionEvent actionEvent) throws IOException {
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
    public void loadOA(ActionEvent actionEvent) throws IOException {

        ArrayList<FarmerApplication> faList = new ArrayList<>();
        ArrayList<Supplier> supList = new ArrayList<>();

        Helper.loadFrom("allApplications.bin", faList);
        Helper.loadFrom("allSuppliers.bin", supList);

        for (FarmerApplication fa : faList){
            if (fa.getApplicantLocation().equals(currentUser.getOfficerRegion()) && fa.getStatus().equals("Approved") && !fa.isAssigned()){
                farmerTableView.getItems().add(fa);
            }
        }

        for (Supplier s : supList){
            if (s.getServiceArea().equals(currentUser.getOfficerRegion()) && s.isVerified() && !s.isAssigned()){
                supplierTableView.getItems().add(s);
            }
        }
    }

    @javafx.fxml.FXML
    public void takeSupOA(ActionEvent actionEvent) {
        s = supplierTableView.getSelectionModel().getSelectedItem();
        supLabel.setText(s.getSupplierID());


    }

    @javafx.fxml.FXML
    public void takeAppOA(ActionEvent actionEvent) {
        f = farmerTableView.getSelectionModel().getSelectedItem();
        appLabel.setText(f.getApplicantId());
    }
}