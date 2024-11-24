package com.kosta.winter.data.bm.seller;

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
public class Menu implements Binder {
    private int seq = -1;
    private String uuid;
    private String ownerStoreUuid;
    private String title;
    private String comments;
    private int price;
    private String img = "default";
    private Date mdate;
    public Menu(){
    }

    @Override
    public void setter(ResultSet item) throws SQLException {
        seq = item.getInt("SEQ");
        uuid = item.getString("UUID");
        ownerStoreUuid = item.getString("OWNER_STORE_UUID");
        title = item.getString("TITLE");
        comments = item.getString("COMMENTS");
        price = item.getInt("PRICE");
        img = item.getString("IMG");
        mdate = item.getDate("MDATE");
    }

    @Override
    public Object[] getter() {
        return new Object[]{
                uuid, ownerStoreUuid, title, comments, price, img
        };
    }

    @Override
    public Menu filter() {
        return this;
    }
}
