package cse213.badc.rhythm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.ArrayList;
import java.io.FileWriter;

public class ReviewEligibilityController {
    @FXML
    private TextField circularIdTextField;
    @FXML
    private TextField ageMinTextField;
    @FXML
    private TextField ageMaxTextField;
    @FXML
    private TextField applicationFeeTextField;
    @FXML
    private TextArea qualificationsTextArea;
    @FXML
    private TextArea experienceTextArea;
    @FXML
    private Label statusLabel;

    private ArrayList<Circular> circularList;
    private ArrayList<EligibilityCriteria> eligibilityList;

    @FXML
    public void initialize() {
        circularList = new ArrayList<>();
        eligibilityList = new ArrayList<>();

        circularList.add(new Circular("C001", "Senior Officer", "Administration", "31-12-2025", "Graduate with 3 years experience", 5, 500, 35));
        circularList.add(new Circular("C002", "Research Officer", "Research", "15-01-2026", "Masters in relevant field", 3, 400, 32));
        circularList.add(new Circular("C003", "Junior Officer", "Seed Division", "28-02-2026", "Bachelor's degree in Agriculture", 10, 300, 28));

        eligibilityList.add(new EligibilityCriteria("C001", "21", "35", "Bachelor's degree or equivalent\nNo third division in academic career\nBangladeshi national", "3 years relevant experience in administration or related field", "500 BDT"));
        eligibilityList.add(new EligibilityCriteria("C002", "22", "32", "Master's degree in relevant field\nBangladeshi national\nNo criminal record", "0 years (fresh graduates welcome)\nResearch publications preferred", "400 BDT"));
        eligibilityList.add(new EligibilityCriteria("C003", "21", "28", "Bachelor's degree in Agriculture or related field\nBangladeshi national", "0-2 years experience in seed/agriculture sector", "300 BDT"));
    }

    @FXML
    public void viewC001OA(ActionEvent actionEvent) {
        displayEligibility("C001");
    }

    @FXML
    public void viewC002OA(ActionEvent actionEvent) {
        displayEligibility("C002");
    }

    @FXML
    public void viewC003OA(ActionEvent actionEvent) {
        displayEligibility("C003");
    }

    private void displayEligibility(String circularId) {
        Circular circular = null;
        EligibilityCriteria criteria = null;

        for (Circular c: circularList) {
            if (c.getCircularId().equals(circularId)) {
                circular = c;
            }
        }

        for (EligibilityCriteria ec: eligibilityList) {
            if (ec.getCircularId().equals(circularId)) {
                criteria = ec;
            }
        }

        if (circular != null && criteria != null) {
            circularIdTextField.setText(criteria.getCircularId());
            ageMinTextField.setText(criteria.getAgeMin());
            ageMaxTextField.setText(criteria.getAgeMax());
            applicationFeeTextField.setText(criteria.getApplicationFee());
            qualificationsTextArea.setText(criteria.getQualifications());
            experienceTextArea.setText(criteria.getExperience());
            statusLabel.setText("Eligibility criteria for " + circular.getTitle() + " displayed");
        } else {
            showAlert("Error", "Circular or eligibility criteria not found");
        }
    }

    @FXML
    public void viewFullPolicyOA(ActionEvent actionEvent) {
        String circularId = circularIdTextField.getText();

        if (circularId.isEmpty()) {
            showAlert("Error", "Please view eligibility details first");
            return;
        }

        try {
            FileWriter writer = new FileWriter("eligibility_policy_" + circularId + ".txt");

            for (EligibilityCriteria c: eligibilityList) {
                if (c.getCircularId().equals(circularId)) {
                    writer.write(c.toString() + "\n");
                }
            }

            writer.close();
            statusLabel.setText("Policy document saved successfully");
        } catch (Exception e) {
            showAlert("File Error", "Failed to save policy document");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
