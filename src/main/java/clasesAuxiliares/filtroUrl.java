/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesAuxiliares;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DANIEL
 */
public class filtroUrl implements PhaseListener{

    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext facesContext = event.getFacesContext();
        String currentPage = facesContext.getViewRoot().getViewId();//obtenemos el nombre de la pagina actual
        boolean isPageLogin = currentPage.lastIndexOf("login.xhtml") > -1 ? true : false;//creamos variable boolean para comrpobar si es la pag login qse capturo

        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

        Object usuario = session.getAttribute("usuario");
        if (!isPageLogin && usuario == null) {//si no es la pagina de logeo y el user es nulo,lo redirigimos a la pagina
            NavigationHandler nHandler = facesContext.getApplication().getNavigationHandler();
            nHandler.handleNavigation(facesContext, null, "/login.xhtml");
        }
        
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
    
}
