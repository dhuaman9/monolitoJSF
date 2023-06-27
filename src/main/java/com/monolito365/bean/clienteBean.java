/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monolito365.bean;

import com.monolito365.dao.clienteDao;
import com.monolito365.dao.imp.clienteDaoImp;
import com.monolito365.model.Clientes;
import com.monolito365.model.TipoDocumentos;
import com.monolito365.model.Usuarios;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


/**
 *
 * @author Daniel
 */

@Named(value = "clienteBean")
@SessionScoped
public class clienteBean implements Serializable {

    
    private List<Clientes> listaClientes;
    private Clientes cliente;
    private List<SelectItem> tiposDocList;
    private List<SelectItem> usuariosList;
    
    public clienteBean() {
        
          cliente = new Clientes();
           
    }

    public void setListaClientes(List<Clientes> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public Clientes getCliente() {
        if (this.cliente.getUsuarios() == null){
            this.cliente.setUsuarios(new Usuarios());
        }
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public List<Clientes> getListaClientes() {
        clienteDao cDao = new clienteDaoImp();
        listaClientes = cDao.listarClientes();
        return listaClientes;
    }

     public List<SelectItem> getTiposDocList() {
        
        this.tiposDocList = new ArrayList<SelectItem>();
        clienteDao dao = new clienteDaoImp();
        List<TipoDocumentos> tipos = dao.listarTipoDocumentos();
        tiposDocList.clear();
        for (TipoDocumentos tipo : tipos) {
            SelectItem item = new SelectItem(tipo.getId(), tipo.getDescripcion());
            this.tiposDocList.add(item);
        }
        return tiposDocList;
    }
  
    
    public void setTiposDocList(List<SelectItem> tipoList) {
        this.tiposDocList = tipoList;
    }

   
      public List<SelectItem> getUsuariosList() {
        
        this.usuariosList = new ArrayList<SelectItem>();
        clienteDao dao = new clienteDaoImp();
        List<Usuarios> users = dao.listarUsuariosEnabled();

        usuariosList.clear();
        for (Usuarios user : users) {
            SelectItem item = new SelectItem(user.getId(), user.getUsername());
            this.usuariosList.add(item);
        }
        return usuariosList;
    }
   
     
    public void setUsuariosList(List<SelectItem> usuarioList) {
        this.usuariosList = usuarioList;
    }  
 
      
//    ..................................................  
    
    public void prepararNuevoCliente() {
        cliente = new Clientes(); // dejando vacio a la instancia cliente
    }

    public void nuevoCliente() {
        //System.out.println("---: " + this.cliente.getUsuarios().getId());
        if (this.cliente.getUsuarios().getId() == null){
            this.cliente.setUsuarios(null);
        }
        this.cliente.setFechaRegistro(obtenerFecha());
        clienteDao cDao = new clienteDaoImp();
        cDao.newCliente(cliente);
       

    }

    public void modificarCliente() {
        if (this.cliente.getUsuarios().getId() == null){
            this.cliente.setUsuarios(null);
        }
        this.cliente.setFechaModificacion(obtenerFecha());
        clienteDao cDao = new clienteDaoImp();
        cDao.updateCliente(cliente);
        cliente = new Clientes();
        
    }

    public void eliminarCliente() {
        clienteDao cDao = new clienteDaoImp();
        cDao.deleteCliente(cliente);
        cliente = new Clientes();
    
    }

    public Date obtenerFecha()  {
        Date date = new Date();
        System.out.println("fecha del sistema:.... "+date);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Fecha formateada yyyy-MM-dd: " + dateFormat.format(date));
        try {
             date = dateFormat.parse(dateFormat.format(date));
        } 
        catch (ParseException ex) 
        {
            System.out.println(ex);
        }
        return date;
        
    }
    
   
}
