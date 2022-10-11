package com.crudcoppel.com.crudcoppel.examen.repositories;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.crudcoppel.com.crudcoppel.examen.models.Rolmodel;

public interface Rolrepository extends JpaRepository<Rolmodel,Long> {
    public Optional<Rolmodel> findBynombreRol(String nombre);
}
