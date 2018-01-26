/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.dao.impl.EventDAOImpl;
import com.dao.interfaces.EventDAO;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author shashidhar
 */
@Entity
@Table(name = "eventregistered")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Eventregistered.findAll", query = "SELECT e FROM Eventregistered e"),
    @NamedQuery(name = "Eventregistered.findByUserId", query = "SELECT e FROM Eventregistered e WHERE e.userId = :userId"),
    @NamedQuery(name = "Eventregistered.findByEventId", query = "SELECT e FROM Eventregistered e WHERE e.eventId = :eventId")})
public class Eventregistered implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "userId")
    private Integer userId;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "eventId")
    private Integer eventId;

    public Eventregistered() {
    }

    public Eventregistered(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eventId != null ? eventId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Eventregistered)) {
            return false;
        }
        Eventregistered other = (Eventregistered) object;
        if ((this.eventId == null && other.eventId != null) || (this.eventId != null && !this.eventId.equals(other.eventId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.Eventregistered[ eventId=" + eventId + " ]";
    }
    
   
}
