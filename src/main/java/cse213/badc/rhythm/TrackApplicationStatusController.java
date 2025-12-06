package cse213.badc.rhythm;

import cse213.badc.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class TrackApplicationStatusController {
    @FXML
    private TextField ApplicationIdTextField;
    @FXML
    private TableView<ApplicationStatus> statusHistoryTableView;
    @FXML
    private TableColumn<ApplicationStatus, String> applicationIdTC;
    @FXML
    private TableColumn<ApplicationStatus, String> statusTC;
    @FXML
    private Label messageLabel;

    private ArrayList<ApplicationStatus> allApplications;

    @FXML
    public void initialize() {
        applicationIdTC.setCellValueFactory(new PropertyValueFactory<>("applicationId"));
        statusTC.setCellValueFactory(new PropertyValueFactory<>("status"));

        allApplications = new ArrayList<>();

        try {
            File file = new File("applications.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.startsWith("JobApplication")) {
                    int idMarkerPos = line.indexOf("applicationId='");
                    if (idMarkerPos != -1) {
                        int idStart = idMarkerPos + "applicationId='".length();
                        int idEnd = line.indexOf("'", idStart);
                        if (idEnd != -1) {
                            String applicationId = line.substring(idStart, idEnd);
                            allApplications.add(new ApplicationStatus(applicationId, "Approved"));
                        }
                    }
                }
            }

            scanner.close();
        } catch (Exception e) {
            Helper.showAlert("File Error", "Could not read applications file");
        }
    }

    @FXML
    public void searchApplicationStatusOA(ActionEvent actionEvent) {
        String searchId = ApplicationIdTextField.getText();

        if (searchId.isEmpty()) {
            Helper.showAlert("Input Error", "Please enter an Application ID");
            return;
        }

        statusHistoryTableView.getItems().clear();

        for (ApplicationStatus app: allApplications) {
            if (app.getApplicationId().equals(searchId)) {
                statusHistoryTableView.getItems().add(app);
                messageLabel.setText("Application found");
                return;
            }
        }

        Helper.showAlert("Not Found", "Application ID not found");
        messageLabel.setText("Application not found");
    }

    @FXML
    public void clearSearchOA(ActionEvent actionEvent) {
        ApplicationIdTextField.clear();
        statusHistoryTableView.getItems().clear();
        messageLabel.setText("");
    }
}
