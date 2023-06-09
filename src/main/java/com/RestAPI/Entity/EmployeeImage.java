package com.RestAPI.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class EmployeeImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;

    @Lob
    @Column(name = "imagedata")
    private byte[] imageData;
}
