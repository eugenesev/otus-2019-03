package ru.otus.hw12.server;

import com.google.gson.Gson;
import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.LoginService;
import org.eclipse.jetty.security.SecurityHandler;
import org.eclipse.jetty.security.authentication.BasicAuthenticator;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.security.Constraint;
import ru.otus.hw10.api.service.DBServiceUser;
import ru.otus.hw12.services.TemplateProcessor;
import ru.otus.hw12.servlets.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class UserServerImpl implements UserServer {

    private final int port;
    private static final String ROLE_NAME_USER = "user";
    private static final String ROLE_NAME_ADMIN = "admin";
    private static final String CONSTRAINT_NAME = "auth";

    private final Gson gson;
    private final DBServiceUser dbServiceUser;
    private final LoginService loginServiceForBasicSecurity;
    private final TemplateProcessor templateProcessor;
    private final Server server;

    public UserServerImpl(int port, Gson gson, DBServiceUser dbServiceUser, LoginService loginServiceForBasicSecurity, TemplateProcessor templateProcessor) {
        this.port = port;
        this.gson = gson;
        this.dbServiceUser = dbServiceUser;
        this.loginServiceForBasicSecurity = loginServiceForBasicSecurity;
        this.templateProcessor = templateProcessor;
        server = initContext();
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

        ServletContextHandler servletContextHandler = createServletContextHandler();
        handlers.addHandler(applySecurity(servletContextHandler));

        Server srv = new Server(port);
        srv.setHandler(handlers);
        return srv;
    }

    private ServletContextHandler createServletContextHandler() {
        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.addServlet(new ServletHolder(new UsersServlet(templateProcessor, dbServiceUser)), "/");
        servletContextHandler.addServlet(new ServletHolder(new EditUserServlet(templateProcessor, dbServiceUser)), "/api/edit/*");
        servletContextHandler.addServlet(new ServletHolder(new GetPhonesServlet(dbServiceUser, gson)), "/api/phones/*");
        servletContextHandler.addServlet(new ServletHolder(new AddUserServlet(templateProcessor, dbServiceUser)), "/api/add");
        return servletContextHandler;
    }

    private Handler applySecurity(ServletContextHandler servletContextHandler) {
        return createBasicAuthSecurityHandler(servletContextHandler, "/", "/api/edit/*s", "/api/phones/*", "/api/add");
    }

    private SecurityHandler createBasicAuthSecurityHandler(ServletContextHandler context, String... paths) {
        Constraint constraint = new Constraint();
        constraint.setName(CONSTRAINT_NAME);
        constraint.setAuthenticate(true);
        constraint.setRoles(new String[]{ROLE_NAME_USER, ROLE_NAME_ADMIN});

        List<ConstraintMapping> constraintMappings = new ArrayList<>();
        IntStream.range(0, paths.length).forEachOrdered(i -> {
            ConstraintMapping mapping = new ConstraintMapping();
            mapping.setPathSpec(paths[i]);
            mapping.setConstraint(constraint);
            constraintMappings.add(mapping);
        });

        ConstraintSecurityHandler security = new ConstraintSecurityHandler();
        security.setAuthenticator(new BasicAuthenticator());
        security.setLoginService(loginServiceForBasicSecurity);
        security.setConstraintMappings(constraintMappings);
        security.setHandler(new HandlerList(context));

        return security;
    }

}

