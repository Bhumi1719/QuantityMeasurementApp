package com.example.QuantityApp.service;

import com.example.QuantityApp.dto.QuantityDTO;

public interface IQuantityMeasurementService {

    boolean compare(QuantityDTO q1, QuantityDTO q2);

    QuantityDTO add(QuantityDTO q1, QuantityDTO q2);

    QuantityDTO convert(QuantityDTO q, String targetUnit);
}
