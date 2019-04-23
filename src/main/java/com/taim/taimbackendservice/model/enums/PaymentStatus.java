package com.taim.taimbackendservice.model.enums;

public enum PaymentStatus{
    UNPAID("Unpaid"),
    PARTIALLY_PAID("Partially Paid"),
    PAID("Fully Paid");

    private String value;

    PaymentStatus(String vvalue){
        this.value = vvalue;
    }

    public String getValue() {
        return value;
    }

    public static PaymentStatus getStatus(String value){
        for (PaymentStatus ps : PaymentStatus.values()){
            if (value.equalsIgnoreCase(ps.name())){
                return ps;
            }
        }
        return null;
    }
}
