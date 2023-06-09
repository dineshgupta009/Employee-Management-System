package com.RestAPI.Controller;

import com.RestAPI.Entity.Employee;
import com.RestAPI.Exception.ResourceNotFoundException;
import com.RestAPI.Repository.EmployeeRepository;
import com.RestAPI.Service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class home {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/home")
    public String home(){
        return "You are at home page";
    }

    // get all employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){

        return  employeeRepository.findAll();
    }

    // create employee rest api
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.newEmployee(employee);
    }

    // get employee by id rest api
    @GetMapping("employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable  long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id :"+id));
                return ResponseEntity.ok(employee);
    }

    // update employee rest api
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id , @RequestBody Employee employeeDetails){

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Employee Does Not Exits with Id : " +id));
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());
        employee.setSalary(employeeDetails.getSalary());

        Employee updateEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updateEmployee);
    }

    // delete employee rest api
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));

        employeeRepository.delete(employee);
        Map<String, String> response = new HashMap<>();
        response.put("deleted", "Employee Deleted With it "+id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/employees/greaterthan")
    public ResponseEntity<List<Employee>> getLaptopsByGreaterThan (@RequestParam Long salary) {
        return new ResponseEntity<List<Employee>>(employeeRepository.findBySalaryGreaterThan(salary), HttpStatus.OK);
    }


    @GetMapping("/employees/lessthan")
    public ResponseEntity<List<Employee>> getLaptopsByLessThan (@RequestParam Long salary) {
        return new ResponseEntity<List<Employee>>(employeeRepository.findBySalaryLessThan(salary), HttpStatus.OK);
    }

    @GetMapping("search")
    public ResponseEntity<List<Employee>> searchProduct(@RequestParam ("query") String query){


        if(query != null){
            return ResponseEntity.ok(employeeService.searchEmployee(query.trim()));
        }
        return ResponseEntity.ok(employeeRepository.findAll());
    }
}
