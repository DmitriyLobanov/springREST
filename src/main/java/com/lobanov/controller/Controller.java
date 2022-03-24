package com.lobanov.controller;

import com.lobanov.entity.Employee;
import com.lobanov.exeption.EmployeeIncorrectValue;
import com.lobanov.exeption.NoSuchEmployee;
import com.lobanov.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmpl() {
        List<Employee> getAllEmployees = employeeService.getAllEmployees();
        return  getAllEmployees;
    }

    @GetMapping("/employees/{id}")
    public  Employee getEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);
        if (employee == null) {
            throw new NoSuchEmployee("No employee with id = " + id + " in database");
        }
        return employee;
    }

    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployeee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);
        if (employee == null) {
            throw new NoSuchEmployee("There no employee with id = " + id + " in db");
        }

        employeeService.deleteEmployee(id);
        return "Employee wit id = " + id + " was removed";
    }







    @ExceptionHandler
    public ResponseEntity<EmployeeIncorrectValue> handleExeption(NoSuchEmployee exeption) {
        EmployeeIncorrectValue incorrectValue = new EmployeeIncorrectValue();
        incorrectValue.setInfo(exeption.getMessage());
        return  new ResponseEntity<>(incorrectValue, HttpStatus.NOT_FOUND);
    }
}
