package com.example.repository;

import com.example.model.QuantityMeasurementEntity;
import java.util.List;

public interface IQuantityMeasurementRepository {

    void save(QuantityMeasurementEntity entity);
    List<QuantityMeasurementEntity> findAll();
}
