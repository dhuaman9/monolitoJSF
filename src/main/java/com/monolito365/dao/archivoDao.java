/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monolito365.dao;

import com.monolito365.model.ArchivosProyectos;
import com.monolito365.model.Proyectos;
import java.util.List;

/**
 *
 * @author Daniel
 */
public interface archivoDao {
    public List<ArchivosProyectos> listarArchivosxProyecto(int proyectoId);

    public void newArchivo(ArchivosProyectos archivo);

    public void updateArchivo(ArchivosProyectos archivo);

    public void deleteArchivo(ArchivosProyectos archivo);
    
    public Proyectos obtenerProyecto(int proyectoId);
}
