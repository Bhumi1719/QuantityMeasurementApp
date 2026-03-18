package com.example.QuantityApp.controller;

import com.example.QuantityApp.dto.QuantityDTO;
import com.example.QuantityApp.repository.QuantityMeasurementDatabaseRepository;
import com.example.QuantityApp.service.IQuantityMeasurementService;
import com.example.QuantityApp.service.QuantityMeasurementServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuantityMeasurementController {

    private static final Logger logger =
            LoggerFactory.getLogger(QuantityMeasurementController.class);

    private IQuantityMeasurementService service;

    public QuantityMeasurementController() {

        logger.info("Using DATABASE repository");

        this.service =
                new QuantityMeasurementServiceImpl(
                        new QuantityMeasurementDatabaseRepository());
    }

    public void compare(QuantityDTO a, QuantityDTO b) {

        boolean result = service.compare(a, b);
        System.out.println(result);
    }

    public void add(QuantityDTO a, QuantityDTO b) {

        QuantityDTO result = service.add(a, b);
        System.out.println(result.getValue() + " " + result.getUnit());
    }

    public void convert(QuantityDTO a, String target) {

        QuantityDTO result = service.convert(a, target);
        System.out.println(result.getValue() + " " + result.getUnit());
    }
}
