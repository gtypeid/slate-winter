package com.kosta.winter.core;

import com.kosta.winter.def.AppConfig;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckTable {
    private final DataBase dataBase;
    private final AppConfig appConfig;

    public CheckTable(DataBase dataBase, AppConfig appConfig){
        this.dataBase = dataBase;
        this.appConfig = appConfig;

        String[] tables = appConfig.appDefinedTableNames();
        for(String it : tables){
            checkTable(it, appConfig);
        }
    }

    private void checkTable(String tableName, AppConfig appConfig) {
        try {
            if(tableExists(tableName)){
                if(appConfig.DB_TEST_OVERLAP_REGENERATE){
                    deleteTable(tableName);
                    createTable(tableName, appConfig);
                }
            }
            else {
                createTable(tableName, appConfig);
            }
        } catch (Exception e) {
        } finally {
        }
    }

    private void createTable(String tableName, AppConfig appConfig){
        String sequenceSQL = appConfig.appSequence(tableName);
        String tableSQL = appConfig.appTable(tableName);

        if(!sequenceSQL.isEmpty())
            dataBase.sqlExecute(sequenceSQL);
        if(!tableSQL.isEmpty())
            dataBase.sqlExecute(tableSQL);
    }

    private void deleteTable(String tableName){
        String sql = "DROP TABLE " + tableName + " CASCADE CONSTRAINTS PURGE";
        dataBase.sqlExecute(sql);
    }

    private boolean tableExists(String tableName) throws SQLException {
        Connection conn = dataBase.connection();
        boolean found = false;
        DatabaseMetaData databaseMetaData = conn.getMetaData();
        ResultSet rs = databaseMetaData.getTables(null, null, tableName, null);
        while (rs.next()) {
            String name = rs.getString("TABLE_NAME");
            if (tableName.equals(name)) {
                found = true;
                break;
            }
        }

        rs.close();
        conn.close();

        return found;
    }
}
