package services;

import java.util.List;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import model.PassengerModel;

import util.Crypt;;

public class SendMailService {

    private String EMAIL;
    private String PASS;

    public String sendBookingDetails(String userid,int bookingid) {
        System.out.println(userid);
        String result = "0";
        Session session = getAuthenticator();
        DashboardService dashboardService = new DashboardService();
        List<PassengerModel> bookings = dashboardService.getBookingDetails(userid,bookingid);

        String subject = "Your booking details";
        StringBuilder msg = new StringBuilder(
                "<div id='bookingListDiv'><h1>Railway Reservation</h1><table border='1' cellpadding='10'><tr><th>Booking Id</th><th>Customer Id</th><th>Name</th><th>Age</th><th>Gender</th><th>From</th><th>To</th><th>Class Name</th><th>SeatNo</th><th>Status</th></tr>");

        for (PassengerModel p : bookings) {
            StringBuilder row = new StringBuilder("<tr>");
            row.append("<td>" + p.getBookingId() + "</td>");
            row.append("<td>" + p.getCustomerId() + "</td>");
            row.append("<td>" + p.getName() + "</td>");
            row.append("<td>" + p.getAge() + "</td>");
            row.append("<td>" + p.getGender() + "</td>");
            row.append("<td>" + p.getSource() + "</td>");
            row.append("<td>" + p.getDestination() + "</td>");
            row.append("<td>" + p.getClassName() + "</td>");
            row.append("<td>" + p.getSeatNo() + "</td>");
            row.append("<td>" + p.getStatus() + "</td>");
            row.append("</tr>");
            msg.append(row);
        }
        msg.append("</table></div>");        
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userid));
            message.setSubject(subject);
            message.setText(msg.toString(), "utf-8", "html");

            // 3rd step)send message
            Transport.send(message);

            System.out.println("Done");
            result = "1";

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return result;
    }

    public String sendBookingDetails(String userid) {
        System.out.println(userid);
        String result = "0";
        Session session = getAuthenticator();
        DashboardService dashboardService = new DashboardService();
        List<PassengerModel> bookings = dashboardService.getBookingDetails(userid);

        String subject = "Your booking details";
        StringBuilder msg = new StringBuilder(
                "<div id='bookingListDiv'><h1>Railway Reservation</h1><table border='1' cellpadding='10'><tr><th>Booking Id</th><th>Customer Id</th><th>Name</th><th>Age</th><th>Gender</th><th>From</th><th>To</th><th>Class Name</th><th>SeatNo</th><th>Status</th></tr>");

        for (PassengerModel p : bookings) {
            StringBuilder row = new StringBuilder("<tr>");
            row.append("<td>" + p.getBookingId() + "</td>");
            row.append("<td>" + p.getCustomerId() + "</td>");
            row.append("<td>" + p.getName() + "</td>");
            row.append("<td>" + p.getAge() + "</td>");
            row.append("<td>" + p.getGender() + "</td>");
            row.append("<td>" + p.getSource() + "</td>");
            row.append("<td>" + p.getDestination() + "</td>");
            row.append("<td>" + p.getClassName() + "</td>");
            row.append("<td>" + p.getSeatNo() + "</td>");
            row.append("<td>" + p.getStatus() + "</td>");
            row.append("</tr>");
            msg.append(row);
        }
        msg.append("</table></div>");        
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userid));
            message.setSubject(subject);
            message.setText(msg.toString(), "utf-8", "html");

            // 3rd step)send message
            Transport.send(message);

            System.out.println("Done");
            result = "1";

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return result;
    }

    Session getAuthenticator() {        
        Crypt c = new Crypt();
        try {
            FileReader mail = new FileReader("C:\\Users\\Gokulakrishnan\\Desktop\\ZOHOIncubation\\Tasks\\railwayServletCopy\\tomcat\\webapps\\railway\\WEB-INF\\classes\\conf\\mail.properties");
            Properties p = new Properties();
            p.load(mail);
            this.EMAIL = (String) p.getProperty("EMAIL");
            this.PASS = c.caesarCipherDecrypte((String) p.getProperty("PASS"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Properties props=new Properties();;
        try {
            FileReader gmail = new FileReader("C:\\Users\\Gokulakrishnan\\Desktop\\ZOHOIncubation\\Tasks\\railwayServletCopy\\tomcat\\webapps\\railway\\WEB-INF\\classes\\conf\\gmail.properties");            
            props.load(gmail);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL, PASS);
            }
        });
        return session;
    }
}