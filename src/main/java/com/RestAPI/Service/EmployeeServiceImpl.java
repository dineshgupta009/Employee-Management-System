package com.RestAPI.Service;

import com.RestAPI.Entity.Employee;
import com.RestAPI.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public List<Employee> searchEmployee(String query) {
        List<Employee> employee = employeeRepository.searchProducts(query);
        return employee;
    }

    @Override
    public Employee newEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}
