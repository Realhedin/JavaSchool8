package dao;

import entities.Email;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * entities.User: dkorolev
 * Date: 12.06.13
 * Time: 15:10
 * To change this template use File | Settings | File Templates.
 */
public interface EmailDAO {
        /** get all Emails from DB*/
        //public List getAllUsers();
        /**get one entities.Email per idUser */
        public Email getEmail(Integer userId);
        //**get one entities.Email per uniqAddress
        public Email getEmail(String uniqAddr);
        //get all List<Emails> by idUser
        public List<Email> getAllEmails(Integer userId);
        //public entities.Email getEmail(String n, String s);
        /** insert entities.Email in DB*/
        public Boolean insert(Email us);

        /* delete entities.Email by EmailId */
        public Boolean deleteEmail(Integer emailId);

        /* get EmailAddress by emailId */
        public String getEmailAddressByEmailId(Integer emailId);
//    public void update(entities.User us);
//    public void delete(entities.User us);
}
