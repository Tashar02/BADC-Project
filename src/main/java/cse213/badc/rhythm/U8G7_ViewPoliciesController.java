package cse213.badc.rhythm;

import cse213.badc.Helper;
import javafx.event.ActionEvent;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class U8G7_ViewPoliciesController {
    @javafx.fxml.FXML
    private TableView<PolicyDocument> policiesTableView;
    @javafx.fxml.FXML
    private TableColumn<PolicyDocument, String> policyIdTC;
    @javafx.fxml.FXML
    private TableColumn<PolicyDocument, String> titleTC;
    @javafx.fxml.FXML
    private TableColumn<PolicyDocument, String> descriptionTC;
    @javafx.fxml.FXML
    private TableColumn<PolicyDocument, LocalDate> issueDateTC;
    @javafx.fxml.FXML
    private TableColumn<PolicyDocument, String> statusTC;
    @javafx.fxml.FXML
    private Label totalPoliciesLabel;

    private ArrayList<PolicyDocument> policies;

    @javafx.fxml.FXML
    public void initialize() {
        policyIdTC.setCellValueFactory(new PropertyValueFactory<>("policyId"));
        titleTC.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionTC.setCellValueFactory(new PropertyValueFactory<>("description"));
        issueDateTC.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
        statusTC.setCellValueFactory(new PropertyValueFactory<>("status"));

        policies = new ArrayList<>();
        loadPolicies();
        refreshTable();
    }

    private void loadPolicies() {
        try {
            File file = new File("policies.bin");

            if (!file.exists()) {
                addSampleData();
                return;
            }

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

            while (true) {
                try {
                    PolicyDocument policy = (PolicyDocument) ois.readObject();
                    if (policy.isActive()) {
                        policies.add(policy);
                    }
                } catch (Exception e) {
                    break;
                }
            }

            ois.close();
        } catch (Exception e) {
            addSampleData();
        }
    }

    private void addSampleData() {
        policies.add(new PolicyDocument("POL-2025-001", "Development Partner Code of Conduct", "Guidelines for ethical business practices and regulatory compliance", LocalDate.of(2025, 1, 10)));
        policies.add(new PolicyDocument("POL-2025-002", "Project Proposal Requirements", "Standards and requirements for submitting development proposals", LocalDate.of(2025, 1, 15)));
        policies.add(new PolicyDocument("POL-2025-003", "Financial Reporting Standards", "Requirements for financial documentation and audit compliance", LocalDate.of(2025, 2, 1)));
        policies.add(new PolicyDocument("POL-2025-004", "Environmental Guidelines", "Environmental protection and sustainability requirements", LocalDate.of(2025, 2, 10)));
        policies.add(new PolicyDocument("POL-2025-005", "Data Privacy and Security", "Data handling and cybersecurity requirements for development partners", LocalDate.of(2025, 2, 20)));
    }

    private void refreshTable() {
        policiesTableView.getItems().clear();
        for (PolicyDocument policy: policies) {
            policiesTableView.getItems().add(policy);
        }

        totalPoliciesLabel.setText(Integer.toString(policies.size()));
    }

    @javafx.fxml.FXML
    public void backToDashboardOA(ActionEvent actionEvent) throws IOException {
        Helper.backToDashboardU8(actionEvent);
    }
}
