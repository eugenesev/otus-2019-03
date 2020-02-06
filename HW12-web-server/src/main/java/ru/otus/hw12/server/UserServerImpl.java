package ru.otus.hw12.server;

import com.google.gson.Gson;
import org.eclipse.jetty.server.Server;
import ru.otus.hw12.services.TemplateProcessorImpl;

public class UserServerImpl implements UserServer {

    private final Gson gson;
    private final TemplateProcessorImpl templateProcessor;
    private final Server server;

    UserServerImpl(Gson gson, TemplateProcessorImpl templateProcessor, Server server){
        this.gson = gson;
        this.templateProcessor = templateProcessor;
        this.server = server;
    }

    @Override
    public void start() throws Exception {

    }

    @Override
    public void join() throws Exception {

    }

    @Override
    public void stop() throws Exception {

    }
}
