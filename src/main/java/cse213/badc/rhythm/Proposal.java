package cse213.badc.rhythm;

import cse213.badc.saad.Report;

public class Proposal extends Report {
    private String subject;
    private String projectDuration;
    private float budgetEstimate;

    public Proposal(String proposalId, String authorId, String subject, String summary, String projectDuration, float budgetEstimate) {
        super(proposalId, authorId, summary);
        this.subject = subject;
        this.projectDuration = projectDuration;
        this.budgetEstimate = budgetEstimate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getProjectDuration() {
        return projectDuration;
    }

    public void setProjectDuration(String projectDuration) {
        this.projectDuration = projectDuration;
    }

    public float getBudgetEstimate() {
        return budgetEstimate;
    }

    public void setBudgetEstimate(float budgetEstimate) {
        this.budgetEstimate = budgetEstimate;
    }

    @Override
    public String toString() {
        return "Proposal{" +
                "reportId='" + reportId + '\'' +
                ", authorId='" + authorId + '\'' +
                ", subject='" + subject + '\'' +
                ", summary='" + summary + '\'' +
                ", projectDuration='" + projectDuration + '\'' +
                ", budgetEstimate=" + budgetEstimate +
                ", status='" + status + '\'' +
                ", reportDate=" + reportDate +
                '}';
    }
}
