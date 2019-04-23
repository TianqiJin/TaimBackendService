package com.taim.taimbackendservice.model.enums;

public enum Position{
    SALES("Sales"), MANAGER("Manager"), DISTRIBUTOR("Distributor");

    private String pos;

    Position(String pos){
        this.pos = pos;
    }

    public static Position getPosition(String pos){
        for (Position ppos : Position.values()){
            if (ppos.name().equalsIgnoreCase(pos)){
                return ppos;
            }
        }
        return null;
    }

    public String getValue() {
        return pos;
    }
}
