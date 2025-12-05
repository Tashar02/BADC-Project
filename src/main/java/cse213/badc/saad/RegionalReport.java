package com.example.mainproject.saad;

public class RegionalReport extends Report{
    private int totalApplications, totalApprovedApplications;
    private int totalComplaints, totalApprovedComplaints;
    private int totalMaintenanceReports, totalApprovedMaintenanceReports;
    private String obs, chal;


    public RegionalReport(String reportId, String authorId, String summary, int totalApplications, int totalApprovedApplications, int totalComplaints, int totalApprovedComplaints, int totalMaintenanceReports, int totalApprovedMaintenanceReports, String obs, String chal) {
        super(reportId, authorId, summary);
        this.totalApplications = totalApplications;
        this.totalApprovedApplications = totalApprovedApplications;
        this.totalComplaints = totalComplaints;
        this.totalApprovedComplaints = totalApprovedComplaints;
        this.totalMaintenanceReports = totalMaintenanceReports;
        this.totalApprovedMaintenanceReports = totalApprovedMaintenanceReports;
        this.obs = obs;
        this.chal = chal;
    }

    public int getTotalApplications() {
        return totalApplications;
    }

    public void setTotalApplications(int totalApplications) {
        this.totalApplications = totalApplications;
    }

    public int getTotalApprovedApplications() {
        return totalApprovedApplications;
    }

    public void setTotalApprovedApplications(int totalApprovedApplications) {
        this.totalApprovedApplications = totalApprovedApplications;
    }

    public int getTotalComplaints() {
        return totalComplaints;
    }

    public void setTotalComplaints(int totalComplaints) {
        this.totalComplaints = totalComplaints;
    }

    public int getTotalApprovedComplaints() {
        return totalApprovedComplaints;
    }

    public void setTotalApprovedComplaints(int totalApprovedComplaints) {
        this.totalApprovedComplaints = totalApprovedComplaints;
    }

    public int getTotalMaintenanceReports() {
        return totalMaintenanceReports;
    }

    public void setTotalMaintenanceReports(int totalMaintenanceReports) {
        this.totalMaintenanceReports = totalMaintenanceReports;
    }

    public int getTotalApprovedMaintenanceReports() {
        return totalApprovedMaintenanceReports;
    }

    public void setTotalApprovedMaintenanceReports(int totalApprovedMaintenanceReports) {
        this.totalApprovedMaintenanceReports = totalApprovedMaintenanceReports;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getChal() {
        return chal;
    }

    public void setChal(String chal) {
        this.chal = chal;
    }

    @Override
    public String toString() {
        return "RegionalReport{" +
                "totalApplications=" + totalApplications +
                ", totalApprovedApplications=" + totalApprovedApplications +
                ", totalComplaints=" + totalComplaints +
                ", totalApprovedComplaints=" + totalApprovedComplaints +
                ", totalMaintenanceReports=" + totalMaintenanceReports +
                ", totalApprovedMaintenanceReports=" + totalApprovedMaintenanceReports +
                ", obs='" + obs + '\'' +
                ", chal='" + chal + '\'' +
                ", reportDate=" + reportDate +
                ", status='" + status + '\'' +
                ", summary='" + summary + '\'' +
                ", authorId='" + authorId + '\'' +
                ", reportId='" + reportId + '\'' +
                '}';
    }
}
