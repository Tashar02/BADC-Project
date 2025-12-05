package com.example.mainproject.saad;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class U3ViewTendersController
{
    @javafx.fxml.FXML
    private TableColumn<Tender, String> titleCol;
    @javafx.fxml.FXML
    private TableColumn<Tender, String> regionCol;
    @javafx.fxml.FXML
    private TableView<Tender> tenderTableView;
    @javafx.fxml.FXML
    private TableColumn<Tender, LocalDate> dateCol;
    @javafx.fxml.FXML
    private Label insLabel;
    @javafx.fxml.FXML
    private Label detailsLabel;

    @javafx.fxml.FXML
    public void initialize() {
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("publishDate"));
        regionCol.setCellValueFactory(new PropertyValueFactory<>("region"));
    }

    Supplier currentUser;
    public void passTenderInterface(Supplier s){
        currentUser = s;
    }


    @javafx.fxml.FXML
    public void detailsOA(ActionEvent actionEvent) {

        if (!currentUser.isVerified()){
            Helper.showAlert("You need to be verified");
            return;
        }


        insLabel.setText("Choose a tender from table to view");
        Tender tender = tenderTableView.getSelectionModel().getSelectedItem();
        detailsLabel.setText(tender.toString());
    }

    @javafx.fxml.FXML
    public void allOA(ActionEvent actionEvent) throws IOException {
        ArrayList<Tender> tenders = new ArrayList<>();
        Helper.loadFrom("allTenders.bin", tenders);
        tenderTableView.getItems().addAll(tenders);

    }


    @javafx.fxml.FXML
    public void regionOA(ActionEvent actionEvent) throws IOException {

        if (!currentUser.isVerified()){
            Helper.showAlert("You are eligible to use this feature");
            return;
        }

        ArrayList<Tender> tenders = new ArrayList<>();
        Helper.loadFrom("allTenders.bin", tenders);
        for (Tender t : tenders){
            if (t.getRegion().equals(currentUser.getServiceArea())){
                tenderTableView.getItems().add(t);
            }
        }
    }
}