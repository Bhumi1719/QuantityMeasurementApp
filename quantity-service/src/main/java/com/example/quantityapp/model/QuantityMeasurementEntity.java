package com.example.quantityapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "quantity_measurements")
public class QuantityMeasurementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;   // JWT se aaya user email/name — per-user history ke liye
    private String operation;
    private String result;

    public QuantityMeasurementEntity() {}

    public QuantityMeasurementEntity(Long id, String username, String operation, String result) {
        this.id        = id;
        this.username  = username;
        this.operation = operation;
        this.result    = result;
    }

    public Long getId()              { return id; }
    public String getUsername()      { return username; }
    public String getOperation()     { return operation; }
    public String getResult()        { return result; }
    public void setUsername(String u){ this.username = u; }
    public void setOperation(String operation) { this.operation = operation; }
    public void setResult(String result)       { this.result = result; }
}
