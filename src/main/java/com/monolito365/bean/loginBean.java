/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monolito365.bean;

import com.monolito365.dao.imp.usuarioDaoImp;
import com.monolito365.dao.usuarioDao;
import com.monolito365.model.Usuarios;
import java.awt.event.ActionEvent;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext; // a partir de primefaces 7.0 no lo admite
import javax.servlet.http.HttpSession;
/**
 *
 * @author Daniel
 */
@Named(value = "loginBean")
@SessionScoped
public class loginBean implements Serializable {

    private String username;
    private String password;
    private Usuarios usuario;
    
    public loginBean() {
         this.usuario= new Usuarios();
        
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
    
    
    public void login(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean loggedIn = false;
        String ruta = "";
        usuarioDao usuDao = new usuarioDaoImp();
        this.usuario = usuDao.login(this.usuario);
        if (this.usuario != null) {
          FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", this.usuario.getUsername());
          loggedIn = true;
          message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", usuario.getUsername());
          ruta = "/monolito365/faces/index.xhtml";
        } else {
          loggedIn = false;
          message = new FacesMessage(FacesMessage.SEVERITY_WARN, " Error de acceso", "El Usuario y/o Clave es incorrecto.");
          this.usuario = new Usuarios();
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);
        context.addCallbackParam("ruta", ruta);
    } 
    
    //metodo de cerrar sesion
    public String cerrarSession() {
        this.usuario.setUsername(null); this.usuario.setPassword(null);
       // this.password = null;
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        httpSession.invalidate();
        return "/login"; // /monolito365/faces/login.xhtml
    }
    
}
