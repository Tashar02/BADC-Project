package cse213.badc.saad;

import java.io.Serializable;

public class FieldOfficer implements Serializable {

    private String officerId, officerRegion, password, fullName, email;


    public FieldOfficer(String officerId, String officerRegion, String password) {
        this.officerId = officerId;
        this.officerRegion = officerRegion;
        this.password = password;
    }

    public FieldOfficer(String supplierID, String password, String fullName, String email) {
        this.officerId = supplierID;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getOfficerId() {
        return officerId;
    }

    public void setOfficerId(String officerId) {
        this.officerId = officerId;
    }

    public String getOfficerRegion() {
        return officerRegion;
    }

    public void setOfficerRegion(String officerRegion) {
        this.officerRegion = officerRegion;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
