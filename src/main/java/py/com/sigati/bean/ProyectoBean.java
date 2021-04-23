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
import py.com.sigati.ejb.ProyectoEJB;
import py.com.sigati.entities.Proyecto;
import py.com.sigati.entities.Usuario;

/**
 *
 * @author Juanhi
 */
@ManagedBean
@SessionScoped
public class ProyectoBean extends AbstractBean implements Serializable {

    private List<Proyecto> listaProyecto = new ArrayList<>();
    private Proyecto proyectoSeleccionado;
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
    private ProyectoEJB proyectoEJB;

    @PostConstruct
    public void init() {
        proyectoSeleccionado = new Proyecto();
        listaProyecto = proyectoEJB.findAll();
    }

    @Override
    public void resetearValores() {
        proyectoSeleccionado = new Proyecto();
        editando = false;
    }

    @Override
    public void inicializarListas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void guardar() {
        try {
            proyectoEJB.create(proyectoSeleccionado);
            infoMessage("Se guardó correctamente.");
            listaProyecto = proyectoEJB.findAll();
            resetearValores();
            PrimeFaces.current().executeScript("PF('wbProyectos').hide()");
        } catch (Exception e) {
            errorMessage("Se produjo un error.");
        }
    }

    @Override
    public void antesActualizar() {
        editando = true;
        listaProyecto = proyectoEJB.findAll();
    }

    @Override
    public void actualizar() {
        try {
            proyectoEJB.edit(proyectoSeleccionado);
            infoMessage("Se actualizó correctamente.");
            listaProyecto = proyectoEJB.findAll();
            resetearValores();
            PrimeFaces.current().executeScript("PF('wbProyectos').hide()");
        } catch (Exception e) {
            errorMessage("Se produjo un error.");
        }
    }

    @Override
    public void eliminar() {
        try {
            proyectoEJB.remove(proyectoSeleccionado);
            infoMessage("Eliminado correctamente");
           listaProyecto = proyectoEJB.findAll();
        } catch (Exception e) {
            errorMessage("No se pudo eliminar el registro");
        }

    }

    public void agregar() {
        resetearValores();
        listaProyecto = proyectoEJB.findAll();
    }

    public List<Proyecto> getListaProyecto() {
        return listaProyecto;
    }

    public void setListaProyecto(List<Proyecto> listaProyecto) {
        this.listaProyecto = listaProyecto;
    }

   

    public Proyecto getProyectoSeleccionado() {
        return proyectoSeleccionado;
    }

    public void setProyectoSeleccionado(Proyecto proyectoSeleccionado) {
        this.proyectoSeleccionado = proyectoSeleccionado;
    }

   

    public boolean isEditando() {
        return editando;
    }

    public void setEditando(boolean editando) {
        this.editando = editando;
    }
    
    public boolean agregarProyecto(){
         Usuario u =  loginBean.getUsuarioLogueado();
        
        if (u != null){
           if( u.getIdRol().getDescripcion().equals(pM)
             || u.getIdRol().getDescripcion().equals(admin)){
               
               return true;
           }            
        }
        return false;      
    }
     
    public boolean editarProyecto() {
        Usuario u = loginBean.getUsuarioLogueado();

        if (u != null) {
            if (u.getIdRol().getDescripcion().equals(pM)
                    || u.getIdRol().getDescripcion().equals(admin)) {

                return true;
            }
        }
        return false;
    }
}
