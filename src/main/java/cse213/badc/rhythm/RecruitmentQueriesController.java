package cse213.badc.rhythm;

import cse213.badc.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class RecruitmentQueriesController {
    @FXML
    private ComboBox<String> circularComboBox;
    @FXML
    private TextField subjectTextField;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private TableView<RecruitmentQuery> queriesTableView;
    @FXML
    private TableColumn<RecruitmentQuery, String> queryIdTC;
    @FXML
    private TableColumn<RecruitmentQuery, String> subjectTC;
    @FXML
    private TableColumn<RecruitmentQuery, String> circularTC;
    @FXML
    private TableColumn<RecruitmentQuery, String> statusTC;
    @FXML
    private TableColumn<RecruitmentQuery, String> submittedDateTC;
    @FXML
    private Label messageLabel;

    private ArrayList<RecruitmentQuery> queryList;
    private int nextQueryId;

    @FXML
    public void initialize() throws IOException {
        ArrayList<String> circulars = new ArrayList<>();
        circulars.add("C001 - Senior Officer");
        circulars.add("C002 - Research Officer");
        circulars.add("C003 - Junior Officer");
        circularComboBox.getItems().addAll(circulars);

        queryIdTC.setCellValueFactory(new PropertyValueFactory<>("queryId"));
        subjectTC.setCellValueFactory(new PropertyValueFactory<>("subject"));
        circularTC.setCellValueFactory(new PropertyValueFactory<>("circular"));
        statusTC.setCellValueFactory(new PropertyValueFactory<>("status"));
        submittedDateTC.setCellValueFactory(new PropertyValueFactory<>("submittedDate"));

        queryList = new ArrayList<>();
        nextQueryId = 1001;
        loadQueries();
        refreshQueriesTable();
    }

    private void loadQueries() throws IOException {
        ArrayList<RecruitmentQuery> loadedQueries = new ArrayList<>();
        Helper.loadFrom("recruitment_queries.bin", loadedQueries);

        if (!loadedQueries.isEmpty()) {
            queryList = loadedQueries;
            RecruitmentQuery lastQuery = queryList.getLast();
            nextQueryId = Integer.parseInt(lastQuery.getQueryId()) + 1;
        } else {
            queryList = new ArrayList<>();
        }
    }

    @FXML
    public void submitQueryOA(ActionEvent actionEvent) throws IOException {
        String subject = subjectTextField.getText();
        String description = descriptionTextArea.getText();
        String circular = circularComboBox.getValue();

        if (!validateQuery(subject, description)) {
            return;
        }

        LocalDate today = LocalDate.now();
        String submittedDate = today.getDayOfMonth() + "-" + today.getMonthValue() + "-" + today.getYear();

        String circularText = circular != null ? circular : "Not Selected";

        RecruitmentQuery query = new RecruitmentQuery(
                Integer.toString(nextQueryId),
                subject,
                circularText,
                description,
                "Received",
                submittedDate,
                "No response yet"
        );

        queryList.add(query);
        nextQueryId++;
        Helper.writeInto("recruitment_queries.bin", query);

        messageLabel.setText("Query submitted successfully with ID: " + query.getQueryId());
        clearForm();
        refreshQueriesTable();
    }

    @FXML
    public void clearFormOA(ActionEvent actionEvent) {
        clearForm();
    }

    private void clearForm() {
        subjectTextField.setText("");
        descriptionTextArea.setText("");
        circularComboBox.setValue(null);
        messageLabel.setText("");
    }

    private boolean validateQuery(String subject, String description) {
        if (subject.isEmpty()) {
            Helper.showAlert("Validation Error", "Subject cannot be empty");
            return false;
        }

        if (subject.length() > 100) {
            Helper.showAlert("Validation Error", "Subject must be 100 characters or less");
            return false;
        }

        if (description.isEmpty()) {
            Helper.showAlert("Validation Error", "Description cannot be empty");
            return false;
        }

        if (description.length() > 1500) {
            Helper.showAlert("Validation Error", "Description must be 1500 characters or less");
            return false;
        }

        return true;
    }

    private void refreshQueriesTable() {
        queriesTableView.getItems().clear();
        for (RecruitmentQuery q: queryList) {
            queriesTableView.getItems().add(q);
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
