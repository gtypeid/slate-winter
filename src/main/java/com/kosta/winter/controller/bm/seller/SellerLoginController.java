package com.kosta.winter.controller.bm.seller;

import com.kosta.winter.controller.abs.Controller;
import com.kosta.winter.controller.abs.ControllerProperties;
import com.kosta.winter.controller.abs.Post;
import com.kosta.winter.controller.bm.buyer.BuyerCommon;
import com.kosta.winter.data.bm.buyer.BuyerUser;
import com.kosta.winter.data.bm.seller.SellerUser;
import com.kosta.winter.def.HttpResulter;
import com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;

public class SellerLoginController extends Controller implements Post {
    SellerCommon common;

    @Override
    public ControllerProperties getProperties() {
        common = getCommon();
        return new ControllerProperties()
                .setRoutage("/seller-login");
    }

    @Override
    public HttpResulter doPost(HttpExchange exchange, JSONObject jsonObject) {
        SellerUser sellerUser = cast(jsonObject, SellerUser.class);
        SellerUser findUser = common.getSellerUser(sellerUser.getId());

        if(findUser != null){
            if( findUser.getPassWord().equals(sellerUser.getPassWord()) ){
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
