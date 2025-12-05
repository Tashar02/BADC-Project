package cse213.badc.rhythm;

import cse213.badc.saad.Report;
import java.time.LocalDate;

public class FertilizerImport extends Report {
    private String fertilizerType;
    private float quantity;
    private float cost;
    private LocalDate importDate;

    public FertilizerImport(String importId, String authorId, String fertilizerType, float quantity, float cost, LocalDate importDate) {
        super(importId, authorId, "Fertilizer Import: " + fertilizerType);
        this.fertilizerType = fertilizerType;
        this.quantity = quantity;
        this.cost = cost;
        this.importDate = importDate;
    }

    public String getFertilizerType() {
        return fertilizerType;
    }

    public void setFertilizerType(String fertilizerType) {
        this.fertilizerType = fertilizerType;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public LocalDate getImportDate() {
        return importDate;
    }

    public void setImportDate(LocalDate importDate) {
        this.importDate = importDate;
    }

    @Override
    public String toString() {
        return "FertilizerImport{" +
                "importId='" + reportId + '\'' +
                ", fertilizerType='" + fertilizerType + '\'' +
                ", quantity=" + quantity +
                ", cost=" + cost +
                ", importDate=" + importDate +
                ", status='" + status + '\'' +
                '}';
    }
}
