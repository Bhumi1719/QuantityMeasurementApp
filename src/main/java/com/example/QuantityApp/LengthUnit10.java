package com.example.QuantityApp;

public enum LengthUnit10 implements IMeasurable {

    FEET(12),
    INCHES(1),
    YARD(36);

    private final double factor;

    LengthUnit10(double factor) {
        this.factor = factor;
    }

    public double getConversionFactor() {
        return factor;
    }

    public String getUnitName() {
        return name();
    }
}
