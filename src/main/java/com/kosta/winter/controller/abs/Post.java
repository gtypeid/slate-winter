package com.kosta.winter.controller.abs;

import com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;

public interface Post {
    <T> T doPost(HttpExchange exchange, JSONObject jsonObject);
}
