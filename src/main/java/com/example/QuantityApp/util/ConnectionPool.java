package com.example.QuantityApp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;
import java.util.Queue;

public class ConnectionPool {

    private static final Queue<Connection> pool = new LinkedList<>();

    static {
        try {
            int size = Integer.parseInt(ApplicationConfig.get("db.pool.size"));

            for (int i = 0; i < size; i++) {
                pool.add(createConnection());
            }

            System.out.println("Connection Pool Initialized");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection createConnection() throws Exception {
        return DriverManager.getConnection(
                ApplicationConfig.get("db.url"),
                ApplicationConfig.get("db.username"),
                ApplicationConfig.get("db.password"));
    }

    public synchronized static Connection getConnection() {
        if (pool.isEmpty()) throw new RuntimeException("No connections");
        return pool.peek(); 
    }


    public synchronized static void release(Connection conn) {
        pool.offer(conn);
    }
}