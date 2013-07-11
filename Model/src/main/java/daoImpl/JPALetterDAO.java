package daoImpl;

import dao.LetterDAO;
import entities.Letter;
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
 * Date: 13.06.13
 * Time: 13:37
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class JPALetterDAO implements LetterDAO {
    List<Letter> list;
    Letter let;


    EntityManager em = EntityManagerFactorySingleton.emf.createEntityManager();
    EntityTransaction trx = null;

    /**
     * Method get all letters, which are connected with inbox folder
     *
     * @param folderId
     * @return List of entities.Letter objects
     */
    @Override
    public List<Letter> getLetters(Integer folderId) {
        em.clear();
        trx = em.getTransaction();
        try {
            //List list = null;
            TypedQuery<Letter> query = em.createNamedQuery("findLettersByFolderInId", Letter.class);
            query.setParameter("fIn", folderId);
            trx.begin();
            list = query.getResultList();
            trx.commit();
            System.out.println("IncomingLetters were selected");
            if (list != null) {
                Iterator<Letter> it = list.iterator();
                while (it.hasNext()) {
                    let = it.next();
                    System.out.println("jpaFolderDAO: " + let.getFromEmail() + "   " + let.getSubject());
                }
            }
            if (let == null) {
                System.out.println("jpaFolderDAO: letter == null");
            }

        } finally {
            if (trx.isActive()) {
                trx.rollback();
                System.out.println("Select IncomingLetters was rollbacked");
                return null;
            }
//            emf.close();
            em.close();
        }
        return list;
    }

    /**
     * Get all Outgoing Letters for current entities.Folder
     *
     * @param folderId
     * @return list of Outgoing letters
     */
    @Override
    public List<Letter> getOutLetters(Integer folderId) {
        em.clear();
        trx = em.getTransaction();
        try {
            //List list = null;
            TypedQuery<Letter> query = em.createNamedQuery("findOutLettersByFolderInId", Letter.class);
            query.setParameter("fOut", folderId);
            trx.begin();
            list = query.getResultList();
            trx.commit();
            System.out.println("OutgoingLetters were selected");
            if (list != null) {
                Iterator<Letter> it = list.iterator();
                while (it.hasNext()) {
                    let = it.next();
                    System.out.println("jpaFolderDAO: " + let.getFromEmail() + "   " + let.getSubject());
                }
            }
            if (let == null) {
                System.out.println("jpaFolderDAO: letter == null");
            }

        } finally {
            if (trx.isActive()) {
                trx.rollback();
                System.out.println("Select OutgoingLetters was rollbacked");
                return null;
            }
            em.close();
//            emf.close();
        }
        return list;
    }


    /**
     * Insert letter in DB
     *
     * @param l - entities.Letter
     * @return status of operation
     */
    @Override
    public Boolean insert(Letter l) {
        em.clear();
        trx = em.getTransaction();

        try {
            trx.begin();
            em.persist(l);
            trx.commit();
            System.out.println("entities.Letter insert was commited");
        } finally {
            if (trx.isActive()) {
                trx.rollback();
                System.out.println("entities.Letter insert was rollbacked");
                return false;
            }
            em.close();
//            emf.close();
        }
        return true;
    }

    @Override
    public Boolean delete(Integer id) {
        em.clear();
        trx = em.getTransaction();
        //TypedQuery<entities.Letter> query = em.createNamedQuery("deleteSelectedLettersByLetterId",entities.Letter.class);
        //query.setParameter("idLet",id);
        Query query = em.createQuery("delete Letter where idLetters = :id");
        query.setParameter("id",id);
        try {
            trx.begin();
            int result = query.executeUpdate();
            trx.commit();
            System.out.println("entities.Letter delete was commited");
        } finally {
            if (trx.isActive()) {
                trx.rollback();
                System.out.println("entities.Letter delete was rollbacked");
                return false;
            }
            em.close();
//            emf.close();
        }
        return true;
    }

    @Override
    public Boolean changeAttr(Integer letterId, String uniqAddr, Integer folderId) {
        em.clear();
        trx = em.getTransaction();
        //TypedQuery<entities.Letter> query = em.createNamedQuery("deleteSelectedLettersByLetterId",entities.Letter.class);
        //query.setParameter("idLet",id);
        Query query = em.createQuery("update Letter set toEmail=:toE, letterOwnerTo =:OwnTo where idLetters = :id");
        query.setParameter("id",letterId);
        query.setParameter("toE",uniqAddr);
        query.setParameter("OwnTo",folderId);
        try {
            trx.begin();
            int result = query.executeUpdate();
            trx.commit();
            System.out.println("entities.Letter delete was commited");
        } finally {
            if (trx.isActive()) {
                trx.rollback();
                System.out.println("entities.Letter delete was rollbacked");
                return false;
            }
            em.close();
//            emf.close();
        }
        return true;
    }

}
