package com.kosta.winter.core;
import com.kosta.winter.controller.abs.Controller;
import com.kosta.winter.controller.abs.ControllerProperties;
import com.kosta.winter.def.AppConfig;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;

public class Server {
    private AppConfig appConfig;
    private HttpServer httpServer;

    public Server(){
    }

    public void inIt(AppConfig appConfig){
        this.appConfig = appConfig;
    }

    public void run(){
        try {
            InetSocketAddress address = new InetSocketAddress(appConfig.PORT);
            httpServer = HttpServer.create(address, 0);
            loadControllers(appConfig);
            httpServer.start();
            startBanner();

        } catch (IOException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    private void loadControllers(AppConfig appConfig) {
        Controller[] controllers = appConfig.appControllers();
        for(Controller it : controllers){
            ControllerProperties cp = it.getProperties();
            String path = cp.getRoutage();
            httpServer.createContext(path, it);
        }
    }

    private void startBanner(){
        if(!appConfig.BANNER) return;

        String b =
                ",--.   ,--.,--.          ,--.                 \n" +
                "|  |   |  |`--',--,--, ,-'  '-. ,---. ,--.--. \n" +
                "|  |.'.|  |,--.|      \\'-.  .-'| .-. :|  .--' \n" +
                "|   ,'.   ||  ||  ||  |  |  |  \\   --.|  |    \n" +
                "'--'   '--'`--'`--''--'  `--'   `----'`--'    ";
        System.out.println(b);
    }
}
