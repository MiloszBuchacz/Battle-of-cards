package com.codecool.card;

public enum StatsType {

    HEALTH("Health"),
    STRENGTH("Strength"),
    MAGIC("Magic");

    public final String label;

    private StatsType(String label) {
        this.label = label;
    }

}