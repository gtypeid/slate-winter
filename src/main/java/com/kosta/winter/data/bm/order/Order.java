package com.kosta.winter.data.bm.order;

import com.kosta.winter.data.Binder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Getter
@Setter
@ToString
public class Order implements Binder {
    private int seq = -1;
    private String uuid;
    private String ownerBuyerUuid;
    private String ownerSellerUuid;
    private String ownerStoreUuid;
    private String ownerMenuUuid;
    private Date requestOrder;
    private Date responseOrder;
    private Date completeOrder;

    public Order(){

    }

    @Override
    public void setter(ResultSet item) throws SQLException {
        seq = item.getInt("SEQ");
        uuid = item.getString("UUID");
        ownerBuyerUuid = item.getString("OWNER_BUYER_UUID");
        ownerSellerUuid = item.getString("OWNER_SELLER_UUID");
        ownerStoreUuid = item.getString("OWNER_STORE_UUID");
        ownerMenuUuid = item.getString("OWNER_MENU_UUID");
        requestOrder = item.getDate("REQUEST_ORDER");
        responseOrder = item.getDate("RESPONSE_ORDER");
        completeOrder = item.getDate("COMPLETE_ORDER");
    }

    @Override
    public Object[] getter() {
        return new Object[]{
                uuid, ownerBuyerUuid, ownerSellerUuid, ownerStoreUuid, ownerMenuUuid,
                requestOrder, responseOrder, completeOrder
        };
    }

    @Override
    public Order filter() {
        return this;
    }
}
