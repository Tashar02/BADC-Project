package cse213.badc.saad;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Report implements Serializable {
    protected String reportId, authorId, summary, status;
    protected LocalDate reportDate;

    public Report(String reportId, String authorId, String summary) {
        this.reportId = reportId;
        this.authorId = authorId;
        this.summary = summary;
        this.status = "Pending";
        this.reportDate = LocalDate.now();
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }


    @Override
    public String toString() {
        return "Report{" +
                "reportId='" + reportId + '\'' +
                ", authorId='" + authorId + '\'' +
                ", summary='" + summary + '\'' +
                ", status='" + status + '\'' +
                ", reportDate=" + reportDate +
                '}';
    }
}
