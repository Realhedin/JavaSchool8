package serviceImpl;

import daoImpl.JPAEmailDAO;
import daoImpl.JPAFolderDAO;
import daoImpl.JPALetterDAO;
import entities.Email;
import entities.Folder;
import entities.Letter;
import service.FolderService;

import javax.ejb.Stateless;
import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * entities.User: dkorolev
 * Date: 14.06.13
 * Time: 13:12
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class FolderServiceImpl implements FolderService {
    //JPAFolderDAO jpaFDAO = new JPAFolderDAO();
    //JPAEmailDAO jpaEDAO = new JPAEmailDAO();
    //JPALetterDAO jpaLDAO = new JPALetterDAO();

    @Override
    synchronized public Folder getFolder(Integer emailId) {
        return new JPAFolderDAO().getFolder(emailId);
    }

    @Override
    synchronized public Boolean createFolder(Integer userId, String folderName) {
        Email tempE;
        Folder tempF;
        Boolean st;
        tempE = new Email(folderName+"@demail.com", DateFormat.getDateTimeInstance().format(new Date().getTime()),userId);
        st = new JPAEmailDAO().insert(tempE);
        System.out.println("CreateFolder->entities.Email creation: "+st);
        tempE = new JPAEmailDAO().getEmail(folderName+"@demail.com");
        tempF = new Folder(folderName,tempE.getIdEmails());
        st = new JPAFolderDAO().insert(tempF);
        System.out.println("CreateFolder->entities.Folder creation: "+st);
        return st;
    }

    @Override
    synchronized public Boolean renameFolder(Integer folderID, String folderName) {
        return new JPAFolderDAO().renameFolder(folderID,folderName);
    }

    @Override
    synchronized public Boolean deleteFolder(Integer folderId, Integer emailId) {
        Boolean st=false;
        List<Letter> ls;
        Letter l;
        //get List<entities.Letter> for current folderId(where folderId==LetterOwnerTo)
        ls = new JPALetterDAO().getLetters(folderId);
         //delete each entities.Letter for List<entities.Letter>
        Iterator<Letter> iter = ls.iterator();
        while (iter.hasNext()) {
                 l = iter.next();
            st = new JPALetterDAO().delete(l.getIdLetters());
        }
        //delete entities.Folder by folderId
        st = new JPAFolderDAO().deleteFolder(folderId);
        //delete entities.Email by emailId
        st = new JPAEmailDAO().deleteEmail(emailId);

        return st;
    }
}
