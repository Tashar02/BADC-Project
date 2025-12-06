package cse213.badc.rhythm;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Analytics implements Serializable {
    protected String id;
    protected String region;
    protected LocalDate lastUpdated;

    public Analytics(String id, String region, LocalDate lastUpdated) {
        this.id = id;
        this.region = region;
        this.lastUpdated = lastUpdated;
    }

    public String getId() {
        return id;
    }

    public String getRegion() {
        return region;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public abstract double getPerformanceScore();
    public abstract String getStatus();
}
