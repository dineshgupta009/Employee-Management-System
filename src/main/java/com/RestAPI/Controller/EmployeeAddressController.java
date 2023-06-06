package com.RestAPI.Controller;

import com.RestAPI.Entity.Address;
import com.RestAPI.Entity.Employee;
import com.RestAPI.Repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeAddressController {

    @Autowired
    private AddressRepository addressRepository;
    @GetMapping("/employees/state")
    public ResponseEntity<List<Address>> getLaptopsByGreaterThan (@RequestParam String state) {
        return new ResponseEntity<List<Address>>(addressRepository.findByState(state), HttpStatus.OK);
    }
}
