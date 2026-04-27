package com.example.quantityapp.service;

import com.example.quantityapp.QuantityApp.*;
import com.example.quantityapp.dto.QuantityDTO;
import com.example.quantityapp.exception.QuantityMeasurementException;
import com.example.quantityapp.model.QuantityMeasurementEntity;
import com.example.quantityapp.repository.QuantityMeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    @Autowired
    private QuantityMeasurementRepository repo;

    // ── Helper: readable result string ──────────────────────────────────
    private String formatResult(double value, String unitName) {
        // Round to max 6 significant digits, strip trailing zeros
        String v = String.format("%.6f", value).replaceAll("0*$", "").replaceAll("\\.$", "");
        return v + " " + unitName;
    }

    // ── CONVERT ─────────────────────────────────────────────────────────
    @Override
    public QuantityDTO convert(QuantityDTO q, String targetUnit, String username) {
        try {
            Quantity<IMeasurable> a      = new Quantity<>(q.getValue(), UnitFactory.getUnit(q.getType(), q.getUnit()));
            Quantity<IMeasurable> result = a.convertTo(UnitFactory.getUnit(q.getType(), targetUnit));

            // Result displayed in TARGET unit (to-unit)
            String resultStr = formatResult(result.getValue(), targetUnit);
            repo.save(new QuantityMeasurementEntity(null, username, "CONVERT", resultStr));

            return new QuantityDTO(result.getValue(), targetUnit, q.getType());
        } catch (Exception e) {
            throw new QuantityMeasurementException(e.getMessage());
        }
    }

    // ── ADD ─────────────────────────────────────────────────────────────
    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2, String username) {
        try {
            Quantity<IMeasurable> a      = new Quantity<>(q1.getValue(), UnitFactory.getUnit(q1.getType(), q1.getUnit()));
            Quantity<IMeasurable> b      = new Quantity<>(q2.getValue(), UnitFactory.getUnit(q2.getType(), q2.getUnit()));
            Quantity<IMeasurable> result = a.add(b);

            // Result in unit1 (first operand's unit)
            String resultStr = formatResult(result.getValue(), result.getUnit().getUnitName());
            repo.save(new QuantityMeasurementEntity(null, username, "ADD", resultStr));

            return new QuantityDTO(result.getValue(), result.getUnit().getUnitName(), q1.getType());
        } catch (Exception e) {
            throw new QuantityMeasurementException(e.getMessage());
        }
    }

    // ── SUBTRACT ────────────────────────────────────────────────────────
    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2, String username) {
        try {
            Quantity<IMeasurable> a      = new Quantity<>(q1.getValue(), UnitFactory.getUnit(q1.getType(), q1.getUnit()));
            Quantity<IMeasurable> b      = new Quantity<>(q2.getValue(), UnitFactory.getUnit(q2.getType(), q2.getUnit()));
            Quantity<IMeasurable> result = a.subtract(b);

            String resultStr = formatResult(result.getValue(), result.getUnit().getUnitName());
            repo.save(new QuantityMeasurementEntity(null, username, "SUBTRACT", resultStr));

            return new QuantityDTO(result.getValue(), result.getUnit().getUnitName(), q1.getType());
        } catch (Exception e) {
            throw new QuantityMeasurementException(e.getMessage());
        }
    }

    // ── MULTIPLY ────────────────────────────────────────────────────────
    @Override
    public QuantityDTO multiply(QuantityDTO q1, QuantityDTO q2, String username) {
        try {
            // Multiply: (val1 in base) * (val2 in base), result back in unit1
            IMeasurable unit1 = UnitFactory.getUnit(q1.getType(), q1.getUnit());
            IMeasurable unit2 = UnitFactory.getUnit(q2.getType(), q2.getUnit());
            double base1   = unit1.convertToBaseUnit(q1.getValue());
            double base2   = unit2.convertToBaseUnit(q2.getValue());
            double product = unit1.convertFromBaseUnit(base1 * base2);

            String resultStr = formatResult(product, q1.getUnit());
            repo.save(new QuantityMeasurementEntity(null, username, "MULTIPLY", resultStr));

            return new QuantityDTO(product, q1.getUnit(), q1.getType());
        } catch (Exception e) {
            throw new QuantityMeasurementException(e.getMessage());
        }
    }

    // ── DIVIDE ──────────────────────────────────────────────────────────
    @Override
    public QuantityDTO divide(QuantityDTO q1, QuantityDTO q2, String username) {
        try {
            Quantity<IMeasurable> a = new Quantity<>(q1.getValue(), UnitFactory.getUnit(q1.getType(), q1.getUnit()));
            Quantity<IMeasurable> b = new Quantity<>(q2.getValue(), UnitFactory.getUnit(q2.getType(), q2.getUnit()));
            double quotient = a.divide(b);   // dimensionless ratio

            String resultStr = formatResult(quotient, "(ratio)");
            repo.save(new QuantityMeasurementEntity(null, username, "DIVIDE", resultStr));

            return new QuantityDTO(quotient, "ratio", q1.getType());
        } catch (Exception e) {
            throw new QuantityMeasurementException(e.getMessage());
        }
    }

    // ── COMPARE ─────────────────────────────────────────────────────────
    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2, String username) {
        Quantity<IMeasurable> a = new Quantity<>(q1.getValue(), UnitFactory.getUnit(q1.getType(), q1.getUnit()));
        Quantity<IMeasurable> b = new Quantity<>(q2.getValue(), UnitFactory.getUnit(q2.getType(), q2.getUnit()));
        boolean equal = a.equals(b);

        String resultStr = q1.getValue() + " " + q1.getUnit() + (equal ? " = " : " ≠ ") + q2.getValue() + " " + q2.getUnit();
        repo.save(new QuantityMeasurementEntity(null, username, "COMPARE", resultStr));

        return equal;
    }

    // ── HISTORY: per-user only ───────────────────────────────────────────
    @Override
    public List<QuantityMeasurementEntity> getAll(String username) {
        return repo.findByUsernameOrderByIdDesc(username);
    }

    // ── CRUD ────────────────────────────────────────────────────────────
    @Override
    public QuantityMeasurementEntity getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new QuantityMeasurementException("Data not found"));
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public QuantityMeasurementEntity update(Long id, QuantityMeasurementEntity updated) {
        QuantityMeasurementEntity existing = repo.findById(id)
                .orElseThrow(() -> new QuantityMeasurementException("Data not found"));
        existing.setOperation(updated.getOperation());
        existing.setResult(updated.getResult());
        return repo.save(existing);
    }
}

