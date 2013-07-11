package dao;

import entities.User;

import javax.ejb.Local;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * entities.User: dkorolev
 * Date: 06.06.13
 * Time: 13:21
 * To change this template use File | Settings | File Templates.
 *
 * This interface represents dao layer to work with entities.User
 * @author D.Korolev
 * @version 1.0
 */

public interface UserDAO {
    /** get all entities.User from DB*/
    public List getAllUsers();
    /**get one entities.User per idUser */
    public User getUser(Integer id);
    public User getUser(String n, String s);
    /** insert entities.User in DB*/
    public Boolean insert(User us);
//    public void update(entities.User us);
//    public void delete(entities.User us);
}
