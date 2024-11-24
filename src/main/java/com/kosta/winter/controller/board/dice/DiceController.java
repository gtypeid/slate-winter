package com.kosta.winter.controller.board.dice;

import com.kosta.winter.controller.abs.Controller;
import com.kosta.winter.controller.abs.ControllerProperties;
import com.kosta.winter.controller.abs.Get;
import com.kosta.winter.controller.abs.Post;
import com.kosta.winter.def.HttpResulter;
import com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;

public class DiceController extends Controller implements Get, Post {
    JSONObject curFlow;

    @Override
    public ControllerProperties getProperties() {
        return new ControllerProperties()
                .setRoutage("/flow");
    }

    @Override
    public HttpResulter doGet(HttpExchange exchange) {
        if(curFlow != null) {
            return new HttpResulter()
                    .setStatusCode(200)
                    .setData( curFlow.toString() );
        }

        return new HttpResulter()
                .setStatusCode(500);
    }

    @Override
    public JSONObject doPost(HttpExchange exchange, JSONObject jsonObject) {
        curFlow = jsonObject;
        System.out.println(curFlow);
        return curFlow;
    }

}
