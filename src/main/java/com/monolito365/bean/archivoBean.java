/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monolito365.bean;

import com.monolito365.dao.archivoDao;
import com.monolito365.dao.imp.archivoDaoImp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import com.monolito365.model.ArchivosProyectos;
//import static com.sun.activation.registries.LogSupport.log;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.Part;
//import static sun.util.logging.LoggingSupport.log;
/**
 *
 * @author Daniel
 */
@Named(value = "archivoBean")
@SessionScoped
public class archivoBean implements Serializable {

     private ArchivosProyectos archivo;
     private List<ArchivosProyectos> listaArchivosxProyecto;
     private Part file;
     private int proyectoId; 
     private boolean upladed=false;
     
    public archivoBean() {
        this.archivo = new ArchivosProyectos();

    }

    public int getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(int proyectoId) {
        this.proyectoId = proyectoId;
    }

    
    public ArchivosProyectos getArchivo() {
        return archivo;
    }

    public void setArchivo(ArchivosProyectos archivo) {
        this.archivo = archivo;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public List<ArchivosProyectos> getListaArchivosxProyecto() {
         archivoDao cDao = new archivoDaoImp();
        listaArchivosxProyecto = cDao.listarArchivosxProyecto(proyectoId);
        return listaArchivosxProyecto;
    }

    public void setListaArchivosxProyecto(List<ArchivosProyectos> listaArchivosxProyecto) {
        this.listaArchivosxProyecto = listaArchivosxProyecto;
    }

    public boolean isUpladed() {
        return upladed;
    }

    public void setUpladed(boolean upladed) {
        this.upladed = upladed;
    }
    
    
     public void download(String archivo) {
      
         
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();

            externalContext.setResponseContentType("application/pdf");
            externalContext.setResponseHeader("Content-Disposition", "inline;filename=\"reporte.pdf\"");

            FileInputStream inputStream = new FileInputStream(new File(archivo));
            OutputStream outputStream = externalContext.getResponseOutputStream();

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            inputStream.close();
            context.responseComplete();

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    
     public boolean doUpload(){
         try{
             FacesMessage messages=null;
            InputStream in=file.getInputStream();
            
            File f=new File("C:/365EyC/proyectos/"+file.getSubmittedFileName());
            f.createNewFile();
            FileOutputStream out=new FileOutputStream(f);
            
            byte[] buffer=new byte[1024];
            int length;
            
            while((length=in.read(buffer))>0){
                out.write(buffer, 0, length);
            }
            out.close();
            in.close();
            
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("path", f.getAbsolutePath());
           
            this.archivo.setNombre(file.getSubmittedFileName());
            this.archivo.setRuta("C:/365EyC/proyectos/"+file.getSubmittedFileName());
           // this.btnUpload=false;
            if (file.getSize()>2000000){//si es mayor a 3MB
             System.out.println("file get size"+file.getSize());
             messages = new FacesMessage(FacesMessage.SEVERITY_WARN, " Incorrecto", "Archivo debe ser menor o igual a 2 MB");
             FacesContext.getCurrentInstance().addMessage(null, messages); 
                upladed=false;
            }else{
                upladed=true;
            }
            
           
        }catch(Exception e){
            e.printStackTrace(System.out);
        }
           return upladed;
    }
    
    public void prepararNuevoArchivo() {
        archivo = new ArchivosProyectos();//se vacia la variable
    }
    
    public void nuevoArchivo() {
        
        doUpload();
        if (upladed==true) {
        archivoDao fileDao = new archivoDaoImp();
        this.archivo.setProyectos(fileDao.obtenerProyecto(proyectoId));
        this.archivo.setFechaRegistro(obtenerFecha());

        fileDao.newArchivo(archivo);
        
        FacesMessage  messages =  new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto","Se registró el archivo.");
        FacesContext.getCurrentInstance().addMessage(null, messages); 
        upladed=false;
        }
      
    }
     public void modificarArchivo() throws FileNotFoundException, IOException {
         
        File file = new File(archivo.getRuta());
        FileInputStream readFile = new FileInputStream(file);
        readFile.close();
        file.delete(); 
        //sube archivo a la unidad  E  
        doUpload();
       
        archivoDao fDao = new archivoDaoImp();
        this.archivo.setFechaModificacion(obtenerFecha());
        fDao.updateArchivo(archivo);
        this.archivo = new ArchivosProyectos();
        
        FacesMessage  messages =  new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto","Se cambió el archivo");
        FacesContext.getCurrentInstance().addMessage(null, messages); 
      
    }
     
     public void eliminarArchivo() throws FileNotFoundException, IOException {
         
        File file = new File(archivo.getRuta());
        FileInputStream readFile = new FileInputStream(file);
        readFile.close();
        file.delete();
        
        archivoDao fDao = new archivoDaoImp();
        fDao.deleteArchivo(archivo);
        FacesMessage  messages =  new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto","Se borró el archivo");
        FacesContext.getCurrentInstance().addMessage(null, messages); 
        this.archivo = new ArchivosProyectos();
    }
     //obtiene fecha formateada y luego setea a la BD
     public Date obtenerFecha() {
        Date date = new Date();
        System.out.println("fecha sistema:.... ");
        System.out.println(date);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Fecha formateada yyyy-MM-dd: " + dateFormat.format(date));
        try {
            date = dateFormat.parse(dateFormat.format(date));
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        return date;

    }
     
}
