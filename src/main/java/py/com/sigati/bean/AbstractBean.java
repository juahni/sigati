/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.sigati.bean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Juahni
 */
public abstract class AbstractBean {

    @Inject
    LoginBean loginBean;

    @PostConstruct
    public abstract void init();

    public abstract void resetearValores();

    public abstract void inicializarListas();

    public abstract void guardar();

    public abstract void actualizar();

    public abstract void eliminar();

    public abstract void antesActualizar();

    public void errorMessage(String mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, null));
    }

    public void infoMessage(String mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, null));
    }

}
