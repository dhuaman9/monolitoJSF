/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monolito365.dao.imp;

import com.monolito365.dao.clienteDao;
import com.monolito365.model.Clientes;
import com.monolito365.model.TipoDocumentos;
import com.monolito365.model.Usuarios;
import com.monolito365.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Daniel
 */
public class clienteDaoImp implements clienteDao{

    @Override
    public List<Clientes> listarClientes() {
        List<Clientes> lista = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String hql = "FROM Clientes as client inner join fetch client.tipoDocumentos left join fetch client.usuarios order by client.id asc";

        try {
            lista = session.createQuery(hql).list();
            t.commit();
            session.close();

        } catch (Exception e) {
            t.rollback();
        }
        return lista;

    }

    @Override
    public void newCliente(Clientes clientes) {
       Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(clientes);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();

        } finally {
            if (session != null) {
                session.close();
            }

        }
    
    }

    @Override
    public void updateCliente(Clientes clientes) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(clientes);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();

        } finally {
            if (session != null) {
                session.close();
            }

        }
    }

    @Override
    public void deleteCliente(Clientes clientes) {
       Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(clientes);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();

        } finally {
            if (session != null) {
                session.close();
            }

        }
    }

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

    @Override
    public List<Usuarios> listarUsuariosEnabled() {
        
        List<Usuarios> lista = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String hql = "FROM Usuarios as use where use.estado = 1";

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
