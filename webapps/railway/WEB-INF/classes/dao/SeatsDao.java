package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.SeatsModel;
import util.HibernateUtil;

public class SeatsDao {
    
    public int popSeatNo(int classId) {       
        // int id=0; 
        int seatNo = 0;
        try {
            SessionFactory sessionFactory=HibernateUtil.getSessionFactory();            
            seatNo=(int) sessionFactory.openSession()
                    .createQuery("SELECT seatNo from SeatsModel S where S.classId=:classId and S.isAvailable=1")
                    .setParameter("classId", classId)
                    .setFirstResult(0)
                    .setMaxResults(1)
                    .getSingleResult();

            // sessionFactory.openSession()
            //         .createQuery("UPDATE SeatsModel S SET S.isAvailable=0 where S.seatNo=:seatNo and S.classId=:classId")
            //         .setParameter("seatNo", seatNo)
            //         .setParameter("classId", classId)
            //         .executeUpdate();
            int seatId= (int) sessionFactory.openSession()
                    .createQuery("SELECT id from SeatsModel S where S.classId=:classId and S.seatNo=:seatNo")
                    .setParameter("classId", classId)
                    .setParameter("seatNo", seatNo)
                    .getSingleResult();
            SeatsModel s=new SeatsModel(seatId,seatNo,classId,0);
            updateSeat(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return seatNo;
    }

    public void updateSeat(SeatsModel seat) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.update(seat);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public void pushSeatNo(int classId,int seatNo) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();              
            // String query = "SELECT `seatNo` from `seats` where `classId`=? and `isAvailable`='1' LIMIT 1";            
            session.createQuery("UPDATE SeatsModel S SET S.isAvailable=1 where S.seatNo=:seatNo and S.classId=:classId").setParameter("seatNo", seatNo).setParameter("classId", classId).executeUpdate();
            transaction.commit();            
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }

}