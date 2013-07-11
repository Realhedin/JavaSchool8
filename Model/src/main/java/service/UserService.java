package service;

import entities.User;

import javax.ejb.Local;
import javax.ejb.Remote;

/**
 * Created with IntelliJ IDEA.
 * entities.User: dkorolev
 * Date: 06.06.13
 * Time: 14:36
 * To change this template use File | Settings | File Templates.
 *
 * This interface represents Service level to work with entities.User
 * @author D.Korolev
 * @version 1.0.
 */
@Local
public interface UserService {
    //    public List getAllUsers();
    public User getUser(Integer id);
    public User getUser(String n, String s);
    public boolean insert(User us);
    public boolean createUser(User us);
//    public void update(entities.User us);
//    public void delete(entities.User us);
}
