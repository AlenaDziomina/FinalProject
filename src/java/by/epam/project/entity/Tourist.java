/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.entity;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "tourist")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tourist.findAll", query = "SELECT t FROM Tourist t"),
    @NamedQuery(name = "Tourist.findByIdTourist", query = "SELECT t FROM Tourist t WHERE t.idTourist = :idTourist"),
    @NamedQuery(name = "Tourist.findByFirstName", query = "SELECT t FROM Tourist t WHERE t.firstName = :firstName"),
    @NamedQuery(name = "Tourist.findByMiddleName", query = "SELECT t FROM Tourist t WHERE t.middleName = :middleName"),
    @NamedQuery(name = "Tourist.findByLastName", query = "SELECT t FROM Tourist t WHERE t.lastName = :lastName"),
    @NamedQuery(name = "Tourist.findByBirthDate", query = "SELECT t FROM Tourist t WHERE t.birthDate = :birthDate"),
    @NamedQuery(name = "Tourist.findByPassport", query = "SELECT t FROM Tourist t WHERE t.passport = :passport")})
public class Tourist implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tourist")
    private Integer idTourist;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Column(name = "passport")
    private String passport;
    @JoinColumn(name = "id_order", referencedColumnName = "id_order")
    @ManyToOne(optional = false)
    private Orders idOrder;

    public Tourist() {
    }

    public Tourist(Integer idTourist) {
        this.idTourist = idTourist;
    }

    public Integer getIdTourist() {
        return idTourist;
    }

    public void setIdTourist(Integer idTourist) {
        this.idTourist = idTourist;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public Orders getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Orders idOrder) {
        this.idOrder = idOrder;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTourist != null ? idTourist.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tourist)) {
            return false;
        }
        Tourist other = (Tourist) object;
        if ((this.idTourist == null && other.idTourist != null) || (this.idTourist != null && !this.idTourist.equals(other.idTourist))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "by.epam.project.entity.Tourist[ idTourist=" + idTourist + " ]";
    }
    
}
