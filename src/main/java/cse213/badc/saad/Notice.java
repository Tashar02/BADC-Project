package cse213.badc.saad;

import java.io.Serializable;
import java.time.LocalDate;

public class Notice implements Serializable {
    private String noticeID,  noticeDetails, noticeTitle, region;
    private LocalDate noticeDate;

    public Notice(String noticeID, String noticeDetails, String noticeTitle, String region) {
        this.noticeID = noticeID;
        this.noticeDate = LocalDate.now();
        this.noticeDetails = noticeDetails;
        this.noticeTitle = noticeTitle;
        this.region = region;
    }

    public String getNoticeID() {
        return noticeID;
    }

    public void setNoticeID(String noticeID) {
        this.noticeID = noticeID;
    }

    public LocalDate getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(LocalDate noticeDate) {
        this.noticeDate = noticeDate;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getNoticeDetails() {
        return noticeDetails;
    }

    public void setNoticeDetails(String noticeDetails) {
        this.noticeDetails = noticeDetails;
    }

    public String getMonth(){
        return this.noticeDate.getMonth().toString();
    }

    @Override
    public String toString() {
        return "Notice{" +
                "noticeID='" + noticeID + '\'' +
                ", noticeDetails='" + noticeDetails + '\'' +
                ", noticeTitle='" + noticeTitle + '\'' +
                ", region='" + region + '\'' +
                ", noticeDate=" + noticeDate +
                '}';
    }
}
