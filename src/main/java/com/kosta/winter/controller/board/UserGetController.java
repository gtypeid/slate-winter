package com.kosta.winter.controller.board;

import com.kosta.winter.controller.abs.Controller;
import com.kosta.winter.controller.abs.ControllerProperties;
import com.kosta.winter.controller.abs.Get;
import com.kosta.winter.data.board.User;
import com.kosta.winter.def.PathVariable;
import com.sun.net.httpserver.HttpExchange;

import java.util.List;

public class UserGetController extends Controller implements Get {
    @Override
    public ControllerProperties getProperties() {
        return new ControllerProperties()
                .setRoutage("/user/");
    }

    @Override
    public User doGet(HttpExchange exchange) {
        PathVariable pathVariable = pathVariable(exchange);
        String uuid = pathVariable.value.get(0);
        String sql = "SELECT * FROM app_user WHERE uuid = ?";
        List<User> user = db.sqlQuery(sql, User.class, uuid);
        return user.get(0);
    }
    
}
