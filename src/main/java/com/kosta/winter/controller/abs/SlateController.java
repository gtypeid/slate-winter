package com.kosta.winter.controller.abs;

import com.sun.net.httpserver.HttpExchange;

public class SlateController extends Controller implements Get {

    @Override
    public ControllerProperties getProperties() {
        return new ControllerProperties()
                .setRoutage("/");
    }

    @Override
    public String doGet(HttpExchange exchange) {
        return exchange.getRequestURI().getPath();
    }

}
