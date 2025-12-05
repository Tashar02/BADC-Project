package cse213.badc.rhythm;

import java.time.LocalDate;

public interface PolicyReader {
    String getPolicyContent();
    LocalDate getLastUpdated();
    boolean isActive();
}
