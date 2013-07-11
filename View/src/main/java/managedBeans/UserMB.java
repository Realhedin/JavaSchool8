package managedBeans;

import entities.Email;
import entities.Folder;
import entities.Letter;
import entities.User;
import org.apache.log4j.Logger;
import serviceImpl.EmailServiceImpl;
import serviceImpl.FolderServiceImpl;
import serviceImpl.LetterServiceImpl;
import serviceImpl.UserServiceImpl;

import javax.ejb.EJB;
//import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dkorolev
 * Date: 04.07.13
 * Time: 13:02
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean //(name = "userMB")
@SessionScoped
//@RequestScoped
//@javax.enterprise.context.SessionScoped
//@Named
public class UserMB implements Serializable {

   // @EJB(beanName = UserServiceImpl.JNDI_NAME)
//    @Inject
    @EJB
    private UserServiceImpl userService;

    @EJB
    private EmailServiceImpl emailService; //= new EmailServiceImpl();

    @EJB
    private LetterServiceImpl letterService; //= new LetterServiceImpl();

    @EJB
    private FolderServiceImpl folderService; // = new FolderServiceImpl();

    private User user;
    private String n;
    private String s;
    private List<Email> emails;
    private List<Folder> folders;
    private List<Letter> letters;
    private Email email = null;
    private Folder folder = null;
    private Letter letter = null;
    private Boolean loginBool = false;
    private static Logger logger = Logger.getLogger("name");

    //to validate Login by User from DB
    public Boolean loginUser() {
       // if ((n != null) && (s != null)) {
            logger.info("USERNAME: " + user.getName() + "  " + user.getSurname());
            loginBool = false;
            User user = userService.getUser(n, s);
            if ((user.getName().equals(n)) && (user.getSurname().equals(s))) {
                loginBool = true;
                emails = emailService.getAllEmails(user.getIdUsers());
                Iterator<Email> it = emails.iterator();
                while (it.hasNext()) {
                    email = it.next();
                    logger.info("EMAIL: " + "Id: " + email.getIdEmails() + "  Addr: " + email.getUniqAddress());
                    folder = new FolderServiceImpl().getFolder(email.getIdEmails());
                    logger.info("FOLDER: " + " Id:" + folder.getIdFolders() + "  Name:" + folder.getFolderName());
                    System.out.println("entities.Folder:" + folder.getFolderName());
                    folders.add(folder);
                }
                if (folder != null) {
                    letters = letterService.getLetters(folder.getIdFolders());
                }
                return true;
            }
   //     }
        return false;
    }


    public User getUser() {
        if (user == null) {
            user = new User();
        }
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }

    public String getUserName() {
        return user.getName();
    }

    public void setUserName(String name) {
        this.user.setName(name);
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    public Letter getLetter() {
        return letter;
    }

    public void setLetter(Letter letter) {
        this.letter = letter;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public List<Folder> getFolders() {
        return folders;
    }

    public void setFolders(List<Folder> folders) {
        this.folders = folders;
    }

    public List<Letter> getLetters() {
        return letters;
    }

    public void setLetters(List<Letter> letters) {

        this.letters = letters;
    }

    public UserServiceImpl getUserService() {
        return userService;
    }

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

//    private FacesContext getContext() {
//        FacesContext context = FacesContext.getCurrentInstance();
//        return context;
//    }

}
