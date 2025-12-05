package com.example.mainproject.saad;

import java.time.LocalDate;

public class MaintenanceReport extends Report{
    private String taskId, equipmentId, workOutcome, season, verificationRemark;
    private LocalDate workDate;
    private int cost, pumpRuntime;


    public MaintenanceReport(String reportId, String authorId, String summary, int pumpRuntime, int cost, LocalDate workDate, String season, String workOutcome, String equipmentId, String taskId) {
        super(reportId, authorId, summary);
        this.pumpRuntime = pumpRuntime;
        this.cost = cost;
        this.workDate = workDate;
        this.season = season;
        this.workOutcome = workOutcome;
        this.equipmentId = equipmentId;
        this.taskId = taskId;
        this.verificationRemark = "N/A";
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getWorkOutcome() {
        return workOutcome;
    }

    public void setWorkOutcome(String workOutcome) {
        this.workOutcome = workOutcome;
    }

    public LocalDate getWorkDate() {
        return workDate;
    }

    public void setWorkDate(LocalDate workDate) {
        this.workDate = workDate;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public int getPumpRuntime() {
        return pumpRuntime;
    }

    public void setPumpRuntime(int pumpRuntime) {
        this.pumpRuntime = pumpRuntime;
    }

    public String getVerificationRemark() {
        return verificationRemark;
    }

    public void setVerificationRemark(String verificationRemark) {
        this.verificationRemark = verificationRemark;
    }

    @Override
    public String toString() {
        return "MaintenanceReport{" +
                "taskId='" + taskId + '\'' +
                ", equipmentId='" + equipmentId + '\'' +
                ", workOutcome='" + workOutcome + '\'' +
                ", season='" + season + '\'' +
                ", verificationRemark='" + verificationRemark + '\'' +
                ", workDate=" + workDate +
                ", cost=" + cost +
                ", pumpRuntime=" + pumpRuntime +
                ", reportId='" + reportId + '\'' +
                ", authorId='" + authorId + '\'' +
                ", summary='" + summary + '\'' +
                ", status='" + status + '\'' +
                ", reportDate=" + reportDate +
                '}';
    }
}
