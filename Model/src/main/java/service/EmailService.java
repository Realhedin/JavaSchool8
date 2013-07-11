package service;

import entities.Email;

import javax.ejb.Local;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * entities.User: dkorolev
 * Date: 14.06.13
 * Time: 11:42
 * To change this template use File | Settings | File Templates.
 */
@Local
public interface EmailService {
    public Email getEmail(Integer userId);
    public Email getEmail(String uniqAddr);

    //get all List<Emails> by idUser
    public List<Email> getAllEmails(Integer userId);
}
