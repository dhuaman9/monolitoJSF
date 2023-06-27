/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monolito365.dao;

import com.monolito365.model.Clientes;
import com.monolito365.model.TipoDocumentos;
import com.monolito365.model.Usuarios;
import java.util.List;

/**
 *
 * @author Daniel
 */
public interface clienteDao {

    public List<Clientes> listarClientes();

    public void newCliente(Clientes clientes);

    public void updateCliente(Clientes clientes);

    public void deleteCliente(Clientes clientes);

    public List<TipoDocumentos> listarTipoDocumentos();

    public List<Usuarios> listarUsuariosEnabled();//lista de usuarios habilitados

}
