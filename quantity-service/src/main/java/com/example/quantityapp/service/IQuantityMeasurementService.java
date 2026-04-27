package com.example.quantityapp.service;

import com.example.quantityapp.dto.QuantityDTO;
import com.example.quantityapp.model.QuantityMeasurementEntity;

import java.util.List;

public interface IQuantityMeasurementService {
    QuantityDTO add(QuantityDTO q1, QuantityDTO q2, String username);
    boolean compare(QuantityDTO q1, QuantityDTO q2, String username);
    QuantityDTO convert(QuantityDTO q, String targetUnit, String username);
    QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2, String username);
    QuantityDTO multiply(QuantityDTO q1, QuantityDTO q2, String username);
    QuantityDTO divide(QuantityDTO q1, QuantityDTO q2, String username);
    List<QuantityMeasurementEntity> getAll(String username);
    QuantityMeasurementEntity getById(Long id);
    void delete(Long id);
    QuantityMeasurementEntity update(Long id, QuantityMeasurementEntity entity);
}
