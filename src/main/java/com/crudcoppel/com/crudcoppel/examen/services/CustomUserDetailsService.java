package com.crudcoppel.com.crudcoppel.examen.services;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.crudcoppel.com.crudcoppel.examen.models.Rolmodel;
import com.crudcoppel.com.crudcoppel.examen.models.Users;
import com.crudcoppel.com.crudcoppel.examen.repositories.Usuariorepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private Usuariorepository userrepository;

    @Override
    public UserDetails loadUserByUsername(String Username) throws UsernameNotFoundException {
        Users usuario= userrepository.findByCelular(Username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return new User(usuario.getCelular(), usuario.getPassword(), mapearRoles(usuario.getRoles()));
    }

    private Collection<? extends GrantedAuthority>mapearRoles(Set<Rolmodel> roles){
        return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getNombreRol())).collect(Collectors.toList());
    }
     
}
