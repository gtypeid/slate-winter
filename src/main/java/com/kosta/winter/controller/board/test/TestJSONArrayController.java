package com.kosta.winter.controller.board.test;

import com.kosta.winter.controller.abs.Controller;
import com.kosta.winter.controller.abs.ControllerProperties;
import com.kosta.winter.controller.abs.Get;
import com.kosta.winter.data.board.Board;
import com.sun.net.httpserver.HttpExchange;

import java.util.ArrayList;
import java.util.List;

public class TestJSONArrayController extends Controller implements Get {
    @Override
    public ControllerProperties getProperties() {
        return new ControllerProperties()
                .setRoutage("/test-json-array");
    }

    @Override
    public List<Board> doGet(HttpExchange exchange) {
        Board board = new Board();
        board.setSeq(0);
        board.setContents("test 0");

        Board board2 = new Board();
        board2.setSeq(1);
        board2.setContents("test 1");

        List<Board> list = new ArrayList<>();
        list.add(board);
        list.add(board2);
        return list;
    }

}
