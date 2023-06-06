package com.RestAPI.Repository;

import com.RestAPI.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends  JpaRepository<Employee , Long> {

    List<Employee> findBySalaryGreaterThan(Long salary);
    List<Employee> findBySalaryLessThan(Long salary);

    @Query(value = "SELECT * FROM USERS WHERE EMAIL_ADDRESS = ?1", nativeQuery = true)
    Employee findByEmailAddress(String emailAddress);

    @Query("SELECT t FROM Employee t WHERE " +
            "t.firstName LIKE CONCAT('%',:query, '%')" +
            "Or t.lastName LIKE CONCAT('%', :query, '%')")
    List<Employee> searchProducts(String query);

}
