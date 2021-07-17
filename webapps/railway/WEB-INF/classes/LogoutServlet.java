
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import util.SessionManager;

public class LogoutServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {        
        // HttpSession session=request.getSession();
        // session.invalidate();

        HttpSession session=SessionManager.getSession(request);
        SessionManager.inValidate((String) session.getAttribute("auth_token"));
    }
}