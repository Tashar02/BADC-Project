package cse213.badc.rhythm;

import cse213.badc.Helper;
import cse213.badc.saad.Supplier;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;

public class ViewTendersController
{
    @javafx.fxml.FXML
    private TableColumn<Tender, String> titleTableColumn;
    @javafx.fxml.FXML
    private TableColumn<Tender, String> regionTableColumn;
    @javafx.fxml.FXML
    private TableView<Tender> tenderTableView;
    @javafx.fxml.FXML
    private Label detailsLabel;
    @javafx.fxml.FXML
    private Label messageLabel;
    @javafx.fxml.FXML
    private TableColumn<Tender, String> publishDateTableColumn;

    @javafx.fxml.FXML
    public void initialize() {
        titleTableColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        publishDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("publishDate"));
        regionTableColumn.setCellValueFactory(new PropertyValueFactory<>("region"));
    }

    Supplier currentUser;
    public void passTenderInterface(Supplier s) {
        currentUser = s;
    }

    @javafx.fxml.FXML
    public void detailsOA(ActionEvent actionEvent) {
        if (!currentUser.isVerified()){
            Helper.showAlert("You need to be verified");
            return;
        }

        messageLabel.setText("Choose a tender from table to view");
        Tender tender = tenderTableView.getSelectionModel().getSelectedItem();
        detailsLabel.setText(tender.toString());
    }

    @javafx.fxml.FXML
    public void loadAllOA(ActionEvent actionEvent) {
        try {
            ArrayList<Tender> tenders = new ArrayList<>();
            Helper.loadFrom("allTenders.bin", tenders);
            tenderTableView.getItems().addAll(tenders);
        } catch (Exception e) {
            Helper.showAlert("File Error", "You are not eligible to use this feature");
        }
    }

    @javafx.fxml.FXML
    public void loadBasedOnMyRegionOA(ActionEvent actionEvent) throws IOException {
        if (!currentUser.isVerified()){
            Helper.showAlert("Permission Error", "You are not eligible to use this feature");
            return;
        }

        ArrayList<Tender> tenders = new ArrayList<>();
        Helper.loadFrom("allTenders.bin", tenders);
        for (Tender t: tenders){
            if (t.getRegion().equals(currentUser.getServiceArea())){
                tenderTableView.getItems().add(t);
            }
        }
    }
}
