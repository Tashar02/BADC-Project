package cse213.badc.rhythm;

import java.io.Serializable;

public abstract class PerformanceReport implements Serializable {
    protected String reportId;
    protected String name;
    protected float value;

    public PerformanceReport(String reportId, String name, float value) {
        this.reportId = reportId;
        this.name = name;
        this.value = value;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public abstract boolean isValidPerformance();

    public abstract String getPerformanceCategory();

    public abstract float getPerformanceScore();
}
