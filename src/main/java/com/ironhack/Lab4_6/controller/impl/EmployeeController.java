package com.ironhack.Lab4_6.controller.impl;

import com.ironhack.Lab4_6.controller.dto.DepartmentDto;
import com.ironhack.Lab4_6.controller.dto.NameDto;
import com.ironhack.Lab4_6.controller.dto.StatusDto;
import com.ironhack.Lab4_6.controller.interfaces.IEmployeeController;
import com.ironhack.Lab4_6.enums.Status;
import com.ironhack.Lab4_6.model.Employee;
import com.ironhack.Lab4_6.service.interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EmployeeController implements IEmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    // Create a route to get all doctors
    @GetMapping("/all-doctors")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    // Create a route to get doctor by employee_id
    @GetMapping("/doctor-by-id/{id}")
    public Employee findByEmployeeId(@PathVariable(name="id")Integer id) {
        return employeeService.findByEmployeeId(id);
    }

    // Create a route to get doctors by status
    @GetMapping("/doctors-by-status/{status}")
    public List<Employee> findByStatus(@PathVariable(name="status") Status status) {
        return employeeService.findByStatus(status);
    }

    // Create a route to get doctors by department
    @GetMapping("/doctors-by-department/{department}")
    public List<Employee> findByDepartment(@PathVariable(name="department") String department) {
        return employeeService.findByDepartment(department);
    }

    // Create a route to add a new patient
    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addNewEmployee(@RequestBody @Valid Employee employee) {
        return employeeService.addNewEmployee(employee);
    }

    // Create a route to change an employee's status
    @PatchMapping("/employee-status/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeEmployeeStatus(@PathVariable Integer employeeId, @RequestBody @Valid StatusDto status){
        employeeService.changeEmployeeStatus(employeeId, status.getStatus());
    }

    // Create a route to update an employee's name
    @PatchMapping("employee-name/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeEmployeeName(@PathVariable Integer employeeId, @RequestBody @Valid NameDto name) {
        employeeService.changeEmployeeName(employeeId, name.getName());
    }

    // Create a route to update an employee's department
    @PatchMapping("/employee-department/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeEmployeeDepartment(@PathVariable Integer employeeId, @RequestBody @Valid DepartmentDto department) {
        employeeService.changeEmployeeDepartment(employeeId, department.getDepartment());
    }
}
