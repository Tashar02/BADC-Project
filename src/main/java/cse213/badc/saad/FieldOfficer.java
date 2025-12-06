package cse213.badc.saad;

import java.io.Serializable;

public class FieldOfficer implements Serializable {

    private String officerId, officerRegion, password;


    public FieldOfficer(String officerId, String officerRegion, String password) {
        this.officerId = officerId;
        this.officerRegion = officerRegion;
        this.password = password;
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
