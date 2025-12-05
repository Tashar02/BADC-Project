package cse213.badc.saad;

import java.time.LocalDate;

public class Pump {
    private String equipmentId, model, pumpType, supplierId;
    private int power, rentPrice, purchasePrice;
    private LocalDate dateOfUpload;
    private boolean free;


    public Pump(String equipmentId, String model, String pumpType, String supplierId, int power, int rentPrice, int purchasePrice) {
        this.equipmentId = equipmentId;
        this.model = model;
        this.pumpType = pumpType;
        this.supplierId = supplierId;
        this.power = power;
        this.rentPrice = rentPrice;
        this.purchasePrice = purchasePrice;
        this.dateOfUpload = LocalDate.now();
        this.free = true;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPumpType() {
        return pumpType;
    }

    public void setPumpType(String pumpType) {
        this.pumpType = pumpType;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(int rentPrice) {
        this.rentPrice = rentPrice;
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public LocalDate getDateOfUpload() {
        return dateOfUpload;
    }

    public void setDateOfUpload(LocalDate dateOfUpload) {
        this.dateOfUpload = dateOfUpload;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public String toString() {
        return "Pump{" +
                "equipmentId='" + equipmentId + '\'' +
                ", model='" + model + '\'' +
                ", pumpType='" + pumpType + '\'' +
                ", power=" + power +
                ", rentPrice=" + rentPrice +
                ", purchasePrice=" + purchasePrice +
                ", dateOfUpload=" + dateOfUpload +
                ", free=" + free +
                '}';
    }
}