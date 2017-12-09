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
@Table(name = "Comment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c")
    , @NamedQuery(name = "Comment.findComment", query = "SELECT c FROM Comment c WHERE c.comment = :comment")
    , @NamedQuery(name = "Comment.findById", query = "SELECT c FROM Comment c WHERE c.id = :id")
    , @NamedQuery(name = "Comment.findMediaID", query = "SELECT c FROM Comment c WHERE c.mediaid = :mediaid")
    , @NamedQuery(name = "Comment.findUserID", query = "SELECT c.comment FROM Comment c WHERE c.userid = :userid")
                                                               /*select c.id from Cars c join CarStatus d where c.status = d.status and d.color = 'red'*/
    })
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Column(name = "comment", length = 100)
    private String comment;
    @Column(name = "mediaid")
    private int mediaid;
    @Column(name = "userid")
    private int userid;
    
    
    public Comment() {
    }

    public Comment(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMediaId() {
        return mediaid;
    }

    public void setMediaId(Integer mediaid) {
        this.mediaid = mediaid;
    }
    
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public void setUserId(int userid){
        this.userid = userid;
    }
    
    public Integer getUserId (){
        return userid;
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
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        return other.id == this.id;
    }

    @Override
    public String toString() {
        return "model.User[ id=" + id + " ]";
    }
    
}
