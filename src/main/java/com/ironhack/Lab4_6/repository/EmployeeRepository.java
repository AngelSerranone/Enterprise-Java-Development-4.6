package com.ironhack.Lab4_6.repository;

import com.ironhack.Lab4_6.enums.Status;
import com.ironhack.Lab4_6.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    public Employee findByEmployeeId(Integer employeeId);

    public List<Employee> findByStatus(Status status);

    public List<Employee> findByDepartment(String department);

}