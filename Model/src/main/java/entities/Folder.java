package entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * entities.User: dkorolev
 * Date: 12.06.13
 * Time: 16:41
 * To change this template use File | Settings | File Templates.
 */

@Entity
@NamedQueries({
        @NamedQuery(name = "findFolderByEmailID", query = "select f from Folder f where emailAddressId=:e")
        //@NamedQuery(name = "renameFolderByFolderID", query = "update entities.Folder set folderName=:folName where idFolders=:idFol")
})
@Table(name = "FOLDERS")

public class Folder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idFolders;

    private String folderName;
    private Integer emailAddressId;


    /**
     * Constructors
     */
    public Folder() {
        this(null, null);
    }

    public Folder(String n) {
        this(n,null);
    }


    public Folder(String n, Integer e) {
        this.folderName = n;
        this.emailAddressId = e;
    }


    /**
     * Getters and Setters
     */
    public Integer getIdFolders() {
        return idFolders;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String n) {
        this.folderName = n;
    }

    public Integer getEmailAddress() {
        return emailAddressId;
    }

    public void setEmailAddress(Integer e) {
        this.emailAddressId = e;
    }

}
