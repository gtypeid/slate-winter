package com.kosta.winter.controller.abs;

import com.sun.net.httpserver.HttpExchange;

public interface Get {
    <T> T doGet(HttpExchange exchange);
}
