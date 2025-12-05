package cse213.badc.rhythm;

import java.io.Serializable;

public class NotificationPreference implements Serializable {
    private String departments;
    private String jobLevel;
    private String email;
    private String phone;
    private String notificationMethod;
    private String updatedDate;

    public NotificationPreference(String departments, String jobLevel, String email, String phone, String notificationMethod, String updatedDate) {
        this.departments = departments;
        this.jobLevel = jobLevel;
        this.email = email;
        this.phone = phone;
        this.notificationMethod = notificationMethod;
        this.updatedDate = updatedDate;
    }

    public String getDepartments() {
        return departments;
    }

    public String getJobLevel() {
        return jobLevel;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getNotificationMethod() {
        return notificationMethod;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    @Override
    public String toString() {
        return "NotificationPreference{" +
                "departments='" + departments + '\'' +
                ", jobLevel='" + jobLevel + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", notificationMethod='" + notificationMethod + '\'' +
                ", updatedDate='" + updatedDate + '\'' +
                '}';
    }
}
