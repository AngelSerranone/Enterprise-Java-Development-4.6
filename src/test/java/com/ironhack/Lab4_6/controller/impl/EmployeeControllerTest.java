package com.ironhack.Lab4_6.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.Lab4_6.enums.Status;
import com.ironhack.Lab4_6.model.Employee;
import com.ironhack.Lab4_6.service.impl.EmployeeService;
import com.ironhack.Lab4_6.service.interfaces.IEmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class EmployeeControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private EmployeeService employeeService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        Employee employee1 = new Employee(123456, "cardiology", "Pepe Navarro", Status.ON);
        Employee employee2 = new Employee(654321, "pediatry", "Jaime Lobo", Status.OFF);
        employeeService.saveAll(List.of(employee1, employee2));

    }

    @AfterEach
    void tearDown() {
        employeeService.deleteAll();
    }

    @Test
    void findAll_NoParams_AllEmployees() throws Exception {
        MvcResult result = mockMvc.perform(get("/all-doctors")).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Jaime"));
        assertTrue(result.getResponse().getContentAsString().contains("Pepe"));
    }

    @Test
    void findByEmployeeId_CorrectId_Found() throws Exception {
        MvcResult result = mockMvc.perform(get("/doctor-by-id/123456")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Pepe"));

    }

    @Test
    void findByStatus() throws Exception {
        MvcResult result = mockMvc.perform(get("/doctors-by-status/ON")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Pepe"));
        assertFalse(result.getResponse().getContentAsString().contains("Juan"));
    }

    @Test
    void findByDepartment() throws Exception {
        MvcResult result = mockMvc.perform(get("/doctors-by-department/cardiology")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Pepe"));
        assertFalse(result.getResponse().getContentAsString().contains("Juan"));
    }

    @Test
    void addNewEmployee() throws Exception {
        Employee employee = new Employee(123789, "surgery", "Paco Porras", Status.ON);
        String body = objectMapper.writeValueAsString(employee);
        MvcResult result = mockMvc.perform(
                post("/employee")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Porras"));
    }

    @Test
    void changeEmployeeStatus() throws Exception {
        String body = "{\"status\": \"OFF\"}";
        MvcResult result = mockMvc.perform(
                patch("/employee-status/123456")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent()).andReturn();
    }

    @Test
    void changeEmployeeName() throws Exception {
        String body = "{\"name\": \"Perico\"}";
        MvcResult result = mockMvc.perform(
                patch("/employee-name/123456")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent()).andReturn();
    }

    @Test
    void changeEmployeeDepartment() throws Exception {
        String body = "{\"department\": \"psychiatry\"}";
        MvcResult result = mockMvc.perform(
                patch("/employee-department/123456")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent()).andReturn();
    }
}