package singleton;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created with IntelliJ IDEA.
 * entities.User: dkorolev
 * Date: 19.06.13
 * Time: 11:00
 * To change this template use File | Settings | File Templates.
 */
public class EntityManagerFactorySingleton {
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("demail4");
}
