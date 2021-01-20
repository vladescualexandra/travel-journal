package com.example.travel_journal.model;

public enum Type {
    CITY_BREAK ("City Break"),
    SEA_SIDE ("Sea Side"),
    MOUNTAINS ("Mountains");

    private String type;

    Type(String type) {
        this.type = type;
    }
}
