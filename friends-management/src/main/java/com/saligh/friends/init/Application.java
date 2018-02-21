package com.saligh.friends.init;


import com.saligh.friends.service.FriendService;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.webapp.WebAppContext;
import org.glassfish.jersey.server.ResourceConfig;
/**
 * Created by saligh on 17/2/18.
 */
@Slf4j
public class Application extends ResourceConfig {

    public Application() {
        register(FriendService.class);
    }

    public static void main(String[] args) throws Exception {
        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8081";
        }

        final Server server = new Server(Integer.valueOf(webPort));

        // services
        WebAppContext webcontext = new WebAppContext();
        webcontext.setResourceBase("./friends-management/target/classes/");
        webcontext.setContextPath("/api/*");
        webcontext.setParentLoaderPriority(true);
        server.setHandler(webcontext);

        // front-end
        ResourceHandler webHandler = new ResourceHandler();
        webHandler.setResourceBase("./friends-management/target/classes/");
        webHandler.setWelcomeFiles(new String[]{"index.html"});

        // adding handlers
        HandlerCollection handlers = new HandlerCollection();
        handlers.addHandler(webcontext);
        handlers.addHandler(webHandler);
        server.setHandler(handlers);
        server.start();

        try {
            server.start();
            server.join();
        } finally {
            server.destroy();
        }
    }

}
