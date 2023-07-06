package com.eren.carecommerce.model;


public enum NumberOfDoors {
    TWOTHREE("2/3"),
    FOURFIVE("4/5");

    private String label;

    NumberOfDoors(String label) {
        this.label = label;
    }

    public static NumberOfDoors valueOfLabel(String label) {
        for (NumberOfDoors e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }
}
