package com.example.employees.service;

import com.example.employees.entity.Employees;
import com.example.employees.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeesService {
    @Autowired
    private EmployeesRepository employeesRepository;

    public List<Employees> findAll() {
        return employeesRepository.findAll();
    }

    public Optional<Employees> findById(Long id) {
        return employeesRepository.findById(id);
    }

    public Employees save(Employees employees) {
        return employeesRepository.save(employees);
    }

    public void deleteById(Long id) {
        employeesRepository.deleteById(id);
    }
}
