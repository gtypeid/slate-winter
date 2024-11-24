package com.kosta.winter.controller.bm.buyer;

import com.kosta.winter.controller.abs.Controller;
import com.kosta.winter.controller.abs.ControllerProperties;
import com.kosta.winter.controller.abs.Get;
import com.kosta.winter.controller.bm.seller.SellerCommon;
import com.kosta.winter.data.bm.buyer.BuyerUser;
import com.kosta.winter.data.bm.seller.SellerUser;
import com.kosta.winter.def.PathVariable;
import com.sun.net.httpserver.HttpExchange;

public class BuyerUserGetController extends Controller implements Get {
    BuyerCommon common;

    @Override
    public ControllerProperties getProperties() {
        common = getCommon();
        return new ControllerProperties()
                .setRoutage("/buyer/");
    }

    @Override
    public BuyerUser doGet(HttpExchange exchange) {
        PathVariable pathVariable = pathVariable(exchange);
        String buyerID = pathVariable.value.get(0);
        return common.getBuyerUser(buyerID);
    }

}
