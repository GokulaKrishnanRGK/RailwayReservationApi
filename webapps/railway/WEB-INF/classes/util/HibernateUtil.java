package util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import model.BookingModel;
import model.LoginModel;
import model.PassengerModel;
import model.RegisterModel;
import model.RolesModel;
import model.SeatsModel;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {        
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                FileReader reader;
                String USER = "";
                String PASS = "";
                String JDBC_DRIVER = "";
                String DB_URL = "";
                try {
                    reader = new FileReader(
                            "C:\\Users\\Gokulakrishnan\\Desktop\\ZOHOIncubation\\Tasks\\railwayServletCopy\\tomcat\\webapps\\railway\\WEB-INF\\classes\\conf\\db.properties");
                    Properties p = new Properties();
                    p.load(reader);
                    USER = p.getProperty("USER");
                    PASS = p.getProperty("PASS");
                    JDBC_DRIVER = p.getProperty("JDBC_DRIVER");
                    DB_URL = p.getProperty("DB_URL");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                settings.put(Environment.DRIVER, JDBC_DRIVER);
                settings.put(Environment.URL, DB_URL);
                settings.put(Environment.USER, USER);
                settings.put(Environment.PASS, PASS);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(LoginModel.class);
                configuration.addAnnotatedClass(RegisterModel.class);
                configuration.addAnnotatedClass(RolesModel.class);
                configuration.addAnnotatedClass(PassengerModel.class);
                configuration.addAnnotatedClass(BookingModel.class);
                configuration.addAnnotatedClass(SeatsModel.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                System.out.println("Hibernate Java Config serviceRegistry created");
                System.out.println("Here");
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);    
                return sessionFactory;            
            } catch (Exception e) {
                e.printStackTrace();
            }            
        }
        return sessionFactory;
    }
}
