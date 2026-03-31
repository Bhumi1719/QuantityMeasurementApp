package com.example.QuantityApp;

public class VolumeMeasurement {
	public static void main(String[] args) {

        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> v3 = new Quantity<>(1.0, VolumeUnit.GALLON);

        System.out.println("Equality:");
        System.out.println(v1 + " == " + v2 + " : " + v1.equals(v2));

        System.out.println("\nConversion:");
        System.out.println(v1 + " -> " + v1.convertTo(VolumeUnit.MILLILITRE));
        System.out.println(v3 + " -> " + v3.convertTo(VolumeUnit.LITRE));

        System.out.println("\nAddition:");
        System.out.println(v1 + " + " + v2 + " = " + v1.add(v2));
        System.out.println(v1 + " + " + v3 + " (in mL) = " + v1.add(v3, VolumeUnit.MILLILITRE));
    }
}
