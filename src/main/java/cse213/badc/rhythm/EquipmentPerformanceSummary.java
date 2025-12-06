package cse213.badc.rhythm;

import java.util.ArrayList;

public class EquipmentPerformanceSummary implements AnalyticsSummary {
    private ArrayList<EquipmentAnalytics> equipmentList;

    public EquipmentPerformanceSummary(ArrayList<EquipmentAnalytics> equipmentList) {
        this.equipmentList = equipmentList;
    }

    @Override
    public double getAveragePerformance() {
        if (equipmentList.isEmpty()) {
            return 0;
        }
        double total = 0;
        for (EquipmentAnalytics ea: equipmentList) {
            total += ea.getUptimePercentage();
        }
        return total / equipmentList.size();
    }

    @Override
    public int getTotalCount() {
        return equipmentList.size();
    }

    @Override
    public int getApprovedCount() {
        int count = 0;
        for (EquipmentAnalytics ea: equipmentList) {
            if (ea.getStatus().equals("Excellent") || ea.getStatus().equals("Good")) {
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
        report.add("=== Equipment Performance Summary ===");
        report.add("Total Equipment: " + getTotalCount());
        report.add("Good/Excellent Status: " + getApprovedCount());
        report.add("Average Uptime: " + String.format("%.1f%%", getAveragePerformance()));

        return report;
    }
}
