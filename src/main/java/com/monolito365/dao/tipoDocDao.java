/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monolito365.dao;

import com.monolito365.model.TipoDocumentos;
import java.util.List;

/**
 *
 * @author Daniel
 */
public interface tipoDocDao {
    
       public List<TipoDocumentos> listarTipoDocumentos();
//       public String obtenerTipoDoc(int tipoDocId) ;//obtener el tipo de documento : DNI o RUC  segun el id
    
}
