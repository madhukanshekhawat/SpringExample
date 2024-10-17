package com.example.SpringExample;

import com.example.SpringExample.entity.Employee;
import com.example.SpringExample.repository.EmployeeRepository;
import com.example.SpringExample.service.EmployeeServiceImpl;
import com.example.SpringExample.service.ResourceAlreadyExistsException;
import com.example.SpringExample.service.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    Employee employee;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        employee = new Employee(1,"Madhu", "Shekhawat","mkshekhawat47@gmail.com");
    }

    @Test
    void testSaveEmployee_NewEmployee(){
        when(employeeRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        employeeService.saveEmployee(employee);
        ArgumentCaptor<Employee> captor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepository).save(captor.capture());
        Employee capturedEmployee = captor.getValue();
        Assertions.assertEquals(employee.getEmail(), capturedEmployee.getEmail());
        verify(employeeRepository).findByEmail(eq("mkshekhawat47@gmail.com"));
    }

    @Test
    void testSaveEmployee_EmployeeAlreadyExists(){
        Optional<Employee> existingEmployee = Optional.of(employee);
        when(employeeRepository.findByEmail(anyString())).thenReturn(existingEmployee);
        Exception exception = Assertions.assertThrows(ResourceAlreadyExistsException.class , () -> {
            employeeService.saveEmployee(employee);
        });
        Assertions.assertTrue(exception.getMessage().contains("already exist"));
    }

    @Test
    void testUpdateEmployee(){
        when(employeeRepository.save(employee)).thenReturn(employee);
        final Employee actualEmployee = employeeService.updateEmployee(employee);
        Assertions.assertNotNull(actualEmployee);
        Assertions.assertSame(employee, actualEmployee);
        Assertions.assertEquals(employee.getFirstName(), actualEmployee.getFirstName());
        Assertions.assertEquals(employee.getEmail(), actualEmployee.getEmail());
    }

    @Test
    void testGetEmployeeById_ExistingEMployee(){
       when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
       Employee result = employeeService.getEmployeeById(1L);
       Assertions.assertNotNull(result);
       Assertions.assertEquals(employee.getFirstName(), result.getFirstName());
       Assertions.assertEquals(employee.getLastName(), result.getLastName());
       Assertions.assertEquals(employee.getEmail(),result.getEmail());
    }

    @Test
    void testGetAllEmployee_ExistingEmployee(){
        List<Employee> mockEmployees = Collections.singletonList(employee);
        when(employeeService.getAllEmployees()).thenReturn(mockEmployees);
        List<Employee> employees = employeeService.getAllEmployees();
        Assertions.assertSame(mockEmployees, employees);
    }

    @Test
    void testDeleteEmployee(){
        long employeeId = 1L;
        doNothing().when(employeeRepository).deleteById(employeeId);
        employeeService.deleteEmployee(employeeId);
        verify(employeeRepository, times(1)).deleteById(employeeId);
    }

    @Test
    void testDeleteEmployee_EmployeeNotFound(){
        long employeeId = 1L;
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());
        ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> employeeService.deleteEmployee(employeeId));
        Assertions.assertEquals("Employee not found with id: 1", exception.getMessage());
    }
}
