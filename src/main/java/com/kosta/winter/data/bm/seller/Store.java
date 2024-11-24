package com.kosta.winter.data.bm.seller;


import com.kosta.winter.data.Binder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@ToString
public class Store implements Binder {
    private int seq = -1;
    private String uuid;
    private String ownerSellerUuid;
    private String title;
    private String comments;
    private String storeCategory;
    private String logo = "default";
    private Date mdate;

    public Store(){

    }

    @Override
    public void setter(ResultSet item) throws SQLException {
        seq = item.getInt("SEQ");
        uuid = item.getString("UUID");
        ownerSellerUuid = item.getString("OWNER_SELLER_UUID");
        title = item.getString("TITLE");
        comments = item.getString("COMMENTS");
        storeCategory = item.getString("STORE_CATEGORY");
        logo = item.getString("LOGO");
        mdate = item.getDate("MDATE");
    }

    @Override
    public Object[] getter() {
        return new Object[]{
                uuid, ownerSellerUuid, title, comments, storeCategory, logo
        };
    }

    @Override
    public Store filter() {
        return this;
    }
}
