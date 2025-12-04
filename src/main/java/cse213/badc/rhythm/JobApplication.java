package cse213.badc.rhythm;

import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.Period;

public class JobApplication implements Serializable {
    private String applicationId;
    private String circularId;
    private String fullName;
    private String dateOfBirth;
    private String contactNumber;
    private String email;
    private String presentAddress;
    private String nationalId;
    private ArrayList<Education> educationList;
    private ArrayList<WorkExperience> experienceList;
    private String submissionDate;
    private String status;

    public JobApplication(String applicationId, String circularId, String fullName, String dateOfBirth,
                          String contactNumber, String email, String presentAddress, String nationalId,
                          ArrayList<Education> educationList, ArrayList<WorkExperience> experienceList,
                          String submissionDate, String status) {
        this.applicationId = applicationId;
        this.circularId = circularId;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.contactNumber = contactNumber;
        this.email = email;
        this.presentAddress = presentAddress;
        this.nationalId = nationalId;
        this.educationList = educationList;
        this.experienceList = experienceList;
        this.submissionDate = submissionDate;
        this.status = status;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getCircularId() {
        return circularId;
    }

    public void setCircularId(String circularId) {
        this.circularId = circularId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(String presentAddress) {
        this.presentAddress = presentAddress;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public ArrayList<Education> getEducationList() {
        return educationList;
    }

    public void setEducationList(ArrayList<Education> educationList) {
        this.educationList = educationList;
    }

    public ArrayList<WorkExperience> getExperienceList() {
        return experienceList;
    }

    public void setExperienceList(ArrayList<WorkExperience> experienceList) {
        this.experienceList = experienceList;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private boolean isAgeValid() {
        try {
            LocalDate birthDate = LocalDate.parse(dateOfBirth);
            LocalDate today = LocalDate.now();
            Period period = Period.between(birthDate, today);

            return period.getYears() >= 18;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validatePersonalInfo() {
        if (fullName == null || fullName.isEmpty() || fullName.length() > 100) {
            System.out.println("Full name is empty");
            return false;
        }
        if (!isAgeValid()) {
            System.out.println("Age is invalid");
            return false;
        }
        if (contactNumber.startsWith("01") || contactNumber.startsWith("+8801")) {
            if (contactNumber.length() != 11) {
                System.out.println("Contact number is invalid");
                return false;
            }
        }
        if (email != null && !email.isEmpty() && !email.contains("@")) {
            System.out.println("Email is invalid");
            return false;
        }
        if (presentAddress == null || presentAddress.isEmpty() || presentAddress.length() > 200) {
            System.out.println("Address is invalid");
            return false;
        }
        if (nationalId.length() != 10 && nationalId.length() != 13) {
            System.out.println("NID is invalid");
            return false;
        }
        return true;
    }

    public boolean isFormComplete() {
        if (!validatePersonalInfo()) {
            System.out.println("Personal info is invalid");
            return false;
        }
        if (educationList == null || educationList.isEmpty()) {
            System.out.println("Education list is empty");
            return false;
        }
        for (Education edu: educationList) {
            if (!edu.validateEducation()) {
                System.out.println("Education list is invalid");
                return false;
            }
        }
        if (experienceList != null) {
            for (WorkExperience exp: experienceList) {
                if (!exp.validateExperience()) {
                    System.out.println("Experience is invalid");
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "JobApplication{" +
                "presentAddress='" + presentAddress + '\'' +
                ", applicationId='" + applicationId + '\'' +
                ", circularId='" + circularId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", email='" + email + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", educationList=" + educationList +
                ", experienceList=" + experienceList +
                ", submissionDate='" + submissionDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
