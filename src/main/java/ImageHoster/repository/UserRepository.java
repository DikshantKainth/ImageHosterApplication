package ImageHoster.repository;

import ImageHoster.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

//Write the annotation which is a special type of @Component annotation and describes that the class defines a data repository
@Repository
public class UserRepository {
    //Get an instance of EntityManagerFactory from persistence unit with name as 'imageHoster'
    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;

    //The method receives the User object to be persisted in the database
    //Creates an instance of EntityManager
    //Starts a transaction
    //The transaction is committed if it is successful
    //The transaction is rolled back in case of unsuccessful transaction
    public void registerUser(User newUser) {
        EntityManager em = emf.createEntityManager();
        //Complete the method
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(newUser);
            transaction.commit();

        }
        catch (Exception e) {
            System.out.println(e);
            transaction.rollback();
        }
    }

    public User checkCredentials(String username, String password) {
        try{
            EntityManager entityManager = emf.createEntityManager();
            TypedQuery<User> query =entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password",User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            return query.getSingleResult();
        }
        catch(Exception e){
            //System.out.println(e);
            return null;
        }
    }
}