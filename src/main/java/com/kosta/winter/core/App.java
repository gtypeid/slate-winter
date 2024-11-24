package com.kosta.winter.core;

import com.kosta.winter.controller.abs.Common;
import com.kosta.winter.def.AppConfig;

public class App {

    private AppConfig appConfig;
    private Server server;
    private DataBase dataBase;
    private Common common;
    private static App app;
    private App(){
        dataBase = new DataBase();
        server = new Server();
    }

    public static App getInstance(){
        if(app == null){
            app = new App();
        }
        return app;
    }

    public void run(AppConfig appConfig){
        this.appConfig = appConfig;
        dataBase.inIt(appConfig);
        server.inIt(appConfig);
        server.run();
    }

    public AppConfig getConfig(){
        return appConfig;
    }

    public <T> T getCommon(){
        if(common == null){
            common = appConfig.getCommon();
            common.setDb(dataBase);
        }
        return (T) common;
    }

    public DataBase getDataBase(){
        return dataBase;
    }

    public Server getServer(){
        return server;
    }
}
