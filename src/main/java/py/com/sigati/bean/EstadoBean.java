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
import py.com.sigati.ejb.EstadoEJB;
import py.com.sigati.entities.Estado;

/**
 *
 * @author Juanhi
 */
@ManagedBean
@SessionScoped
public class EstadoBean extends AbstractBean implements Serializable {

    private List<Estado> listaEstado = new ArrayList<>();
    private Estado estadoSeleccionado;
    private boolean editando;

    @EJB
    private EstadoEJB estadoEJB;

    @PostConstruct
    public void init() {
        estadoSeleccionado = new Estado();
        listaEstado = estadoEJB.findAll();
    }

    @Override
    public void resetearValores() {
        estadoSeleccionado = new Estado();
        editando = false;
    }

    @Override
    public void inicializarListas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void guardar() {
        try {
             estadoEJB.create(estadoSeleccionado);
            infoMessage("Se guardó correctamente.");
            listaEstado = estadoEJB.findAll();
            resetearValores();
            PrimeFaces.current().executeScript("PF('wbGeneric').hide()");
        } catch (Exception e) {
            errorMessage("Se produjo un error.");
        }
    }

    @Override
    public void antesActualizar() {
        editando = true;
        listaEstado = estadoEJB.findAll();
    }

    @Override
    public void actualizar() {
        try {
            estadoEJB.edit(estadoSeleccionado);
            infoMessage("Se actualizó correctamente.");
            listaEstado = estadoEJB.findAll();
            resetearValores();
            PrimeFaces.current().executeScript("PF('wbGeneric').hide()");
        } catch (Exception e) {
            errorMessage("Se produjo un error.");
        }
    }

    @Override
    public void eliminar() {
        try {
            estadoEJB.remove(estadoSeleccionado);
            infoMessage("Eliminado correctamente");
           listaEstado = estadoEJB.findAll();
        } catch (Exception e) {
            errorMessage("No se pudo eliminar el registro");
        }

    }

    public void agregar() {
        resetearValores();
        listaEstado = estadoEJB.findAll();
    }

    public List<Estado> getListaEstado() {
        return listaEstado;
    }

    public void setListaEstado(List<Estado> listaEstado) {
        this.listaEstado = listaEstado;
    }

   

    public Estado getEstadoSeleccionado() {
        return estadoSeleccionado;
    }

    public void setEstadoSeleccionado(Estado estadoSeleccionado) {
        this.estadoSeleccionado = estadoSeleccionado;
    }

   

    public boolean isEditando() {
        return editando;
    }

    public void setEditando(boolean editando) {
        this.editando = editando;
    }

}
