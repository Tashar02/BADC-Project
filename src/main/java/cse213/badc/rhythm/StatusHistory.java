package cse213.badc.rhythm;

import java.io.Serializable;
import java.util.ArrayList;

public class StatusHistory implements Serializable {
    private String applicationId;
    private ArrayList<StatusUpdate> updates;

    public StatusHistory(String applicationId) {
        this.applicationId = applicationId;
        this.updates = new ArrayList<>();
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public ArrayList<StatusUpdate> getUpdates() {
        return updates;
    }

    public void setUpdates(ArrayList<StatusUpdate> updates) {
        this.updates = updates;
    }

    public void addUpdate(StatusUpdate update) {
        this.updates.add(update);
    }

    public StatusUpdate getLatestUpdate() {
        if (updates.isEmpty()) {
            return null;
        }
        StatusUpdate latest = null;
        for (StatusUpdate update: updates) {
            latest = update;
        }
        return latest;
    }


    @Override
    public String toString() {
        return "StatusHistory{" +
                "applicationId='" + applicationId + '\'' +
                ", updates=" + updates +
                '}';
    }
}
