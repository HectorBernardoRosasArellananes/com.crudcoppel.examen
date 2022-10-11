package com.crudcoppel.com.crudcoppel.examen.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
@Entity
@Table(name = "usuarios", uniqueConstraints = {@UniqueConstraint(columnNames = {"Celular"}),@UniqueConstraint(columnNames = {"Celular"})})
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 10)
    private String celular;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER,cascade= CascadeType.ALL)
    @JoinTable(name = "usuario_roles", joinColumns = @JoinColumn(name ="usuario_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name="rol_id",referencedColumnName = "id"))
    private Set<Rolmodel> roles=new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rolmodel> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rolmodel> roles) {
        this.roles = roles;
    }
    

    
}
