/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monolito365.bean;


import com.monolito365.dao.usuarioDao;
import com.monolito365.dao.imp.usuarioDaoImp;
import com.monolito365.model.Roles;
import com.monolito365.model.Usuarios;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
/**
 *
 * @author Daniel
 */
@Named(value = "usuarioBean")
@ManagedBean
@SessionScoped

public class usuarioBean  implements Serializable{

    private List<Usuarios> listaUsuarios;
    private Usuarios usuario;
    private List<SelectItem> rolList;
    
    public usuarioBean() {
        usuario = new Usuarios();

    }
    public List<Usuarios> getListaUsuarios() {
        
        usuarioDao cDao = new usuarioDaoImp();
        listaUsuarios = cDao.listarUsuarios();
        return listaUsuarios;
    }
    public void setListaUsuarios(List<Usuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public List<SelectItem> getRolList() {
        
        this.rolList = new ArrayList<SelectItem>();
        usuarioDao dao = new usuarioDaoImp();
        List<Roles> roless = dao.listarRoles();

        rolList.clear();
        for (Roles rol : roless) {
            SelectItem item = new SelectItem(rol.getId(), rol.getNombre());
            this.rolList.add(item);
        }
        return rolList;
        
    }
    public void setRolList(List<SelectItem> rolList) {
        this.rolList = rolList;
    }
      
    //.................
    public void prepararNuevoUsuario() {
        usuario = new Usuarios();// vaciamos el objeto
        usuario.setEstado(true);
    }

    public void nuevoUsuario() {

        this.usuario.setFechaRegistro(obtenerFecha());
//      this.usuario.setFechaModificacion(obtenerFecha());
        usuarioDao uDao = new usuarioDaoImp();
        uDao.newUsuario(usuario);
        FacesMessage  messages3 =  new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto","Se registró exitosamente.");
        FacesContext.getCurrentInstance().addMessage(null, messages3); 
    }
      
     public void modificarUsuario() {
      
        this.usuario.setFechaModificacion(obtenerFecha());
      //  this.usuario.setRoles(role);
        usuarioDao uDao = new usuarioDaoImp();
        uDao.updateUsuario(usuario);
        usuario=new Usuarios();
        FacesMessage  messages =  new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto","Se actualizó el Usuario.");
        FacesContext.getCurrentInstance().addMessage(null, messages); 
    }  
    public Date obtenerFecha()  {
        Date date = new Date();
        System.out.println("fecha sistema:.... ");
        System.out.println(date);
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
    //    public void eliminarUsuario() {
//
//        usuarioDao uDao = new usuarioDaoImp();
//        uDao.deleteUsuario(usuario);
//        usuario = new Usuarios();
//
//    }
    
}
