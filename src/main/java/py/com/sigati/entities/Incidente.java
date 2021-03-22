/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.sigati.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "incidente")
@NamedQueries({
    @NamedQuery(name = "Incidente.findAll", query = "SELECT i FROM Incidente i")
    , @NamedQuery(name = "Incidente.findById", query = "SELECT i FROM Incidente i WHERE i.id = :id")
    , @NamedQuery(name = "Incidente.findByNombre", query = "SELECT i FROM Incidente i WHERE i.nombre = :nombre")
    , @NamedQuery(name = "Incidente.findByDescripcion", query = "SELECT i FROM Incidente i WHERE i.descripcion = :descripcion")
    , @NamedQuery(name = "Incidente.findByFechaInicio", query = "SELECT i FROM Incidente i WHERE i.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Incidente.findByFechaInicioEstimado", query = "SELECT i FROM Incidente i WHERE i.fechaInicioEstimado = :fechaInicioEstimado")
    , @NamedQuery(name = "Incidente.findByFechaFin", query = "SELECT i FROM Incidente i WHERE i.fechaFin = :fechaFin")
    , @NamedQuery(name = "Incidente.findByFechaFinEstimado", query = "SELECT i FROM Incidente i WHERE i.fechaFinEstimado = :fechaFinEstimado")})
public class Incidente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    /*@NotNull*/
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 100)
    @Column(name = "nombre", length = 100)
    private String nombre;
    @Size(max = 200)
    @Column(name = "descripcion", length = 200)
    private String descripcion;
    @JoinColumn(name = "id_complejidad", referencedColumnName = "id")
    @ManyToOne
    /*private Complejidad idComplejidad;*/
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
    /*@NotNull*/
    @Column(name = "fecha_fin", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_fin_estimado", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaFinEstimado;   
    @JoinColumn(name = "id_estado", referencedColumnName = "id")
    @ManyToOne
    private Estado idEstado;
    @JoinColumn(name = "id_prioridad", referencedColumnName = "id")
    @ManyToOne
    private Prioridad idPrioridad;
    @JoinColumn(name = "id_segmento", referencedColumnName = "id")
    @ManyToOne
    private Segmento idSegmento;
    @JoinColumn(name = "id_servicio", referencedColumnName = "id")
    @ManyToOne
    private Servicio idServicio;
    @JoinColumn(name = "id_reportador", referencedColumnName = "codigo_humano")
    @ManyToOne
    private Usuario idReportador;
    @OneToMany(mappedBy = "idIncidente")
    private List<Tarea> tareaList;

    public Incidente() {
    }

    public Incidente(Integer id) {
        this.id = id;
    }

    public Incidente(Integer id, Date fechaInicio, Date fechaInicioEstimado, Date fechaFin, Date fechaFinEstimado) {
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

    public Estado getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estado idEstado) {
        this.idEstado = idEstado;
    }

    public Prioridad getIdPrioridad() {
        return idPrioridad;
    }

    public void setIdPrioridad(Prioridad idPrioridad) {
        this.idPrioridad = idPrioridad;
    }

    public Segmento getIdSegmento() {
        return idSegmento;
    }

    public void setIdSegmento(Segmento idSegmento) {
        this.idSegmento = idSegmento;
    }

    public Servicio getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Servicio idServicio) {
        this.idServicio = idServicio;
    }

    public Usuario getIdReportador() {
        return idReportador;
    }

    public void setIdReportador(Usuario idReportador) {
        this.idReportador = idReportador;
    }

    public List<Tarea> getTareaList() {
        return tareaList;
    }

    public void setTareaList(List<Tarea> tareaList) {
        this.tareaList = tareaList;
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
        if (!(object instanceof Incidente)) {
            return false;
        }
        Incidente other = (Incidente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.sigati.entities.Incidente[ id=" + id + " ]";
    }
    
}
