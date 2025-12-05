package cse213.badc.rhythm;

public class SeedTrial extends PerformanceReport {
    private String seedName;
    private String cropType;
    private float testArea;
    private float averageYield;
    private float germinationRate;
    private String remarks;

    public SeedTrial(String trialId, String seedName, String cropType, float testArea, float averageYield, float germinationRate) {
        super(trialId, seedName, averageYield);
        this.seedName = seedName;
        this.cropType = cropType;
        this.testArea = testArea;
        this.averageYield = averageYield;
        this.germinationRate = germinationRate;
        this.remarks = "";
    }

    public String getTrialId() {
        return reportId;
    }

    public String getSeedName() {
        return seedName;
    }

    public void setSeedName(String seedName) {
        this.seedName = seedName;
    }

    public String getCropType() {
        return cropType;
    }

    public void setCropType(String cropType) {
        this.cropType = cropType;
    }

    public float getTestArea() {
        return testArea;
    }

    public void setTestArea(float testArea) {
        this.testArea = testArea;
    }

    public float getAverageYield() {
        return averageYield;
    }

    public void setAverageYield(float averageYield) {
        this.averageYield = averageYield;
    }

    public float getGerminationRate() {
        return germinationRate;
    }

    public void setGerminationRate(float germinationRate) {
        this.germinationRate = germinationRate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public boolean isValidPerformance() {
        return averageYield > 0 && germinationRate > 0 && germinationRate <= 100;
    }

    @Override
    public String getPerformanceCategory() {
        return cropType;
    }

    @Override
    public float getPerformanceScore() {
        return (averageYield / 10000f) * 50 + (germinationRate / 100f) * 50;
    }

    @Override
    public String toString() {
        return "SeedTrial{" +
                "trialId='" + reportId + '\'' +
                ", seedName='" + seedName + '\'' +
                ", cropType='" + cropType + '\'' +
                ", testArea=" + testArea +
                ", averageYield=" + averageYield +
                ", germinationRate=" + germinationRate +
                '}';
    }
}
