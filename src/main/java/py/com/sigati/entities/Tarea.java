/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.sigati.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author nelson182py
 */
@Entity
@Table(name = "tarea")
@NamedQueries({
    @NamedQuery(name = "Tarea.findAll", query = "SELECT t FROM Tarea t")
    , @NamedQuery(name = "Tarea.findById", query = "SELECT t FROM Tarea t WHERE t.id = :id")
    , @NamedQuery(name = "Tarea.findByNombre", query = "SELECT t FROM Tarea t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "Tarea.findByDescripcion", query = "SELECT t FROM Tarea t WHERE t.descripcion = :descripcion")
    , @NamedQuery(name = "Tarea.findByFechaInicio", query = "SELECT t FROM Tarea t WHERE t.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Tarea.findByFechaInicioEstimado", query = "SELECT t FROM Tarea t WHERE t.fechaInicioEstimado = :fechaInicioEstimado")
    , @NamedQuery(name = "Tarea.findByFechaFin", query = "SELECT t FROM Tarea t WHERE t.fechaFin = :fechaFin")
    , @NamedQuery(name = "Tarea.findByFechaFinEstimado", query = "SELECT t FROM Tarea t WHERE t.fechaFinEstimado = :fechaFinEstimado")})
public class Tarea implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 100)
    @Column(name = "nombre", length = 100)
    private String nombre;
    @Size(max = 200)
    @Column(name = "descripcion", length = 200)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio_estimado", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaInicioEstimado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_fin", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_fin_estimado", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaFinEstimado;
    @JoinColumn(name = "id_ambiente", referencedColumnName = "id")
    @ManyToOne
    private Ambiente idAmbiente;
    @JoinColumn(name = "id_complejidad", referencedColumnName = "id")
    @ManyToOne
    private Complejidad idComplejidad;
    @JoinColumn(name = "id_entregable", referencedColumnName = "id")
    @ManyToOne
    private Entregable idEntregable;
    @JoinColumn(name = "id_incidente", referencedColumnName = "id")
    @ManyToOne
    private Incidente idIncidente;

    public Tarea() {
    }

    public Tarea(Integer id) {
        this.id = id;
    }

    public Tarea(Integer id, Date fechaInicio, Date fechaInicioEstimado, Date fechaFin, Date fechaFinEstimado) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaInicioEstimado = fechaInicioEstimado;
        this.fechaFin = fechaFin;
        this.fechaFinEstimado = fechaFinEstimado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaInicioEstimado() {
        return fechaInicioEstimado;
    }

    public void setFechaInicioEstimado(Date fechaInicioEstimado) {
        this.fechaInicioEstimado = fechaInicioEstimado;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaFinEstimado() {
        return fechaFinEstimado;
    }

    public void setFechaFinEstimado(Date fechaFinEstimado) {
        this.fechaFinEstimado = fechaFinEstimado;
    }

    public Ambiente getIdAmbiente() {
        return idAmbiente;
    }

    public void setIdAmbiente(Ambiente idAmbiente) {
        this.idAmbiente = idAmbiente;
    }

    public Complejidad getIdComplejidad() {
        return idComplejidad;
    }

    public void setIdComplejidad(Complejidad idComplejidad) {
        this.idComplejidad = idComplejidad;
    }

    public Entregable getIdEntregable() {
        return idEntregable;
    }

    public void setIdEntregable(Entregable idEntregable) {
        this.idEntregable = idEntregable;
    }

    public Incidente getIdIncidente() {
        return idIncidente;
    }

    public void setIdIncidente(Incidente idIncidente) {
        this.idIncidente = idIncidente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarea)) {
            return false;
        }
        Tarea other = (Tarea) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.sigati.entities.Tarea[ id=" + id + " ]";
    }
    
}
