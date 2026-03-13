package com.example.QuantityApp;

enum StandaloneLengthUnit {

	 FEET(1.0),
	 INCHES(1.0 / 12.0),
	 YARDS(3.0),
	 CENTIMETERS(1.0 / 30.48);
	
	 private final double toFeetFactor;
	
	 StandaloneLengthUnit(double toFeetFactor) {
	     this.toFeetFactor = toFeetFactor;
	 }
	
	 // Convert value in this unit to base unit (FEET)
	 public double convertToBaseUnit(double value) {
	     return value * toFeetFactor;
	 }
	
	 // Convert value from base unit (FEET) to this unit
	 public double convertFromBaseUnit(double baseValue) {
	     return baseValue / toFeetFactor;
	 }
	
	 public double getConversionFactor() {
	     return toFeetFactor;
	 }
}

public class StandaloneUnit {

	 private final double value;
	 private final StandaloneLengthUnit unit;
	 private static final double EPSILON = 0.0001;
	
	 public StandaloneUnit(double value, StandaloneLengthUnit unit) {
		 
	     if(unit == null || !Double.isFinite(value)) {
	         throw new IllegalArgumentException("Invalid value or unit");
	     }
	     
	     this.value = value;
	     this.unit = unit;
	 }
	
	 public double getValue() {
	     return value;
	 }
	
	 public StandaloneLengthUnit getUnit() {
	     return unit;
	 }

	 public StandaloneUnit convertTo(StandaloneLengthUnit targetUnit) {
		 
	     if(targetUnit == null) {
	         throw new IllegalArgumentException("Target unit cannot be null");
	     }
	
	     double base = unit.convertToBaseUnit(value);
	     double converted = targetUnit.convertFromBaseUnit(base);
	
	     return new StandaloneUnit(converted, targetUnit);
	 }

	 public StandaloneUnit add(StandaloneUnit other) {
	     if(other == null) {
	         throw new IllegalArgumentException("Second operand cannot be null");
	     }
	     return add(this, other, this.unit);
	 }

	 public static StandaloneUnit add(StandaloneUnit q1, StandaloneUnit q2, StandaloneLengthUnit targetUnit) {
	
	     if(q1 == null || q2 == null || targetUnit == null) {
	         throw new IllegalArgumentException("Operands and target unit must not be null");
	     }
	
	     double base1 = q1.unit.convertToBaseUnit(q1.value);
	     double base2 = q2.unit.convertToBaseUnit(q2.value);
	
	     double sumBase = base1 + base2;
	
	     double result = targetUnit.convertFromBaseUnit(sumBase);
	
	     return new StandaloneUnit(result, targetUnit);
	 }

	 @Override
	 public boolean equals(Object obj) {
	     if(!(obj instanceof StandaloneUnit)) {
	    	 return false;
	     }
	
	     StandaloneUnit other = (StandaloneUnit) obj;
	
	     double thisBase = this.unit.convertToBaseUnit(this.value);
	     double otherBase = other.unit.convertToBaseUnit(other.value);
	
	     return Math.abs(thisBase - otherBase) < EPSILON;
	 }
	
	 @Override
	 public String toString() {
	     return "Quantity(" + value + ", " + unit + ")";
	 }
}
