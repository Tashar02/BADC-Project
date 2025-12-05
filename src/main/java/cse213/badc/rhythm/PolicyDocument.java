package cse213.badc.rhythm;

import java.io.Serializable;
import java.time.LocalDate;

public class PolicyDocument implements Serializable, PolicyReader {
    private String policyId;
    private String title;
    private String description;
    private LocalDate issueDate;
    private LocalDate lastUpdated;
    private String status;

    public PolicyDocument(String policyId, String title, String description, LocalDate issueDate) {
        this.policyId = policyId;
        this.title = title;
        this.description = description;
        this.issueDate = issueDate;
        this.lastUpdated = issueDate;
        this.status = "Published";
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getPolicyContent() {
        return description;
    }

    @Override
    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    @Override
    public boolean isActive() {
        return status.equals("Published");
    }

    @Override
    public String toString() {
        return "PolicyDocument{" +
                "policyId='" + policyId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", issueDate=" + issueDate +
                ", status='" + status + '\'' +
                '}';
    }
}
