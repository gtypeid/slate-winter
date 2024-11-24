package com.kosta.winter.data.bm.seller;

import com.kosta.winter.data.Binder;
import com.kosta.winter.data.bm.buyer.BuyerUser;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@ToString
public class SellerUser implements Binder {
    private int seq = -1;
    private String uuid;
    private String id;
    private String passWord;
    private Date mdate;

    public SellerUser(){
    }

    @Override
    public void setter(ResultSet item) throws SQLException {
        seq = item.getInt("SEQ");
        uuid = item.getString("UUID");
        id = item.getString("ID");
        passWord = item.getString("PASSWORD");
        mdate = item.getDate("MDATE");
    }

    @Override
    public Object[] getter() {
        return new Object[]{
                uuid, id, passWord
        };
    }

    @Override
    public SellerUser filter() {
        return this;
    }
}
