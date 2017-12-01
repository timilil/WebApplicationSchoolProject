/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TimiL
 */
@Entity
@Table(name = "Media")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Media.findAll", query = "SELECT m FROM Media m")
    , @NamedQuery(name = "Media.findById", query = "SELECT m FROM Media m WHERE m.id = :id")
    , @NamedQuery(name = "Media.findByName", query = "SELECT m FROM Media m WHERE m.name = :name")
    , @NamedQuery(name = "Media.findByUserID", query = "SELECT m FROM Media m WHERE m.userid = :userid")
    , @NamedQuery(name = "Media.findByPostDate", query = "SELECT m FROM Media m WHERE m.postdate = :postdate")
    , @NamedQuery(name = "Media.findByURL", query = "SELECT m FROM Media m WHERE m.url = :url")})

public class Media implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    
    @Column(name = "ID")
    private int id;
    
    @Column(name = "name", length = 40)
    private String name;
    
    @Column(name = "userid")
    private String userid;
    
    @Column(name = "postdate")
    private String postdate;
    
    @Column(name = "url")
    private String url;

    public Media() {
    }

    public Media(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getUserID() {
        return userid;
    }

    public void setUserID(String userid) {
        this.userid = userid;
    }
    
    public String getPostDate() {
        return postdate;
    }

    public void setPostDate(String postdate) {
       this.postdate = postdate;
    }
    
    public String getURL() {
        return url;
    }

    public void setURL(String url) {
       this.url = url;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ((Integer)id).hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Media)) {
            return false;
        }
        Media other = (Media) object;
        return other.id == this.id;
    }

    @Override
    public String toString() {
        return "model.Media[ id=" + id + " ]";
    }
    
}
