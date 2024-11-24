package com.kosta.winter.controller.bm.seller;

import com.kosta.winter.controller.abs.Controller;
import com.kosta.winter.controller.abs.ControllerProperties;
import com.kosta.winter.controller.abs.Get;
import com.kosta.winter.data.bm.seller.SellerUser;
import com.kosta.winter.data.bm.seller.Store;
import com.kosta.winter.def.HttpResulter;
import com.kosta.winter.def.PathVariable;
import com.sun.net.httpserver.HttpExchange;

import java.util.List;

public class SellerStoresGetController extends Controller implements Get {
    SellerCommon common;

    @Override
    public ControllerProperties getProperties() {
        common = getCommon();
        return new ControllerProperties()
                .setRoutage("/seller-stores/");
    }

    @Override
    public List<Store> doGet(HttpExchange exchange) {
        PathVariable pathVariable = pathVariable(exchange);

        String category = pathVariable.value.get(0);
        return common.getSellerStores(category);
    }

}
