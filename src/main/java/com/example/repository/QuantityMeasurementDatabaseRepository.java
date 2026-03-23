package com.example.repository;

import com.example.model.QuantityMeasurementEntity;
import com.example.util.ConnectionPool;
import com.example.exception.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementDatabaseRepository implements IQuantityMeasurementRepository {

    private static final String INSERT_SQL =
            "INSERT INTO quantity_measurements (operation, result) VALUES (?, ?)";

    private static final String SELECT_SQL =
            "SELECT operation, result FROM quantity_measurements";

    @Override
    public void save(QuantityMeasurementEntity entity) {

        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {

            statement.setString(1, entity.getOperation());
            statement.setString(2, entity.getResult());

            statement.executeUpdate();

        } catch (Exception e) {
            throw new DatabaseException("Failed to save measurement", e);
        }
    }

    @Override
    public List<QuantityMeasurementEntity> findAll() {

        List<QuantityMeasurementEntity> list = new ArrayList<>();

        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_SQL);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {

                String operation = rs.getString("operation");
                String result = rs.getString("result");

                list.add(new QuantityMeasurementEntity(operation, result));
            }

        } catch (Exception e) {
            throw new DatabaseException("Failed to fetch data", e);
        }

        return list;
    }
}
