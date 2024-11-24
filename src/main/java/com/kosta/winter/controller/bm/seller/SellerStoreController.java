package com.kosta.winter.controller.bm.seller;

import com.kosta.winter.controller.abs.Controller;
import com.kosta.winter.controller.abs.ControllerProperties;
import com.kosta.winter.controller.abs.Get;
import com.kosta.winter.controller.abs.Post;
import com.kosta.winter.data.bm.seller.SellerUser;
import com.kosta.winter.data.bm.seller.Store;
import com.kosta.winter.def.HttpResulter;
import com.kosta.winter.def.PathVariable;
import com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;

public class SellerStoreController extends Controller implements Get, Post {
    SellerCommon common;

    @Override
    public ControllerProperties getProperties() {
        common = getCommon();
        return new ControllerProperties()
                .setRoutage("/store/");
    }

    @Override
    public HttpResulter doGet(HttpExchange exchange) {
        PathVariable pathVariable = pathVariable(exchange);
        String sellerUUID = pathVariable.value.get(0);
        return common.getSellerStoreMatchSellerUUID(sellerUUID);
    }

    @Override
    public HttpResulter doPost(HttpExchange exchange, JSONObject jsonObject) {
        PathVariable pathVariable = pathVariable(exchange);
        String sellerUUID = pathVariable.value.get(0);
        SellerUser sellerUser = common.getSellerUserMatchUUID(sellerUUID);

        if(sellerUser != null){
            Store store = cast(jsonObject, Store.class);
            int rs = common.insertStore(sellerUser, store);
            if(rs == 1) {
                return new HttpResulter()
                        .setFilterData(store)
                        .setStatusCode(201)
                        .setMsg("스토어 추가");
            }
        }

        return new HttpResulter()
                .setStatusCode(400)
                .setMsg("잘못된 유저 UUID");
    }
}
