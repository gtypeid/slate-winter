package com.kosta.winter.data.board;

import com.kosta.winter.data.Binder;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Reply implements Binder {

    private int seq = -1;
    private String uuid;
    private String owner;
    private String bindBoard;
    private String contents;
    private Date mdate;

    public Reply(){

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

    public String getBindBoard() {
        return bindBoard;
    }

    public void setBindBoard(String bindBoard) {
        this.bindBoard = bindBoard;
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
    public String toString() {
        return "Reply{" +
                "seq=" + seq +
                ", uuid='" + uuid + '\'' +
                ", owner='" + owner + '\'' +
                ", bindBoard=" + bindBoard +
                ", contents='" + contents + '\'' +
                ", mdate=" + mdate +
                '}';
    }

    @Override
    public void setter(ResultSet item) throws SQLException {
        seq = item.getInt("SEQ");
        uuid = item.getString("UUID");
        owner = item.getString("OWNER");
        bindBoard = item.getString("BIND_BOARD");
        contents = item.getString("CONTENTS");
        mdate = item.getDate("MDATE");
    }

    @Override
    public Object[] getter() {
        return new Object[]{
            uuid, owner, bindBoard, contents, mdate
        };
    }

    @Override
    public Reply filter() {
        Reply reply = new Reply();
        reply.setUuid(uuid);
        reply.setOwner(owner);
        reply.setBindBoard(bindBoard);
        reply.setContents(contents);
        reply.setMdate(mdate);
        return reply;
    }

}
