package com.example.app;

import com.example.controller.QuantityMeasurementController;
import com.example.dto.QuantityDTO;
import com.example.repository.QuantityMeasurementCacheRepository;
import com.example.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        QuantityMeasurementController controller = new QuantityMeasurementController(new QuantityMeasurementServiceImpl(QuantityMeasurementCacheRepository.getInstance()));

        QuantityDTO q1 = new QuantityDTO(1,"FEET","LENGTH");
        QuantityDTO q2 = new QuantityDTO(12,"INCHES","LENGTH");

        controller.compare(q1,q2);
        controller.add(q1,q2);
        controller.convert(q1,"INCHES");
    }
}
