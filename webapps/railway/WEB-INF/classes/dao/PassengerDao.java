package dao;

import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.PassengerModel;
import util.HibernateUtil;

public class PassengerDao {

    public void savepassenger(PassengerModel passenger) {        
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(passenger);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updatepassenger(PassengerModel passenger) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.update(passenger);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public List<PassengerModel> getPassengerDetailsByUserId(String userid) {
        try{            
            SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
            return sessionFactory.openSession()
            .createQuery("from PassengerModel P where P.userid=:userid and P.isCancelled=0")
            .setParameter("userid", userid)
            .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<PassengerModel> getPassengerDetailsByBookingId(int bookingid, String userid) {
        try{            
            SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
            return sessionFactory.openSession()
            .createQuery("from PassengerModel P where P.userid=:userid and P.bookingId=:bookingId and P.isCancelled=0")
            .setParameter("userid", userid).setParameter("bookingId", bookingid)
            .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }        
    }
    
    public int getBookingSize(int classId) {        
        try{            
            SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
            return (int) (long) sessionFactory.openSession()
                .createQuery("SELECT COUNT(*) FROM PassengerModel P WHERE  P.classId=:classId and P.status='CF' and P.isCancelled=0")
                .setParameter("classId", classId)
                .getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getWaitingSize(int classId) {
        try{            
            SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
            return (int) (long) sessionFactory.openSession()
                .createQuery("SELECT COUNT(*) FROM PassengerModel P WHERE  P.classId=:classId and P.status='WL' and P.isCancelled=0")
                .setParameter("classId", classId)
                .getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Integer> getBookingAndCustomerId() {
        List<Integer> result=new ArrayList<>();        
        try{            
            SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
            List<Object[]> l=sessionFactory.openSession()
                            .createSQLQuery("Select coalesce(MAX(`bookingId`),0), coalesce(MAX(`customerId`),0) from `passenger`")
                            .list();            
            Object[] o=l.get(0);
            int bookingid=((BigInteger) o[0]).intValue()+1;
            int customerid=((BigInteger) o[1]).intValue()+1;
            System.out.println(bookingid+" "+customerid);
            result.add(bookingid);
            result.add(customerid);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}