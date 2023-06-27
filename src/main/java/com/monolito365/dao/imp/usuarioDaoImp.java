/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monolito365.dao.imp;

import com.monolito365.dao.usuarioDao;
import com.monolito365.model.Roles;
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
public class usuarioDaoImp implements usuarioDao{
 
    
    @Override
    public List<Usuarios> listarUsuarios() {
        List<Usuarios> lista = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String hql = "FROM Usuarios as user inner join fetch user.roles order by user.id ASC";

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
    public void newUsuario(Usuarios usuario) {
     Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(usuario);
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
    public void updateUsuario(Usuarios usuario) {
     
       Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(usuario);
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
    public void deleteUsuario(Usuarios usuario) {
      
     Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(usuario);
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
    public List<Roles> listarRoles() {
        List<Roles> lista = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String hql = "FROM Roles";

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
    public Usuarios obtenerDatosPorUsuario(Usuarios usuario) {
        //se obtiene datos del usuario, despues del login
      
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql="FROM Usuarios where username=:username";
        Query q=session.createQuery(hql);
        q.setParameter("username",usuario.getUsername());
        return (Usuarios) q.uniqueResult();
    }

    @Override
    public Usuarios login(Usuarios usuario) {
          Usuarios user=this.obtenerDatosPorUsuario(usuario);
        if (user!=null) {
            if (!user.getPassword().equals(usuario.getPassword())) {
                user=null;
            }
        }
        return user;
    }

 
    
}
