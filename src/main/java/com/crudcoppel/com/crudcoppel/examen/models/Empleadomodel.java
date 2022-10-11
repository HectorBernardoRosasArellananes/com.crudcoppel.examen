package com.crudcoppel.com.crudcoppel.examen.models;

import javax.persistence.*;

@Entity
@Table(name = "Empleados")
public class Empleadomodel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long IdEmpleado;

    @Column(name = "Nombre", nullable = false, length = 100)
    private String Nombre;

    @Column(name = "Apellido", nullable = false, length = 200)
    private String Apellido;

    @Column(name = "Puesto", nullable = false, length = 200)
    private String Puesto;

    
    public long getIdEmpleado() {
        return IdEmpleado;
    }

    public void setIdEmpleado(long idEmpleado) {
        IdEmpleado = idEmpleado;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getPuesto() {
        return Puesto;
    }

    public void setPuesto(String puesto) {
        Puesto = puesto;
    }


}
