package com.kosta.winter.controller.board;

import com.kosta.winter.controller.abs.Controller;
import com.kosta.winter.controller.abs.ControllerProperties;
import com.kosta.winter.controller.abs.Post;
import com.kosta.winter.data.board.User;
import com.kosta.winter.def.HttpResulter;
import com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;

import java.util.List;

public class UserLoginController extends Controller implements Post {
    @Override
    public ControllerProperties getProperties() {
        return new ControllerProperties()
                .setRoutage("/login");
    }

    @Override
    public HttpResulter doPost(HttpExchange exchange, JSONObject jsonObject) {
        User user = cast(jsonObject, User.class);
        User findUser = findUser(user);

        if(findUser != null){
            if( findUser.getPassWord().equals(user.getPassWord()) ){
                return new HttpResulter()
                        .setFilterData(findUser)
                        .setStatusCode(200)
                        .setMsg("로그인 성공");
            }
        }

        return new HttpResulter()
                .setStatusCode(400)
                .setMsg("로그인 실패");
    }


    private User findUser(User insertUser){
        String sql = "SELECT * FROM app_user WHERE id = ?";
        List<User> users = db.sqlQuery(sql, User.class, insertUser.getId());
        if(!users.isEmpty()){
            return users.get(0);
        }
        return null;
    }
}
