package cse213.badc.rhythm;

import cse213.badc.saad.Report;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class ViewFieldReportsController {
    @FXML
    private TableView<Report> fieldReportsTableView;
    @FXML
    private TableColumn<Report, String> reportIdTC;
    @FXML
    private TableColumn<Report, String> authorIdTC;
    @FXML
    private TableColumn<Report, String> summaryTC;
    @FXML
    private TableColumn<Report, String> statusTC;
    @FXML
    private TableColumn<Report, LocalDate> reportDateTC;

    private ArrayList<Report> fieldReports;

    @FXML
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
            showAlert("File Error", "Could not read field reports");
        }
    }

    private void refreshFieldReportsTable() {
        fieldReportsTableView.getItems().clear();
        for (Report report: fieldReports) {
            fieldReportsTableView.getItems().add(report);
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
