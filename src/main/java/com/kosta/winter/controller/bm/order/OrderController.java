package com.kosta.winter.controller.bm.order;

import com.kosta.winter.controller.abs.Controller;
import com.kosta.winter.controller.abs.ControllerProperties;
import com.kosta.winter.controller.abs.Get;
import com.kosta.winter.controller.abs.Post;
import com.kosta.winter.data.bm.order.Order;
import com.kosta.winter.data.bm.seller.Menu;
import com.kosta.winter.data.bm.seller.Store;
import com.kosta.winter.def.HttpResulter;
import com.kosta.winter.def.PathVariable;
import com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;

import java.util.List;

public class OrderController extends Controller implements Get, Post {
    OrderCommon common;

    @Override
    public ControllerProperties getProperties() {
        common = getCommon();
        return new ControllerProperties()
                .setRoutage("/orders");
    }

    @Override
    public List<Order> doGet(HttpExchange exchange) {
        return common.getOrders();
    }

    @Override
    public HttpResulter doPost(HttpExchange exchange, JSONObject jsonObject) {
        Order order = cast(jsonObject, Order.class);
        int rs = common.insertOrder(order);
        if(rs == 1) {
            return new HttpResulter()
                    .setFilterData(order)
                    .setStatusCode(201)
                    .setMsg("오더 추가");
        }
        return new HttpResulter()
                .setStatusCode(400)
                .setMsg("잘못된 오더 데이터");
    }
}
