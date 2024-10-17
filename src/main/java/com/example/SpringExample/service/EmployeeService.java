package com.example.SpringExample.service;

import com.example.SpringExample.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(long id);

    Employee updateEmployee(Employee updatedEmployee);

    void deleteEmployee(long id);

}