/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unam.dgtic.diplo.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author edher
 */
@Entity
@Table(name = "factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f")
    , @NamedQuery(name = "Factura.findById", query = "SELECT f FROM Factura f WHERE f.id = :id")
    , @NamedQuery(name = "Factura.findByNumeroFactura", query = "SELECT f FROM Factura f WHERE f.numeroFactura = :numeroFactura")
    , @NamedQuery(name = "Factura.findByMoneda", query = "SELECT f FROM Factura f WHERE f.moneda = :moneda")
    , @NamedQuery(name = "Factura.findByFechaFactura", query = "SELECT f FROM Factura f WHERE f.fechaFactura = :fechaFactura")
    , @NamedQuery(name = "Factura.findByRutaFacturaXml", query = "SELECT f FROM Factura f WHERE f.rutaFacturaXml = :rutaFacturaXml")
    , @NamedQuery(name = "Factura.findByRutaFacturaPdf", query = "SELECT f FROM Factura f WHERE f.rutaFacturaPdf = :rutaFacturaPdf")
    , @NamedQuery(name = "Factura.findByEstatus", query = "SELECT f FROM Factura f WHERE f.estatus = :estatus")})
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "numero_factura")
    private String numeroFactura;
    
    @Size(max = 3)
    @Column(name = "moneda")
    private String moneda;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_factura")
    @Temporal(TemporalType.DATE)
    private Date fechaFactura;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "ruta_factura_xml")
    private String rutaFacturaXml;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "ruta_factura_pdf")
    private String rutaFacturaPdf;
    @Size(max = 45)
    @Column(name = "estatus")
    private String estatus;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id")
    @ManyToOne
    private Empresa idEmpresa;
    @JoinColumn(name = "id_dolar", referencedColumnName = "id")
    @ManyToOne
    private Dolar idDolar;
    //Extra
    @Basic(optional = false)
    @Column(name = "monto")
    private float monto;
    //

    public Factura() {
    }

    public Factura(Integer id) {
        this.id = id;
    }

    public Factura(Integer id, String numeroFactura, Date fechaFactura, String rutaFacturaXml, String rutaFacturaPdf) {
        this.id = id;
        this.numeroFactura = numeroFactura;
        this.fechaFactura = fechaFactura;
        this.rutaFacturaXml = rutaFacturaXml;
        this.rutaFacturaPdf = rutaFacturaPdf;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public String getRutaFacturaXml() {
        return rutaFacturaXml;
    }

    public void setRutaFacturaXml(String rutaFacturaXml) {
        this.rutaFacturaXml = rutaFacturaXml;
    }

    public String getRutaFacturaPdf() {
        return rutaFacturaPdf;
    }

    public void setRutaFacturaPdf(String rutaFacturaPdf) {
        this.rutaFacturaPdf = rutaFacturaPdf;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Empresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Dolar getIdDolar() {
        return idDolar;
    }

    public void setIdDolar(Dolar idDolar) {
        this.idDolar = idDolar;
    }
    
    ///SET GET optional
    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }
    /////////////////////
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "unam.dgtic.diplo.entity.Factura[ id=" + id + " ]";
    }

    
    
}
