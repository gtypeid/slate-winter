package com.kosta.winter.data.bm.seller;
import com.kosta.winter.data.Binder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Blob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

@Setter
@Getter
@ToString
public class SellerFile implements Binder {

    private int seq = -1;
    private String uuid;
    private Blob binary;
    private Date mdate;

    public SellerFile(){

    }

    @Override
    public void setter(ResultSet item) throws SQLException {
        seq = item.getInt("SEQ");
        uuid = item.getString("UUID");
        binary = item.getBlob("BINARY");
        mdate = item.getDate("MDATE");
    }

    @Override
    public Object[] getter() {
        return new Object[]{
                uuid, binary
        };
    }

    @Override
    public SellerFile filter() {
        return this;
    }

}
