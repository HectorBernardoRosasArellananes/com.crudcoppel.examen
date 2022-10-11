package com.crudcoppel.com.crudcoppel.examen.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.crudcoppel.com.crudcoppel.examen.models.inventariomodel;

@Repository
public interface inventariorepository extends CrudRepository<inventariomodel,Long> {
    
}
