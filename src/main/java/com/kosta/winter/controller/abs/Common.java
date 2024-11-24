package com.kosta.winter.controller.abs;

import com.kosta.winter.core.DataBase;
import java.util.List;

public abstract class Common {

    public Common(){

    }

    public void setDb(DataBase db){
        this.db = db;
    }

    protected DataBase db;

    public <T> T getMatchID(String table, Class<T> t, String id){
        String sql = "SELECT * FROM "
                + table
                + " WHERE id = ?";
        List<T> finds = db.sqlQuery(sql, t, id);
        if(finds.isEmpty()){
            return null;
        }
        return finds.get(0);
    }

    public <T> T getMatchUUID(String table, Class<T> t, String uuid){
        String sql = "SELECT * FROM "
                + table
                + " WHERE uuid = ?";
        List<T> finds = db.sqlQuery(sql, t, uuid);
        if(finds.isEmpty()){
            return null;
        }
        return finds.get(0);
    }

    public <T> boolean notDuplicateID(String table, Class<T> t, String id){
        String sql = "SELECT * FROM "
                + table
                + " WHERE id = ?";
        List<T> finds = db.sqlQuery(sql, t, id);
        return finds.isEmpty();
    }
}
