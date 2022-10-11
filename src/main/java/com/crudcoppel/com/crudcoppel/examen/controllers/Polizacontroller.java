package com.crudcoppel.com.crudcoppel.examen.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crudcoppel.com.crudcoppel.examen.models.DetalleArticulo;
import com.crudcoppel.com.crudcoppel.examen.models.DetallePoliza;
import com.crudcoppel.com.crudcoppel.examen.models.Empleado;
import com.crudcoppel.com.crudcoppel.examen.models.Empleadomodel;
import com.crudcoppel.com.crudcoppel.examen.models.MensajeResponse;
import com.crudcoppel.com.crudcoppel.examen.models.Poliza;
import com.crudcoppel.com.crudcoppel.examen.models.Polizasmodel;
import com.crudcoppel.com.crudcoppel.examen.models.ResponseResult;
import com.crudcoppel.com.crudcoppel.examen.models.StatusResponse;
import com.crudcoppel.com.crudcoppel.examen.models.inventariomodel;
import com.crudcoppel.com.crudcoppel.examen.services.Empleadoservice;
import com.crudcoppel.com.crudcoppel.examen.services.Polizaservice;
import com.crudcoppel.com.crudcoppel.examen.services.inventarioservice;

@RestController
@RequestMapping("/Poliza")
public class Polizacontroller {
    
    ResponseResult response = new ResponseResult();
    StatusResponse stares = new StatusResponse();
    MensajeResponse msjres = new MensajeResponse();
    Poliza Pol = new Poliza();
    DetalleArticulo DetArt = new DetalleArticulo();
    Empleado Emp= new Empleado();
    DetallePoliza detallepoliza= new DetallePoliza();

    @Autowired
    Polizaservice services;

    @Autowired
    inventarioservice serviceinv;

    @Autowired
    Empleadoservice serviceemp;
    

    @GetMapping("/SelectAll")
    public ResponseResult GetListPol() {
        try {
            stares.Status = "OK";
            response.setMeta(stares);
            List<Polizasmodel> List = services.GetAllPoliza();

            if (List.size() > 0) {
                Pol.setPoliza(List);
                response.setData(Pol);
            } else {
                stares.Status = "FAILURE";
                msjres.Mensaje = "No Existen Registros";
                response.setMeta(stares);
                response.setData(msjres);
            }
        } catch (Exception x) {
            stares.Status = "FAILURE";
            msjres.Mensaje = x.getMessage();
            response.setMeta(stares);
        }

        return response;

    }

    @GetMapping("/SelectPolid/{id}")
    public ResponseResult GetIDPol(@PathVariable long id) {
        try {
            stares.setStatus("OK");
            Optional<Polizasmodel> pol = services.GetidPoliza(id);
            
            
            if (pol.isPresent()) {
                inventariomodel inv = serviceinv.GetidInv(pol.get().getSKU()).get();
                Empleadomodel emp= serviceemp.GetidEmp(pol.get().getEmpleadoGenero()).get();
                detallepoliza.setDetallearticulo(inv);
                detallepoliza.setEmpleado(emp);
                detallepoliza.setPoliza(pol);
                response.setData(detallepoliza);
            } else {
                stares.Status = "FAILURE";
                msjres.Mensaje = "No Existen Registros";
                response.setData(msjres);
            }
        } catch (Exception x) {
            stares.setStatus("FAILURE");
            msjres.setMensaje("Error 500 : al intentar consultar la poliza");
            response.setData(msjres);
        }
        response.setMeta(stares);

        return response;

    }

    @PostMapping("/SavePol")
    public ResponseResult SavePol(@RequestBody Polizasmodel modelPol) {
        Polizasmodel PolSave = new Polizasmodel();
        Date Fecha= new Date(System.currentTimeMillis());
        modelPol.setFecha(Fecha);
        try {
            PolSave = services.SavePoliza(modelPol);
            stares.Status = "OK";
            response.setMeta(stares);
            if (PolSave != null) {
                inventariomodel inv = serviceinv.GetidInv(PolSave.getSKU()).get();
                inv.setCantidad(inv.getCantidad() - modelPol.getCantidad());
                serviceinv.SaveInv(inv);    
                Pol.setPoliza(PolSave);
                response.setData(Pol);
            } else {
                stares.Status = "FAILURE";
                msjres.Mensaje = "Ocurrio un error al guardar o actualizar el registro.";
                response.setMeta(stares);
                response.setData(msjres);
            }
        } catch (Exception x) {
            stares.Status = "FAILURE";
            msjres.Mensaje = "Ocurrio un error al guardar o actualizar el registro.".concat(x.getMessage());
            response.setMeta(stares);
            response.setData(msjres);
        }

        return response;
    }

    @DeleteMapping("/DeletePol/{id}")
    public ResponseResult DeletePol(@PathVariable Long id) {

        try
        {
           services.DeletePoliza(id);
           stares.Status = "OK";
           msjres.Mensaje = "Se elimino correctamente el registro";
           response.setMeta(stares);
           response.setData(msjres);
        }catch (Exception x) {
           stares.Status = "FAILURE";
           msjres.Mensaje = "Ocurrio un error al guardar o actualizar el registro: ".concat(x.getMessage());
           response.setMeta(stares);
           response.setData(msjres);
        }
        
        return response;

    }

}

