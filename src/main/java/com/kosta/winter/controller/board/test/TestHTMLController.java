package com.kosta.winter.controller.board.test;

import com.kosta.winter.controller.abs.Controller;
import com.kosta.winter.controller.abs.ControllerProperties;
import com.kosta.winter.controller.abs.Get;
import com.sun.net.httpserver.HttpExchange;

public class TestHTMLController extends Controller implements Get {
    @Override
    public ControllerProperties getProperties() {
        return new ControllerProperties()
                .setRoutage("/test-html");
    }

    @Override
    public String doGet(HttpExchange exchange) {
        return "/test-hello.html";
    }
}
