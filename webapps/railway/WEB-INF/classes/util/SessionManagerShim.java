package util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class SessionManagerShim implements ServletContextListener {
    static Manager manager;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            manager = getManagerFromServletContextEvent(sce);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        manager = null;
    }

    private static Manager getManagerFromServletContextEvent(ServletContextEvent sce) throws NoSuchFieldException, IllegalAccessException {
        // Step one - get the ApplicationContextFacade (Tomcat loves facades)
        ApplicationContextFacade contextFacade = (ApplicationContextFacade)sce.getSource();

        // Step two - get the ApplicationContext the facade wraps
        Field appContextField = ApplicationContextFacade.class.getDeclaredField("context");
        appContextField.setAccessible(true);
        ApplicationContext applicationContext = (ApplicationContext)
                appContextField.get(contextFacade);

        // Step three - get the Context (a tomcat context class) from the facade
        Field contextField = ApplicationContext.class.getDeclaredField("context");
        contextField.setAccessible(true);
        Context context = (Context) contextField.get(applicationContext);

        // Step four - get the Manager. This is the class Tomcat uses to manage sessions
        return context.getManager();
    }

    public static Session getSession(String sessionID) throws IOException {
        return manager.findSession(sessionID);
    }
}
