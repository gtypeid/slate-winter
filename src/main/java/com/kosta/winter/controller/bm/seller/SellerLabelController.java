package com.kosta.winter.controller.bm.seller;

import com.kosta.winter.controller.abs.Controller;
import com.kosta.winter.controller.abs.ControllerProperties;
import com.kosta.winter.controller.abs.Get;
import com.kosta.winter.controller.abs.Post;
import com.kosta.winter.data.bm.seller.Label;
import com.kosta.winter.data.bm.seller.Menu;
import com.kosta.winter.data.bm.seller.Store;
import com.kosta.winter.def.HttpResulter;
import com.kosta.winter.def.PathVariable;
import com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;

import java.util.List;

public class SellerLabelController extends Controller implements Get, Post {
    SellerCommon common;

    @Override
    public ControllerProperties getProperties() {
        common = getCommon();
        return new ControllerProperties()
                .setRoutage("/store-labels/");
    }

    @Override
    public List<Label> doGet(HttpExchange exchange) {
        PathVariable pathVariable = pathVariable(exchange);
        String storeUUID = pathVariable.value.get(0);
        return common.getStoreLabels(storeUUID);
    }

    @Override
    public HttpResulter doPost(HttpExchange exchange, JSONObject jsonObject) {
        PathVariable pathVariable = pathVariable(exchange);
        String storeUUID = pathVariable.value.get(0);
        Store store = common.getSellerStore(storeUUID);
        if(store != null){
            Label label = cast(jsonObject, Label.class);
            int rs = common.insertLabel(store, label);
            if(rs == 1) {
                return new HttpResulter()
                        .setFilterData(label)
                        .setStatusCode(201)
                        .setMsg("스토어에 라벨 추가");
            }
        }

        return new HttpResulter()
                .setStatusCode(400)
                .setMsg("잘못된 스토어 UUID");
    }
}
