package com.kosta.winter.controller.bm.seller;

import com.kosta.winter.controller.abs.Controller;
import com.kosta.winter.controller.abs.ControllerProperties;
import com.kosta.winter.controller.abs.Post;
import com.kosta.winter.data.bm.buyer.BuyerUser;
import com.kosta.winter.data.bm.seller.SellerUser;
import com.kosta.winter.data.board.User;
import com.kosta.winter.def.HttpResulter;
import com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public class SellerUserSignController extends Controller implements Post {
    SellerCommon common;
    @Override
    public ControllerProperties getProperties() {
        common = getCommon();
        return new ControllerProperties()
                .setRoutage("/seller-sign");
    }

    @Override
    public HttpResulter doPost(HttpExchange exchange, JSONObject jsonObject) {
        SellerUser sellerUser = cast(jsonObject, SellerUser.class);
        if(common.notDuplicateSellerID(sellerUser.getId())){
            int rs = common.signSellerUser(sellerUser);
            if(rs == 1) {
                return new HttpResulter()
                        .setFilterData(sellerUser)
                        .setStatusCode(201)
                        .setMsg("아이디 생성");
            }
        }

        return new HttpResulter()
                .setStatusCode(400)
                .setMsg("아이디 중복");
    }

}