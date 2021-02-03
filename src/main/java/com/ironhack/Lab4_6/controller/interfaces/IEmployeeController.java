package com.ironhack.Lab4_6.controller.interfaces;

import com.ironhack.Lab4_6.controller.dto.DepartmentDto;
import com.ironhack.Lab4_6.enums.Status;
import com.ironhack.Lab4_6.model.Employee;
import com.ironhack.Lab4_6.controller.dto.NameDto;
import com.ironhack.Lab4_6.controller.dto.StatusDto;

import java.util.List;

public interface IEmployeeController {

    // Create a route to get doctor by employee_id
    public Employee findByEmployeeId(Integer employeeId);

    // Create a route to get doctors by status
    public List<Employee> findByStatus(Status status);

    // Create a route to get doctors by department
    public List<Employee> findByDepartment(String department);

    // Create a route to add a new employee
    public Employee addNewEmployee(Employee employee);

    // Create a route to change an employee's status
    public void changeEmployeeStatus(Integer employeeId, StatusDto status);

    // Create a route to update an employee's name
    public void changeEmployeeName(Integer employeeId, NameDto name);

    // Create a route to update an employee's department
    public void changeEmployeeDepartment(Integer employeeId, DepartmentDto department);
}
