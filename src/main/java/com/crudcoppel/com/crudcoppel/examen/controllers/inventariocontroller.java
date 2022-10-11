package com.crudcoppel.com.crudcoppel.examen.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.crudcoppel.com.crudcoppel.examen.models.DetalleArticulo;
import com.crudcoppel.com.crudcoppel.examen.models.MensajeResponse;
import com.crudcoppel.com.crudcoppel.examen.models.ResponseResult;
import com.crudcoppel.com.crudcoppel.examen.models.StatusResponse;
import com.crudcoppel.com.crudcoppel.examen.models.inventariomodel;
import com.crudcoppel.com.crudcoppel.examen.services.inventarioservice;

@RestController
@RequestMapping("/Inven")

public class inventariocontroller {

ResponseResult response = new ResponseResult();
StatusResponse stares = new StatusResponse();
MensajeResponse msjres= new MensajeResponse();
DetalleArticulo ArtDet= new DetalleArticulo();

@Autowired
inventarioservice services;

@GetMapping("/SelectAll")
public ResponseResult GetListInv() {
 try{
   List<inventariomodel> List = services.GetAllInv();
   
   response.setMeta(stares);
   if(List.size() > 0 ){
      stares.Status = "OK";
      ArtDet.setDetallearticulo(List);
      response.setData(ArtDet);
   }else{
      stares.Status = "FAILURE";
      msjres.Mensaje = "No Existen Registros";
      response.setData(msjres);
   }
}
catch(Exception x){
     stares.Status = "FAILURE";
     response.setMeta(stares);
     msjres.Mensaje = x.getMessage();
     response.setData(msjres);
}

  return response;

}

@GetMapping("/SelectId/{id}")
public ResponseResult GetIdInv(@PathVariable long id) {
   try {
      Optional<inventariomodel> invt = services.GetidInv(id);
      stares.Status = "OK";
      response.setMeta(stares);
      if (invt.isPresent()) {
         ArtDet.setDetallearticulo(invt);
         response.setData(ArtDet);
      } else {
         stares.Status = "FAILURE";
         msjres.Mensaje = "No Existe el articulo";
         response.setData(msjres);
      }
   } catch (Exception x) {
      stares.Status = "FAILURE";
      response.setMeta(stares);
      msjres.Mensaje = x.getMessage();
      response.setData(msjres);
   }

   return response;

}

@PostMapping("/SaveInv")
public ResponseResult  SaveInv(@RequestBody inventariomodel modelinv){
   inventariomodel invSave = new  inventariomodel();
    try {
      invSave=services.SaveInv(modelinv);
      stares.Status = "OK";
      response.setMeta(stares);
      if(invSave!=null){
         ArtDet.setDetallearticulo(invSave);
         response.setData(ArtDet);
      }
      else{
         stares.Status = "FAILURE";
         msjres.Mensaje = "Ocurrio un error al guardar o actualizar el registro.";
         response.setMeta(stares);
         response.setData(msjres);
      }
    }
   catch(Exception x){
     stares.Status = "FAILURE";
     msjres.Mensaje = "Ocurrio un error al guardar o actualizar el registro.".concat(x.getMessage());
     response.setMeta(stares);
     response.setData(msjres);
   }

   return response;
 }

@DeleteMapping("/DeleteInv/{id}")
public ResponseResult DeleteInv(@PathVariable Long id) {
    try {
         services.DeleteInv(id);
         
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
