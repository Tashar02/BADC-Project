package cse213.badc.rhythm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;

public class HelpdeskController {

    @FXML
    private TextField subjectTextField;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Label messageLabel;

    @FXML
    private CheckBox showResolvedOnlyCheckBox;

    @FXML
    private TableView<HelpdeskTicket> ticketHistoryTableView;

    @FXML
    private TableColumn<HelpdeskTicket, String> ticketIdTC;

    @FXML
    private TableColumn<HelpdeskTicket, String> subjectTC;

    @FXML
    private TableColumn<HelpdeskTicket, String> statusTC;

    @FXML
    private TableColumn<HelpdeskTicket, String> submissionDateTC;

    @FXML
    private TableColumn<HelpdeskTicket, String> lastUpdatedTC;

    @FXML
    private TextField detailsSubjectTextField;

    @FXML
    private TextArea detailsDescriptionTextArea;

    private ArrayList<HelpdeskTicket> allTickets;
    private int ticketCounter = 1001;

    @FXML
    public void initialize() {
        ticketIdTC.setCellValueFactory(new PropertyValueFactory<>("ticketId"));
        subjectTC.setCellValueFactory(new PropertyValueFactory<>("subject"));
        statusTC.setCellValueFactory(new PropertyValueFactory<>("status"));
        submissionDateTC.setCellValueFactory(new PropertyValueFactory<>("submissionDate"));
        lastUpdatedTC.setCellValueFactory(new PropertyValueFactory<>("lastUpdated"));

        ArrayList<String> categories = new ArrayList<>();
        categories.add("Application Status");
        categories.add("Technical Issue");
        categories.add("Document Problem");
        categoryComboBox.getItems().addAll(categories);

        allTickets = new ArrayList<>();
        loadSampleTickets();

        displayTickets(false);
    }

    @FXML
    public void submitTicketOA(ActionEvent actionEvent) {
        String subject = subjectTextField.getText().trim();
        String category = categoryComboBox.getValue();
        String description = descriptionTextArea.getText().trim();

        if (!validateTicketFields(subject, category, description)) {
            return;
        }

        LocalDate today = LocalDate.now();
        String submissionDate = today.getDayOfMonth() + "-" + today.getMonthValue() + "-" + today.getYear();

        String ticketId = "TKT" + ticketCounter++;
        HelpdeskTicket newTicket = new HelpdeskTicket(
                ticketId,
                subject,
                category,
                description,
                "Open",
                submissionDate,
                submissionDate,
                ""
        );

        allTickets.add(newTicket);

        try {
            FileWriter writer = new FileWriter("helpdesk_tickets.txt", true);
            writer.write(newTicket.toString() + "\n");
            writer.close();

            messageLabel.setText("Ticket created successfully. Ticket ID: " + ticketId);
            clearFormOA(null);
        } catch (Exception e) {
            showAlert("File Error", "Failed to save ticket");
        }
    }

    @FXML
    public void clearFormOA(ActionEvent actionEvent) {
        subjectTextField.setText("");
        categoryComboBox.setValue(null);
        descriptionTextArea.setText("");
        messageLabel.setText("");
    }

    @FXML
    public void filterTicketsOA(ActionEvent actionEvent) {
        boolean showResolvedOnly = showResolvedOnlyCheckBox.isSelected();
        displayTickets(showResolvedOnly);
    }

    @FXML
    public void viewDetailsOA(ActionEvent actionEvent) {
        HelpdeskTicket selectedTicket = ticketHistoryTableView.getSelectionModel().getSelectedItem();

        if (selectedTicket == null) {
            showAlert("No Selection", "Please select a ticket to view details");
            return;
        }

        detailsSubjectTextField.setText(selectedTicket.getSubject());
        detailsDescriptionTextArea.setText(selectedTicket.getDescription());
    }

    private boolean validateTicketFields(String subject, String category, String description) {
        if (subject.isEmpty()) {
            showAlert("Validation Error", "Subject cannot be empty");
            return false;
        }

        if (subject.length() > 100) {
            showAlert("Validation Error", "Subject cannot exceed 100 characters");
            return false;
        }

        if (category == null || category.isEmpty()) {
            showAlert("Validation Error", "Please select a category");
            return false;
        }

        if (description.isEmpty()) {
            showAlert("Validation Error", "Description cannot be empty");
            return false;
        }

        if (description.length() > 1000) {
            showAlert("Validation Error", "Description cannot exceed 1000 characters");
            return false;
        }

        return true;
    }

    private void displayTickets(boolean showResolvedOnly) {
        ticketHistoryTableView.getItems().clear();

        for (HelpdeskTicket ticket: allTickets) {
            if (showResolvedOnly) {
                if (ticket.getStatus().equals("Resolved")) {
                    ticketHistoryTableView.getItems().add(ticket);
                }
            } else {
                ticketHistoryTableView.getItems().add(ticket);
            }
        }
    }

    private void loadSampleTickets() {
        LocalDate today = LocalDate.now();
        String todayStr = today.getDayOfMonth() + "-" + today.getMonthValue() + "-" + today.getYear();

        HelpdeskTicket ticket1 = new HelpdeskTicket(
                "TKT1000",
                "Application status not showing",
                "Application Status",
                "I submitted my job application 2 weeks ago but the status page shows nothing",
                "Open",
                todayStr,
                todayStr,
                ""
        );

        HelpdeskTicket ticket2 = new HelpdeskTicket(
                "TKT1001",
                "Cannot login to account",
                "Technical Issue",
                "Getting error when trying to login with my credentials",
                "Resolved",
                "20-11-2025",
                "22-11-2025",
                "Password was reset. User able to login successfully."
        );

        HelpdeskTicket ticket3 = new HelpdeskTicket(
                "TKT1002",
                "Form field names unclear",
                "Document Problem",
                "Not sure what information to fill in the education section",
                "Open",
                "28-11-2025",
                "28-11-2025",
                ""
        );

        allTickets.add(ticket1);
        allTickets.add(ticket2);
        allTickets.add(ticket3);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
