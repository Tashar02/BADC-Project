package cse213.badc.rhythm;

import java.io.Serializable;

public class RecruitmentQuery implements Serializable {
    private String queryId;
    private String subject;
    private String circular;
    private String description;
    private String status;
    private String submittedDate;
    private String response;

    public RecruitmentQuery(String queryId, String subject, String circular, String description, String status, String submittedDate, String response) {
        this.queryId = queryId;
        this.subject = subject;
        this.circular = circular;
        this.description = description;
        this.status = status;
        this.submittedDate = submittedDate;
        this.response = response;
    }

    public String getQueryId() {
        return queryId;
    }

    public String getSubject() {
        return subject;
    }

    public String getCircular() {
        return circular;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getSubmittedDate() {
        return submittedDate;
    }

    public String getResponse() {
        return response;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "RecruitmentQuery{" +
                "queryId='" + queryId + '\'' +
                ", subject='" + subject + '\'' +
                ", circular='" + circular + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", submittedDate='" + submittedDate + '\'' +
                ", response='" + response + '\'' +
                '}';
    }
}
