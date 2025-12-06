package cse213.badc.rhythm;

import java.util.ArrayList;

public class ApplicationApprovalSummary implements AnalyticsSummary {
    private ArrayList<ApplicationAnalytics> applicationList;

    public ApplicationApprovalSummary(ArrayList<ApplicationAnalytics> applicationList) {
        this.applicationList = applicationList;
    }

    @Override
    public float getAveragePerformance() {
        if (applicationList.isEmpty()) {
            return 0;
        }
        return (getApprovedCount() * 100.0f) / applicationList.size();
    }

    @Override
    public int getTotalCount() {
        return applicationList.size();
    }

    @Override
    public int getApprovedCount() {
        int count = 0;
        for (ApplicationAnalytics app: applicationList) {
            if (app.getStatus().equals("Approved")) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String getTopPerformingRegion() {
        return "See filtered results";
    }

    @Override
    public ArrayList<String> generateReport() {
        ArrayList<String> report = new ArrayList<>();
        report.add("=== Application Approval Summary ===");
        report.add("Total Applications: " + getTotalCount());
        report.add("Approved Applications: " + getApprovedCount());
        report.add("Approval Rate: " + String.format("%.1f%%", getAveragePerformance()));
        return report;
    }
}
