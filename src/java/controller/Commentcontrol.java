/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import model.Comment;
import model.Media;
import model.User;

/**
 *
 * @author TimiL
 */
@Stateless
public class Commentcontrol {

    @PersistenceContext
    private EntityManager em;

    public Commentcontrol() {
    }
    
    public List<Comment> getAll(){
        return em.createNamedQuery("Comment.findAll").getResultList();
    }
    
    public List<Comment> getCommentsByUser(){
        return em.createQuery("SELECT m.id, u.name, c.comment FROM User u, Comment c, Media m WHERE u.id = c.userid AND c.mediaid = m.id").getResultList();
        /*return em.createNamedQuery("Comment.findCommentByUser").getResultList();*/
    }
    
    public List<Comment> getCommentsByMediaId(String mediaId){
        return em.createQuery("select u.name, c.mediaid, c.comment from User u, Comment c where u.id=c.userid and c.mediaid = "+mediaId).getResultList();
        /*return em.createNamedQuery("Comment.findCommentByUser").getResultList();*/
    }
    
    /*public List<Media> getAllMedia(){
        return em.createNamedQuery("Media.findAll").getResultList();
    }*/
    
    public Comment insert(Comment c){
        em.persist(c);
        return c;
    }
}
