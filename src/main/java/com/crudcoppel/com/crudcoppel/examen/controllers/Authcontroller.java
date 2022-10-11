package com.crudcoppel.com.crudcoppel.examen.controllers;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crudcoppel.com.crudcoppel.examen.Security.JwtTokenProvider;
import com.crudcoppel.com.crudcoppel.examen.Security.Tokenmodel;
import com.crudcoppel.com.crudcoppel.examen.models.Rolmodel;
import com.crudcoppel.com.crudcoppel.examen.models.Users;
import com.crudcoppel.com.crudcoppel.examen.repositories.Rolrepository;
import com.crudcoppel.com.crudcoppel.examen.repositories.Usuariorepository;

import io.jsonwebtoken.Jwt;

@RestController
@RequestMapping("/auth")
public class Authcontroller {

    @Autowired
    private AuthenticationManager authorizationManager;

    @Autowired
    private Usuariorepository usuariorepository;

    @Autowired
    private Rolrepository rolesrepositor;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @PostMapping("/InitSesion")
    public ResponseEntity<Tokenmodel> authaAthenticateUser(@RequestBody Users login){
         Authentication authentication = authorizationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getCelular(), login.getPassword()));
         SecurityContextHolder.getContext().setAuthentication(authentication);
        
         String token = jwtTokenProvider.generarToken(authentication);

         return  ResponseEntity.ok(new Tokenmodel(token));
    }

    @PostMapping("/RegisterUser")
    public ResponseEntity<?> SaveUser(@RequestBody Users usuario ){
        if(usuariorepository.existsByCelular(usuario.getCelular())){
            return new ResponseEntity<>("Ese usuario ya existe", HttpStatus.BAD_REQUEST);
        }
        Users user= new Users();
        user.setCelular(usuario.getCelular());
        user.setPassword(passwordEncoder.encode(usuario.getPassword()));
        Rolmodel  roles = rolesrepositor.findBynombreRol("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));
        usuariorepository.save(user);
        return new ResponseEntity<>("Se registro correctamente el usuario", HttpStatus.OK);
    }
}
