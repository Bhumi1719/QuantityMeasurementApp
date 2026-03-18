package com.example.QuantityApp.app;

import com.example.QuantityApp.controller.QuantityMeasurementController;
import com.example.QuantityApp.dto.QuantityDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuantityMeasurementApp {

    private static final Logger logger =
            LoggerFactory.getLogger(QuantityMeasurementApp.class);

    public static void main(String[] args) {

        logger.info("Starting Quantity Measurement Application");
        logger.info("Using DATABASE repository");

        QuantityMeasurementController controller =
                new QuantityMeasurementController();

        // SAMPLE TEST RUN (UC16)

        QuantityDTO q1 = new QuantityDTO(1, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(12, "INCHES", "LENGTH");

        controller.compare(q1, q2);
        controller.add(q1, q2);
        controller.convert(q1, "INCHES");
    }
}
