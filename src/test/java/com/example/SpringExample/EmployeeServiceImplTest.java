package com.example.SpringExample;

import com.example.SpringExample.entity.Employee;
import com.example.SpringExample.repository.EmployeeRepository;
import com.example.SpringExample.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveEmployee_NewEmployee(){
        Employee employee = new Employee(1,"Madhu", "Shekhawat","mkshekhawat47@gmail.com");
        when(employeeRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        employeeService.saveEmployee(employee);
        ArgumentCaptor<Employee> captor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepository).save(captor.capture());
        Employee capturedEmployee = captor.getValue();
        Assertions.assertEquals("Madhu", capturedEmployee.getFirstName());
        verify(employeeRepository).findByEmail(eq("mkshekhawat47@gmail.com"));
    }

//    @Test
//    void testUpdateEmployee(){
//        Employee expectedEmployee = new Employee("Madhu", "kanwar","madhu.shekhawat@gmail.com");
//        when(employeeRepository.save(new Employee())).thenReturn(expectedEmployee);
//        Employee savedEmployee = employeeService.updateEmployee(new Employee());
//        Assertions.assertEquals(expectedEmployee.getFirstName(), savedEmployee.getFirstName());
//        Assertions.assertEquals(expectedEmployee.getLastName(), savedEmployee.getLastName());
//        Assertions.assertEquals(expectedEmployee.getEmail(), savedEmployee.getEmail());
//
//    }

//    @Test(expected = ResourceNotFoundException.class)
//    void testSaveEmployee_EmployeeAlreadyExists(){
//        Employee employee = new Employee(1,"Madhu", "Shekhawat","mkshekhawat47@gmail.com");
//        when(employeeRepository.findByEmail(anyString())).thenReturn(Optional.of(employee));
//        employeeService.saveEmployee(employee);
//    }

}
