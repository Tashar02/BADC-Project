package cse213.badc.rhythm;

import cse213.badc.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;

public class U7G4_HelpdeskController {
    @FXML
    private TextField subjectTextField;
    @FXML
    private ComboBox<String> categoryComboBox;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private Label messageLabel;
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
    private int ticketCounter;

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
        ticketCounter = 1000;
        loadTicketsFromFile();
        displayTickets();
    }

    private void loadTicketsFromFile() {
        try {
            File file = new File("helpdesk_tickets.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.startsWith("HelpdeskTicket")) {
                    int idStart = line.indexOf("ticketId='") + "ticketId='".length();
                    int idEnd = line.indexOf("'", idStart);
                    String ticketId = line.substring(idStart, idEnd);

                    int subjStart = line.indexOf("subject='", idEnd) + "subject='".length();
                    int subjEnd = line.indexOf("'", subjStart);
                    String subject = line.substring(subjStart, subjEnd);

                    int catStart = line.indexOf("category='", subjEnd) + "category='".length();
                    int catEnd = line.indexOf("'", catStart);
                    String category = line.substring(catStart, catEnd);

                    int descStart = line.indexOf("description='", catEnd) + "description='".length();
                    int descEnd = line.indexOf("'", descStart);
                    String description = line.substring(descStart, descEnd);

                    int statStart = line.indexOf("status='", descEnd) + "status='".length();
                    int statEnd = line.indexOf("'", statStart);
                    String status = line.substring(statStart, statEnd);

                    int subDateStart = line.indexOf("submissionDate='", statEnd) + "submissionDate='".length();
                    int subDateEnd = line.indexOf("'", subDateStart);
                    String submissionDate = line.substring(subDateStart, subDateEnd);

                    int lastUpdStart = line.indexOf("lastUpdated='", subDateEnd) + "lastUpdated='".length();
                    int lastUpdEnd = line.indexOf("'", lastUpdStart);
                    String lastUpdated = line.substring(lastUpdStart, lastUpdEnd);

                    int remarksStart = line.indexOf("remarks='", lastUpdEnd) + "remarks='".length();
                    int remarksEnd = line.indexOf("'", remarksStart);
                    String remarks = line.substring(remarksStart, remarksEnd);

                    HelpdeskTicket ticket = new HelpdeskTicket(
                            ticketId,
                            subject,
                            category,
                            description,
                            status,
                            submissionDate,
                            lastUpdated,
                            remarks
                    );

                    allTickets.add(ticket);
                    ticketCounter++;
                }
            }

            scanner.close();
        } catch (Exception e) {
            Helper.showAlert("File Error", "Could not read helpdesk tickets file");
        }
    }

    @FXML
    public void submitTicketOA(ActionEvent actionEvent) {
        String subject = subjectTextField.getText();
        String category = categoryComboBox.getValue();
        String description = descriptionTextArea.getText();

        if (!validateTicketFields(subject, category, description)) {
            return;
        }

        LocalDate today = LocalDate.now();
        String submissionDate = today.getDayOfMonth() + "-" + today.getMonthValue() + "-" + today.getYear();

        String ticketId = "TKT" + ticketCounter;
        ticketCounter++;

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

        if (Helper.appendTextFile("helpdesk_tickets.txt", newTicket.toString())) {
            messageLabel.setText("Ticket created successfully. Ticket ID: " + ticketId);
            clearFormOA(null);
            displayTickets();
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
    public void viewDetailsOA(ActionEvent actionEvent) {
        HelpdeskTicket selectedTicket = ticketHistoryTableView.getSelectionModel().getSelectedItem();

        if (selectedTicket == null) {
            Helper.showAlert("No Selection", "Please select a ticket to view details");
            return;
        }

        detailsSubjectTextField.setText(selectedTicket.getSubject());
        detailsDescriptionTextArea.setText(selectedTicket.getDescription());
    }

    private boolean validateTicketFields(String subject, String category, String description) {
        if (subject.isEmpty()) {
            Helper.showAlert("Validation Error", "Subject cannot be empty");
            return false;
        }

        if (subject.length() > 100) {
            Helper.showAlert("Validation Error", "Subject cannot exceed 100 characters");
            return false;
        }

        if (category == null || category.isEmpty()) {
            Helper.showAlert("Validation Error", "Please select a category");
            return false;
        }

        if (description.isEmpty()) {
            Helper.showAlert("Validation Error", "Description cannot be empty");
            return false;
        }

        if (description.length() > 1000) {
            Helper.showAlert("Validation Error", "Description cannot exceed 1000 characters");
            return false;
        }

        return true;
    }

    private void displayTickets() {
        ticketHistoryTableView.getItems().clear();

        for (HelpdeskTicket ticket: allTickets) {
            ticketHistoryTableView.getItems().add(ticket);
        }
    }
}
