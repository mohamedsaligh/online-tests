package com.saligh.friends.init;

import com.saligh.friends.db.Connection;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 * Created by saligh on 17/2/18.
 */
@Slf4j
public class Application {

    public static void main(String[] args) throws Exception {
        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        final Server server = new Server(Integer.valueOf(webPort));

        ResourceConfig config = new ResourceConfig();
        config.packages("com.saligh.friends");
        ServletHolder servlet = new ServletHolder(new ServletContainer(config));

        ServletContextHandler context = new ServletContextHandler(server, "/api/*");
        context.addServlet(servlet, "/*");
        server.setHandler(context);

        // Web
        ResourceHandler webHandler = new ResourceHandler();
        webHandler.setResourceBase("./target/classes/");
        webHandler.setWelcomeFiles(new String[]{"index.html"});

        // Server
        HandlerCollection handlers = new HandlerCollection();
        handlers.addHandler(context);
        handlers.addHandler(webHandler);
        server.setHandler(handlers);
        server.start();

        // DBConnection
        java.sql.Connection dbConn = null;
        try {
            log.info("Going to get DB Connection...");
            dbConn = Connection.getConnection();
            log.info("Get connection successful!");
        } finally {
            Connection.closeResources(null, null, dbConn);
        }
        //PlayerDAO playerDAO = new PlayerDAO();
        //playerDAO.checkAllTables(null);
        //playerDAO.createPlayerTable();

        try {
            server.start();
            server.join();
        } finally {
            server.destroy();
        }
    }

}
