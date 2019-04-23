package com.taim.taimbackendservice.model.enums;

public enum PaymentType{
    CASH("Cash"),
    CREDIT("Credit"),
    DEBIT("Debit"),
    CHEQUE("Cheque"),
    VOID("Void"),
    STORE_CREDIT("Store Credit");

    private String value;
    PaymentType(String vvalue){
        this.value = vvalue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static PaymentType getValue(String value) {
        for (PaymentType p: PaymentType.values()){
            if (p.name().equalsIgnoreCase(value)){
                return p;
            }
        }
        return null;
    }
}
