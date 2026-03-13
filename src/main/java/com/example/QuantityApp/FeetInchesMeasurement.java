package com.example.QuantityApp;

public class FeetInchesMeasurement {

    public static boolean checkFeetEquality(double a, double b) {
        return Math.abs(a - b) < 0.0001;
    }

    public static boolean checkInchesEquality(double a, double b) {
        return Math.abs(a - b) < 0.0001;
    }

    public static class Feet {

        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;
            if (obj == null) return false;
            if (!(obj instanceof Feet)) return false;

            Feet other = (Feet) obj;

            return Math.abs(this.value - other.value) < 0.0001;
        }
    }

    public static class Inches {

        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;
            if (obj == null) return false;
            if (!(obj instanceof Inches)) return false;

            Inches other = (Inches) obj;

            return Math.abs(this.value - other.value) < 0.0001;
        }
    }
}
