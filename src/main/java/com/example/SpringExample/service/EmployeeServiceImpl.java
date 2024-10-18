package com.example.SpringExample.service;

import com.example.SpringExample.entity.Employee;
import com.example.SpringExample.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        employeeRepository.findByEmail(employee.getEmail())
                .orElseThrow(() -> {
                    throw new ResourceAlreadyExistsException( "Employee already exists with given email: " + employee.getEmail());
                });
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
        return employeeRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException(" Employee not found with id: "+ id));
    }

    @Override
    public Employee updateEmployee(Employee updatedEmployee) {
        return employeeRepository.save(updatedEmployee);
    }

    @Override
    public void deleteEmployee(long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not found with id: "+ id));
        employeeRepository.deleteById(id);
    }
}
