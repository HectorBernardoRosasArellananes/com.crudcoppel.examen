package com.crudcoppel.com.crudcoppel.examen.models;
import java.util.Date;
import javax.persistence.*;
@Entity
@Table(name = "Polizas")
public class Polizasmodel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long IdPolizas;

    @Column(name = "EmpleadoGenero", nullable = false)
    private long EmpleadoGenero;

    @Column(name = "SKU", nullable = false)
    private long SKU;

    @Column(name = "Cantidad", nullable = false, length = 200)
    private double Cantidad;

    @Column(name = "Fecha",nullable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date Fecha;

    //@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    //@JoinTable(name = "inventario",joinColumns = @JoinColumn( name= "SKU",referencedColumnName = "SKU"),inverseJoinColumns = @JoinColumn(name="SKU", referencedColumnName = "SKU"))
    //private Set<> PolizaDetalle = new HashSet<>();

    public long getIdPolizas() {
        return IdPolizas;
    }

    public void setIdPolizas(long idPolizas) {
        IdPolizas = idPolizas;
    }

    public long getEmpleadoGenero() {
        return EmpleadoGenero;
    }

    public void setEmpleadoGenero(long empleadoGenero) {
        EmpleadoGenero = empleadoGenero;
    }

    public long getSKU() {
        return SKU;
    }

    public void setSKU(long sKU) {
        SKU = sKU;
    }

    public double getCantidad() {
        return Cantidad;
    }

    public void setCantidad(double cantidad) {
        Cantidad = cantidad;
    }


    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }


}
