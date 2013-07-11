package entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * entities.User: dkorolev
 * Date: 05.06.13
 * Time: 13:35
 * To change this template use File | Settings | File Templates.
 * <p/>
 * This class represents Object LETTER
 *
 * @author D.Korolev
 * @version 1.0
 */


@Entity
@NamedQueries({
        @NamedQuery(name = "findLettersByFolderInId", query = "select l from Letter l where letterOwnerTo=:fIn"),
        @NamedQuery(name = "findOutLettersByFolderInId", query = "select l from Letter l where letterOwnerFrom=:fOut"),
        //@NamedQuery(name = "deleteSelectedLettersByLetterId", query = "delete entities.Letter where idLetters=:idLet")
        //@NamedQuery(name = "findAllUsers", query = "SELECT c FROM entities.User c")
})
@Table(name="LETTERS")
public class Letter implements Serializable {
    /* Class can be serialized */

    /**
     * Class fields
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idLetters;  //id

    private String fromEmail;        //EmailAddress from whom
    private String toEmail;          //EmailAddress to who
    private String date;            //Date, when entities.Email was sent
    private String subject;         //Subject of entities.Email
    private String body;            //Body of entities.Email
    private Integer letterOwnerTo;  //id EmailTo(inbox)
    private Integer letterOwnerFrom;//id EmailFrom(sent)


    /**
     * Constructors
     */
    public  Letter() {
        this(null,null,null,null,null);
    }

    public Letter(String f, String t, String d) {
        this(f, t, d, null, null);
    }

    public Letter(String f, String t, String d, String s) {
        this(f, t, d, s, null);
    }

    public Letter(String f, String t, String d, String s, String b) {
        this.fromEmail = f;
        this.toEmail = t;
        this.date = d;
        this.subject = s;
        this.body = b;
    }


    /**
     * Getters and Setters
     */
    public Integer getIdLetters() {
        return idLetters;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String f) {
        this.fromEmail = f;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmailo(String t) {
        this.toEmail = t;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String d) {
        this.date = d;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String s) {
        this.subject = s;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String b) {
        this.body = b;
    }

    public Integer getLetterOwnerTo() {
        return letterOwnerTo;
    }

    public void setLetterOwnerTo(Integer t) {
        this.letterOwnerTo = t;
    }

    public Integer getLetterOwnerFrom() {
        return letterOwnerFrom;
    }

    public void setLetterOwnerFrom(Integer f) {
        this.letterOwnerFrom = f;
    }

}
