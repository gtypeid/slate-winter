package com.kosta.winter.controller.bm.order;

import com.kosta.winter.controller.abs.Controller;
import com.kosta.winter.controller.abs.ControllerProperties;
import com.kosta.winter.controller.abs.Get;
import com.kosta.winter.controller.bm.seller.SellerCommon;
import com.kosta.winter.data.bm.order.Order;
import com.kosta.winter.data.bm.seller.Store;
import com.kosta.winter.def.PathVariable;
import com.sun.net.httpserver.HttpExchange;

import java.util.List;

public class OrderGetController extends Controller implements Get {
    OrderCommon common;

    @Override
    public ControllerProperties getProperties() {
        common = getCommon();
        return new ControllerProperties()
                .setRoutage("/order/");
    }

    @Override
    public List<Order> doGet(HttpExchange exchange) {
        PathVariable pathVariable = pathVariable(exchange);
        String sellerUUID = pathVariable.value.get(0);
        return common.getOrders(sellerUUID);
    }

}
