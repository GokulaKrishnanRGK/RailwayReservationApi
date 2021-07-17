import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import services.CancelService;
import services.DashboardService;
import model.PassengerModel;

import util.SessionManager;

public class CancelServlet extends HttpServlet {    

    // The doGet() runs once per HTTP GET request to this servlet.
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DashboardService dashboardService = new DashboardService();
        Gson gson=new Gson();                
        HttpSession session=SessionManager.getSession(request);
        String userid=(String) session.getAttribute("userid");        
        List<PassengerModel> bookings = dashboardService.getBookingDetails(userid);  
        HashMap<String,List<PassengerModel>> jsonObject=new HashMap<>();
        jsonObject.put("passengers",bookings);
        String passengersJsonString = gson.toJson(jsonObject);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(passengersJsonString);
        out.flush();  
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CancelService cancelService = new CancelService();
        String[] customerIds =request.getParameterValues("customerIds[]");                        
        String userid = (String) request.getRemoteUser();
        String result=cancelService.cancelBooking(userid, customerIds);
        response.getWriter().write(result);
    }
}