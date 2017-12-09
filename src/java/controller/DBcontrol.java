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
public class DBcontrol {

    @PersistenceContext
    private EntityManager em;

    public DBcontrol() {
    }
    
    public List<User> getAll(){
        return em.createNamedQuery("User.findAll").getResultList();
    }
    
    public List<Media> getAllMedia(){
        return em.createNamedQuery("Media.findAll").getResultList();
    }
    
    public List<Comment> getAllComments(){
        return em.createNamedQuery("Comment.findAll").getResultList();
    }
    
    public User insert(User u){
        em.persist(u);
        return u;
    }
    
    public Media insertImg(Media m){
        em.persist(m);
        return m;
    }
    
    public Comment insertComment(Comment c){
        em.persist(c);
        return c;
    };
    
    public boolean checkUsername(String u, String p){
        if(isUsernameUsed(u)){
            if(isPasswordSame(u).equals(p)){
                return true;
            }
        } 
        return false;
    }
    
    public boolean isUsernameUsed(String name) {
        try {
            em.createNamedQuery("User.findByName").setParameter("name", name).getSingleResult();
        } catch (NoResultException e) {
            return false;
        }
        return true;
    }

    public String isPasswordSame(String name) {
        try {
            String result = em.createNamedQuery("User.findUserPassword").setParameter("name", name).getSingleResult().toString();
            return result;
        } catch (NoResultException e) {
            return null;
        }   
    }
    
    public boolean isEmailUsed(String email) {
        try {
            em.createNamedQuery("User.findByEmail").setParameter("email", email).getSingleResult();
        } catch (NoResultException e) {
            return false;
        }
        return true;
    }
    
    public int getUserId(String name){
        int nameid = (int) em.createNamedQuery("User.findUserId").setParameter("name", name).getSingleResult();
        return nameid;
    }
    
}

