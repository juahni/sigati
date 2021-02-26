/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.sigati.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import py.com.sigati.entities.Estado;

/**
 *
 * @author Nelson182py
 */
@Stateless
public class EstadoEJB extends AbstractFacade<Estado> {

    @PersistenceContext(unitName = "com.mycompany_Rollout_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoEJB() {
        super(Estado.class);
    }


    
}
