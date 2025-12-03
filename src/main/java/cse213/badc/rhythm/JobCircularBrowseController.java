package cse213.badc.rhythm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import java.io.FileWriter;
import java.time.LocalDate;

public class JobCircularBrowseController {
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
    private TextArea expResponsibilitiesTextArea;
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
    @FXML
    private Label statusLabel;

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
        circularList.add(new Circular("C001", "Senior Officer", "Administration", "31-12-2025",
                "Graduate with 3 years experience", 5, 500, 35));
        circularList.add(new Circular("C002", "Research Officer", "Research", "15-01-2026",
                "Masters in relevant field", 3, 400, 32));
        circularList.add(new Circular("C003", "Junior Officer", "Seed Division", "28-02-2026",
                "Bachelor's degree in Agriculture", 10, 300, 28));

        educationList = new ArrayList<>();
        experienceList = new ArrayList<>();

        loadCircularsToTable(circularList);
    }

    private void loadCircularsToTable(ArrayList<Circular> circulars) {
        circularTableView.getItems().clear();
        circularTableView.getItems().addAll(circulars);
    }

    @FXML
    public void searchByTitleOA(ActionEvent actionEvent) {
        String searchText = searchTitleTextField.getText().toLowerCase();
        ArrayList<Circular> filtered = filterCircularsByTitle(searchText);
        loadCircularsToTable(filtered);
    }

    private ArrayList<Circular> filterCircularsByTitle(String searchText) {
        ArrayList<Circular> filtered = new ArrayList<>();
        for (Circular c: circularList) {
            if (c.getTitle().toLowerCase().contains(searchText)) {
                filtered.add(c);
            }
        }
        return filtered;
    }

    @FXML
    public void viewDetailsOA(ActionEvent actionEvent) {
        Circular selected = circularTableView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert("Select Circular", "Please select a circular first");
            return;
        }

        selectedCircular = selected;
        displayCircularDetails(selected);
    }

    private void displayCircularDetails(Circular circular) {
        String details = "Circular ID: " + circular.getCircularId() + "\n" +
                "Title: " + circular.getTitle() + "\n" +
                "Department: " + circular.getDepartment() + "\n" +
                "Deadline: " + circular.getDeadline() + "\n" +
                "Eligibility: " + circular.getEligibility() + "\n" +
                "Vacancies: " + circular.getVacancies() + "\n" +
                "Application Fee: " + circular.getApplicationFee() + "\n" +
                "Age Limit: " + circular.getAgeLimit();
        detailsTextArea.setText(details);
    }

    @FXML
    public void addEducationOA(ActionEvent actionEvent) {
        String degree = eduDegreeTextField.getText();
        String institution = eduInstitutionTextField.getText();
        String yearStr = eduYearTextField.getText();
        String gpaStr = eduGpaTextField.getText();

        if (validateEducationInput(degree, institution, yearStr, gpaStr)) {
            int year = Integer.parseInt(yearStr);
            float gpa = Float.parseFloat(gpaStr);

            Education education = new Education(degree, institution, year, gpa);

            if (education.validateEducation()) {
                educationList.add(education);
                educationTableView.getItems().add(education);
                clearEducationFields();
                statusLabel.setText("Education added successfully");
            } else {
                showAlert("Validation Error", "Invalid education details");
            }
        } else {
            showAlert("Input Error", "Please fill all education fields correctly");
        }
    }

    private boolean validateEducationInput(String degree, String institution, String year, String gpa) {
        if (degree.isEmpty() || institution.isEmpty() || year.isEmpty() || gpa.isEmpty()) {
            return false;
        }

        try {
            Integer.parseInt(year);
            Float.parseFloat(gpa);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void clearEducationFields() {
        eduDegreeTextField.clear();
        eduInstitutionTextField.clear();
        eduYearTextField.clear();
        eduGpaTextField.clear();
    }

    @FXML
    public void removeEducationOA(ActionEvent actionEvent) {
        Education selected = educationTableView.getSelectionModel().getSelectedItem();

        if (selected != null) {
            educationList.remove(selected);
            educationTableView.getItems().remove(selected);
            statusLabel.setText("Education entry removed");
        } else {
            showAlert("Select Entry", "Please select an education entry to remove");
        }
    }

    @FXML
    public void addExperienceOA(ActionEvent actionEvent) {
        String employer = expEmployerTextField.getText();
        String jobTitle = expJobTitleTextField.getText();
        String startYearStr = expStartYearTextField.getText();
        String endYearStr = expEndYearTextField.getText();
        String responsibilities = expResponsibilitiesTextArea.getText();

        if (validateExperienceInput(employer, jobTitle, startYearStr, endYearStr, responsibilities)) {
            int startYear = Integer.parseInt(startYearStr);
            int endYear = Integer.parseInt(endYearStr);

            WorkExperience experience = new WorkExperience(employer, jobTitle, startYear, endYear, responsibilities);

            if (experience.validateExperience()) {
                experienceList.add(experience);
                experienceTableView.getItems().add(experience);
                clearExperienceFields();
                statusLabel.setText("Work experience added successfully");
            } else {
                showAlert("Validation Error", "Invalid work experience details");
            }
        } else {
            showAlert("Input Error", "Please fill all experience fields correctly");
        }
    }

    private boolean validateExperienceInput(String employer, String jobTitle, String startYear, String endYear, String responsibilities) {
        if (employer.isEmpty() || jobTitle.isEmpty() || startYear.isEmpty() || endYear.isEmpty() || responsibilities.isEmpty()) {
            return false;
        }

        try {
            Integer.parseInt(startYear);
            Integer.parseInt(endYear);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void clearExperienceFields() {
        expEmployerTextField.clear();
        expJobTitleTextField.clear();
        expStartYearTextField.clear();
        expEndYearTextField.clear();
        expResponsibilitiesTextArea.clear();
    }

    @FXML
    public void removeExperienceOA(ActionEvent actionEvent) {
        WorkExperience selected = experienceTableView.getSelectionModel().getSelectedItem();

        if (selected != null) {
            experienceList.remove(selected);
            experienceTableView.getItems().remove(selected);
            statusLabel.setText("Work experience entry removed");
        } else {
            showAlert("Select Entry", "Please select a work experience entry to remove");
        }
    }

    @FXML
    public void submitApplicationOA(ActionEvent actionEvent) {
        if (selectedCircular == null) {
            showAlert("No Circular Selected", "Please select a circular first");
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
            saveApplicationToFile(application);
            statusLabel.setText("Application Submitted! ID: " + application.getApplicationId());
            clearFormOA(actionEvent);
        } else {
            showAlert("Validation Error", "Application form is incomplete or has errors");
        }
    }

    private void saveApplicationToFile(JobApplication application) {
        try {
            FileWriter writer = new FileWriter("applications.txt", true);
            writer.write(application.toString() + "\n");
            writer.close();
        } catch (Exception e) {
            showAlert("File Error", "Failed to save application");
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
        clearEducationFields();
        clearExperienceFields();
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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
