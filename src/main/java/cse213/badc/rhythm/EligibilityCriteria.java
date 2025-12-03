package cse213.badc.rhythm;

import java.io.Serializable;

public class EligibilityCriteria implements Serializable {
    private String circularId;
    private String ageMin;
    private String ageMax;
    private String qualifications;
    private String experience;
    private String applicationFee;

    public EligibilityCriteria(String circularId, String ageMin, String ageMax, String qualifications, String experience, String applicationFee) {
        this.circularId = circularId;
        this.ageMin = ageMin;
        this.ageMax = ageMax;
        this.qualifications = qualifications;
        this.experience = experience;
        this.applicationFee = applicationFee;
    }

    public String getCircularId() {
        return circularId;
    }

    public void setCircularId(String circularId) {
        this.circularId = circularId;
    }

    public String getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(String ageMin) {
        this.ageMin = ageMin;
    }

    public String getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(String ageMax) {
        this.ageMax = ageMax;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getApplicationFee() {
        return applicationFee;
    }

    public void setApplicationFee(String applicationFee) {
        this.applicationFee = applicationFee;
    }

    @Override
    public String toString() {
        return "EligibilityCriteria{" +
                "circularId='" + circularId + '\'' +
                ", ageMin='" + ageMin + '\'' +
                ", ageMax='" + ageMax + '\'' +
                ", qualifications='" + qualifications + '\'' +
                ", experience='" + experience + '\'' +
                ", applicationFee='" + applicationFee + '\'' +
                '}';
    }
}
