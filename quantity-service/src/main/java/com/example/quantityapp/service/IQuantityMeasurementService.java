package com.example.quantityapp.service;

import com.example.quantityapp.dto.QuantityDTO;
import com.example.quantityapp.model.QuantityMeasurementEntity;

import java.util.List;

public interface IQuantityMeasurementService {
    QuantityDTO add(QuantityDTO q1, QuantityDTO q2);
    boolean compare(QuantityDTO q1, QuantityDTO q2);
    QuantityDTO convert(QuantityDTO q, String targetUnit);
    List<QuantityMeasurementEntity> getAll();
    QuantityMeasurementEntity getById(Long id);
    void delete(Long id);
    QuantityMeasurementEntity update(Long id, QuantityMeasurementEntity entity);
}
