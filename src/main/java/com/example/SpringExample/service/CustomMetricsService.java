package com.example.SpringExample.services;

import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Service;
import io.micrometer.core.instrument.Counter;

@Service
public class CustomMetricsService implements HealthIndicator {

    private static final Logger logger = LoggerFactory.getLogger(CustomMetricsService.class);

    private Counter methodCounter;

    private boolean isDown = false;

    public CustomMetricsService(MeterRegistry meterRegistry){
        this.methodCounter = meterRegistry.counter("custom", "method", "runCount");
    }

    public void runCustomMethod(){
        methodCounter.increment();
        logger.info("Method executed. RUn Count: " + methodCounter.count());

        if(methodCounter.count()>=5){
            isDown = true;
        }
    }

    @Override
    public Health health() {
        if (methodCounter == null) {
            return Health.down().withDetail("Error", "Metrics not initialized").build();
        }
        return isDown ? Health.down().withDetail("Error", "Method run limit exceeded").build() : Health.up().build();
    }
}
