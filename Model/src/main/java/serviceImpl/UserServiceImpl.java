package serviceImpl;

//import daoImpl.JPAEmailDAO;
//import daoImpl.JPAFolderDAO;

import daoImpl.JPAEmailDAO;
import daoImpl.JPAFolderDAO;
import daoImpl.JPAUserDAO;
import entities.Email;
import entities.Folder;
import entities.User;
import service.UserService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

//import daoImpl.JdbcUserDAO;
//import entities.Email;
//import entities.Folder;

/**
 * Created with IntelliJ IDEA.
 * entities.User: dkorolev
 * Date: 06.06.13
 * Time: 14:38
 * To change this template use File | Settings | File Templates.
 * <p/>
 * This class represents implementaion of services.UserService interface to work with entities.User at Service layer
 *
 * @author D.Korolev
 * @version 1.0
 */
@Stateless  (name = "UserServiceImpl")
public class UserServiceImpl implements UserService,Serializable {
//    JdbcUserDAO juDAO = new JdbcUserDAO();

//    @EJB
//    private JPAUserDAO jpaUserDAO;
//    private JPAUserDAO jpaUDAO = new JPAUserDAO();
//    JPAEmailDAO jpaEDAO = new JPAEmailDAO();
//    JPAFolderDAO jpaFDAO = new JPAFolderDAO();
   private boolean status = false;

    public static final String JNDI_NAME = "UserServiceImpl";

    @Override
    public User getUser(Integer id) {
        return new JPAUserDAO().getUser(id);
    }

    @Override
    public User getUser(String n, String s) {
        return new JPAUserDAO().getUser(n, s);
    }

    @Override
   public boolean insert(User us) {
        status = new JPAUserDAO().insert(us);
        return status;
    }

    @Override
   public boolean createUser(User us) {
        String nameUser = us.getName();
        String surnameUser = us.getSurname();
        User testU;
        Email testE;
        Folder testF;
        //prove that user is new
        if (new JPAUserDAO().getUser(nameUser, surnameUser) == null) {
            //user insert
            status = new JPAUserDAO().insert(us);
            System.out.println("After insert entities.User: " +status);
            //get user by Name,Surname
            testU = new JPAUserDAO().getUser(nameUser, surnameUser);
            System.out.println("After get user: " +testU.getIdUsers() + " " + testU.getSurname());
            //create EmailObject and insert entities.Email with entities.User id
            testE = new Email(testU.getSurname()+"@demail.com", DateFormat.getDateTimeInstance().format(new Date().getTime()), testU.getIdUsers());
            System.out.println(testE.getUniqAddress() +"  "+testE.getCreateDate() + "  " + testE.getEmailOwner());
            status = new JPAEmailDAO().insert(testE);
            System.out.println("After insert entities.Email: " +status);
            //create FolderObject and insert entities.Folder with EmailAddress id and entities.User id
            testF = new Folder(testU.getSurname(), testE.getIdEmails());
            System.out.println(testF.getFolderName() +"  "+ testF.getEmailAddress());
            status =  new JPAFolderDAO().insert(testF);
            System.out.println("After insert entities.Folder: " +status);

            return status;
        } else {
            System.out.println("This user exists in DB");
            return status;
        }
    }
}
