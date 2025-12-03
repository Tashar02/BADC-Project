package cse213.badc.rhythm;

import java.io.Serializable;

public class HelpdeskTicket implements Serializable {
    private String ticketId;
    private String subject;
    private String category;
    private String description;
    private String status;
    private String submissionDate;
    private String lastUpdated;
    private String resolution;

    public HelpdeskTicket(String ticketId, String subject, String category, String description,
                          String status, String submissionDate, String lastUpdated, String resolution) {
        this.ticketId = ticketId;
        this.subject = subject;
        this.category = category;
        this.description = description;
        this.status = status;
        this.submissionDate = submissionDate;
        this.lastUpdated = lastUpdated;
        this.resolution = resolution;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    @Override
    public String toString() {
        return "HelpdeskTicket{" +
                "ticketId='" + ticketId + '\'' +
                ", subject='" + subject + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", submissionDate='" + submissionDate + '\'' +
                ", lastUpdated='" + lastUpdated + '\'' +
                ", resolution='" + resolution + '\'' +
                '}';
    }
}
