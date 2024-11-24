package com.kosta.winter.def.bm;

import com.kosta.winter.controller.abs.Common;
import com.kosta.winter.controller.abs.Controller;
import com.kosta.winter.controller.abs.SlateController;
import com.kosta.winter.controller.bm.buyer.BuyerCommon;
import com.kosta.winter.controller.bm.buyer.BuyerLoginController;
import com.kosta.winter.controller.bm.buyer.BuyerUserGetController;
import com.kosta.winter.controller.bm.buyer.BuyerUserSignController;
import com.kosta.winter.def.AppConfig;

import java.sql.Date;

public class BMBuyerAppConfig extends AppConfig {

    private final String TABLE_USER = "BM_BUYER_USER";

    public BMBuyerAppConfig(){
        PORT = 8081;
        INDEX_HTML = "bm-buyer-index.html";
        DB_TEST_CHECK = true;
        DB_TEST_OVERLAP_REGENERATE = false;
    }

    @Override
    public Controller[] appControllers() {
        return new Controller[]{
                new SlateController(),

                new BuyerUserGetController(),
                new BuyerUserSignController(),
                new BuyerLoginController()
        };
    }

    @Override
    public String[] appDefinedTableNames() {
        return new String[]{
                TABLE_USER
        };
    }

    @Override
    public String appSequence(String tableName) {
        String sql = "";

        if(tableName.equals(TABLE_USER)){
            sql = "CREATE SEQUENCE bm_buyer_user_seq "
                    + "START WITH 1 "
                    + "INCREMENT BY 1 "
                    + "NOCACHE "
                    + "NOCYCLE";
        }
        return sql;
    }

    @Override
    public String appTable(String tableName) {
        String sql = "";

        if(tableName.equals(TABLE_USER)){
            sql = "CREATE TABLE "
                    + tableName
                    + " (seq NUMBER NOT NULL, "
                    + "uuid VARCHAR2(40), "
                    + "id VARCHAR2(20), "
                    + "password VARCHAR2(20), "
                    + "nickname VARCHAR2(20), "
                    + "profile VARCHAR2(40), "
                    + "location VARCHAR2(40), "
                    + "mdate DATE default sysdate, "
                    + "PRIMARY KEY(seq))";
        }
        return sql;
    }

    @Override
    public Common getCommon() {
        return new BuyerCommon();
    }
}
