package serviceImpl;

import daoImpl.JPAEmailDAO;
import entities.Email;
import service.EmailService;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * entities.User: dkorolev
 * Date: 14.06.13
 * Time: 11:43
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class EmailServiceImpl implements EmailService {
    //private Email email;
    //JPAEmailDAO jpaEDAO = new JPAEmailDAO();

    @Override
   synchronized public Email getEmail(Integer userId) {
        return new JPAEmailDAO().getEmail(userId);
    }

    @Override
    synchronized public Email getEmail(String uniqAddr) {
        return new JPAEmailDAO().getEmail(uniqAddr);
    }

    synchronized public List<Email> getAllEmails(Integer userId) {
        return new JPAEmailDAO().getAllEmails(userId);
    }
}
