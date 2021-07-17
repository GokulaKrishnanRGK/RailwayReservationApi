import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import java.util.HashMap;

import services.LoginService;

import util.SessionManager;

public class LoginServlet extends HttpServlet {

   // @Override
   // public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
   //    request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
   // }

   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {      		      
      LoginService loginService = new LoginService();      
      Gson gson=new Gson();    
      String userid = request.getParameter("username");
      String password = request.getParameter("password");      
      HashMap<String, String> result = loginService.validateUser(userid, password); 
      PrintWriter out = response.getWriter();
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      if(result.containsKey("error")){
         response.setStatus(400);
      }
      else{
         HttpSession newSession = request.getSession(true);         
         newSession.setAttribute("userid", userid);
         String auth_token =newSession.getId();
         result.put("access_token",auth_token);
         SessionManager.addSession(auth_token, newSession);
      }
      String loginJson = gson.toJson(result);
      out.print(loginJson);
      out.flush();       
   }
}