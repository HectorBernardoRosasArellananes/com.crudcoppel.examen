package com.crudcoppel.com.crudcoppel.examen.repositories;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.crudcoppel.com.crudcoppel.examen.models.Users;

public interface Usuariorepository extends JpaRepository<Users,Long> {
    public Optional<Users> findByCelular(String num);
    public boolean existsByCelular (String Num);
}
