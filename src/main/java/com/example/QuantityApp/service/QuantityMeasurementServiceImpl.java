package com.example.QuantityApp.service;

import com.example.QuantityApp.dto.QuantityDTO;
import com.example.QuantityApp.exception.QuantityMeasurementException;
import com.example.QuantityApp.model.QuantityMeasurementEntity;
import com.example.QuantityApp.repository.IQuantityMeasurementRepository;
import com.example.QuantityApp.IMeasurable;
import com.example.QuantityApp.Quantity;
import com.example.QuantityApp.UnitFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class QuantityMeasurementServiceImpl
        implements IQuantityMeasurementService {

    private static final Logger logger =
            LoggerFactory.getLogger(QuantityMeasurementServiceImpl.class);

    private IQuantityMeasurementRepository repo;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repo) {
        this.repo = repo;
    }

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {

        Quantity<IMeasurable> a =
                new Quantity<>(q1.getValue(),
                        UnitFactory.getUnit(q1.getType(), q1.getUnit()));

        Quantity<IMeasurable> b =
                new Quantity<>(q2.getValue(),
                        UnitFactory.getUnit(q2.getType(), q2.getUnit()));

        boolean result = a.equals(b);

        saveHistory("COMPARE", a.toString());

        logger.info("Compared quantities: {} and {}", a, b);

        return result;
    }

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {

        try {
            Quantity<IMeasurable> a =
                    new Quantity<>(q1.getValue(),
                            UnitFactory.getUnit(q1.getType(), q1.getUnit()));

            Quantity<IMeasurable> b =
                    new Quantity<>(q2.getValue(),
                            UnitFactory.getUnit(q2.getType(), q2.getUnit()));

            Quantity<IMeasurable> result = a.add(b);

            saveHistory("ADD", result.toString());

            logger.info("Addition result: {}", result);

            return new QuantityDTO(
                    result.getValue(),
                    result.getUnit().getUnitName(),
                    q1.getType());

        } catch (Exception e) {
            logger.error("Error during add", e);
            throw new QuantityMeasurementException(e.getMessage());
        }
    }

    @Override
    public QuantityDTO convert(QuantityDTO q, String targetUnit) {

        try {
            Quantity<IMeasurable> a =
                    new Quantity<>(q.getValue(),
                            UnitFactory.getUnit(q.getType(), q.getUnit()));

            Quantity<IMeasurable> result =
                    a.convertTo(UnitFactory.getUnit(q.getType(), targetUnit));

            saveHistory("CONVERT", result.toString());

            logger.info("Conversion result: {}", result);

            return new QuantityDTO(
                    result.getValue(),
                    targetUnit,
                    q.getType());

        } catch (Exception e) {
            logger.error("Error during convert", e);
            throw new QuantityMeasurementException(e.getMessage());
        }
    }

    private void saveHistory(String operation, String value) {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity();

        entity.setOperation(operation);
        entity.setMeasurementType(value);
        entity.setValue(0);   // optional (depends schema)
        entity.setTimestamp(new Date());

        repo.save(entity);
    }
}