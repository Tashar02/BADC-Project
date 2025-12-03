package cse213.badc.rhythm;

import java.io.Serializable;

public class Education implements Serializable {
    private String degree;
    private String institution;
    private int passingYear;
    private float gpa;

    public Education(String degree, String institution, int passingYear, float gpa) {
        this.degree = degree;
        this.institution = institution;
        this.passingYear = passingYear;
        this.gpa = gpa;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public int getPassingYear() {
        return passingYear;
    }

    public void setPassingYear(int passingYear) {
        this.passingYear = passingYear;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public boolean validateEducation() {
        if (degree == null || degree.isEmpty() || degree.length() > 100) {
            return false;
        }
        if (institution == null || institution.isEmpty() || institution.length() > 150) {
            return false;
        }
        if (passingYear < 1900 || passingYear > 2025) {
            return false;
        }
        if (gpa < 0.0f || gpa > 5.0f) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Education{" +
                "degree='" + degree + '\'' +
                ", institution='" + institution + '\'' +
                ", passingYear=" + passingYear +
                ", gpa=" + gpa +
                '}';
    }
}
