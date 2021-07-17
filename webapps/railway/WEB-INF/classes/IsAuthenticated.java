import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.SessionManager;

public class IsAuthenticated extends HttpServlet {

   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {      		          
    PrintWriter out=response.getWriter();    
    String result="0";
    if(SessionManager.isValidSession(request)){        
        result="1";
    }
    out.print(result);
    out.flush();   
   }
}