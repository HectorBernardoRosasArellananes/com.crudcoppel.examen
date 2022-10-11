package com.crudcoppel.com.crudcoppel.examen.services;
import  com.crudcoppel.com.crudcoppel.examen.repositories.inventariorepository;
import com.crudcoppel.com.crudcoppel.examen.models.inventariomodel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class inventarioservice {
    @Autowired
    inventariorepository repository;

    public List<inventariomodel> GetAllInv(){
        return (List<inventariomodel>)repository.findAll();
    }

    public Optional<inventariomodel> GetidInv(long id) {
        return (Optional<inventariomodel>) repository.findById(id);
    }

    public inventariomodel SaveInv(inventariomodel modelinv){
        return repository.save(modelinv);
    }

    public void DeleteInv(Long id) {
        repository.deleteById(id);
    }

}
