package cse213.badc.rhythm;

import java.io.Serializable;

public class WorkExperience implements Serializable {
    private String employer;
    private String jobTitle;
    private int startYear;
    private int endYear;
    private String responsibilities;

    public WorkExperience(String employer, String jobTitle, int startYear, int endYear, String responsibilities) {
        this.employer = employer;
        this.jobTitle = jobTitle;
        this.startYear = startYear;
        this.endYear = endYear;
        this.responsibilities = responsibilities;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public boolean validateExperience() {
        if (employer == null || employer.isEmpty() || employer.length() > 150) {
            return false;
        }
        if (jobTitle == null || jobTitle.isEmpty() || jobTitle.length() > 100) {
            return false;
        }
        if (startYear < 1900 || startYear > 2025) {
            return false;
        }
        if (endYear < 1900 || endYear > 2025) {
            return false;
        }
        if (startYear > endYear) {
            return false;
        }
        if (responsibilities == null || responsibilities.length() > 500) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WorkExperience{" +
                "employer='" + employer + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", startYear=" + startYear +
                ", endYear=" + endYear +
                ", responsibilities='" + responsibilities + '\'' +
                '}';
    }
}
