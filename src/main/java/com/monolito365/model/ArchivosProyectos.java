package com.monolito365.model;
// Generated 30/08/2021 10:24:59 AM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * ArchivosProyectos generated by hbm2java
 */
public class ArchivosProyectos  implements java.io.Serializable {


     private Integer id;
     private Proyectos proyectos;
     private String nombre;
     private String ruta;
     private Date fechaRegistro;
     private Date fechaModificacion;

    public ArchivosProyectos() {
          // se agrega manualmente
        this.proyectos = new Proyectos();
    }

	
    public ArchivosProyectos(Proyectos proyectos, String nombre, String ruta, Date fechaRegistro) {
        this.proyectos = proyectos;
        this.nombre = nombre;
        this.ruta = ruta;
        this.fechaRegistro = fechaRegistro;
    }
    public ArchivosProyectos(Proyectos proyectos, String nombre, String ruta, Date fechaRegistro, Date fechaModificacion) {
       this.proyectos = proyectos;
       this.nombre = nombre;
       this.ruta = ruta;
       this.fechaRegistro = fechaRegistro;
       this.fechaModificacion = fechaModificacion;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Proyectos getProyectos() {
        return this.proyectos;
    }
    
    public void setProyectos(Proyectos proyectos) {
        this.proyectos = proyectos;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getRuta() {
        return this.ruta;
    }
    
    public void setRuta(String ruta) {
        this.ruta = ruta;
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






}

