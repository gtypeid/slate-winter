package com.kosta.winter.controller.bm.order;

import com.kosta.winter.controller.abs.Controller;
import com.kosta.winter.controller.abs.ControllerProperties;
import com.kosta.winter.controller.abs.Get;
import com.kosta.winter.data.bm.order.Order;
import com.kosta.winter.def.HttpResulter;
import com.kosta.winter.def.PathVariable;
import com.sun.net.httpserver.HttpExchange;

import java.sql.Date;

public class OrderResponseController extends Controller implements Get {
    OrderCommon common;

    @Override
    public ControllerProperties getProperties() {
        common = getCommon();
        return new ControllerProperties()
                .setRoutage("/order-response/");
    }

    @Override
    public HttpResulter doGet(HttpExchange exchange) {
        PathVariable pathVariable = pathVariable(exchange);
        String orderUUID = pathVariable.value.get(0);

        java.util.Date jDate = new java.util.Date();
        java.sql.Date dataValue = new Date( jDate.getTime() );
        Order order = common.getOrderMatchUUID(orderUUID);
        if(order != null){
            if(order.getResponseOrder() == null){
                int rs = common.updateResponseOrder(order, dataValue);
                if( rs == 1){
                    order.setResponseOrder(dataValue);
                    return new HttpResulter()
                            .setMsg("order 승인 완료")
                            .setFilterData(order)
                            .setStatusCode(201);
                }
            }
            else {
                return new HttpResulter()
                        .setMsg("이미 처리된 order")
                        .setStatusCode(400);
            }
        }
        return new HttpResulter()
                .setMsg("잘못된 order uuid")
                .setStatusCode(400);
    }


}
