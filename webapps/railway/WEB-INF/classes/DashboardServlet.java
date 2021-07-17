import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession; 

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

import model.PassengerModel;
import services.DashboardService;
import util.SessionManager;

public class DashboardServlet extends HttpServlet {


    // The doGet() runs once per HTTP GET request to this servlet.
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {        
        Gson gson=new Gson();
        DashboardService dashboardService = new DashboardService();                                        
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

    // @Override
    // public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    //     int id=0;
    //     dashboardService = new DashboardService();
    //     String userid = (String) request.getRemoteUser();        
    //     List<PassengerModel> bookings = dashboardService.getBookingDetails(userid);           
    //     Gson gson=new Gson();
    //     JsonElement element = gson.toJsonTree(bookings, new TypeToken<List<PassengerModel>>() {
    //     }.getType());
    //     JsonArray jsonArray = element.getAsJsonArray();
    //     response.setContentType("application/json");
    //     response.getWriter().print(jsonArray);
    // }
}

// String userid = "";
//         try{
//             Cookie ck[]=request.getCookies();
//             if(ck==null){
//                 System.out.println("no cookies");
//             }   
//             else{
//                 userid = (String) ck[0].getValue();
//             }
//         }   
//         catch(Exception e){
//             e.printStackTrace();
//             System.out.println("No cookies");
//         }
//         if(userid==""){
//             HttpSession session=request.getSession(false);
//             if(session!=null){
//                 try{                
//                     userid=(String) session.getAttribute("userid");
//                 }
//                 catch(Exception e){
//                     e.printStackTrace();
//                     System.out.println("No prev session");
//                 }
//             }
//             else{
//                 System.out.println("Prev session null");
//             }
//         }
//         if(userid==""){
//             try{
//                 userid=request.getParameter("userid");
//             }
//             catch(Exception e){
//                 e.printStackTrace();
//                 System.out.println("No parameter");
//             }
//         }
//         if(userid=="" || userid==null){
//             userid="gokulshyam567@gmail.com";
//         }