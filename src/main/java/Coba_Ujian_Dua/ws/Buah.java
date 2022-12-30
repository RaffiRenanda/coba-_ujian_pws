/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coba_Ujian_Dua.ws;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TUF GAMING
 */
@Entity
@Table(name = "buah")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Buah.findAll", query = "SELECT b FROM Buah b"),
    @NamedQuery(name = "Buah.findByIdBuah", query = "SELECT b FROM Buah b WHERE b.idBuah = :idBuah"),
    @NamedQuery(name = "Buah.findByNamaBuah", query = "SELECT b FROM Buah b WHERE b.namaBuah = :namaBuah")})
public class Buah implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_buah")
    private Integer idBuah;
    @Column(name = "nama_buah")
    private String namaBuah;

    public Buah() {
    }

    public Buah(Integer idBuah) {
        this.idBuah = idBuah;
    }

    public Integer getIdBuah() {
        return idBuah;
    }

    public void setIdBuah(Integer idBuah) {
        this.idBuah = idBuah;
    }

    public String getNamaBuah() {
        return namaBuah;
    }

    public void setNamaBuah(String namaBuah) {
        this.namaBuah = namaBuah;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBuah != null ? idBuah.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Buah)) {
            return false;
        }
        Buah other = (Buah) object;
        if ((this.idBuah == null && other.idBuah != null) || (this.idBuah != null && !this.idBuah.equals(other.idBuah))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Coba_Ujian_Dua.ws.Buah[ idBuah=" + idBuah + " ]";
    }
    
}
