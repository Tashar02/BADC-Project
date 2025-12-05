package cse213.badc.rhythm;

import java.io.Serializable;

public class ApprovedApplication implements Serializable {
    private String applicationId;
    private String applicantName;
    private String nationalId;
    private String circular;
    private String rollNumber;
    private String examDate;
    private String examTime;
    private String status;

    public ApprovedApplication(String applicationId, String applicantName, String nationalId, String circular, String rollNumber, String examDate, String examTime, String status) {
        this.applicationId = applicationId;
        this.applicantName = applicantName;
        this.nationalId = nationalId;
        this.circular = circular;
        this.rollNumber = rollNumber;
        this.examDate = examDate;
        this.examTime = examTime;
        this.status = status;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public String getNationalId() {
        return nationalId;
    }

    public String getCircular() {
        return circular;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getExamDate() {
        return examDate;
    }

    public String getExamTime() {
        return examTime;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "ApprovedApplication{" +
                "applicationId='" + applicationId + '\'' +
                ", applicantName='" + applicantName + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", circular='" + circular + '\'' +
                ", rollNumber='" + rollNumber + '\'' +
                ", examDate='" + examDate + '\'' +
                ", examTime='" + examTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
