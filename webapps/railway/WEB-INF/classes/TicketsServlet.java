import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;

import model.TicketsModel;

import java.util.HashMap;
import java.util.List;
import util.SessionManager;

import services.TicketsService;

public class TicketsServlet extends HttpServlet {

    // private String sendAsJson(HttpServletResponse response, Object obj) throws
    // IOException {

    // response.setContentType("application/json");
    // Gson gson = new GsonBuilder().create();
    // String res = gson.toJson(obj);

    // return res;
    // }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // int id=0;
        Gson gson = new Gson();
        TicketsService ticketsService = new TicketsService();
        List<TicketsModel> tickets = ticketsService.getTicketDetails();
        HashMap<String, List<TicketsModel>> jsonObject = new HashMap<>();
        jsonObject.put("tickets", tickets);
        String ticketsJsonString = gson.toJson(jsonObject);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(ticketsJsonString);
        out.flush();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String json = (String) request.getParameter("tickets");
        System.out.println(json);
        TicketsService ticketsService = new TicketsService();
        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String, Object>>() {
        }.getType();
        HashMap<String, Object> map = gson.fromJson(json, type);
        HttpSession session=SessionManager.getSession(request);
        String userid=(String) session.getAttribute("userid");        
        String result = ticketsService.bookTickets(userid, map);
        response.getWriter().write(result);
    }
}