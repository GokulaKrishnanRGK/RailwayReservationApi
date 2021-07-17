package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.LoginModel;
import util.HibernateUtil;

public class LoginDao {

    public void saveUser(LoginModel user) {
        Transaction transaction = null;
        System.out.println("LoginDAO save user");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(user);
            // commit transaction
            transaction.commit();
            System.out.println("LoginDAO save user success");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("LoginDAO save user error");
            e.printStackTrace();
        }
    }

    public LoginModel getUser(String userid) {        
        Transaction transaction = null;
        LoginModel user = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {            
            transaction = session.beginTransaction();            
            user = (LoginModel) session.createQuery("from LoginModel L where L.userid=:userid").setParameter("userid", userid).uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }

    public boolean validate(String userid, String password) {

        Transaction transaction = null;
        LoginModel user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            user = (LoginModel) session.createQuery("FROM LoginModel L WHERE L.userid = :userid")
                    .setParameter("userid", userid).uniqueResult();

            if (user != null && user.getPassword().equals(password)) {
                return true;
            }
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }
}