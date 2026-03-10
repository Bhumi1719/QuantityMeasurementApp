package com.example.QuantityApp;

public class SubtractionAndDivision {

    public static void main(String[] args) {

        System.out.println("------ SUBTRACTION ------");

        Quantity<LengthUnit10> q1 = new Quantity<>(10.0, LengthUnit10.FEET);
        Quantity<LengthUnit10> q2 = new Quantity<>(6.0, LengthUnit10.INCHES);

        System.out.println(q1.subtract(q2));
        System.out.println(q1.subtract(q2, LengthUnit10.INCHES));

        Quantity<WeightUnit10> w1 = new Quantity<>(10.0, WeightUnit10.KILOGRAM);
        Quantity<WeightUnit10> w2 = new Quantity<>(5000.0, WeightUnit10.GRAM);

        System.out.println(w1.subtract(w2));

        Quantity<VolumeUnit> v1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        System.out.println(v1.subtract(v2));

        System.out.println("\n------ DIVISION ------");

        System.out.println(q1.divide(new Quantity<>(2.0, LengthUnit10.FEET)));

        System.out.println(new Quantity<>(24.0, LengthUnit10.INCHES)
                        .divide(new Quantity<>(2.0, LengthUnit10.FEET)));

        System.out.println(new Quantity<>(10.0, WeightUnit10.KILOGRAM)
                        .divide(new Quantity<>(5.0, WeightUnit10.KILOGRAM)));

        System.out.println(new Quantity<>(5.0, VolumeUnit.LITRE)
                        .divide(new Quantity<>(10.0, VolumeUnit.LITRE)));
    }
}
