package com.taim.taimbackendservice.model.enums;

public enum TransactionType{
    QUOTATION("Quotation"),
    INVOICE("Invoice"),
    INBOUND("Inbound Order"),
    RETURN("Return");

    TransactionType(String vvalue){
        this.value = vvalue;
    }

    private String value;

    public String getValue() {
        return value;
    }
}