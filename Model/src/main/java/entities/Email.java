package entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * entities.User: dkorolev
 * Date: 11.06.13
 * Time: 16:35
 * To change this template use File | Settings | File Templates.
 * <p/>
 * This class represents EMAIL entity
 *
 * @author D.Korolev
 * @version 1.0
 */
@Entity
@NamedQueries( {
        @NamedQuery(name = "findEmailByUserId", query = "select e from Email e where emailOwner=:emailOwner"),
        @NamedQuery(name = "findEmailByUniqAdress", query = "select e from Email e where uniqAddress=:uniqAddr")
})
@Table(name = "EMAILS")
public class Email implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idEmails;

    private String uniqAddress;
    private String createDate;
    private Integer emailOwner;


    /**
     * Constructors
     */

    public Email() {
        this(null, null, null);
    }

    public Email(String u, String c, Integer e) {
        this.uniqAddress = u;
        this.createDate = c;
        this.emailOwner = e;
    }


    /**
     * Getters and Setters
     */
    public Integer getIdEmails() {
        return idEmails;
    }

    public String getUniqAddress() {
        return uniqAddress;
    }

    public void setUniqAdress(String u) {
        this.uniqAddress = u;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String d) {
        this.createDate=d;
    }

    public Integer getEmailOwner() {
        return emailOwner;
    }

    public void setEmailOwner(Integer e) {
        this.emailOwner = e;
    }
}
