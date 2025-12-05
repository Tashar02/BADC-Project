package com.example.mainproject.saad;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class U3UploadPumpCatalogController
{
    @javafx.fxml.FXML
    private TextField rentPriceTF;
    @javafx.fxml.FXML
    private TextField purchasePriceTF;
    @javafx.fxml.FXML
    private ComboBox<String> pumpTypeComboBox;
    @javafx.fxml.FXML
    private TextField modelTF;
    @javafx.fxml.FXML
    private TableColumn<Pump, String> idCol;
    @javafx.fxml.FXML
    private TableColumn<Pump, Boolean> statusCol;
    @javafx.fxml.FXML
    private TableView<Pump> pumpTableView;
    @javafx.fxml.FXML
    private TextField powerTF;
    @javafx.fxml.FXML
    private TextField idTF;

    @javafx.fxml.FXML
    public void initialize() {
        pumpTypeComboBox.getItems().addAll("Diesel Pump", "Electric Pump", "Monoblock Pump", "STW Centrifugal Pump", "STW Diesel Pump", "Solar Surface Pump");

        idCol.setCellValueFactory(new PropertyValueFactory<>("equipmentId"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("free"));
    }

    Supplier currentUser;
    public void passPumpInterface(Supplier s){
        currentUser = s;
    }

    @javafx.fxml.FXML
    public void cancelOA(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("U3IESDashboardView.fxml"));

        Scene scene = new Scene(loader.load());

        U3IESDashboardController controller = loader.getController();
        controller.passIESDashboard(currentUser);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        stage.setTitle("IES Dashboard");
        stage.setScene(scene);
        stage.show();


    }

    @javafx.fxml.FXML
    //
    public void pumpUploadOA(ActionEvent actionEvent) throws IOException {
        Pump pump = new Pump(idTF.getText(), modelTF.getText(), pumpTypeComboBox.getValue(), currentUser.getSupplierID(), Integer.parseInt(powerTF.getText()), Integer.parseInt(rentPriceTF.getText()), Integer.parseInt(purchasePriceTF.getText()));
        Helper.writeInto("allPumps.bin", pump);
        Helper.showAlert("Successfully Uploaded");
    }

    @javafx.fxml.FXML
    public void showOA(ActionEvent actionEvent) throws IOException {
        ArrayList<Pump> pumps = new ArrayList<>();

        Helper.loadFrom("allPumps.bin", pumps);

        for (Pump p : pumps){
            if (!currentUser.getSupplierID().equals(p.getSupplierId())){
                pumps.remove(p);
            }
        }

        pumpTableView.getItems().addAll(pumps);


    }
}