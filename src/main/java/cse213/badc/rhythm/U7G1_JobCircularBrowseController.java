package cse213.badc.rhythm;

import cse213.badc.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import java.time.LocalDate;

public class U7G1_JobCircularBrowseController {
    @FXML
    private TextField searchTitleTextField;
    @FXML
    private TableView<Circular> circularTableView;
    @FXML
    private TableColumn<Circular, String> circularIdTC;
    @FXML
    private TableColumn<Circular, String> titleTC;
    @FXML
    private TableColumn<Circular, String> departmentTC;
    @FXML
    private TableColumn<Circular, String> deadlineTC;
    @FXML
    private TextArea detailsTextArea;
    @FXML
    private TextField fullNameTextField;
    @FXML
    private DatePicker dobDatePicker;
    @FXML
    private TextField contactTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextArea addressTextArea;
    @FXML
    private TextField nidTextField;
    @FXML
    private TextField eduDegreeTextField;
    @FXML
    private TextField eduInstitutionTextField;
    @FXML
    private TextField eduYearTextField;
    @FXML
    private TextField eduGpaTextField;
    @FXML
    private TableView<Education> educationTableView;
    @FXML
    private TableColumn<Education, String> degreeTC;
    @FXML
    private TableColumn<Education, String> institutionTC;
    @FXML
    private TableColumn<Education, Integer> yearTC;
    @FXML
    private TableColumn<Education, Float> gpaTC;
    @FXML
    private TextField expEmployerTextField;
    @FXML
    private TextField expJobTitleTextField;
    @FXML
    private TextField expStartYearTextField;
    @FXML
    private TextField expEndYearTextField;
    @FXML
    private TableView<WorkExperience> experienceTableView;
    @FXML
    private TableColumn<WorkExperience, String> employerTC;
    @FXML
    private TableColumn<WorkExperience, String> jobTitleTC;
    @FXML
    private TableColumn<WorkExperience, Integer> startYearTC;
    @FXML
    private TableColumn<WorkExperience, Integer> endYearTC;

    private ArrayList<Circular> circularList;
    private ArrayList<Education> educationList;
    private ArrayList<WorkExperience> experienceList;
    private Circular selectedCircular;

