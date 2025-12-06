package cse213.badc.rhythm;

import cse213.badc.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.io.IOException;

public class U8G4_UploadProposalController {
    @FXML
    private TextField proposalIdTextField;
    @FXML
    private TextField authorIdTextField;
    @FXML
    private TextField subjectTextField;
    @FXML
    private TextArea summaryTextArea;
    @FXML
    private TextField projectDurationTextField;
    @FXML
    private TextField budgetEstimateTextField;
    @FXML
    private Label messageLabel;

    @FXML
    public void submitProposalOA(ActionEvent actionEvent) throws IOException {
        String proposalId = proposalIdTextField.getText();
        String authorId = authorIdTextField.getText();
        String subject = subjectTextField.getText();
        String summary = summaryTextArea.getText();
        String projectDuration = projectDurationTextField.getText();
        String budgetEstimateStr = budgetEstimateTextField.getText();

        if (proposalId.isEmpty()) {
            Helper.showAlert("Validation Error", "Proposal ID cannot be empty");
            return;
        }

        if (authorId.isEmpty()) {
            Helper.showAlert("Validation Error", "Author ID cannot be empty");
            return;
        }

        if (subject.isEmpty() || subject.length() > 100) {
            Helper.showAlert("Validation Error", "Subject must be between 1 and 100 characters");
            return;
        }

        if (summary.isEmpty() || summary.length() > 2000) {
            Helper.showAlert("Validation Error", "Summary must be between 1 and 2000 characters");
            return;
        }

        if (projectDuration.isEmpty()) {
            Helper.showAlert("Validation Error", "Project Duration cannot be empty");
            return;
        }

        float budgetEstimate = 0;
        try {
            budgetEstimate = Float.parseFloat(budgetEstimateStr);
        } catch (Exception e) {
            Helper.showAlert("Validation Error", "Budget Estimate must be a numeric value");
            return;
        }

        Proposal proposal = new Proposal(proposalId, authorId, subject, summary, projectDuration, budgetEstimate);
        Helper.writeInto("proposals.bin", proposal);

        messageLabel.setText("Proposal submitted successfully!");
        clearForm();
    }

    @FXML
    public void clearFormOA(ActionEvent actionEvent) {
        clearForm();
    }

    private void clearForm() {
        proposalIdTextField.setText("");
        authorIdTextField.setText("");
        subjectTextField.setText("");
        summaryTextArea.setText("");
        projectDurationTextField.setText("");
        budgetEstimateTextField.setText("");
        messageLabel.setText("");
    }
}
