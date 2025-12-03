package cse213.badc.rhythm;

import java.io.Serializable;

public class StatusUpdate implements Serializable {
    private String updateDate;
    private String status;
    private String description;

    public StatusUpdate(String updateDate, String status, String description) {
        this.updateDate = updateDate;
        this.status = status;
        this.description = description;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "StatusUpdate{" +
                "updateDate='" + updateDate + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
