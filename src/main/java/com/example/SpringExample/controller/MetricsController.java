package com.example.SpringExample.controller;

import com.example.SpringExample.services.CustomMetricsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetricsController {

    private final CustomMetricsService customMetricsService;

    public MetricsController(CustomMetricsService customMetricsService){
        this.customMetricsService = customMetricsService;
    }

    @GetMapping("/run-method")
    public String runMethod(){
        customMetricsService.runCustomMethod();
        return "Method executed";
    }
}
