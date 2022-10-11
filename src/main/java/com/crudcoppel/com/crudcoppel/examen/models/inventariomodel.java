package com.crudcoppel.com.crudcoppel.examen.models;
import javax.persistence.*;

@Entity
@Table(name = "inventario")
public class inventariomodel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private long SKU;

    @Column(name = "Nombre", nullable = false, length = 100)
    private String Nombre;

    @Column(name = "Cantidad", nullable = false)
    private double Cantidad;

    public long getSKU() {
        return SKU;
    }

    public void setSKU(long sKU) {
        SKU = sKU;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public double getCantidad() {
        return Cantidad;
    }

    public void setCantidad(double cantidad) {
        Cantidad = cantidad;
    }

}
