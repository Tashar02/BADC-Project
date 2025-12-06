package cse213.badc.rhythm;

import cse213.badc.Helper;
import cse213.badc.saad.Report;
import javafx.event.ActionEvent;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class U8G1_ViewFieldReportsController {
    @javafx.fxml.FXML
    private TableView<Report> fieldReportsTableView;
    @javafx.fxml.FXML
    private TableColumn<Report, String> reportIdTC;
    @javafx.fxml.FXML
    private TableColumn<Report, String> authorIdTC;
    @javafx.fxml.FXML
    private TableColumn<Report, String> summaryTC;
    @javafx.fxml.FXML
    private TableColumn<Report, String> statusTC;
    @javafx.fxml.FXML
    private TableColumn<Report, LocalDate> reportDateTC;

    private ArrayList<Report> fieldReports;

    @javafx.fxml.FXML
    public void initialize() {
        reportIdTC.setCellValueFactory(new PropertyValueFactory<>("reportId"));
        authorIdTC.setCellValueFactory(new PropertyValueFactory<>("authorId"));
        summaryTC.setCellValueFactory(new PropertyValueFactory<>("summary"));
        statusTC.setCellValueFactory(new PropertyValueFactory<>("status"));
        reportDateTC.setCellValueFactory(new PropertyValueFactory<>("reportDate"));

        fieldReports = new ArrayList<>();
        loadReportsFromFile();
        refreshFieldReportsTable();
    }

    private void loadReportsFromFile() {
        try {
            File file = new File("allRegionalReports.bin");

            if (!file.exists()) {
                return;
            }

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

            while (true) {
                try {
                    Report report = (Report) ois.readObject();
                    fieldReports.add(report);
                } catch (Exception e) {
                    break;
                }
            }

            ois.close();
        } catch (Exception e) {
            Helper.showAlert("File Error", "Could not read field reports");
        }
    }

    private void refreshFieldReportsTable() {
        fieldReportsTableView.getItems().clear();
        for (Report report: fieldReports) {
            fieldReportsTableView.getItems().add(report);
        }
    }

    @javafx.fxml.FXML
    public void backToDashboardOA(ActionEvent actionEvent) throws IOException {
        Helper.backToDashboardU8(actionEvent);
    }
}
