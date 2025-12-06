package cse213.badc.saad;

import java.io.Serializable;
import java.time.LocalDate;

public class FarmerApplication implements Serializable {
    private String applicationId, applicantId, status, applicationRemark, applicantLocation, applicantMobileNo, details;
    private LocalDate applicationDate;
    private boolean assigned;

    public FarmerApplication(String applicationId, String applicantId, String applicantLocation, String applicantMobileNo, String details) {
        this.applicationId = applicationId;
        this.applicantId = applicantId;
        this.status = "Pending";
        this.applicationRemark = "N/A";
        this.applicationDate = LocalDate.now();
        this.assigned = false;
        this.applicantLocation = applicantLocation;
        this.applicantMobileNo = applicantMobileNo;
        this.details = details;

    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApplicationRemark() {
        return applicationRemark;
    }

    public void setApplicationRemark(String applicationRemark) {
        this.applicationRemark = applicationRemark;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getApplicantLocation() {
        return applicantLocation;
    }

    public void setApplicantLocation(String applicantLocation) {
        this.applicantLocation = applicantLocation;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    public String getApplicantMobileNo() {
        return applicantMobileNo;
    }

    public void setApplicantMobileNo(String applicantMobileNo) {
        this.applicantMobileNo = applicantMobileNo;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "FarmerApplication{" +
                "applicationId='" + applicationId + '\'' +
                ", applicantId='" + applicantId + '\'' +
                ", status='" + status + '\'' +
                ", applicationRemark='" + applicationRemark + '\'' +
                ", applicantLocation='" + applicantLocation + '\'' +
                ", applicantMobileNo='" + applicantMobileNo + '\'' +
                ", details='" + details + '\'' +
                ", applicationDate=" + applicationDate +
                ", assigned=" + assigned +
                '}';
    }
}
