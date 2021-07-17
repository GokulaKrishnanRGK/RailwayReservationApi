package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.BookingModel;
import model.LoginModel;
import model.PassengerModel;
import model.RegisterModel;
import model.RolesModel;
import model.TicketsModel;
import util.ClassConstants;
import util.Crypt;
public class DatabaseStoreDao {

    public boolean isUserExists(String userid) {        
        boolean isExist = false;
        LoginDao loginDao=new LoginDao();
        LoginModel user=loginDao.getUser(userid);
        if(user!=null){
            isExist=true;            
        }        
        return isExist;
    }

    public HashMap<String, String> validateUser(String userid, String password) {       
        HashMap<String, String> result = new HashMap<>();
        LoginDao loginDao=new LoginDao();
        Crypt crypt=new Crypt();
        boolean isValid= loginDao.validate(userid, password);        
        if (isValid) {            
            result.put("access_token", crypt.caesarCipherEncrypt(userid));
            result.put("token_type","bearer");
        } else {
            result.put("error","Invalid credentials");
        }
        return result;
    }

    public String registerUser(LoginModel user,RegisterModel register,RolesModel role) { 
        // int id=0;          
        boolean isValid = isUserExists(user.getUserid());
        String result="0";
        if (isValid) {
            return result;
        }            
        try{
            LoginDao loginDao=new LoginDao();
            loginDao.saveUser(user);
            RegisterDao registerDao=new RegisterDao();
            registerDao.saveUser(register);
            RolesDao rolesDao=new RolesDao();
            rolesDao.saveRole(role);
            result="1";
        }
        catch(Exception e){
            e.printStackTrace();            
            result="-1";
        }
        return result;
    }

    public List<PassengerModel> getBookingList(String userid) {        
        List<PassengerModel> result = new ArrayList<>();        
        try{
            PassengerDao passengerDao=new PassengerDao();
            result=passengerDao.getPassengerDetailsByUserId(userid);            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public List<PassengerModel> getBookingList(String userid, int bookingId) {        
        List<PassengerModel> result = new ArrayList<>();
        try{
            PassengerDao passengerDao=new PassengerDao();
            result=passengerDao.getPassengerDetailsByBookingId(bookingId, userid);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    // Getters//

    public int getBookingSize(ClassConstants c) {
        // int id=0;
        int size = 0;
        int classId = c.value();
        try{
            PassengerDao passengerDao = new PassengerDao();
            size=passengerDao.getBookingSize(classId);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return size;
    }

    public int getWaitingSize(ClassConstants c) {
        int size = 0;
        int classId = c.value();
        try{
            PassengerDao passengerDao = new PassengerDao();
            size=passengerDao.getWaitingSize(classId);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return size;
    }

    int getN() {
        int n = 5;
        return n;
    }

    List<Integer> getBookingAndCustomerId() {               
        List<Integer> ans = new ArrayList<>();
        PassengerDao passengerDao=new PassengerDao();
        try {
            ans=passengerDao.getBookingAndCustomerId();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        System.out.println("getBookingCustomerId: "+ans.get(0)+" "+ans.get(1)+" "+ans);
        return ans;
    }
    // Getters//

    public List<TicketsModel> getTicketsList() {        
        List<TicketsModel> result = new ArrayList<>();
        int ACBookingSize = getBookingSize(ClassConstants.AC);
        int NACBookingSize = getBookingSize(ClassConstants.NONAC);
        int SeaterBookingSize = getBookingSize(ClassConstants.SEATER);

        int ACWaitingSize = getWaitingSize(ClassConstants.AC);
        int NACWaitingSize = getWaitingSize(ClassConstants.NONAC);
        int SeaterWaitingSize = getWaitingSize(ClassConstants.SEATER);
        int n = getN();
        TicketsModel ac = new TicketsModel();
        ac.setId(1);
        ac.setClassId(1);
        ac.setClassName("AC");
        ac.setAvailable((n - ACBookingSize) > 0 ? n - ACBookingSize : 0);
        ac.setWaiting(ACWaitingSize);

        TicketsModel nac = new TicketsModel();
        nac.setId(2);
        nac.setClassId(2);
        nac.setClassName("Non-AC");
        nac.setAvailable((n - NACBookingSize) > 0 ? n - NACBookingSize : 0);
        nac.setWaiting(NACWaitingSize);

        TicketsModel seater = new TicketsModel();
        seater.setId(3);
        seater.setClassId(3);
        seater.setClassName("Seater");
        seater.setAvailable((n - SeaterBookingSize) > 0 ? n - SeaterBookingSize : 0);
        seater.setWaiting(SeaterWaitingSize);

        result.add(ac);
        result.add(nac);
        result.add(seater);

        return result;
    }

    // Add Ticket//
    public int addPassenger(ClassConstants c, List<PassengerModel> passengers) {
        // int id=0;        
        PassengerDao passengerDao=new PassengerDao();
        int classId = c.value();
        List<Integer> ids = getBookingAndCustomerId();
        int bookingId = ids.get(0);
        int customerId = ids.get(1);
        try {                        
            for (PassengerModel p : passengers) {                      
                p.setBookingId(bookingId);
                p.setCustomerId(customerId);
                p.setIsCancelled(0);
                int bookingSize=getBookingSize(c);
                int n=getN();
                System.out.printf("%d %d %d\n",customerId,bookingSize,n);
                if (bookingSize >= n) {
                    p.setStatus("WL");
                    p.setSeatNo(0);
                } else {
                    p.setStatus("CF");
                    p.setSeatNo(popSeatNo(classId));
                }                                
                passengerDao.savepassenger(p);
                customerId+=1;
            }            
            System.out.println("booked");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookingId;
    }

    public void addBooking(String userid, BookingModel b) {                
        b.setBookingId(getBookingAndCustomerId().get(0));
        b.setUserid(userid);
        b.setIsCancelled(0);
        try {
            BookingDao bookingDao= new BookingDao();
            bookingDao.savebooking(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    int popSeatNo(int classId) {    
        // int id=0;    
        int seatNo = 0;
        try {
            SeatsDao seatsDao=new SeatsDao();
            seatNo=seatsDao.popSeatNo(classId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return seatNo;
    }

    void pushSeatNo(int seatNo, int classId) {
        try {
            SeatsDao seatsDao=new SeatsDao();
            seatsDao.pushSeatNo(classId, seatNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Add//

    //Cancel tickets//
    public String cancelBooking(String userid,String[] customerIds){
        String result="1";
        for(String i:customerIds){
            int cid=Integer.parseInt(i);
            System.out.println(cid);
        }
        return result;
    }
    //Cancel tickets//

}
