package entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: dkorolev
 * Date: 04.07.13
 * Time: 12:54
 * To change this template use File | Settings | File Templates.
 */

@Entity
@NamedQueries(   {
        @NamedQuery(name = "findAllUsers", query = "SELECT c FROM User c"),
        @NamedQuery(name = "findUserByNameSurname", query = "select u FROM User u where u.name = :n and u.surname = :s")
})
@Table(name="USERS")
public class User implements Serializable {
    /* Class can be serialized */

    /**
     * Class fields
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUsers;

    private String name;
    private String surname;
    private String birthday;
    private Long phone;
    //  private Integer operation;

    /**
     * Constructors with fields
     */
    public User() {
        this(null,null,null,null);
    }

    public User(String n, String sur) {
        this(n, sur, null, null);
    }

    public User(String n, String sur, String bir, Long p) {
        this.name = n;
        this.surname = sur;
        this.birthday = bir;
        this.phone = p;
    }

    public String toString() {
        return ("Name: " +this.getName() +" Surname: " + this.getSurname() + " Birthday: " + this.getBirthday() + " Phone: " + this.getPhone());
    }

    /**
     * Setters and Getters
     */
    public Integer getIdUsers() {
        return  idUsers;
    }

    public void setIdUsers(Integer i) {
        this.idUsers=i;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        this.name = n;
    }

    public String getSurname() {
        return  surname;
    }

    public void setSurname(String s) {
        this.surname = s;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String b) {
        this.birthday = b;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long p) {
        this.phone = p;
    }
}

