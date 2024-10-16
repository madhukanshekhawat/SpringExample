package com.example.SpringExample;

import com.example.SpringExample.entity.Employee;
import com.example.SpringExample.repository.EmployeeRepository;
import com.example.SpringExample.service.EmployeeServiceImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Spy
    private Employee employee;

    @BeforeEach
    public void setup(){
        employee = new Employee.Builder()
                .id(1L)
                .firstName("Madhu")
                .lastName("Shekhawat")
                .email("madhu@gmail.com")
                .build();
    }

//    @BeforeEach
//    public void init(){
//        MockitoAnnotations.openMocks(this);
//    }

    @DisplayName("JUnit test for saveEmployee method")
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject(){
        when(employeeRepository.findByEmail(employee.getEmail()))
                .thenReturn(Optional.empty());

        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee savedEmployee = employeeService.saveEmployee(employee);

        Assertions.assertNotNull(savedEmployee);
        System.out.println("Test Case Executed");
    }

    @DisplayName("JUnit test for getEmployeeById method")
    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject(){

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Employee savedEmployee = employeeService.getEmployeeById(employee.getId()).get();

        assertThat(savedEmployee).isNotNull();
    }

    @DisplayName("JUnit test for deleteEmployee method")
    @Test
    public void givenEmployeeId_whenDeleteEmployee_thenNothing(){

        long employeeId = 1L;

        doNothing().when(employeeRepository).deleteById(employeeId);

        employeeService.deleteEmployee(employeeId);

        verify(employeeRepository, times(1)).deleteById(employeeId);
    }
}

