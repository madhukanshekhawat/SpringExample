package com.example.SpringExample.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class MyDBHealthService implements HealthIndicator {

    @Autowired
    private DataSource dataSource;

    public static final String DB_SERVICE = "Database Service";

    public boolean checkHealth(){
        return true;
    }

    @Override
    public Health health() {
        try (Connection conn = dataSource.getConnection()) {
            if (conn.isValid(1000)) {
                return Health.up().withDetail(DB_SERVICE, "Database Service is running").build();
            }
        } catch (SQLException e) {
            return Health.down().withDetail(DB_SERVICE, "Database Service is down: " + e.getMessage()).build();
        }
        return Health.down().withDetail(DB_SERVICE, "Database Service is unreachable").build();
    }

}
