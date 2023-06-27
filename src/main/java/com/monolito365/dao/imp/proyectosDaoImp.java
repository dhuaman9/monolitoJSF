/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monolito365.dao.imp;


import com.monolito365.dao.proyectosDao;
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
public class proyectosDaoImp implements proyectosDao {

    @Override
    public List<Proyectos> listarProyectos() {
     
        List<Proyectos> lista = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String hql = "FROM Proyectos as proy inner join fetch proy.usuarios  order by proy.id asc";
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
    public void newProyecto(Proyectos proyecto) {
      Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(proyecto);
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
    public void updateProyecto(Proyectos proyecto) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(proyecto);
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
    public void deleteProyecto(Proyectos proyecto) {
    
          Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
//            String hql = "delete from ArchivosProyectos where proyectos.id = :idproyecto";
//            Query query = session.createQuery(hql);
//            query.setParameter("idproyecto", proyecto.getId());
//            query.executeUpdate();

            
            session.delete(proyecto);
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
    public List<Proyectos> listarProyectosUsuarios(String usuario) {
        
        List<Proyectos> lista = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String hql = "FROM Proyectos as proy inner join fetch proy.usuarios where proy.usuarios.username=:usuario ";
        Query q=session.createQuery(hql);
        q.setParameter("usuario",usuario);
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
    public boolean obtenerArchivo(int proyectoId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "SELECT count(*)  FROM ArchivosProyectos file WHERE file.proyectos.id=:proyectoId ";
        Query q = session.createQuery(hql);
        q.setParameter("proyectoId", proyectoId);
          Long count = (Long) q .uniqueResult();;
        
        if ( count < 1) {
            System.out.println(" es nulo");
            return true;
        }else{
            System.out.println("existe archivos");
           return false; 
        }
        
        
    }

   
    
}
