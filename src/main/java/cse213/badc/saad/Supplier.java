package cse213.badc.saad;

import java.io.Serializable;
import java.time.LocalDate;

public class Supplier implements Serializable {
    private String supplierID, name, mobileNo, email, companyName, serviceArea, status, verificationRemark, password;
    private long tradeLicenseNo;
    private boolean assigned, verified, applied;
    private LocalDate dateOfRegistration;
    private String fullName;

    public Supplier(String supplierID, String name, String mobileNo, String email, String password) {
        this.supplierID = supplierID;
        this.name = name;
        this.mobileNo = mobileNo;
        this.email = email;
        this.companyName = "N/A";
        this.serviceArea = "N/A";
        this.tradeLicenseNo = 0;
        this.status = "N/A";
        this.dateOfRegistration = null;
        this.assigned = false;
        this.verified = false;
        this.applied = false;
        this.verificationRemark = "N/A";
        this.password = password;
    }

    public Supplier(String supplierID, String password, String fullName, String email) {
        this.supplierID = supplierID;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }
    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getServiceArea() {
        return serviceArea;
    }

    public void setServiceArea(String serviceArea) {
        this.serviceArea = serviceArea;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTradeLicenseNo() {
        return tradeLicenseNo;
    }

    public void setTradeLicenseNo(long tradeLicenseNo) {
        this.tradeLicenseNo = tradeLicenseNo;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(LocalDate dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public boolean isApplied() {
        return applied;
    }

    public void setApplied(boolean applied) {
        this.applied = applied;
    }

    public String getVerificationRemark() {
        return verificationRemark;
    }

    public void setVerificationRemark(String verificationRemark) {
        this.verificationRemark = verificationRemark;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierID='" + supplierID + '\'' +
                ", name='" + name + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", email='" + email + '\'' +
                ", companyName='" + companyName + '\'' +
                ", serviceArea='" + serviceArea + '\'' +
                ", status='" + status + '\'' +
                ", verificationRemark='" + verificationRemark + '\'' +
                ", tradeLicenseNo=" + tradeLicenseNo +
                ", assigned=" + assigned +
                ", verified=" + verified +
                ", applied=" + applied +
                ", dateOfRegistration=" + dateOfRegistration +
                '}';
    }
}


