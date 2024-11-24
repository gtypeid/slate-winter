package com.kosta.winter.controller.board;

import com.kosta.winter.controller.abs.Controller;
import com.kosta.winter.controller.abs.ControllerProperties;
import com.kosta.winter.controller.abs.Post;
import com.kosta.winter.data.board.Board;
import com.kosta.winter.data.board.Reply;
import com.kosta.winter.def.HttpResulter;
import com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public class ReplyPostController extends Controller implements Post {
    @Override
    public ControllerProperties getProperties() {
        return new ControllerProperties()
                .setRoutage("/reply");
    }

    @Override
    public HttpResulter doPost(HttpExchange exchange, JSONObject jsonObject) {
        Reply reply = cast(jsonObject, Reply.class);
        Board board = findBoard(reply);
        if(board != null){
            java.util.Date jDate = new java.util.Date();
            reply.setUuid( UUID.randomUUID().toString() );
            reply.setMdate( new Date( jDate.getTime() ) );

            String sql =  "INSERT INTO app_reply"
                    + "(SEQ, UUID, OWNER, BIND_BOARD, CONTENTS, MDATE) "
                    + "values(reply_seq.nextval, ?, ?, ?, ?, ?)";

            int rs = db.sqlUpdate(sql, reply);
            if(rs == 1) {
                return new HttpResulter()
                        .setFilterData(reply)
                        .setStatusCode(201)
                        .setMsg("리플 생성");
            }
        }
        return new HttpResulter()
                .setStatusCode(400)
                .setMsg("리플 생성 실패");
    }

    private Board findBoard(Reply reply){
        String sql = "SELECT * FROM app_board WHERE uuid = ?";
        String bindBoardUUID = reply.getBindBoard();
        List<Board> board = db.sqlQuery(sql, Board.class, bindBoardUUID);
        return board.get(0);
    }
}
