package cse213.badc.rhythm;

import java.io.Serializable;

public class DevelopmentPartner implements Serializable {
    private String partnerId;
    private String password;
    private String fullName;
    private String email;

    public DevelopmentPartner(String partnerId, String password, String fullName, String email) {
        this.partnerId = partnerId;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
    }

    public String getPartnerId() {
        return partnerId;
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
