package daoImpl;

import dao.UserDAO;
import entities.User;
import singleton.EntityManagerFactorySingleton;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * entities.User: dkorolev
 * Date: 06.06.13
 * Time: 20:51
 * To change this template use File | Settings | File Templates.
 * <p/>
 * This class represents Hibernate implementation for dao.UserDAO
 *
 * @author Korolev D.
 * @version 1.0
 */

@Stateless
public class JPAUserDAO implements UserDAO {
   // @PersistenceContext
    User user;

    EntityManager em = EntityManagerFactorySingleton.emf.createEntityManager();
    EntityTransaction trx = null;

    /**
     * implementation method getAllUser
     */
    @Override
    public List getAllUsers() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * implementation method getUser(Integer id) from dao.UserDAO
     * to get entities.User by his Id from DB
     */
    @Override
    public User getUser(Integer id) {
        user = new User();
        //clear - to clean current EntityManager
        em.clear();
        //create transaction
        trx = em.getTransaction();

        try {
            trx.begin();
            //using find method
            user = em.find(User.class, id);

//        System.out.println("name:" + user.getName() + " surname:" + user.getSurname());
            trx.commit();
            System.out.println("Transaction is committed");
        } finally {
            //best practise - rollback, if transaction wasn't successful
            if (trx.isActive()) {
                System.err.println("Rollback transaction");
                trx.rollback();
            }
            em.close();
//            emf.close();
        }
        return user;  //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * implementation method getUser(Integer id) from dao.UserDAO
     * to get entities.User by his Id from DB
     */
    @Override
    public User getUser(String n, String s) {
        User u = null;
        em.clear();
        List list = null;
        TypedQuery<User> query = em.createNamedQuery("findUserByNameSurname", User.class);
        query.setParameter("n", n);
        query.setParameter("s", s);
        trx = em.getTransaction();
        trx.begin();
        list = query.getResultList();
        trx.commit();

        if (list != null) {
            Iterator<User> it = list.iterator();
            while (it.hasNext()) {
                u = it.next();
                System.out.println("huUserDAO: " +u.getName() + "   " + u.getSurname());
            }
        }
        if (u==null) {
            System.out.println("huUserDAO: u == null");
        }
        em.close();
//        emf.close();
        return u;
    }

    /**
     * implementation method insert(entities.User us)
     * to add new entities.User to DB (registration on main UI screen
     */
    @Override
    public Boolean insert(User us) {
        //clean EntityManager
        em.clear();
        trx = em.getTransaction();

        try {
            trx.begin();
            em.persist(us);
            trx.commit();
            System.out.println("entities.User: name-" + us.getName() + " surname-" + us.getSurname() + "was added");
        } finally {
            if (trx.isActive()) {
                trx.rollback();
                System.err.println("Insert was rollbacked");
                return false;
            }
            em.close();
//            emf.close();
        }

        return true;
    }
}
