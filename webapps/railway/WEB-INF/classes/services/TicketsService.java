package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;

import dao.DatabaseStoreDao;
import model.BookingModel;
import model.PassengerModel;
import model.TicketsModel;
import util.ClassConstants;

public class TicketsService {
    DatabaseStoreDao databaseStoreDao;
    public List<TicketsModel> getTicketDetails() {
        // int id=0;
        databaseStoreDao=new DatabaseStoreDao();                
         List<TicketsModel> bookings = databaseStoreDao.getTicketsList();         
         return bookings;
    }

    public String bookTickets(String userid,HashMap<String,Object> map){
        // int id=0;
        DatabaseStoreDao databaseStoreDao=new DatabaseStoreDao();
        SendMailService sendMailService=new SendMailService();
        String result="1";
        String from = (map.get("from")).toString();
        String to = (map.get("to")).toString();        
        Integer classId=Integer.parseInt((map.get("classId")).toString());
        ClassConstants c = ClassConstants.getValueById(classId);
        String className = c.className();
        int cost = c.cost();
        List<?> tickets= convertObjectToList(map.get("tickets"));    
        int ticketsCount=tickets.size();
        int totalCost=ticketsCount*cost;
        BookingModel b=new BookingModel();
        b.setClassId(classId);        
        b.setClassName(className);
        b.setSource(from);
        b.setDestination(to);
        b.setTicketsCount(ticketsCount);
        b.setTotalCost(totalCost);        
        databaseStoreDao.addBooking(userid,b);    
        List<PassengerModel> passengers=new ArrayList<>();    
        for(Object i:tickets){            
            List<?> ticket=convertObjectToList(i);            
            String name=(ticket.get(0)).toString();            
            Integer age=Integer.parseInt((ticket.get(1)).toString());
            String gender=(ticket.get(2)).toString(); 
            PassengerModel p=new PassengerModel();
            p.setAge(age);
            p.setName(name);
            p.setSource(from);
            p.setDestination(to);
            p.setClassId(classId);
            p.setClassName(className);
            p.setGender(gender);
            p.setCost(cost);
            p.setUserid(userid);
            passengers.add(p);
        }
        int bookingid=databaseStoreDao.addPassenger(c, passengers);                
        sendMailService.sendBookingDetails(userid, bookingid);
        return result;
    }

    public List<?> convertObjectToList(Object obj) {
        List<?> list = new ArrayList<>();
        if (obj.getClass().isArray()) {
            list = Arrays.asList((Object[])obj);
        } else if (obj instanceof Collection) {
            list = new ArrayList<>((Collection<?>)obj);
        }
        return list;
    }
}
