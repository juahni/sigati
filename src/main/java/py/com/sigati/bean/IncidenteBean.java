/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.sigati.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.PrimeFaces;
import py.com.sigati.ejb.IncidenteEJB;
import py.com.sigati.entities.Incidente;

/**
 *
 * @author Juanhi
 */
@ManagedBean
@SessionScoped
public class IncidenteBean extends AbstractBean implements Serializable {

    private List<Incidente> listaIncidente = new ArrayList<>();
    private Incidente incidenteSeleccionado;
    private boolean editando;

    @EJB
    private IncidenteEJB incidenteEJB;

    @PostConstruct
    public void init() {
        incidenteSeleccionado = new Incidente();
        listaIncidente = incidenteEJB.findAll();
    }

    @Override
    public void resetearValores() {
        incidenteSeleccionado = new Incidente();
        editando = false;
    }

    @Override
    public void inicializarListas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void guardar() {
        try {
            incidenteEJB.create(incidenteSeleccionado);
            infoMessage("Se guardó correctamente.");
            listaIncidente = incidenteEJB.findAll();
            resetearValores();
            PrimeFaces.current().executeScript("PF('wbIncidentes').hide()");
        } catch (Exception e) {
            errorMessage("Se produjo un error.");
        }
    }

    @Override
    public void antesActualizar() {
        editando = true;
        listaIncidente = incidenteEJB.findAll();
    }

    @Override
    public void actualizar() {
        try {
            incidenteEJB.edit(incidenteSeleccionado);
            infoMessage("Se actualizó correctamente.");
            listaIncidente = incidenteEJB.findAll();
            resetearValores();
            PrimeFaces.current().executeScript("PF('wbIncidentes').hide()");
        } catch (Exception e) {
            errorMessage("Se produjo un error.");
        }
    }

    @Override
    public void eliminar() {
        try {
            incidenteEJB.remove(incidenteSeleccionado);
            infoMessage("Eliminado correctamente");
           listaIncidente = incidenteEJB.findAll();
        } catch (Exception e) {
            errorMessage("No se pudo eliminar el registro");
        }

    }

    public void agregar() {
        resetearValores();
        listaIncidente = incidenteEJB.findAll();
    }

    public List<Incidente> getListaIncidente() {
        return listaIncidente;
    }

    public void setListaIncidente(List<Incidente> listaIncidente) {
        this.listaIncidente = listaIncidente;
    }

   

    public Incidente getIncidenteSeleccionado() {
        return incidenteSeleccionado;
    }

    public void setIncidenteSeleccionado(Incidente incidenteSeleccionado) {
        this.incidenteSeleccionado = incidenteSeleccionado;
    }

   

    public boolean isEditando() {
        return editando;
    }

    public void setEditando(boolean editando) {
        this.editando = editando;
    }

}
