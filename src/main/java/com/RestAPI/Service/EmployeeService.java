package com.RestAPI.Service;

import com.RestAPI.Entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> searchEmployee(String query);

    Employee newEmployee(Employee employee);
}
