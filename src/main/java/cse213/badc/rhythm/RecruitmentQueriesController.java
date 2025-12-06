package cse213.badc.rhythm;

import cse213.badc.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
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
    public void initialize() {
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

    private void loadQueries() {
        try {
            FileInputStream fis = new FileInputStream("recruitment_queries.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);

            queryList = (ArrayList<RecruitmentQuery>) ois.readObject();
            ois.close();
            fis.close();

            if (queryList != null && !queryList.isEmpty()) {
                RecruitmentQuery lastQuery = null;
                for (RecruitmentQuery q: queryList) {
                    lastQuery = q;
                }
                if (lastQuery != null) {
                    nextQueryId = Integer.parseInt(lastQuery.getQueryId()) + 1;
                }
            } else {
                queryList = new ArrayList<>();
            }
        } catch (Exception e) {
            queryList = new ArrayList<>();
        }
    }

    private void saveQueries() {
        try {
            FileOutputStream fos = new FileOutputStream("recruitment_queries.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(queryList);
            oos.close();
            fos.close();
        } catch (Exception e) {
            Helper.showAlert("File Error", "Failed to save queries");
        }
    }

    @FXML
    public void submitQueryOA(ActionEvent actionEvent) {
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
        saveQueries();

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
