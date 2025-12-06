package cse213.badc.rhythm;

import java.io.Serializable;
import java.time.LocalDate;

public class Tender implements Serializable {
    private String tenderId, title, details, region;
    private LocalDate publishDate;

    public Tender(String tenderId, String title, String details, String region) {
        this.tenderId = tenderId;
        this.title = title;
        this.details = details;
        this.region = region;
        this.publishDate = LocalDate.now();
    }

    public String getTenderId() {
        return tenderId;
    }

    public void setTenderId(String tenderId) {
        this.tenderId = tenderId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return "Tender{" +
                "tenderId='" + tenderId + '\'' +
                ", title='" + title + '\'' +
                ", details='" + details + '\'' +
                ", region='" + region + '\'' +
                ", publishDate=" + publishDate +
                '}';
    }
}
