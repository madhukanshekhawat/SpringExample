package com.example.SpringExample.services;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Service;
import io.micrometer.core.instrument.Counter;

@Service
public class CustomMetricsService implements HealthIndicator {

    private Counter methodCounter;

    private boolean isDown = false;

    public CustomMetricsService(MeterRegistry meterRegistry){
        this.methodCounter = meterRegistry.counter("custom", "method", "runCount");
    }

    public void runCustomMethod(){
        methodCounter.increment();
        System.out.println("Method executed. RUn Count: " + methodCounter.count());

        if(methodCounter.count()>=5){
            isDown = true;
        }
    }

    @Override
    public Health health() {
        if(isDown){
            return Health.down().withDetail("Error","Method run limit exceeded").build();
        }
        return Health.up().build();
    }
}
