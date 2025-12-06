package cse213.badc.rhythm;

import java.io.Serializable;

public class BADCJobApplicant implements Serializable {
    private String applicantId;
    private String password;
    private String fullName;
    private String email;

    public BADCJobApplicant(String applicantId, String password, String fullName, String email) {
        this.applicantId = applicantId;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }
}
