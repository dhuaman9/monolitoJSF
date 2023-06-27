package com.monolito365.model;
// Generated 30/08/2021 10:24:59 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Roles generated by hbm2java
 */
public class Roles  implements java.io.Serializable {


     private Integer id;
     private String nombre;
     private Date fechaRegistro;
     private Date fechaModificacion;
     private Set<Usuarios> usuarioses = new HashSet<Usuarios>(0);

    public Roles() {
    }

	
    public Roles(String nombre, Date fechaRegistro) {
        this.nombre = nombre;
        this.fechaRegistro = fechaRegistro;
    }
    public Roles(String nombre, Date fechaRegistro, Date fechaModificacion, Set<Usuarios> usuarioses) {
       this.nombre = nombre;
       this.fechaRegistro = fechaRegistro;
       this.fechaModificacion = fechaModificacion;
       this.usuarioses = usuarioses;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }
    
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    public Date getFechaModificacion() {
        return this.fechaModificacion;
    }
    
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
    public Set<Usuarios> getUsuarioses() {
        return this.usuarioses;
    }
    
    public void setUsuarioses(Set<Usuarios> usuarioses) {
        this.usuarioses = usuarioses;
    }




}


