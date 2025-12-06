package cse213.badc.rhythm;

import java.time.LocalDate;

public class ApplicationAnalytics extends Analytics {
    private String applicationId;
    private String farmerName;
    private String status;

    public ApplicationAnalytics(String applicationId, String farmerName, String status, String region) {
        super(applicationId, region, LocalDate.now());
        this.applicationId = applicationId;
        this.farmerName = farmerName;
        this.status = status;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public String getFarmerName() {
        return farmerName;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public float getPerformanceScore() {
        if (status.equals("Approved")) {
            return 100;
        } else if (status.equals("Pending")) {
            return 50;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return applicationId + " | " + farmerName + " | " + status;
    }
}
