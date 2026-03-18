package com.example.QuantityApp;

public class CentralizedArithematicLogicOperations {

    public static void main(String[] args) {

        Quantity<LengthUnit10> q1 = new Quantity<>(10, LengthUnit10.FEET);
        Quantity<LengthUnit10> q2 = new Quantity<>(6, LengthUnit10.INCHES);

        System.out.println("Addition: " + q1.add(q2));
        System.out.println("Subtraction: " + q1.subtract(q2));
        System.out.println("Division: " + q1.divide(new Quantity<>(2, LengthUnit10.FEET)));

        Quantity<WeightUnit10> w1 = new Quantity<>(10, WeightUnit10.KILOGRAM);
        Quantity<WeightUnit10> w2 = new Quantity<>(5000, WeightUnit10.GRAM);

        System.out.println("Weight Addition: " + w1.add(w2));

        Quantity<VolumeUnit> v1 = new Quantity<>(5, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(500, VolumeUnit.MILLILITRE);

        System.out.println("Volume Subtraction: " + v1.subtract(v2));
    }
}
