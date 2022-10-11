package com.crudcoppel.com.crudcoppel.examen.services;
import com.crudcoppel.com.crudcoppel.examen.repositories.Empleadorepository;
import com.crudcoppel.com.crudcoppel.examen.models.Empleadomodel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class Empleadoservice {
    @Autowired
    Empleadorepository repository;

    public List<Empleadomodel> GetAllEmp() {
        return (List<Empleadomodel>) repository.findAll();
    }

    public Optional<Empleadomodel> GetidEmp(Long id) {
        return (Optional<Empleadomodel>) repository.findById(id);
    }

    public Empleadomodel SaveEmp(Empleadomodel modelEmp) {
        return repository.save(modelEmp);
    }

    public void DeleteEmp(Long id) {
        repository.deleteById(id);
    }

}