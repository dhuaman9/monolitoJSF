/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesAuxiliares;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;
/**
 *
 * @author Daniel
 */
@FacesValidator(value="fileUploadValidator")
public class FileUploadValidator implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
     Part file = (Part) value;
 
        FacesMessage messages=null;
  
        try {
 
            if (file==null || file.getSize()<=0 || file.getContentType().isEmpty() ){
            
            messages = new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrecto","Ingrese un archivo");
             FacesContext.getCurrentInstance().addMessage(null, messages); 
             return;
            }
              
            else if (!file.getContentType().endsWith("pdf")){
            
            messages = new FacesMessage(FacesMessage.SEVERITY_WARN, " Incorrecto", "Seleccione un archivo PDF");
            FacesContext.getCurrentInstance().addMessage(null, messages); 
             return;
            }
//            else if (file.getSize()>3000000){//si es mayor a 3MB
//            
//            messages = new FacesMessage(FacesMessage.SEVERITY_WARN, " Incorrecto", "Archivo debe ser menor o igual a 3 MB");
//             FacesContext.getCurrentInstance().addMessage(null, messages); 
//            return;
//            }
            if (messages!=null && !messages.getDetail().isEmpty())
                {
                    messages.setSeverity(FacesMessage.SEVERITY_ERROR);
                    throw new ValidatorException(messages );
                }
 
        } catch (Exception ex) {
               throw new ValidatorException(new FacesMessage(ex.getMessage()));
        }

    }
        
}
