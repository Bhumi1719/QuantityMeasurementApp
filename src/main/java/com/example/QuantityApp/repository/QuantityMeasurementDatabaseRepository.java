package com.example.QuantityApp.repository;

import com.example.QuantityApp.model.QuantityMeasurementEntity;
import com.example.QuantityApp.util.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

public class QuantityMeasurementDatabaseRepository
        implements IQuantityMeasurementRepository {

    private static final Logger logger =
            LoggerFactory.getLogger(QuantityMeasurementDatabaseRepository.class);

    private static final String INSERT_SQL =
            "INSERT INTO quantity_measurement(operation, measurement_type, measurement_value, timestamp) VALUES (?, ?, ?, ?)";

    @Override
    public void save(QuantityMeasurementEntity entity) {

        Connection conn = null;

        try {
            conn = ConnectionPool.getConnection();

            // ⭐ STEP-3 FIX → Ensure table exists using SAME connection
            try (Statement st = conn.createStatement()) {
                st.execute("CREATE TABLE IF NOT EXISTS quantity_measurement (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "operation VARCHAR(50)," +
                        "measurement_type VARCHAR(50)," +
                        "measurement_value DOUBLE," +
                        "timestamp TIMESTAMP)");
            }

            try (PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {

                ps.setString(1, entity.getOperation());
                ps.setString(2, entity.getMeasurementType());
                ps.setDouble(3, entity.getValue());
                ps.setTimestamp(4,
                        new Timestamp(entity.getTimestamp().getTime()));

                ps.executeUpdate();

                logger.info("Saved measurement to database");
            }

        } catch (Exception e) {

            logger.error("DB save failed", e);
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                ConnectionPool.release(conn);
            }
        }
    }

    @Override
    public List<QuantityMeasurementEntity> findAll() {
        return null;
    }
}
