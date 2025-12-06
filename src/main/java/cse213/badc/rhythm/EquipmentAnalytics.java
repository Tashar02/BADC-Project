package cse213.badc.rhythm;

import java.time.LocalDate;

public class EquipmentAnalytics extends Analytics {
    private String pumpId;
    private String location;
    private float uptimePercentage;
    private String status;

    public EquipmentAnalytics(String pumpId, String location, float uptimePercentage, String region) {
        super(pumpId, region, LocalDate.now());
        this.pumpId = pumpId;
        this.location = location;
        this.uptimePercentage = uptimePercentage;
        this.status = getStatusFromUptime(uptimePercentage);
    }

    private String getStatusFromUptime(float uptime) {
        if (uptime >= 90) {
            return "Excellent";
        } else if (uptime >= 80) {
            return "Good";
        } else {
            return "Needs Attention";
        }
    }

    public String getPumpId() {
        return pumpId;
    }

    public String getLocation() {
        return location;
    }

    public float getUptimePercentage() {
        return uptimePercentage;
    }

    @Override
    public float getPerformanceScore() {
        return uptimePercentage;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return pumpId + " | " + location + " | " + String.format("%.1f%%", uptimePercentage);
    }
}
