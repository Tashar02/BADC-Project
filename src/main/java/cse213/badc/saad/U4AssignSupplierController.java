package com.example.mainproject.saad;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    public void initialize() throws IOException {
        farmerIdCol.setCellValueFactory(new PropertyValueFactory<>("applicantId"));
        supplierIdCol.setCellValueFactory(new PropertyValueFactory<>("supplierID"));

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

    FieldOfficer currentUser;
    public void passAssignInterface(FieldOfficer fo){
        currentUser = fo;
    }



    @javafx.fxml.FXML
    public void assignOa(ActionEvent actionEvent) {
        Supplier s = supplierTableView.getSelectionModel().getSelectedItem();
        FarmerApplication f = farmerTableView.getSelectionModel().getSelectedItem();

        // taskId, String assignedSupplier, String applicantId, String applicantMobileNo, String details
        Task t = new Task(taskIdTF.getText(), s.getSupplierID(), f.getApplicantId(), f.getApplicantMobileNo(), detailsTA.getText());
        s.setAssigned(true);
        f.setAssigned(true);
        farmerTableView.getItems().remove(f);
        supplierTableView.getItems().removeAll(s);
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
}