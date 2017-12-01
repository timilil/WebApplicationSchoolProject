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
import model.Media;

/**
 *
 * @author TimiL
 */
@Stateless
public class Mediacontrol {

    @PersistenceContext
    private EntityManager em;

    public Mediacontrol() {
    }
    
    public List<Media> getAll(){
        return em.createNamedQuery("Media.findAll").getResultList();
    }
    
    public Media insert(Media m){
        em.persist(m);
        return m;
    }
    
}
