package com.kosta.winter.controller.bm.order;

import com.kosta.winter.controller.abs.Common;
import com.kosta.winter.data.bm.buyer.BuyerUser;
import com.kosta.winter.data.bm.order.Order;
import com.kosta.winter.data.bm.order.OrderUser;
import com.kosta.winter.data.bm.seller.Label;
import com.kosta.winter.data.bm.seller.SellerUser;
import com.kosta.winter.data.bm.seller.Store;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public class OrderCommon extends Common {

    public OrderUser getOrderUser(String orderID){
        String table = "bm_order_user";
        return getMatchID(table, OrderUser.class, orderID);
    }

    public List<Order> getOrders(){
        String table = "bm_order_order";
        String sql = "SELECT * FROM "
                + table
                + " ORDER BY seq DESC";
        return db.sqlQuery(sql, Order.class);
    }

    public List<Order> getOrders(String sellerUUID){
        String table = "bm_order_order";
        String sql = "SELECT * FROM "
                + table
                + " WHERE owner_seller_uuid = ?"
                + " ORDER BY seq DESC";
        return db.sqlQuery(sql, Order.class, sellerUUID);
    }

    public Order getOrderMatchUUID(String orderUUID){
        String table = "bm_order_order";
        return getMatchUUID(table, Order.class, orderUUID);
    }

    public int updateResponseOrder(Order order, java.util.Date dataValue){
        String orderUUID = order.getUuid();
        String table = "bm_order_order";
        String sql = "UPDATE "
                + table
                + " SET response_order = ? WHERE uuid = ?";
        return db.sqlUpdate(sql, dataValue, orderUUID);
    }

    public int insertOrder(Order order){
        order.setUuid( UUID.randomUUID().toString() );
        java.util.Date jDate = new java.util.Date();
        order.setRequestOrder( new Date( jDate.getTime() ) );
        String table = "bm_order_order";
        String seq = "bm_order_order_seq.nextval";
        String sql =  "INSERT INTO "
                + table
                + " (SEQ, UUID, OWNER_BUYER_UUID, OWNER_SELLER_UUID, OWNER_STORE_UUID, OWNER_MENU_UUID, REQUEST_ORDER, RESPONSE_ORDER, COMPLETE_ORDER)"
                + " values(" + seq + ", ?, ?, ?, ?, ?, ?, ?, ?)";
        return db.sqlUpdate(sql, order);
    }
}