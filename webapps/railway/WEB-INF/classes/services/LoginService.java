package services;

import java.util.HashMap;

import dao.DatabaseStoreDao;

public class LoginService {
    // int id=0;
    DatabaseStoreDao databaseStoreDao;
    public HashMap<String, String> validateUser(String userid, String password) {
        databaseStoreDao=new DatabaseStoreDao();
        HashMap<String, String> result = databaseStoreDao.validateUser(userid, password);
        return result;
    }
}