    @FXML
    public void initialize() {
        circularIdTC.setCellValueFactory(new PropertyValueFactory<>("circularId"));
        titleTC.setCellValueFactory(new PropertyValueFactory<>("title"));
        departmentTC.setCellValueFactory(new PropertyValueFactory<>("department"));
        deadlineTC.setCellValueFactory(new PropertyValueFactory<>("deadline"));

        degreeTC.setCellValueFactory(new PropertyValueFactory<>("degree"));
        institutionTC.setCellValueFactory(new PropertyValueFactory<>("institution"));
        yearTC.setCellValueFactory(new PropertyValueFactory<>("passingYear"));
        gpaTC.setCellValueFactory(new PropertyValueFactory<>("gpa"));

        employerTC.setCellValueFactory(new PropertyValueFactory<>("employer"));
        jobTitleTC.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));
        startYearTC.setCellValueFactory(new PropertyValueFactory<>("startYear"));
        endYearTC.setCellValueFactory(new PropertyValueFactory<>("endYear"));

        circularList = new ArrayList<>();

        /* The circulars need to come from User-6 Goal-1 , this is sample data provided */
        circularList.add(new Circular("C001", "Senior Officer", "Administration", "31-12-2025",
                "Graduate with 3 years experience", 5, 500, 35));
        circularList.add(new Circular("C002", "Research Officer", "Research", "15-01-2026",
                "Masters in relevant field", 3, 400, 32));
        circularList.add(new Circular("C003", "Junior Officer", "Seed Division", "28-02-2026",
                "Bachelor's degree in Agriculture", 10, 300, 28));

        educationList = new ArrayList<>();
        experienceList = new ArrayList<>();

        circularTableView.getItems().addAll(circularList);
    }

    @FXML
    public void searchByTitleOA(ActionEvent actionEvent) {
        String searchText = searchTitleTextField.getText().toLowerCase();
        ArrayList<Circular> filtered = new ArrayList<>();
        for (Circular c: circularList) {
            if (c.getTitle().toLowerCase().contains(searchText)) {
                filtered.add(c);
            }
        }
        circularTableView.getItems().clear();
        circularTableView.getItems().addAll(filtered);
    }

    @FXML
    public void viewDetailsOA(ActionEvent actionEvent) {
        /* Selection procedure was referenced from getSelectionModel: https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TableView.html#getSelectionModel-- */
        Circular selected = circularTableView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            Helper.showAlert("Select Circular", "Please select a circular first");
            return;
        }

        selectedCircular = selected;
        String details = "Circular ID: " + selected.getCircularId() + "\n" +
                "Title: " + selected.getTitle() + "\n" +
                "Department: " + selected.getDepartment() + "\n" +
                "Deadline: " + selected.getDeadline() + "\n" +
                "Eligibility: " + selected.getEligibility() + "\n" +
                "Vacancies: " + selected.getVacancies() + "\n" +
                "Application Fee: " + selected.getApplicationFee() + "\n" +
                "Age Limit: " + selected.getAgeLimit();
        detailsTextArea.setText(details);
    }

    @FXML
    public void addEducationOA(ActionEvent actionEvent) {
        String degree = eduDegreeTextField.getText();
        String institution = eduInstitutionTextField.getText();
        String yearStr = eduYearTextField.getText();
        String gpaStr = eduGpaTextField.getText();

        if (degree.isEmpty() || institution.isEmpty() || yearStr.isEmpty() || gpaStr.isEmpty()) {
            Helper.showAlert("Input Error", "Please fill all education fields");
            return;
        }

        try {
            int year = Integer.parseInt(yearStr);
            float gpa = Float.parseFloat(gpaStr);

            Education education = new Education(degree, institution, year, gpa);

            if (education.validateEducation()) {
                educationList.add(education);
                educationTableView.getItems().add(education);
                eduDegreeTextField.clear();
                eduInstitutionTextField.clear();
                eduYearTextField.clear();
                eduGpaTextField.clear();
                Helper.showAlert("Success", "Education added successfully");
            } else {
                Helper.showAlert("Validation Error", "Invalid education details");
            }
        } catch (Exception e) {
            Helper.showAlert("Input Error", "Please provide correct input data");
        }
    }

    @FXML
    public void removeEducationOA(ActionEvent actionEvent) {
        Education selected = educationTableView.getSelectionModel().getSelectedItem();

        if (selected != null) {
            educationList.remove(selected);
            educationTableView.getItems().remove(selected);
            Helper.showAlert("Success", "Education entry removed");
        } else {
            Helper.showAlert("Select Entry", "Please select an education entry to remove");
        }
    }

    @FXML
    public void addExperienceOA(ActionEvent actionEvent) {
        String employer = expEmployerTextField.getText();
        String jobTitle = expJobTitleTextField.getText();
        String startYearStr = expStartYearTextField.getText();
        String endYearStr = expEndYearTextField.getText();

        if (employer.isEmpty() || jobTitle.isEmpty() || startYearStr.isEmpty() || endYearStr.isEmpty()) {
            Helper.showAlert("Input Error", "Please fill all experience fields");
            return;
        }

        try {
            int startYear = Integer.parseInt(startYearStr);
            int endYear = Integer.parseInt(endYearStr);

            WorkExperience experience = new WorkExperience(employer, jobTitle, startYear, endYear);

            if (experience.validateExperience()) {
                experienceList.add(experience);
                experienceTableView.getItems().add(experience);
                expEmployerTextField.clear();
                expJobTitleTextField.clear();
                expStartYearTextField.clear();
                expEndYearTextField.clear();
                Helper.showAlert("Success", "Work experience added successfully");
            } else {
                Helper.showAlert("Validation Error", "Invalid work experience details");
            }
        } catch (Exception e) {
            Helper.showAlert("Input Error", "Please enter valid data");
        }
    }

    @FXML
    public void removeExperienceOA(ActionEvent actionEvent) {
        WorkExperience selected = experienceTableView.getSelectionModel().getSelectedItem();

        if (selected != null) {
            experienceList.remove(selected);
            experienceTableView.getItems().remove(selected);
            Helper.showAlert("Success", "Work experience entry removed");
        } else {
            Helper.showAlert("Select Entry", "Please select a work experience entry to remove");
        }
    }

    @FXML
    public void submitApplicationOA(ActionEvent actionEvent) {
        if (selectedCircular == null) {
            Helper.showAlert("No Circular Selected", "Please select a circular first");
            return;
        }

        String fullName = fullNameTextField.getText();
        String dob = dobDatePicker.getValue() != null ? dobDatePicker.getValue().toString() : "";
        String contact = contactTextField.getText();
        String email = emailTextField.getText();
        String address = addressTextArea.getText();
        String nid = nidTextField.getText();

        JobApplication application = new JobApplication(
                generateApplicationId(),
                selectedCircular.getCircularId(),
                fullName,
                dob,
                contact,
                email,
                address,
                nid,
                educationList,
                experienceList,
                getCurrentDate(),
                "Submitted"
        );

        if (application.isFormComplete()) {
            if (Helper.appendTextFile("applications.txt", application.toString())) {
                Helper.showAlert("Success", "Application Submitted! ID: " + application.getApplicationId());
                clearFormOA(actionEvent);
            }
        } else {
            Helper.showAlert("Validation Error", "Application form is incomplete or has errors");
        }
    }

    @FXML
    public void clearFormOA(ActionEvent actionEvent) {
        fullNameTextField.clear();
        dobDatePicker.setValue(null);
        contactTextField.clear();
        emailTextField.clear();
        addressTextArea.clear();
        nidTextField.clear();
        eduDegreeTextField.clear();
        eduInstitutionTextField.clear();
        eduYearTextField.clear();
        eduGpaTextField.clear();
        expEmployerTextField.clear();
        expJobTitleTextField.clear();
        expStartYearTextField.clear();
        expEndYearTextField.clear();
        educationList.clear();
        experienceList.clear();
        educationTableView.getItems().clear();
        experienceTableView.getItems().clear();
        selectedCircular = null;
        detailsTextArea.clear();
    }

    private String generateApplicationId() {
        return "APP" + System.currentTimeMillis();
    }

    private String getCurrentDate() {
        LocalDate today = LocalDate.now();
        return String.format("%02d-%02d-%04d", today.getDayOfMonth(), today.getMonthValue(), today.getYear());
    }
}
