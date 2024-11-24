package com.kosta.winter.controller.bm.buyer;

import com.kosta.winter.controller.abs.Controller;
import com.kosta.winter.controller.abs.ControllerProperties;
import com.kosta.winter.controller.abs.Post;
import com.kosta.winter.controller.bm.seller.SellerCommon;
import com.kosta.winter.data.bm.buyer.BuyerUser;
import com.kosta.winter.data.bm.seller.SellerUser;
import com.kosta.winter.def.HttpResulter;
import com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;

public class BuyerUserSignController extends Controller implements Post {
    BuyerCommon common;
    @Override
    public ControllerProperties getProperties() {
        common = getCommon();
        return new ControllerProperties()
                .setRoutage("/buyer-sign");
    }

    @Override
    public HttpResulter doPost(HttpExchange exchange, JSONObject jsonObject) {
        BuyerUser buyerUser = cast(jsonObject, BuyerUser.class);

        if(common.notDuplicateBuyerID(buyerUser.getId())){
            int rs = common.signBuyerUser(buyerUser);
            if(rs == 1) {
                return new HttpResulter()
                        .setFilterData(buyerUser)
                        .setStatusCode(201)
                        .setMsg("아이디 생성");
            }
        }

        return new HttpResulter()
                .setStatusCode(400)
                .setMsg("아이디 중복");
    }

}