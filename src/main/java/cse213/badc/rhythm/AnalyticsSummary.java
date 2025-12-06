package cse213.badc.rhythm;

import java.util.ArrayList;

public interface AnalyticsSummary {
    float getAveragePerformance();
    int getTotalCount();
    int getApprovedCount();
    String getTopPerformingRegion();
    ArrayList<String> generateReport();
}
