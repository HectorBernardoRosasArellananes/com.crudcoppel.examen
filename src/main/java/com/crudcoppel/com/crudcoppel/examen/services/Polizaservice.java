package com.crudcoppel.com.crudcoppel.examen.services;
import com.crudcoppel.com.crudcoppel.examen.repositories.Polizarepository;
import com.crudcoppel.com.crudcoppel.examen.models.Polizasmodel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Polizaservice {
    @Autowired
    Polizarepository repository;

    public List<Polizasmodel> GetAllPoliza() {
        return (List<Polizasmodel>) repository.findAll();
    }

    public Optional<Polizasmodel> GetidPoliza(Long id) {
        return (Optional<Polizasmodel>) repository.findById(id);
    }

    public Polizasmodel SavePoliza(Polizasmodel modelEmp) {
        return repository.save(modelEmp);
    }

    public void DeletePoliza(Long id) {
        repository.deleteById(id);
    }
}
