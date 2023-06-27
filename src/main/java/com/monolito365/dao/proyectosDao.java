/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monolito365.dao;


import com.monolito365.model.Proyectos;
import java.util.List;


/**
 *
 * @author Daniel
 */
public interface proyectosDao {
    
    public List<Proyectos> listarProyectos();

    public void newProyecto(Proyectos proyecto);

    public void updateProyecto(Proyectos proyecto);

    public void deleteProyecto(Proyectos proyecto);

    public List<Proyectos> listarProyectosUsuarios(String username);//lista de proyectos x usuario
    
    public boolean obtenerArchivo(int proyectoId); //saber si el proyecto tiene archivo o NO

     
  
}
