package com.example.QuantityApp.model;

import java.io.Serializable;
import java.util.Date;

public class QuantityMeasurementEntity implements Serializable {

    private int id;
    private String operation;
    private String measurementType;
    private double value;
    private Date timestamp;

    public QuantityMeasurementEntity() {
    }

    public QuantityMeasurementEntity(String operation,
                                     String measurementType,
                                     double value,
                                     Date timestamp) {
        this.operation = operation;
        this.measurementType = measurementType;
        this.value = value;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}