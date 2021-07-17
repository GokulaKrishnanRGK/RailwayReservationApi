
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.SendMailService;

public class SendMailServlet extends HttpServlet {

   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {      
      SendMailService sendMailService = new SendMailService();
      // String userid = (String) request.getRemoteUser();
      HttpSession session = request.getSession();
      String userid = (String) session.getAttribute("userid");
      String result = sendMailService.sendBookingDetails(userid);
      response.getWriter().write(result);
   }
}