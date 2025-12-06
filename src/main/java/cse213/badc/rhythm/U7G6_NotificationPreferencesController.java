package cse213.badc.rhythm;

import cse213.badc.Helper;
import javafx.event.ActionEvent;

import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class U7G6_NotificationPreferencesController {
    @javafx.fxml.FXML
    private CheckBox hrCheckBox;
    @javafx.fxml.FXML
    private CheckBox itCheckBox;
    @javafx.fxml.FXML
    private CheckBox financeCheckBox;
    @javafx.fxml.FXML
    private CheckBox operationsCheckBox;
    @javafx.fxml.FXML
    private ComboBox<String> jobLevelComboBox;
    @javafx.fxml.FXML
    private CheckBox emailCheckBox;
    @javafx.fxml.FXML
    private CheckBox smsCheckBox;
    @javafx.fxml.FXML
    private TextField emailTextField;
    @javafx.fxml.FXML
    private TextField phoneTextField;
    @javafx.fxml.FXML
    private Label currentPreferencesLabel;

    private NotificationPreference currentPreference;

    @javafx.fxml.FXML
    public void initialize() {
        ArrayList<String> jobLevels = new ArrayList<>();
        jobLevels.add("Entry Level");
        jobLevels.add("Mid Level");
        jobLevels.add("Senior Level");
        jobLevels.add("Executive");
        jobLevelComboBox.getItems().addAll(jobLevels);
        loadPreferences();
    }

    private void loadPreferences() {
        currentPreference = (NotificationPreference) Helper.loadFromSingleObject("notification_preferences.bin");
        displayCurrentPreferences();
    }

    @javafx.fxml.FXML
    public void savePreferencesOA(ActionEvent actionEvent) throws IOException {
        ArrayList<String> selectedDepartments = new ArrayList<>();
        if (hrCheckBox.isSelected()) {
            selectedDepartments.add("HR");
        }
        if (itCheckBox.isSelected()) {
            selectedDepartments.add("IT");
        }
        if (financeCheckBox.isSelected()) {
            selectedDepartments.add("Finance");
        }
        if (operationsCheckBox.isSelected()) {
            selectedDepartments.add("Operations");
        }

        String jobLevel = jobLevelComboBox.getValue();
        String email = emailTextField.getText();
        String phone = phoneTextField.getText();
        boolean emailSelected = emailCheckBox.isSelected();
        boolean smsSelected = smsCheckBox.isSelected();

        if (!validatePreferences(selectedDepartments, jobLevel, email, phone, emailSelected, smsSelected)) {
            return;
        }

        LocalDate today = LocalDate.now();
        String updatedDate = today.getDayOfMonth() + "-" + today.getMonthValue() + "-" + today.getYear();

        String departmentsStr = "";
        for (String dept: selectedDepartments) {
            departmentsStr = departmentsStr + dept + ", ";
        }
        if (!departmentsStr.isEmpty()) {
            departmentsStr = departmentsStr.substring(0, departmentsStr.length() - 2);
        }

        String methodStr = "";
        if (emailSelected) {
            methodStr = methodStr + "Email";
        }
        if (smsSelected) {
            if (!methodStr.isEmpty()) {
                methodStr = methodStr + ", SMS";
            } else {
                methodStr = "SMS";
            }
        }

        NotificationPreference preference = new NotificationPreference(
                departmentsStr,
                jobLevel,
                email,
                phone,
                methodStr,
                updatedDate
        );

        Helper.writeInto("notification_preferences.bin", preference);
        currentPreference = preference;
        displayCurrentPreferences();
        Helper.showAlert("Success", "Preferences saved successfully");
    }

    @javafx.fxml.FXML
    public void clearFormOA(ActionEvent actionEvent) {
        hrCheckBox.setSelected(false);
        itCheckBox.setSelected(false);
        financeCheckBox.setSelected(false);
        operationsCheckBox.setSelected(false);
        jobLevelComboBox.setValue(null);
        emailCheckBox.setSelected(false);
        smsCheckBox.setSelected(false);
        emailTextField.setText("");
        phoneTextField.setText("");
    }

    private void displayCurrentPreferences() {
        if (currentPreference != null) {
            String displayText = "Departments: " + currentPreference.getDepartments() + "\n" +
                    "Job Level: " + currentPreference.getJobLevel() + "\n" +
                    "Email: " + currentPreference.getEmail() + "\n" +
                    "Phone: " + currentPreference.getPhone() + "\n" +
                    "Notification Methods: " + currentPreference.getNotificationMethod() + "\n" +
                    "Updated: " + currentPreference.getUpdatedDate();
            currentPreferencesLabel.setText(displayText);
        } else {
            currentPreferencesLabel.setText("No preferences saved yet.");
        }
    }

    private boolean validatePreferences(ArrayList<String> departments, String jobLevel, String email, String phone, boolean emailSelected, boolean smsSelected) {
        if (departments.isEmpty()) {
            Helper.showAlert("Validation Error", "Please select at least one department");
            return false;
        }

        if (jobLevel == null || jobLevel.isEmpty()) {
            Helper.showAlert("Validation Error", "Please select a job level");
            return false;
        }

        if (!emailSelected && !smsSelected) {
            Helper.showAlert("Validation Error", "Please select at least one notification method");
            return false;
        }

        if (emailSelected) {
            if (email.isEmpty()) {
                Helper.showAlert("Validation Error", "Email address cannot be empty if Email is selected");
                return false;
            }
            if (!email.contains("@")) {
                Helper.showAlert("Validation Error", "Email address must contain '@' symbol");
                return false;
            }
        }

        if (smsSelected) {
            if (phone.isEmpty()) {
                Helper.showAlert("Validation Error", "Phone number cannot be empty if SMS is selected");
                return false;
            }
            if (phone.length() != 11) {
                Helper.showAlert("Validation Error", "Phone number must be exactly 11 digits");
                return false;
            }
            if (!phone.startsWith("01")) {
                Helper.showAlert("Validation Error", "Phone number must start with 01");
                return false;
            }
        }

        return true;
    }

    @javafx.fxml.FXML
    public void backToDashboardOA(ActionEvent actionEvent) throws IOException {
        Helper.backToDashboardU7(actionEvent);
    }
}
