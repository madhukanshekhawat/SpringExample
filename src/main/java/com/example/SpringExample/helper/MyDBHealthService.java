package com.example.SpringExample.helper;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service
public class MyDBHealthService implements HealthIndicator {

    public static final String DB_SERVICE = "Database Service";

    public boolean checkHealth(){
        return true;
    }

    @Override
    public Health health() {
        if (checkHealth()) {
            return Health.up().withDetail(DB_SERVICE, "Databse Sevice is running").build();
        }
        return Health.down().withDetail(DB_SERVICE, "Database service is running down").build();
    }

}
