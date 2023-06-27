/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monolito365.dao.imp;

import com.monolito365.dao.archivoDao;
import com.monolito365.model.ArchivosProyectos;
import com.monolito365.model.Proyectos;
import com.monolito365.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Daniel
 */
public class archivoDaoImp implements archivoDao{

    @Override
    public List<ArchivosProyectos> listarArchivosxProyecto(int proyectoId) {
         List<ArchivosProyectos> lista = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String hql = "FROM ArchivosProyectos as archivo inner join fetch archivo.proyectos where archivo.proyectos.id=:proyectoId ";
        Query q=session.createQuery(hql);
        q.setParameter("proyectoId",proyectoId);
        try {
            lista = q.list();
         
            t.commit();
            session.close();

        } catch (Exception e) {
            t.rollback();
        }
        return lista;  
    }

    @Override
    public void newArchivo(ArchivosProyectos archivo) {
        
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(archivo);
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
    public void updateArchivo(ArchivosProyectos archivo) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(archivo);
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
    public void deleteArchivo(ArchivosProyectos archivo) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(archivo);
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
    public Proyectos obtenerProyecto(int proyectoId) {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql="FROM Proyectos where id=:proyectoId";
        Query q=session.createQuery(hql);
        q.setParameter("proyectoId",proyectoId);
        return (Proyectos) q.uniqueResult();
    }
    
}
