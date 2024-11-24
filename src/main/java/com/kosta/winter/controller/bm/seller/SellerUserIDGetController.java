package com.kosta.winter.controller.bm.seller;

import com.kosta.winter.controller.abs.Controller;
import com.kosta.winter.controller.abs.ControllerProperties;
import com.kosta.winter.controller.abs.Get;
import com.kosta.winter.data.bm.seller.SellerUser;
import com.kosta.winter.def.PathVariable;
import com.sun.net.httpserver.HttpExchange;

public class SellerUserIDGetController extends Controller implements Get {
    SellerCommon common;

    @Override
    public ControllerProperties getProperties() {
        common = getCommon();
        return new ControllerProperties()
                .setRoutage("/seller-id/");
    }

    @Override
    public SellerUser doGet(HttpExchange exchange) {
        PathVariable pathVariable = pathVariable(exchange);
        String sellerID = pathVariable.value.get(0);
        return common.getSellerUser(sellerID);
    }

}
