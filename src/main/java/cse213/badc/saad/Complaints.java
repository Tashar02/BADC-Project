package cse213.badc.saad;

import java.io.Serializable;
import java.time.LocalDate;

public class Complaints implements Serializable {
    private String complaintId, farmerId, accusedId, details;
    private LocalDate dateOfComplaint;
    private String status, verificationRemark;
    private boolean varified;

    public Complaints(String complaintId, String farmerId, String accusedId, String details) {
        this.complaintId = complaintId;
        this.farmerId = farmerId;
        this.accusedId = accusedId;
        this.details = details;
        this.dateOfComplaint = LocalDate.now();
        this.status = "Pending";
        this.varified = false;
    }

    public String getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(String complaintId) {
        this.complaintId = complaintId;
    }

    public String getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(String farmerId) {
        this.farmerId = farmerId;
    }

    public String getAccusedId() {
        return accusedId;
    }

    public void setAccusedId(String accusedId) {
        this.accusedId = accusedId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDate getDateOfComplaint() {
        return dateOfComplaint;
    }

    public void setDateOfComplaint(LocalDate dateOfComplaint) {
        this.dateOfComplaint = dateOfComplaint;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVerificationRemark() {
        return verificationRemark;
    }

    public void setVerificationRemark(String verificationRemark) {
        this.verificationRemark = verificationRemark;
    }

    public boolean isVarified() {
        return varified;
    }

    public void setVarified(boolean varified) {
        this.varified = varified;
    }

    @Override
    public String toString() {
        return "Complaints{" +
                "complaintId='" + complaintId + '\'' +
                ", farmerId='" + farmerId + '\'' +
                ", accusedId='" + accusedId + '\'' +
                ", details='" + details + '\'' +
                ", dateOfComplaint=" + dateOfComplaint +
                ", status='" + status + '\'' +
                ", verificationRemark='" + verificationRemark + '\'' +
                ", varified=" + varified +
                '}';
    }
}
