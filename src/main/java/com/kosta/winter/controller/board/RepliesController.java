package com.kosta.winter.controller.board;

import com.kosta.winter.controller.abs.Controller;
import com.kosta.winter.controller.abs.ControllerProperties;
import com.kosta.winter.controller.abs.Get;
import com.kosta.winter.data.board.Reply;
import com.kosta.winter.def.PathVariable;
import com.sun.net.httpserver.HttpExchange;

import java.util.List;

public class RepliesController extends Controller implements Get {
    @Override
    public ControllerProperties getProperties() {
        return new ControllerProperties()
                .setRoutage("/replies/");
    }

    @Override
    public List<Reply> doGet(HttpExchange exchange) {
        PathVariable pathVariable = pathVariable(exchange);
        String bindBoardUUID = pathVariable.value.get(0);
        String sql = "SELECT * FROM app_reply WHERE bind_board = ? ORDER BY seq ASC";
        return db.sqlQuery(sql, Reply.class, bindBoardUUID);
    }
}
