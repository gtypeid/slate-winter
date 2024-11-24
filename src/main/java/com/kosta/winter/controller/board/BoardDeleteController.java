package com.kosta.winter.controller.board;

import com.kosta.winter.controller.abs.Controller;
import com.kosta.winter.controller.abs.ControllerProperties;
import com.kosta.winter.controller.abs.Post;
import com.kosta.winter.def.HttpResulter;
import com.kosta.winter.def.PathVariable;
import com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;

public class BoardDeleteController extends Controller implements Post{

    @Override
    public ControllerProperties getProperties() {
        return new ControllerProperties()
                .setRoutage("/board-delete/");
    }

    @Override
    public HttpResulter doPost(HttpExchange exchange, JSONObject jsonObject) {
        PathVariable pathVariable = pathVariable(exchange);
        String uuid = pathVariable.value.get(0);
        String sql = "DELETE FROM app_board WHERE uuid = ?";
        int rs = db.sqlUpdate(sql, uuid);
        if(rs == 1){
            return new HttpResulter()
                    .setStatusCode(200)
                    .setMsg("삭제 성공");
        }

        return new HttpResulter()
                .setStatusCode(400)
                .setMsg("삭제 실패");
    }
}
