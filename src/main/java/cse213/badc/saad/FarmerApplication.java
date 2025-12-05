package com.example.mainproject.saad;

import java.time.LocalDate;

public class FarmerApplication {
    private String applicationId, applicantId, status, applicationRemark, applicantLocation, applicantMobileNo;
    private LocalDate applicationDate;
    private boolean assigned;

    public FarmerApplication(String applicationId, String applicantId, String applicantLocation, String applicantMobileNo) {
        this.applicationId = applicationId;
        this.applicantId = applicantId;
        this.status = "Pending";
        this.applicationRemark = "N/A";
        this.applicationDate = LocalDate.now();
        this.assigned = false;
        this.applicantLocation = applicantLocation;
        this.applicantMobileNo = applicantMobileNo;

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

    @Override
    public String toString() {
        return "FarmerApplication{" +
                "applicationId='" + applicationId + '\'' +
                ", applicantId='" + applicantId + '\'' +
                ", status='" + status + '\'' +
                ", applicationRemark='" + applicationRemark + '\'' +
                ", applicantLocation='" + applicantLocation + '\'' +
                ", applicantMobileNo='" + applicantMobileNo + '\'' +
                ", applicationDate=" + applicationDate +
                ", assigned=" + assigned +
                '}';
    }
}
