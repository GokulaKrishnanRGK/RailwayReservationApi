package services;

import dao.DatabaseStoreDao;
import model.LoginModel;
import model.RegisterModel;
import model.RolesModel;

public class RegisterService {     
    public String registerUser(String userid, String name, String phoneno, String dob, String password) {                
        // int id=0;
        LoginModel user=new LoginModel(userid,password);        
        RegisterModel register=new RegisterModel(userid, name, phoneno, dob);        
        RolesModel role=new RolesModel(userid,"user");        
        DatabaseStoreDao databaseStoreDao=new DatabaseStoreDao();        
        String result = databaseStoreDao.registerUser(user,register,role);        
        return result;
    }
}
