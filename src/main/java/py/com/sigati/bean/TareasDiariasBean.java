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
import py.com.sigati.ejb.TareasDiariasEJB;
import py.com.sigati.entities.Tarea;
import py.com.sigati.entities.TareasDiarias;
import py.com.sigati.entities.Usuario;

/**
 *
 * @author Juanhi
 */
@ManagedBean
@SessionScoped
public class TareasDiariasBean extends AbstractBean implements Serializable {

    private List<TareasDiarias> listaTareasDiarias = new ArrayList<>();
    private List<TareasDiarias> listaTareasDiariasPorAnalista;
    private TareasDiarias tareaSeleccionado;
    private boolean editando;
    private String alta = "alta";
    private String baja = "baja";
    private String modificacion = "modificacion";
    private String completo = "completo"; 
    private String ninguno = "ninguno";
    private String informes = "descarga"; 
    private String admin = "Administrador";
    private String pM = "Project Manager";
    private String liderTecnico = "Lider Tecnico";
    private String analista = "Analista";  
    private String soporte = "Soporte";
    
    @EJB
    private TareasDiariasEJB tareasDiariasEJB;

    @PostConstruct
    public void init() {
        tareaSeleccionado = new TareasDiarias();
        listaTareasDiarias = tareasDiariasEJB.findAll();
    }

    @Override
    public void resetearValores() {
        tareaSeleccionado = new TareasDiarias();
        editando = false;
    }

    @Override
    public void inicializarListas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void guardar() {
        try {
            tareasDiariasEJB.create(tareaSeleccionado);
            infoMessage("Se guardó correctamente.");
            listaTareasDiarias = tareasDiariasEJB.findAll();
            resetearValores();
            PrimeFaces.current().executeScript("PF('wbTareas').hide()");
        } catch (Exception e) {
            errorMessage("Se produjo un error.");
        }
    }

    @Override
    public void antesActualizar() {
        editando = true;
        listaTareasDiarias = tareasDiariasEJB.findAll();
    }

    @Override
    public void actualizar() {
        try {
            tareasDiariasEJB.edit(tareaSeleccionado);
            infoMessage("Se actualizó correctamente.");
            listaTareasDiarias = tareasDiariasEJB.findAll();
            resetearValores();
            PrimeFaces.current().executeScript("PF('wbTareas').hide()");
        } catch (Exception e) {
            errorMessage("Se produjo un error.");
        }
    }

    @Override
    public void eliminar() {
        try {
            tareasDiariasEJB.remove(tareaSeleccionado);
            infoMessage("Eliminado correctamente");
           listaTareasDiarias = tareasDiariasEJB.findAll();
        } catch (Exception e) {
            errorMessage("No se pudo eliminar el registro");
        }

    }

    public void agregar() {
        resetearValores();
        listaTareasDiarias = tareasDiariasEJB.findAll();
    }

    public List<TareasDiarias> getListaTarea() {
        return listaTareasDiarias;
    }

    public void setListaTarea(List<TareasDiarias> listaTarea) {
        this.listaTareasDiarias = listaTarea;
    }

   

    public TareasDiarias getTareaSeleccionado() {
        return tareaSeleccionado;
    }

    public void setTareaSeleccionado(TareasDiarias tareaSeleccionado) {
        this.tareaSeleccionado = tareaSeleccionado;
    }

   

    public boolean isEditando() {
        return editando;
    }

    public void setEditando(boolean editando) {
        this.editando = editando;
    }

     public boolean agregarTarea() {
        Usuario u = loginBean.getUsuarioLogueado();

        if (u != null) {
            if (u.getIdRol().getDescripcion().equals(liderTecnico)
                    || u.getIdRol().getDescripcion().equals(admin)) {

                return true;
            }
        }
        return false;
    }

    public boolean editarTarea() {
        Usuario u = loginBean.getUsuarioLogueado();

        if (u != null) {
            if (u.getIdRol().getDescripcion().equals(admin)
                    || u.getIdRol().getDescripcion().equals(liderTecnico)
                    || u.getIdRol().getDescripcion().equals(analista)) {

                return true;
            }
        }
        return false;
    }
   
    public boolean eliminarTarea() {
        Usuario u = loginBean.getUsuarioLogueado();

        if (u != null) {
            if (u.getIdRol().getDescripcion().equals(admin)
                    || u.getIdRol().getDescripcion().equals(liderTecnico)) {

                return true;
            }
        }
        return false;
    }
    
     public List<TareasDiarias> getListaTareasPorAnalistas() {
        
       Usuario u =  loginBean.getUsuarioLogueado();       
       listaTareasDiarias = tareasDiariasEJB.findAll();
       listaTareasDiariasPorAnalista = new ArrayList<>();
    
        for (TareasDiarias t:listaTareasDiarias) {
            if (t != null){
                if (u.getUsuario().equals(t.getIdTarea().getIdResponsable().getUsuario())){
                    if(!t.getIdTarea().getIdEstado().getDescripcion().equals("Finalizado") 
                       && !t.getIdTarea().getIdEstado().getDescripcion().equals("Cancelado")){
                    
                        listaTareasDiariasPorAnalista.add(t);
                    }
                }
            }
        }
        return listaTareasDiariasPorAnalista;
    }
}
