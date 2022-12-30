/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coba_Ujian_Dua.ws;

import Coba_Ujian_Dua.ws.exceptions.NonexistentEntityException;
import Coba_Ujian_Dua.ws.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author TUF GAMING
 */
public class BuahJpaController implements Serializable {

    public BuahJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Coba_Ujian_Dua_ws_jar_0.0.1-SNAPSHOTPU");
    
    public BuahJpaController(){}

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Buah buah) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(buah);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBuah(buah.getIdBuah()) != null) {
                throw new PreexistingEntityException("Buah " + buah + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Buah buah) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            buah = em.merge(buah);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = buah.getIdBuah();
                if (findBuah(id) == null) {
                    throw new NonexistentEntityException("The buah with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Buah buah;
            try {
                buah = em.getReference(Buah.class, id);
                buah.getIdBuah();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The buah with id " + id + " no longer exists.", enfe);
            }
            em.remove(buah);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Buah> findBuahEntities() {
        return findBuahEntities(true, -1, -1);
    }

    public List<Buah> findBuahEntities(int maxResults, int firstResult) {
        return findBuahEntities(false, maxResults, firstResult);
    }

    private List<Buah> findBuahEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Buah.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Buah findBuah(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Buah.class, id);
        } finally {
            em.close();
        }
    }

    public int getBuahCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Buah> rt = cq.from(Buah.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
