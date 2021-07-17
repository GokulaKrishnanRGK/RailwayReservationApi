package services;

import java.util.List;

import dao.DatabaseStoreDao;
import model.PassengerModel;

public class DashboardService {
    DatabaseStoreDao databaseStoreDao;
    public List<PassengerModel> getBookingDetails(String userid) {        
        databaseStoreDao=new DatabaseStoreDao();                
         List<PassengerModel> bookings = databaseStoreDao.getBookingList(userid);         
         return bookings;
    }
    public List<PassengerModel> getBookingDetails(String userid,int bookingid) {
        databaseStoreDao=new DatabaseStoreDao();                
         List<PassengerModel> bookings = databaseStoreDao.getBookingList(userid,bookingid);         
         return bookings;
    }
}
