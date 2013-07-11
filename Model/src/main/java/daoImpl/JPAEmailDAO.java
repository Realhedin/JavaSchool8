package daoImpl;

import dao.EmailDAO;
import entities.Email;
import singleton.EntityManagerFactorySingleton;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * entities.User: dkorolev
 * Date: 12.06.13
 * Time: 15:11
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class JPAEmailDAO implements EmailDAO {
    Email email;

    EntityManager em = EntityManagerFactorySingleton.emf.createEntityManager();
    EntityTransaction trx = null;

    /**
     * Get entities.Email by userId
     *
     * @param userId
     * @return entities.Email object
     */
    @Override
    public Email getEmail(Integer userId) {
        em.clear();
        trx = em.getTransaction();
        try {
            List list = null;
            TypedQuery<Email> query = em.createNamedQuery("findEmailByUserId", Email.class);
            query.setParameter("emailOwner", userId);
            trx.begin();
            list = query.getResultList();
            trx.commit();
            System.out.println("entities.Email insert was committed");
            if (list != null) {
                Iterator<Email> it = list.iterator();
                while (it.hasNext()) {
                    email = it.next();
                    System.out.println("jpaEmailDAO: " + email.getUniqAddress() + "   " + email.getCreateDate());
                }
            }
            if (email == null) {
                System.out.println("jpaEmailDAO: email == null");
            }

        } finally {
            if (trx.isActive()) {
                trx.rollback();
                System.out.println("entities.Email insert was rollbacked");
                return null;
            }
            em.close();
//            emf.close();
            return email;
        }
    }

    /**
     * get entities.Email by uniqAddress
     *
     * @param uniqAddr
     * @return
     */
    @Override
    public Email getEmail(String uniqAddr) {
        em.clear();
        trx = em.getTransaction();
        try {
            TypedQuery<Email> query = em.createNamedQuery("findEmailByUniqAdress", Email.class);
            query.setParameter("uniqAddr", uniqAddr);
            trx.begin();
            email = query.getSingleResult();
            trx.commit();
        } finally {
            if (trx.isActive()) {
                trx.rollback();
                System.out.println("entities.Email insert was rollbacked");
                return null;
            }
            em.close();
//            emf.close();
            return email;
        }
    }

    @Override
    public List<Email> getAllEmails(Integer userId) {
        em.clear();
        trx = em.getTransaction();
        List list = null;
        try {
            TypedQuery<Email> query = em.createNamedQuery("findEmailByUserId", Email.class);
            query.setParameter("emailOwner", userId);
            trx.begin();
            list = query.getResultList();
            trx.commit();
            System.out.println("entities.Email insert was committed");
            if (list != null) {
                return list;
            }
        } finally {
            if (trx.isActive()) {
                trx.rollback();
                System.out.println("entities.Email insert was rollbacked");
                return null;
            }
            em.close();
//            emf.close();
        }
        return list;
    }

    /**
     * This method insert entities.Email in BD
     *
     * @param e - email to insert
     * @return status of inner operation
     */
    @Override
    public Boolean insert(Email e) {
        em.clear();
        trx = em.getTransaction();

        try {
            trx.begin();
            em.persist(e);
            trx.commit();
            System.out.println("entities.Email insert was commited");
        } finally {
            if (trx.isActive()) {
                trx.rollback();
                System.out.println("entities.Email insert was rollbacked");
                return false;
            }
            em.close();
//            emf.close();
        }
        return true;
    }

    @Override
    public Boolean deleteEmail(Integer emailId) {
        em.clear();
        trx = em.getTransaction();
        //TypedQuery<entities.Letter> query = em.createNamedQuery("deleteSelectedLettersByLetterId",entities.Letter.class);
        //query.setParameter("idLet",id);
        Query query = em.createQuery("delete Email where idEmails = :idEm");
        query.setParameter("idEm", emailId);
        try {
            trx.begin();
            int result = query.executeUpdate();
            trx.commit();
            System.out.println("entities.Email delete was commited");
        } finally {
            if (trx.isActive()) {
                trx.rollback();
                System.out.println("entities.Email delete was rollbacked");
                return false;
            }
            em.close();
//            emf.close();
        }
        return true;
    }

    //getEmailAdress by emailId
    @Override
    public String getEmailAddressByEmailId(Integer emailId) {
        em.clear();
        trx = em.getTransaction();
        String tempEmAddr;
        Query query = em.createQuery("select e.uniqAddress from Email e where idEmails = :idEm");
        query.setParameter("idEm", emailId);
        try {
            trx.begin();
            tempEmAddr = (String)query.getSingleResult();
            trx.commit();
            System.out.println("entities.Email delete was commited");
        } finally {
            if (trx.isActive()) {
                trx.rollback();
                System.out.println("entities.Email delete was rollbacked");
                return null;
            }
            em.close();
//            emf.close();
        }
        return tempEmAddr;
    }
}
