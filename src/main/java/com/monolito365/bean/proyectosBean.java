/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monolito365.bean;

import com.monolito365.dao.clienteDao;
import com.monolito365.dao.proyectosDao;
import com.monolito365.dao.imp.clienteDaoImp;
import com.monolito365.dao.imp.proyectosDaoImp;
import com.monolito365.model.Proyectos;
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
@Named(value = "proyectosBean")
//@ViewScoped
@SessionScoped
public class proyectosBean  implements Serializable{

    private List<Proyectos> listaProyectos;
    private List<Proyectos> listaProyectosxUsers;//lista de proyectos x usuario
    private Proyectos proyecto;
    private List<SelectItem> usuariosList;//lista de usuarios para el combobox
    private int proyectoId; 
    
    public proyectosBean() {
       proyecto= new Proyectos();
    }

    public int getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(int proyectoId) {
        this.proyectoId = proyectoId;
    }

    
    public List<Proyectos> getListaProyectos() {
        proyectosDao cDao = new proyectosDaoImp();
        listaProyectos = cDao.listarProyectos();
       
        return listaProyectos;
    }

    public void setListaProyectos(List<Proyectos> listaProyectos) {
        this.listaProyectos = listaProyectos;
    }

    public Proyectos getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyectos proyecto) {
        this.proyecto = proyecto;
    }

//----USUARIOS-----
    public List<SelectItem> getUsuariosList() {
        this.usuariosList = new ArrayList<SelectItem>();
        //cambiar aqui
        clienteDao dao = new clienteDaoImp();
        List<Usuarios> users = dao.listarUsuariosEnabled();
        usuariosList.clear();
        for (Usuarios user : users) {
            SelectItem item = new SelectItem(user.getId(), user.getUsername());
            this.usuariosList.add(item);
        }
        return usuariosList;
    }

    public void setUsuariosList(List<SelectItem> usuariosList) {
        this.usuariosList = usuariosList;
    }

    public List<Proyectos> obtenerListaProyectosxUsers(String usuario ) {
        proyectosDao cDao = new proyectosDaoImp();
        listaProyectosxUsers = cDao.listarProyectosUsuarios(usuario);
        return listaProyectosxUsers;
    }
    public void setListaProyectosxUsers(List<Proyectos> listaProyectosxUsers) {
        this.listaProyectosxUsers = listaProyectosxUsers;
    }
    
 //-----------------------------------------------
   
    public void prepararNuevoProyecto() {
       proyecto = new Proyectos();//creando instancia del objeto 
       proyecto.setEstado(true);//valor x defecto
    }
    //METODOS CRUD
    public void nuevoProyecto() {
        this.proyecto.setFechaRegistro(obtenerFecha());
        this.proyecto.setFechaModificacion(obtenerFecha());
   
        proyectosDao pryDao = new proyectosDaoImp();
        pryDao.newProyecto(proyecto);
        FacesMessage  messages2 =  new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto","Se registró exitosamente.");
        FacesContext.getCurrentInstance().addMessage(null, messages2); 
        
    }
    
    public void modificarProyecto() {
     
        this.proyecto.setFechaModificacion(obtenerFecha());
        proyectosDao pryDao = new proyectosDaoImp();
        pryDao.updateProyecto(proyecto);
        proyecto = new Proyectos();
        FacesMessage  messages =  new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto","Se actualizó el Proyecto.");
        FacesContext.getCurrentInstance().addMessage(null, messages); 
    }

//    public void eliminarProyecto() {
//
//        proyectosDao pryDao = new proyectosDaoImp();
//        pryDao.deleteProyecto(proyecto);
//        proyecto = new Proyectos();
//
//    }

 //otros Metodos
    
     public Date obtenerFecha() {
        Date date = new Date();
        System.out.println("fecha sistema:.... ");
        System.out.println(date);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Fecha formateada yyyy-MM-dd: " + dateFormat.format(date));
        try {
            date = dateFormat.parse(dateFormat.format(date));
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        return date;

    }
    
    public boolean validarArchivo(int id) {
        proyectosDao pryDao = new proyectosDaoImp();
        if (pryDao.obtenerArchivo(id) == true) {
            return true;//El proyecto tiene archivos
        } else {
            return false;//Falta agregar archivos al proyecto
        }
    }
//    public void limpiarForm() {
//      this.proyecto = new Proyectos();
//    }
      
}
