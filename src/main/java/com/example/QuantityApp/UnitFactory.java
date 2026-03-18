package com.example.QuantityApp;

public class UnitFactory {

    public static IMeasurable getUnit(String type, String unit) {

    	   unit = unit.toUpperCase();

    	    if(unit.equals("INCH"))
    	        unit = "INCHES";

    	    if(unit.equals("FOOT"))
    	        unit = "FEET";
    	    
        switch(type.toUpperCase()) {

            case "LENGTH":
                return LengthUnit10.valueOf(unit.toUpperCase());

            case "WEIGHT":
                return WeightUnit10.valueOf(unit.toUpperCase());

            case "VOLUME":
                return VolumeUnit.valueOf(unit.toUpperCase());

            case "TEMPERATURE":
                return TemperatureUnit.valueOf(unit.toUpperCase());

            default:
                throw new IllegalArgumentException("Invalid measurement type");
        }
    }
}
