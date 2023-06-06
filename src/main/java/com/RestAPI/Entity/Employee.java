package com.RestAPI.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String emailId;
    private Long salary;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Address address;

}
