package com.kosta.winter.controller.board;

import com.kosta.winter.controller.abs.Controller;
import com.kosta.winter.controller.abs.ControllerProperties;
import com.kosta.winter.controller.abs.Post;
import com.kosta.winter.data.board.User;
import com.kosta.winter.def.HttpResulter;
import com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public class UserSignController extends Controller implements Post {
    @Override
    public ControllerProperties getProperties() {
        return new ControllerProperties()
                .setRoutage("/user");
    }

    @Override
    public HttpResulter doPost(HttpExchange exchange, JSONObject jsonObject) {
        User user = cast(jsonObject, User.class);
        if(notDuplicateID(user.getId())){
            java.util.Date jDate = new java.util.Date();
            user.setUuid( UUID.randomUUID().toString() );
            user.setMdate( new Date( jDate.getTime() ) );
            user.setProfile("default");

            String sql =  "INSERT INTO app_user"
                    + "(SEQ, UUID, ID, PASSWORD, PROFILE, MDATE) "
                    + "values(user_seq.nextval, ?, ?, ?, ?, ?)";
            int rs = db.sqlUpdate(sql, user);
            if(rs == 1) {
                return new HttpResulter()
                        .setFilterData(user)
                        .setStatusCode(201)
                        .setMsg("아이디 생성");
            }
        }

        return new HttpResulter()
                .setStatusCode(400)
                .setMsg("아이디 중복");
    }

    private boolean notDuplicateID(String id){
        String sql = "SELECT * FROM app_user WHERE id = ?";
        List<User> user = db.sqlQuery(sql, User.class, id);
        return user.isEmpty();
    }
}