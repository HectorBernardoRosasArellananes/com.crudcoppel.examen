package com.crudcoppel.com.crudcoppel.examen.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.crudcoppel.com.crudcoppel.examen.models.Empleadomodel;

@Repository
public interface Empleadorepository extends CrudRepository<Empleadomodel, Long> {

}
