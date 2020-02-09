package ru.otus.hw12;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.LoginService;
import ru.otus.hw10.api.service.DBServiceUser;
import ru.otus.hw12.db.DbStarter;
import ru.otus.hw12.db.DbStarterImpl;
import ru.otus.hw12.helpers.FileSystemHelper;
import ru.otus.hw12.server.UserServer;
import ru.otus.hw12.server.UserServerImpl;
import ru.otus.hw12.services.TemplateProcessor;
import ru.otus.hw12.services.TemplateProcessorImpl;

public class Main {
    private static final int WEB_SERVER_PORT = 8080;
    private static final String TEMPLATES_DIR = "/templates/";
    private static final String HASH_LOGIN_SERVICE_CONFIG_NAME = "realm.properties";
    private static final String REALM_NAME = "AnyRealm";

    public static void main(String[] args) throws Exception {
        DbStarter dbStarter = new DbStarterImpl();
        dbStarter.start();
        DBServiceUser dbServiceUser = dbStarter.getDBServiceUser();
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);

        String hashLoginServiceConfigPath = FileSystemHelper.localFileNameOrResourceNameToFullPath(HASH_LOGIN_SERVICE_CONFIG_NAME);
        LoginService loginServiceForBasicSecurity = new HashLoginService(REALM_NAME, hashLoginServiceConfigPath);

        UserServer userServer = new UserServerImpl(WEB_SERVER_PORT,
                gson,
                dbServiceUser,
                loginServiceForBasicSecurity,
                templateProcessor);

        userServer.start();
        userServer.join();
    }
}
