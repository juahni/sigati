/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.sigati.ejb;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import py.com.sigati.bean.LoginBean;

/**
 *
 * @author Nelson182py
 */
public abstract class AbstractFacade<T> {

    @Inject
    LoginBean loginBean;

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findAllLast(String idKey) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery q = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        Root<T> c = q.from(entityClass);
        q.select(c);
        q.orderBy(criteriaBuilder.desc(c.get(idKey)));
        return getEntityManager().createQuery(q).getResultList();
    }

    public List<T> findAllActivos() {
        javax.persistence.criteria.CriteriaQuery q = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        Root<T> c = q.from(entityClass);
        q.select(c);
        ParameterExpression<Boolean> p = getEntityManager().getCriteriaBuilder().parameter(Boolean.class);
        q.where(
                getEntityManager().getCriteriaBuilder().equal(c.get("activo"), true)
        );
        return getEntityManager().createQuery(q).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
