package com.kosta.winter.data.board;

import com.kosta.winter.data.Binder;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Board implements Binder {
    private int seq = -1;
    private String uuid;
    private String owner;
    private String title;
    private String contents;
    private Date mdate;

    public Board(){

    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }


    @Override
    public void setter(ResultSet item) throws SQLException {
        seq = item.getInt("SEQ");
        uuid = item.getString("UUID");
        owner = item.getString("OWNER");
        title = item.getString("TITLE");
        contents = item.getString("CONTENTS");
        mdate = item.getDate("MDATE");
    }

    @Override
    public Object[] getter() {
        return new Object[]{
                // seq
                uuid, owner, title, contents, mdate
        };
    }

    @Override
    public Board filter() {
        return this;
    }


    @Override
    public String toString() {
        return "Board{" +
                "seq=" + seq +
                ", uuid='" + uuid + '\'' +
                ", owner='" + owner + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", mdate=" + mdate +
                '}';
    }
}
