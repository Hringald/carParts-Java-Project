package com.carParts.model.entity;

public enum CategoryEnum {

    MAINPARTS("Main Parts"),
    ELECTRONICS("Electronics"),
    INTERIOR("Interior"),
    POWERTRAINANDCHASSIS("Power-train and chassis"),
    MISCELLANEOUS("Miscellaneous");

    private final String value;

    private CategoryEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
