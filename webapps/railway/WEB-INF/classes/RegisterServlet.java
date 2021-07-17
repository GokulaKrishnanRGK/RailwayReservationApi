
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;

import services.RegisterService;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class RegisterServlet extends HttpServlet {

    RegisterService registerService;   
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {      
      request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
   }

   @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {        
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));        
        // int id=0;
		String json = "";
		if(br != null){
			json = br.readLine();			
		}
        registerService = new RegisterService();
        Gson gson=new Gson();
        Type type = new TypeToken<HashMap<String, String>>(){}.getType();
        HashMap<String, String> map = gson.fromJson(json, type);
        String name = (String) map.get("name");
        String userid = (String) map.get("userid");
        String password = (String) map.get("password");      
        String phoneno = (String) map.get("phoneno");        
        String dob = (String) map.get("dob");               
        String isSuccess = registerService.registerUser(userid, name, phoneno, dob, password);          
        response.getWriter().write(isSuccess);
    }
}