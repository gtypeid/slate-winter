package com.kosta.winter.controller.bm.buyer;

import com.kosta.winter.controller.abs.Controller;
import com.kosta.winter.controller.abs.ControllerProperties;
import com.kosta.winter.controller.abs.Post;
import com.kosta.winter.data.bm.buyer.BuyerUser;
import com.kosta.winter.data.board.User;
import com.kosta.winter.def.HttpResulter;
import com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;

import java.util.List;

public class BuyerLoginController extends Controller implements Post {
    BuyerCommon common;

    @Override
    public ControllerProperties getProperties() {
        common = getCommon();
        return new ControllerProperties()
                .setRoutage("/buyer-login");
    }

    @Override
    public HttpResulter doPost(HttpExchange exchange, JSONObject jsonObject) {
        BuyerUser buyerUser = cast(jsonObject, BuyerUser.class);
        BuyerUser findUser = common.getBuyerUser(buyerUser.getId());

        if(findUser != null){
            if( findUser.getPassWord().equals(buyerUser.getPassWord()) ){
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
}
