package cse213.badc.rhythm;

import java.io.Serializable;

public class Circular implements Serializable {
    private String circularId;
    private String title;
    private String department;
    private String deadline;
    private String eligibility;
    private int vacancies;
    private int applicationFee;
    private int ageLimit;

    public Circular(String circularId, String title, String department, String deadline,
                    String eligibility, int vacancies, int applicationFee, int ageLimit) {
        this.circularId = circularId;
        this.title = title;
        this.department = department;
        this.deadline = deadline;
        this.eligibility = eligibility;
        this.vacancies = vacancies;
        this.applicationFee = applicationFee;
        this.ageLimit = ageLimit;
    }

    public String getCircularId() {
        return circularId;
    }

    public void setCircularId(String circularId) {
        this.circularId = circularId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public int getVacancies() {
        return vacancies;
    }

    public void setVacancies(int vacancies) {
        this.vacancies = vacancies;
    }

    public int getApplicationFee() {
        return applicationFee;
    }

    public void setApplicationFee(int applicationFee) {
        this.applicationFee = applicationFee;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    @Override
    public String toString() {
        return "Circular{" +
                "circularId='" + circularId + '\'' +
                ", title='" + title + '\'' +
                ", department='" + department + '\'' +
                ", deadline='" + deadline + '\'' +
                ", eligibility='" + eligibility + '\'' +
                ", vacancies=" + vacancies +
                ", applicationFee=" + applicationFee +
                ", ageLimit=" + ageLimit +
                '}';
    }
}
