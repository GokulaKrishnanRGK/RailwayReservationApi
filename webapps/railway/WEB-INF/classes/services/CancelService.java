package services;

import dao.DatabaseStoreDao;

public class CancelService {
    public String cancelBooking(String userid,String[] customerIds){ 
        String result="1";
        DatabaseStoreDao databaseStoreDao=new DatabaseStoreDao();
        result=databaseStoreDao.cancelBooking(userid,customerIds);
        return result;
    }
}
