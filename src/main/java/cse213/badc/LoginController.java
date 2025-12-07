package cse213.badc;

import cse213.badc.rhythm.BADCJobApplicant;
import cse213.badc.rhythm.DevelopmentPartner;
import cse213.badc.saad.FieldOfficer;
import cse213.badc.saad.Supplier;
import cse213.badc.saad.U3IESDashboardController;
import cse213.badc.saad.U4BFODashboardController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class LoginController {
    @javafx.fxml.FXML
    private ComboBox<String> userTypeComboBox;
    @javafx.fxml.FXML
    private TextField userIdTextField;
    @javafx.fxml.FXML
    private PasswordField passwordField;

    @javafx.fxml.FXML
    public void initialize() {
        userTypeComboBox.getItems().addAll("Irrigation Equipment Supplier",
                "BADC Field Officer", "BADC Job Applicant", "Development Partner");
    }

    @javafx.fxml.FXML
    public void login(ActionEvent actionEvent) throws IOException {
        String userType = userTypeComboBox.getValue();
        String userId = userIdTextField.getText();
        String password = passwordField.getText();

        if (userType == null || userType.isEmpty()) {
            Helper.showAlert("Error", "Please select a user type");
            return;
        }

        if (userId == null || userId.isEmpty()) {
            Helper.showAlert("Error", "Please enter user ID");
            return;
        }

        if (password == null || password.isEmpty()) {
            Helper.showAlert("Error", "Please enter password");
            return;
        }

        if (userType.contains("Irrigation Equipment Supplier")) {
            loginIrrigationEquipmentSupplier(userId, password, actionEvent);
        } else if (userType.contains("BADC Field Officer")) {
            loginBADCFieldOfficer(userId, password, actionEvent);
        } else if (userType.contains("Job Applicant")) {
            loginBADCJobApplicant(userId, password, actionEvent);
        } else if (userType.contains("Development Partner")) {
            loginDevelopmentPartner(userId, password, actionEvent);
        }
    }

    private void loginIrrigationEquipmentSupplier(String userId, String password, ActionEvent actionEvent) throws IOException {
        ArrayList<Supplier> suppliers = new ArrayList<>();
        Helper.loadFrom("allSuppliers.bin", suppliers);

//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("allSuppliers.bin"))) {
//            ArrayList<Supplier> data = (ArrayList<Supplier>) ois.readObject();
//            suppliers.addAll(data);
//        } catch (Exception e) {
//            Helper.showAlert("File Error", "Could not load credentials file");
//            return;
//        }

        for (Supplier supplier: suppliers) {
            if (supplier.getSupplierID().equals(userId) && supplier.getPassword().equals(password)) {
                User.applicantId = supplier.getSupplierID();
                User.fullName = supplier.getFullName();
                User.email = supplier.getEmail();
                User.userType = "IrrigationEquipmentSupplier";

                FXMLLoader loader = new FXMLLoader(BADCApplication.class.getResource("saad/U3IESDashboardView.fxml"));
                Scene scene = new Scene(loader.load());
                U3IESDashboardController controller = loader.getController();
                controller.passIESDashboard(supplier);

                Helper.setScene(actionEvent, scene);
                return;
            }
        }

        Helper.showAlert("Error", "Invalid credentials");
        clearFields();
    }

    private void loginBADCFieldOfficer(String userId, String password, ActionEvent actionEvent) throws IOException {
        ArrayList<FieldOfficer> officers = new ArrayList<>();
        Helper.loadFrom("allFieldOfficers.bin", officers);

//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("allFieldOfficers.bin"))) {
//            ArrayList<FieldOfficer> data = (ArrayList<FieldOfficer>) ois.readObject();
//            officers.addAll(data);
//        } catch (Exception e) {
//            Helper.showAlert("File Error", "Could not load credentials file");
//            return;
//        }

        for (FieldOfficer officer: officers) {
            if (officer.getOfficerId().equals(userId) && officer.getPassword().equals(password)) {
                User.officerId = officer.getOfficerId();
                User.fullName = officer.getFullName();
                User.email = officer.getEmail();
                User.userType = "BADCFieldOfficer";


                FXMLLoader loader = new FXMLLoader(BADCApplication.class.getResource("saad/U4BFODashboardView.fxml"));
                Scene scene = new Scene(loader.load());

                U4BFODashboardController controller = loader.getController();
                controller.passBFODashboard(officer);



                Helper.setScene(actionEvent, scene);
                return;
            }
        }

        Helper.showAlert("Error", "Invalid credentials");
        clearFields();
    }

    private void loginBADCJobApplicant(String userId, String password, ActionEvent actionEvent) throws IOException {
        ArrayList<BADCJobApplicant> applicants = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("BADCJobApplicant.bin"))) {
            ArrayList<BADCJobApplicant> data = (ArrayList<BADCJobApplicant>) ois.readObject();
            applicants.addAll(data);
        } catch (Exception e) {
            Helper.showAlert("File Error", "Could not load credentials file");
            return;
        }

        for (BADCJobApplicant applicant: applicants) {
            if (applicant.getApplicantId().equals(userId) && applicant.getPassword().equals(password)) {
                User.applicantId = applicant.getApplicantId();
                User.fullName = applicant.getFullName();
                User.email = applicant.getEmail();
                User.userType = "BADCJobApplicant";

                FXMLLoader loader = new FXMLLoader(BADCApplication.class.getResource("rhythm/U7_BADCJobApplicantDashboardView.fxml"));
                Scene scene = new Scene(loader.load());
                Helper.setScene(actionEvent, scene);
                return;
            }
        }

        Helper.showAlert("Error", "Invalid credentials");
        clearFields();
    }

    private void loginDevelopmentPartner(String userId, String password, ActionEvent actionEvent) throws IOException {
        ArrayList<DevelopmentPartner> partners = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("developmentPartner.bin"))) {
            ArrayList<DevelopmentPartner> data = (ArrayList<DevelopmentPartner>) ois.readObject();
            partners.addAll(data);
        } catch (Exception e) {
            Helper.showAlert("File Error", "Could not load credentials file");
            return;
        }

        for (DevelopmentPartner partner: partners) {
            if (partner.getPartnerId().equals(userId) && partner.getPassword().equals(password)) {
                User.applicantId = partner.getPartnerId();
                User.fullName = partner.getFullName();
                User.email = partner.getEmail();
                User.userType = "DevelopmentPartner";

                FXMLLoader loader = new FXMLLoader(BADCApplication.class.getResource("rhythm/U8_DevelopmentPartnerDashboardView.fxml"));
                Scene scene = new Scene(loader.load());
                Helper.setScene(actionEvent, scene);
                return;
            }
        }

        Helper.showAlert("Error", "Invalid credentials");
        clearFields();
    }

    private void clearFields() {
        userIdTextField.setText("");
        passwordField.setText("");
    }
}
