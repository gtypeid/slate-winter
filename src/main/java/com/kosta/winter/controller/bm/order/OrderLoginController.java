package com.kosta.winter.controller.bm.order;

import com.kosta.winter.controller.abs.Controller;
import com.kosta.winter.controller.abs.ControllerProperties;
import com.kosta.winter.controller.abs.Post;
import com.kosta.winter.controller.bm.buyer.BuyerCommon;
import com.kosta.winter.data.bm.buyer.BuyerUser;
import com.kosta.winter.data.bm.order.OrderUser;
import com.kosta.winter.def.HttpResulter;
import com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;

public class OrderLoginController extends Controller implements Post {
    OrderCommon common;

    @Override
    public ControllerProperties getProperties() {
        common = getCommon();
        return new ControllerProperties()
                .setRoutage("/order-login");
    }

    @Override
    public HttpResulter doPost(HttpExchange exchange, JSONObject jsonObject) {
        OrderUser orderUser = cast(jsonObject, OrderUser.class);
        OrderUser findUser = common.getOrderUser(orderUser.getId());

        if(findUser != null){
            if( findUser.getPassWord().equals(orderUser.getPassWord()) ){
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
