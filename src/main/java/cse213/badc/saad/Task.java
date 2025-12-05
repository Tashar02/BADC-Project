package com.example.mainproject.saad;

import java.time.LocalDate;

public class Task {
    private String taskId;
    private String assignedSupplier, applicantId, applicantMobileNo, details;
    private boolean reported;
    private LocalDate taskDate;


    public Task(String taskId, String assignedSupplier, String applicantId, String applicantMobileNo, String details) {
        this.taskId = taskId;
        this.assignedSupplier = assignedSupplier;
        this.applicantId = applicantId;
        this.applicantMobileNo = applicantMobileNo;
        this.details = details;
        this.reported = false;
        this.taskDate = LocalDate.now();
    }


    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getAssignedSupplier() {
        return assignedSupplier;
    }

    public void setAssignedSupplier(String assignedSupplier) {
        this.assignedSupplier = assignedSupplier;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
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

    public boolean isReported() {
        return reported;
    }

    public void setReported(boolean reported) {
        this.reported = reported;
    }

    public LocalDate getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(LocalDate taskDate) {
        this.taskDate = taskDate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId='" + taskId + '\'' +
                ", assignedSupplier='" + assignedSupplier + '\'' +
                ", applicantId='" + applicantId + '\'' +
                ", applicantMobileNo='" + applicantMobileNo + '\'' +
                ", details='" + details + '\'' +
                ", reported=" + reported +
                ", taskDate=" + taskDate +
                '}';
    }
}
