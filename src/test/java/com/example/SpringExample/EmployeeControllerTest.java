package com.example.SpringExample;

import com.example.SpringExample.controller.EmployeeController;
import com.example.SpringExample.entity.Employee;
import com.example.SpringExample.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    private Employee getEmployee(){
        return new Employee(1L,"Madhu", "Shekhawat", "mkshekhawat47@gmail.com");
    }

    @Test
    void testGetAllEmployees() throws Exception {
        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(getEmployee()));
        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testGetEmployeeById_Found() throws Exception{
        when(employeeService.getEmployeeById(1L)).thenReturn(Optional.of(getEmployee()));
        mockMvc.perform(get("/api/employees/1"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testGetEmployeeById_NotFound() throws Exception{
        when(employeeService.getEmployeeById(1L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/employees/1"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

}
