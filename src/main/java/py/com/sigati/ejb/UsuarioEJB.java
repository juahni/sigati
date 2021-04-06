/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.sigati.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import py.com.sigati.entities.Usuario;

/**
 *
 * @author Nelson182py
 */
@Stateless
public class UsuarioEJB extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "com.mycompany_Rollout_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioEJB() {
        super(Usuario.class);
    }

    public Usuario obtenerUsuario(String usename){
       Usuario usuario = null;
       try{
           Query q = em.createQuery("Select u from Usuario u where u.usuario = :usuario")
                   .setParameter("usuario", usename);
           
           usuario = (Usuario) q.getSingleResult();
           
       }catch(Exception e){
           
       }
       return usuario;
    }
    
}