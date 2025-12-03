package cse213.badc.rhythm;

import java.io.Serializable;

public class ApplicationStatus implements Serializable {
    private String applicationId;
    private String circularId;
    private String applicantName;
    private String submissionDate;
    private String status;
    private String remarks;

    public ApplicationStatus(String applicationId, String circularId, String applicantName, String submissionDate, String status, String remarks) {
        this.applicationId = applicationId;
        this.circularId = circularId;
        this.applicantName = applicantName;
        this.submissionDate = submissionDate;
        this.status = status;
        this.remarks = remarks;
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

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "ApplicationStatus{" +
                "applicationId='" + applicationId + '\'' +
                ", circularId='" + circularId + '\'' +
                ", applicantName='" + applicantName + '\'' +
                ", submissionDate='" + submissionDate + '\'' +
                ", status='" + status + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
