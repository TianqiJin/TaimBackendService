package com.taim.taimbackendservice.model.enums;

public enum DeliveryStatus{
    UNDELIVERED("Undelivered"),
    DELIVERING("Delivering"),
    DELIVERED("Delivered");

    private String value;

    DeliveryStatus(String vvalue){
        this.value = vvalue;
    }

    public String getValue() {
        return value;
    }

    public static DeliveryStatus getStatus(String value) {
        for (DeliveryStatus ps : DeliveryStatus.values()){
            if (value.equalsIgnoreCase(ps.name())){
                return ps;
            }
        }
        return null;
    }
}
