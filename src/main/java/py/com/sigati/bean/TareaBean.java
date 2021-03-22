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
import py.com.sigati.ejb.TareaEJB;
import py.com.sigati.entities.Tarea;

/**
 *
 * @author Juanhi
 */
@ManagedBean
@SessionScoped
public class TareaBean extends AbstractBean implements Serializable {

    private List<Tarea> listaTarea = new ArrayList<>();
    private Tarea tareaSeleccionado;
    private boolean editando;

    @EJB
    private TareaEJB tareaEJB;

    @PostConstruct
    public void init() {
        tareaSeleccionado = new Tarea();
        listaTarea = tareaEJB.findAll();
    }

    @Override
    public void resetearValores() {
        tareaSeleccionado = new Tarea();
        editando = false;
    }

    @Override
    public void inicializarListas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void guardar() {
        try {
            tareaEJB.create(tareaSeleccionado);
            infoMessage("Se guardó correctamente.");
            listaTarea = tareaEJB.findAll();
            resetearValores();
            PrimeFaces.current().executeScript("PF('wbGeneric').hide()");
        } catch (Exception e) {
            errorMessage("Se produjo un error.");
        }
    }

    @Override
    public void antesActualizar() {
        editando = true;
        listaTarea = tareaEJB.findAll();
    }

    @Override
    public void actualizar() {
        try {
            tareaEJB.edit(tareaSeleccionado);
            infoMessage("Se actualizó correctamente.");
            listaTarea = tareaEJB.findAll();
            resetearValores();
            PrimeFaces.current().executeScript("PF('wbGeneric').hide()");
        } catch (Exception e) {
            errorMessage("Se produjo un error.");
        }
    }

    @Override
    public void eliminar() {
        try {
            tareaEJB.remove(tareaSeleccionado);
            infoMessage("Eliminado correctamente");
           listaTarea = tareaEJB.findAll();
        } catch (Exception e) {
            errorMessage("No se pudo eliminar el registro");
        }

    }

    public void agregar() {
        resetearValores();
        listaTarea = tareaEJB.findAll();
    }

    public List<Tarea> getListaTarea() {
        return listaTarea;
    }

    public void setListaTarea(List<Tarea> listaTarea) {
        this.listaTarea = listaTarea;
    }

   

    public Tarea getTareaSeleccionado() {
        return tareaSeleccionado;
    }

    public void setTareaSeleccionado(Tarea tareaSeleccionado) {
        this.tareaSeleccionado = tareaSeleccionado;
    }

   

    public boolean isEditando() {
        return editando;
    }

    public void setEditando(boolean editando) {
        this.editando = editando;
    }

}
