/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monolito365.dao.imp;

import com.monolito365.dao.tipoDocDao;
import com.monolito365.model.TipoDocumentos;
import com.monolito365.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Daniel
 */
public class tipoDocDaoImp implements tipoDocDao {

    @Override
    public List<TipoDocumentos> listarTipoDocumentos() {
        List<TipoDocumentos> lista = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String hql = "FROM TipoDocumentos ";

        try {
             Query q = session.createQuery(hql);
            lista = q.list();
            t.commit();
            session.close();

        } catch (Exception e) {
            t.rollback();
        }
        return lista;
    }

  
}
