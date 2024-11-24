package com.kosta.winter.controller.board;

import com.kosta.winter.controller.abs.Controller;
import com.kosta.winter.controller.abs.ControllerProperties;
import com.kosta.winter.controller.abs.Post;
import com.kosta.winter.data.board.Board;
import com.kosta.winter.def.HttpResulter;
import com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;

import java.sql.Date;
import java.util.UUID;

public class BoardPostController extends Controller implements Post {
    @Override
    public ControllerProperties getProperties() {
        return new ControllerProperties()
                .setRoutage("/board");
    }

    @Override
    public HttpResulter doPost(HttpExchange exchange, JSONObject jsonObject) {
        Board board = cast(jsonObject, Board.class);
        java.util.Date jDate = new java.util.Date();
        board.setUuid( UUID.randomUUID().toString() );
        board.setMdate( new Date( jDate.getTime() ) );
        board.setOwner( board.getOwner() );

        String sql =  "INSERT INTO APP_BOARD"
                + "(SEQ, UUID, OWNER, TITLE, CONTENTS, MDATE) "
                + "values(board_seq.nextval, ?, ?, ?, ?, ?)";

        int rs = db.sqlUpdate(sql, board);
        if(rs == 1) {
            return new HttpResulter()
                    .setFilterData(board)
                    .setStatusCode(201)
                    .setMsg("게시글 생성");
        }

        return new HttpResulter()
                .setStatusCode(400)
                .setMsg("게시글 생성 실패");
    }

}
