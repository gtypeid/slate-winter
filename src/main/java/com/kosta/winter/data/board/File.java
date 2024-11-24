package com.kosta.winter.data.board;
import com.kosta.winter.data.Binder;

import java.sql.Blob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class File implements Binder {

    private int seq = -1;
    private String uuid;
    private Blob binary;
    private Date mdate;

    public File(){

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

    public Blob getBinary() {
        return binary;
    }

    public void setBinary(Blob binary) {
        this.binary = binary;
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
        binary = item.getBlob("BINARY");
        mdate = item.getDate("MDATE");
    }

    @Override
    public Object[] getter() {
        return new Object[]{
                uuid, binary, mdate
        };
    }

    @Override
    public File filter() {
        return this;
    }

    @Override
    public String toString() {
        return "File{" +
                "seq=" + seq +
                ", uuid='" + uuid + '\'' +
                ", binary=" + binary +
                ", mdate=" + mdate +
                '}';
    }
}
