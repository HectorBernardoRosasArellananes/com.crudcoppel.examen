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

import com.crudcoppel.com.crudcoppel.examen.models.Empleado;
import com.crudcoppel.com.crudcoppel.examen.models.Empleadomodel;
import com.crudcoppel.com.crudcoppel.examen.models.MensajeResponse;
import com.crudcoppel.com.crudcoppel.examen.models.ResponseResult;
import com.crudcoppel.com.crudcoppel.examen.models.StatusResponse;
import com.crudcoppel.com.crudcoppel.examen.services.Empleadoservice;

@RestController
@RequestMapping("/Empleado")
public class Empleadocontroller {

    ResponseResult response = new ResponseResult();
    StatusResponse stares = new StatusResponse();
    MensajeResponse msjres = new MensajeResponse();
    Empleado Emp= new Empleado();
    @Autowired
    Empleadoservice services;

    @GetMapping("/SelectAll")
    public ResponseResult GetListEmp() {
        try {
            stares.Status = "OK";
            response.setMeta(stares);
            List<Empleadomodel> List = services.GetAllEmp();

            if (List.size() > 0) {
                Emp.setEmpleado(List);
                response.setData(Emp);
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

    @GetMapping("/SelectEmpid/{id}")
    public ResponseResult GetIDEmp(@PathVariable long id) {
        try {
            stares.Status = "OK";
            response.setMeta(stares);
            Optional<Empleadomodel> empt = services.GetidEmp(id);

            if (empt.isPresent()) {
                Emp.setEmpleado(empt);
                response.setData(Emp);
            } else {
                stares.Status = "FAILURE";
                msjres.Mensaje = "No Existe el empleado";
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

    @PostMapping("/SaveEmp")
    public ResponseResult SaveEmp(@RequestBody Empleadomodel modelemp) {
        Empleadomodel EmpSave = new Empleadomodel();
        
        try {
            EmpSave = services.SaveEmp(modelemp);
            stares.Status = "OK";
            response.setMeta(stares);
            if (EmpSave != null) {
                Emp.setEmpleado(EmpSave);
                response.setData(Emp);
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

    @DeleteMapping("/DeleteEmp/{id}")
    public ResponseResult DeleteEmp(@PathVariable Long id) {

        try {
            services.DeleteEmp(id);
            stares.Status = "OK";
            msjres.Mensaje = "Se elimino correctamente el registro";
            response.setMeta(stares);
            response.setData(msjres);

        } catch (Exception x) {
            stares.Status = "FAILURE";
            msjres.Mensaje = "Ocurrio un error al guardar o actualizar el registro: ".concat(x.getMessage());
            response.setMeta(stares);
            response.setData(msjres);
        }
        return response;
    }

}