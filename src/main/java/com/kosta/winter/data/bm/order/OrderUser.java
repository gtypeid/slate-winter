package com.kosta.winter.data.bm.order;

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
public class OrderUser implements Binder {
    private String id;
    private String passWord;

    public OrderUser(){
    }

    @Override
    public void setter(ResultSet item) throws SQLException {
        id = item.getString("ID");
        passWord = item.getString("PASSWORD");
    }

    @Override
    public Object[] getter() {
        return new Object[]{
                id, passWord
        };
    }

    @Override
    public OrderUser filter() {
        OrderUser filterUser = new OrderUser();
        filterUser.setId( id );
        return filterUser;
    }
}
