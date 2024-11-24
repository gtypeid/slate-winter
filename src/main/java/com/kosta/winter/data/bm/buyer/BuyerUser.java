package com.kosta.winter.data.bm.buyer;

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
public class BuyerUser implements Binder {
    private int seq = -1;
    private String uuid;
    private String id;
    private String passWord;
    private String nickName;
    private String profile;
    private String location;
    private Date mdate;

    public BuyerUser(){
    }

    @Override
    public void setter(ResultSet item) throws SQLException {
        seq = item.getInt("SEQ");
        uuid = item.getString("UUID");
        id = item.getString("ID");
        passWord = item.getString("PASSWORD");
        nickName = item.getString("NICKNAME");
        profile = item.getString("PROFILE");
        location = item.getString("LOCATION");
        mdate = item.getDate("MDATE");
    }

    @Override
    public Object[] getter() {
        return new Object[]{
                uuid, id, passWord, nickName, profile, location
        };
    }

    @Override
    public BuyerUser filter() {
        BuyerUser filterUser = new BuyerUser();
        filterUser.setUuid( uuid );
        filterUser.setId( id );
        filterUser.setNickName( nickName );
        filterUser.setProfile( profile );
        filterUser.setLocation( location );
        return filterUser;
    }
}
