package com.example.employees.controller;

import com.example.employees.entity.Employees;
import com.example.employees.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("api/v1/employees")
public class EmployeesController {

    @Autowired
    EmployeesService employeesService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Employees>> getList() {
        return ResponseEntity.ok(employeesService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<?> getDetail(@PathVariable Long id) {
        Optional<Employees> optionalEmployees = employeesService.findById(id);
        if (!optionalEmployees.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalEmployees.get());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Employees> create(@RequestBody Employees product) {
        return ResponseEntity.ok(employeesService.save(product));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<Employees> update(@PathVariable Long id, @RequestBody Employees employees) {
        Optional<Employees> optionalEmployees = employeesService.findById(id);
        if (!optionalEmployees.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Employees existEmployees = optionalEmployees.get();
        // map object
        existEmployees.setFullName(employees.getFullName());
        existEmployees.setWage(employees.getWage());
        return ResponseEntity.ok(employeesService.save(existEmployees));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!employeesService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        employeesService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
