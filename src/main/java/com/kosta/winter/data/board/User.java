package com.kosta.winter.data.board;

import com.kosta.winter.data.Binder;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User implements Binder {
    private int seq = -1;
    private String uuid;
    private String id;
    private String passWord;
    private String profile;
    private Date mdate;

    public User(){

    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    @Override
    public void setter(ResultSet item) throws SQLException {
        seq = item.getInt("SEQ");
        uuid = item.getString("UUID");
        id = item.getString("ID");
        passWord = item.getString("PASSWORD");
        profile = item.getString("PROFILE");
        mdate = item.getDate("MDATE");
    }

    @Override
    public Object[] getter() {
        return new Object[]{
                //seq = user_seq
                uuid, id, passWord, profile, mdate
        };
    }

    @Override
    public User filter() {
        User filter = new User();
        filter.setId( id );
        filter.setUuid( uuid );
        filter.setProfile( profile );
        return filter;
    }

    @Override
    public String toString() {
        return "User{" +
                "seq=" + seq +
                ", uuid='" + uuid + '\'' +
                ", id='" + id + '\'' +
                ", passWord='" + passWord + '\'' +
                ", profile='" + profile + '\'' +
                ", mdate=" + mdate +
                '}';
    }
}
