package entities;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * entities.User: dkorolev
 * Date: 14.06.13
 * Time: 14:03
 * To change this template use File | Settings | File Templates.
 */
public class AllEntities implements Serializable {
    private User user;
    private Email email;
    private Folder folder;
    private Letter letter;

    private List<Email> emails;
    private List<Folder> folders;

    //Constructors

    public AllEntities(List<Folder> fs) {
        this(null,null,fs,null);
    }

    public AllEntities(List<Email> es, List<Folder> fs) {
        this(null,es,fs,null);
    }

    public AllEntities(User u, List<Email> es, List<Folder> fs, Letter l) {
        this.user = u;
        //this.email = e;
        this.emails = es;
//        this.folder = f;
        this.folders = fs;
        this.letter = l;
    }


    /**
     * Getters and Setters
     */
    public User getUser() {
        return user;
    }

    public Email getEmail() {
        return email;
    }

    public Folder getFolder() {
        return folder;
    }

    public Letter getLetter() {
        return letter;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public List<Folder> getFolders() {
        return folders;
    }
}
