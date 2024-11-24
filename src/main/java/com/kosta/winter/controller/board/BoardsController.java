package com.kosta.winter.controller.board;
import com.kosta.winter.controller.abs.*;
import com.kosta.winter.data.board.Board;
import com.sun.net.httpserver.HttpExchange;

import java.util.List;

public class BoardsController extends Controller implements Get{
    @Override
    public ControllerProperties getProperties() {
        return new ControllerProperties()
                .setRoutage("/boards");
    }

    @Override
    public List<Board> doGet(HttpExchange exchange) {
        String sql = "SELECT * FROM app_board ORDER BY seq ASC";
        return db.sqlQuery(sql, Board.class);
    }

}