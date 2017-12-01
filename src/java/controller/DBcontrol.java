/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    
    public User insert(User u){
        em.persist(u);
        return u;
    }
    
}
