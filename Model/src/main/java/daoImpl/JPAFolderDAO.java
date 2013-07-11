package daoImpl;

import dao.FolderDAO;
import entities.Folder;
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
 * Time: 16:53
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class JPAFolderDAO implements FolderDAO {
    Folder folder;

    EntityManager em = EntityManagerFactorySingleton.emf.createEntityManager();
    EntityTransaction trx = null;


    /**
     * to get entities.Folder by EmailID
     * @param emailId
     * @return folder
     */
    @Override
    public Folder getFolder(Integer emailId) {
        em.clear();
        trx = em.getTransaction();
        try {
            List list = null;
            TypedQuery<Folder> query = em.createNamedQuery("findFolderByEmailID", Folder.class);
            query.setParameter("e", emailId);
            trx.begin();
            list = query.getResultList();
            trx.commit();
            System.out.println("entities.Folder insert was commited");
            if (list != null) {
                Iterator<Folder> it = list.iterator();
                while (it.hasNext()) {
                    folder = it.next();
                    System.out.println("jpaFolderDAO: " +folder.getFolderName() + "   " + folder.getEmailAddress());
                }
            }
            if (folder==null) {
                System.out.println("jpaFolderDAO: folder == null");
            }

        } finally {
            if (trx.isActive()) {
                trx.rollback();
                System.out.println("entities.Folder insert was rollbacked");
                return null;
            }
        em.close();
//        emf.close();
        }
        return folder;
    }


    /**
     * insert new folder
     * @param f
     * @return result of transaction
     */
    @Override
    public Boolean insert(Folder f) {
        em.clear();
        trx = em.getTransaction();

        try {
            trx.begin();
            em.persist(f);
            trx.commit();
            System.out.println("entities.Folder insert was commited");
        } finally {
            if (trx.isActive()) {
                trx.rollback();
                System.out.println("entities.Folder insert was rollbacked");
                return false;
            }
            em.close();
 //           emf.close();
        }
        return true;
    }


    /**
     * Rename entities.Folder name
     */
    @Override
    public Boolean renameFolder(Integer folderID, String folderName) {
        em.clear();
        trx = em.getTransaction();
        Query query = em.createQuery("update Folder set folderName=:folName where idFolders=:idFol");
        query.setParameter("folName",folderName);
        query.setParameter("idFol",folderID);

        try {
            trx.begin();
            int res = query.executeUpdate();
            trx.commit();
            System.out.println("entities.Folder rename was commited");
        } finally {
            if (trx.isActive()) {
                trx.rollback();
                System.out.println("entities.Folder rename was rollbacked");
                return false;
            }
            em.close();
 //           emf.close();
        }
        return true;
    }

    @Override
    public Boolean deleteFolder(Integer folderId) {
        em.clear();
        trx = em.getTransaction();
        Query query = em.createQuery("delete Folder where idFolders = :idFol");
        query.setParameter("idFol",folderId);

        try {
            trx.begin();
            int res = query.executeUpdate();
            trx.commit();
            System.out.println("entities.Folder delete was commited");
        } finally {
            if (trx.isActive()) {
                trx.rollback();
                System.out.println("entities.Folder delete was rollbacked");
                return false;
            }
            em.close();
 //           emf.close();
        }
        return true;
    }

    @Override
    public Integer getEmailIdByFolderId(Integer folderId) {
        em.clear();
        trx = em.getTransaction();
        Integer tempEmailId;
        Query query = em.createQuery("select f.emailAddressId from Folder f where idFolders=:idF");
        query.setParameter("idF",folderId);

        try {
            trx.begin();
            tempEmailId = (Integer)query.getSingleResult();
            trx.commit();
            System.out.println("Find emailId was commited");
        } finally {
            if (trx.isActive()) {
                trx.rollback();
                System.out.println("Find emailId was rollbacked");
                return null;
            }
            em.close();
  //          emf.close();
        }
        return tempEmailId;
    }
}

