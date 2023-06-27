/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monolito365.dao;

import com.monolito365.model.Roles;
import com.monolito365.model.Usuarios;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Daniel
 */
public interface usuarioDao {
    
    public List<Usuarios> listarUsuarios();
    public void newUsuario(Usuarios usuario);
    public void updateUsuario(Usuarios usuario);
    public void deleteUsuario(Usuarios usuario);
    public List<Roles> listarRoles();

    public Usuarios login(Usuarios usuario);
    public Usuarios obtenerDatosPorUsuario(Usuarios usuario);
}
