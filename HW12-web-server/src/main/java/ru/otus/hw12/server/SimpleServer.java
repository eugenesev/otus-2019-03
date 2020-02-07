package ru.otus.hw12.server;

import com.google.gson.Gson;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.otus.hw10.api.service.DBServiceUser;
import ru.otus.hw12.services.TemplateProcessor;
import ru.otus.hw12.servlets.UsersApiServlet;
import ru.otus.hw12.servlets.UsersServlet;

public class SimpleServer implements UserServer {

    private final int port;
    private static final String START_PAGE_NAME = "login.html";
    private final Gson gson;
    private final DBServiceUser dbServiceUser;
    private final TemplateProcessor templateProcessor;
    private final Server server;

    public SimpleServer(int port, Gson gson, DBServiceUser dbServiceUser, TemplateProcessor templateProcessor) {
        this.port = port;
        this.gson = gson;
        this.dbServiceUser = dbServiceUser;
        this.templateProcessor = templateProcessor;
        server = initContext();
        ;
    }

    @Override
    public void start() throws Exception {
        server.start();
    }

    @Override
    public void join() throws Exception {
        server.join();
    }

    @Override
    public void stop() throws Exception {
        server.stop();
    }

    private Server initContext() {
        HandlerList handlers = new HandlerList();

        ResourceHandler resourceHandler = createResourceHandler();
        handlers.addHandler(resourceHandler);

        ServletContextHandler servletContextHandler = createServletContextHandler();
        handlers.addHandler(servletContextHandler);

        Server srv = new Server(port);
        srv.setHandler(handlers);
        return srv;
    }

    private ResourceHandler createResourceHandler() {
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(false);
//        resourceHandler.setWelcomeFiles(new String[]{START_PAGE_NAME});
//        resourceHandler.setResourceBase(FileSystemHelper.localFileNameOrResourceNameToFullPath(COMMON_RESOURCES_DIR));
        return resourceHandler;
    }

    private ServletContextHandler createServletContextHandler() {
        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.addServlet(new ServletHolder(new UsersServlet(templateProcessor, dbServiceUser)), "/");
//        servletContextHandler.addServlet(new ServletHolder(new UsersApiServlet(dbServiceUser, gson)), "/api/user/*");
        return servletContextHandler;
    }
}
