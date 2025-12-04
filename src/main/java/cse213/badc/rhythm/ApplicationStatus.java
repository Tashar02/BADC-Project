package cse213.badc.rhythm;

import java.io.Serializable;

public class ApplicationStatus implements Serializable {
    private String applicationId;
    private String status;

    public ApplicationStatus(String applicationId, String status) {
        this.applicationId = applicationId;
        this.status = status;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "ApplicationStatus{" +
                "applicationId='" + applicationId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
