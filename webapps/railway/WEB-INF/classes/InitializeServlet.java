import org.hibernate.Session;

import util.HibernateUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;

@SuppressWarnings("serial")
public class InitializeServlet extends HttpServlet{
    public void init() throws ServletException
    {        
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }    
}