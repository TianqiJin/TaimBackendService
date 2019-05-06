package com.taim.taimbackendservice.model.enums;

public enum TransactionStatus {
    CREATED_NOT_SUBMITTED("Non Submitted"),
    SUBMITTED("Submitted");

    private String value;

    TransactionStatus(String vvalue){
        this.value = vvalue;
    }

    public String getValue() {
        return value;
    }

    public static TransactionStatus getStatus(String value){
        for (TransactionStatus ts : TransactionStatus.values()){
            if (value.equalsIgnoreCase(ts.name())){
                return ts;
            }
        }
        return null;
    }
}
