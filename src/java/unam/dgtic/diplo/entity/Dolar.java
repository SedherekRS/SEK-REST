/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unam.dgtic.diplo.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author edher
 */
@Entity
@Table(name = "dolar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dolar.findAll", query = "SELECT d FROM Dolar d")
    , @NamedQuery(name = "Dolar.findById", query = "SELECT d FROM Dolar d WHERE d.id = :id")
    , @NamedQuery(name = "Dolar.findByDia", query = "SELECT d FROM Dolar d WHERE d.dia = :dia")
    , @NamedQuery(name = "Dolar.findByVenta", query = "SELECT d FROM Dolar d WHERE d.venta = :venta")})
public class Dolar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "dia")
    @Temporal(TemporalType.DATE)
    
    private Date dia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta")
    private float venta;
    @OneToMany(mappedBy = "idDolar")
    private Collection<Factura> facturaCollection;

    public Dolar() {
    }

    public Dolar(Integer id) {
        this.id = id;
    }

    public Dolar(Integer id, Date dia, float venta) {
        this.id = id;
        this.dia = dia;
        this.venta = venta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public float getVenta() {
        return venta;
    }

    public void setVenta(float venta) {
        this.venta = venta;
    }

    @XmlTransient
    public Collection<Factura> getFacturaCollection() {
        return facturaCollection;
    }

    public void setFacturaCollection(Collection<Factura> facturaCollection) {
        this.facturaCollection = facturaCollection;
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
        if (!(object instanceof Dolar)) {
            return false;
        }
        Dolar other = (Dolar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "unam.dgtic.diplo.entity.Dolar[ id=" + id + " ]";
    }
    
}
