package serviceImpl;

import daoImpl.JPAEmailDAO;
import daoImpl.JPAFolderDAO;
import daoImpl.JPALetterDAO;
import daoImpl.JPAUserDAO;
import entities.Email;
import entities.Folder;
import entities.Letter;
import entities.User;
import service.LetterService;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * entities.User: dkorolev
 * Date: 13.06.13
 * Time: 14:45
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class LetterServiceImpl implements LetterService {
    List<Letter> letters = null;
    JPAUserDAO jpaUDAO = new JPAUserDAO();
    JPAEmailDAO jpaEDAO = new JPAEmailDAO();
    JPAFolderDAO jpaFDAO = new JPAFolderDAO();
    JPALetterDAO jpaLDAO = new JPALetterDAO();

    /**
     * get letters for folder
     * (get userId, find emailId, find folderId, find letters)
     *
     * @param u - user
     * @return list of Letters
     */
    @Override
    synchronized public List<Letter> getLetters(User u) {
        Email e = null;
        List<Email> el;
        Folder f = null;
        Letter l = null;
        Integer temp = u.getIdUsers();
        System.out.println("servicesImpl.LetterServiceImpl:  UserId:" +u.getIdUsers());
        el = new JPAEmailDAO().getAllEmails(temp);
        temp = 100000;
        Iterator<Email> it = el.iterator();
        while(it.hasNext()) {
           e = it.next();
            //choose smaller id(per createdBydefault)
            if (e.getIdEmails()<temp) {
            temp = e.getIdEmails();
            }
        }

        f = new JPAFolderDAO().getFolder(temp);
        temp = f.getIdFolders();
        System.out.println("servicesImpl.LetterServiceImpl: FolderId" + f.getIdFolders());
        letters = new JPALetterDAO().getLetters(temp);

        Iterator<Letter> iterator = letters.iterator();
        while(iterator.hasNext()) {
            l = iterator.next();
            System.out.println("servicesImpl.LetterServiceImpl: From:" +l.getFromEmail() +"  To:"+l.getToEmail() +
                    " Subject:"+l.getSubject() + "  Body:"+l.getBody());
        }

        return letters;
    }

    /**
     * get OutgoingLetters for current entities.Folder
     * @param u - current entities.User
     * @return
     */
    @Override
   synchronized public List<Letter> getOutLetters(User u) {
        Email e = null;
        List<Email> el;
        Folder f = null;
        Letter l = null;
        Integer temp = u.getIdUsers();
        System.out.println("servicesImpl.LetterServiceImpl:  UserId:" +u.getIdUsers());
        el = new JPAEmailDAO().getAllEmails(temp);
        temp = 100000;
        Iterator<Email> it = el.iterator();
        while(it.hasNext()) {
            e = it.next();
            //choose smaller id(per createdBydefault)
            if (e.getIdEmails()<temp) {
                temp = e.getIdEmails();
            }
        }
        f = new JPAFolderDAO().getFolder(temp);

        temp = f.getIdFolders();
        System.out.println("servicesImpl.LetterServiceImpl: FolderId" + f.getIdFolders());
        letters = new JPALetterDAO().getOutLetters(temp);

        Iterator<Letter> iterator = letters.iterator();
        while(iterator.hasNext()) {
            l = iterator.next();
            System.out.println("servicesImpl.LetterServiceImpl: From:" +l.getFromEmail() +"  To:"+l.getToEmail() +
                    " Subject:"+l.getSubject() + "  Body:"+l.getBody());
        }

        return letters;
    }

    @Override
    synchronized public List<Letter> getLetters(Integer folderInId) {
        return new JPALetterDAO().getLetters(folderInId);
    }

    @Override
    synchronized public List<Letter> getOutLetters(Integer folderOutId) {
        return new JPALetterDAO().getOutLetters(folderOutId);
    }

    @Override
   synchronized public Boolean insert(Letter l) {
        return new JPALetterDAO().insert(l);
    }

    @Override
   synchronized public Boolean delete(Integer id) {
        return new JPALetterDAO().delete(id);
    }

    @Override
   synchronized public Boolean moveLetterToFolder(ArrayList<Integer> ls, Integer folderId) {
        Boolean st = false;
        Integer tempEmId = new JPAFolderDAO().getEmailIdByFolderId(folderId);
        String tempEmAddr = new JPAEmailDAO().getEmailAddressByEmailId(tempEmId);

        //change entities.Letter attributes in loop
        Iterator<Integer> iter = ls.iterator();
        while (iter.hasNext()) {
            st = new JPALetterDAO().changeAttr(iter.next(),tempEmAddr,folderId);
        }
        return st;
    }
}
