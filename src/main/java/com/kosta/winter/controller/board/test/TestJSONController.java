package com.kosta.winter.controller.board.test;

import com.kosta.winter.controller.abs.Controller;
import com.kosta.winter.controller.abs.ControllerProperties;
import com.kosta.winter.controller.abs.Get;
import com.kosta.winter.data.board.Board;
import com.sun.net.httpserver.HttpExchange;

public class TestJSONController extends Controller implements Get {
    @Override
    public ControllerProperties getProperties() {
        return new ControllerProperties()
                .setRoutage("/test-json");
    }

    @Override
    public Board doGet(HttpExchange exchange) {
        Board board = new Board();
        board.setSeq(99);
        board.setContents("json test 99");

        return board;
    }

}
