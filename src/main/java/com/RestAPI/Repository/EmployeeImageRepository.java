package com.RestAPI.Repository;

import com.RestAPI.Entity.EmployeeImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeImageRepository extends JpaRepository<EmployeeImage,Long> {

    Optional<EmployeeImage> findByName(String fileName);
}
