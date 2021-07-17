package util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SessionManager {
    private static HashMap<String, HttpSession> sessionMap = new HashMap<>();

    public static void addSession(String auth_token, HttpSession session) {
        sessionMap.put(auth_token, session);
    }

    public static HttpSession getSession(HttpServletRequest request) {
        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String, HashMap<String, String>>>() {
        }.getType();
        String cookie =  (request.getCookies())[0].getValue();
        String decoded="";
        try {
            decoded = URLDecoder.decode(cookie, "UTF-8");
        } catch (UnsupportedEncodingException e) {            
            e.printStackTrace();
        }        
        HashMap<String, HashMap<String, String>> map = gson.fromJson(decoded, type);
        String JSESSIONID = (map.get("authenticated")).get("access_token").toString();        
        HttpSession session = sessionMap.get(JSESSIONID);
        return session;
    }

    public static void inValidate(String JSESSIONID) {        
        if (sessionMap.containsKey(JSESSIONID)) {
            sessionMap.remove(JSESSIONID);
        }
    }

    public static boolean isValidSession(HttpServletRequest request) {
        HttpSession session=getSession(request);        
        if (session!=null) {            
            return true;
        }
        return false;
    }

}
