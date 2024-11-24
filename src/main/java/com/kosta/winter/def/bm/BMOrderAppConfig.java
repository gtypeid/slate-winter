package com.kosta.winter.def.bm;

import com.kosta.winter.controller.abs.Common;
import com.kosta.winter.controller.abs.Controller;
import com.kosta.winter.controller.abs.SlateController;
import com.kosta.winter.controller.bm.order.*;
import com.kosta.winter.controller.bm.seller.SellerCommon;
import com.kosta.winter.def.AppConfig;

public class BMOrderAppConfig extends AppConfig {

    private final String TABLE_USER = "BM_ORDER_USER";
    private final String TABLE_ORDER = "BM_ORDER_ORDER";

    public BMOrderAppConfig(){
        PORT = 8083;
        INDEX_HTML = "bm-order-index.html";
        DB_TEST_CHECK = true;
        DB_TEST_OVERLAP_REGENERATE = false;
    }

    @Override
    public Controller[] appControllers() {
        return new Controller[]{
                new SlateController(),

                new OrderLoginController(),
                new OrderController(),
                new OrderGetController(),
                new OrderResponseController()
        };
    }

    @Override
    public String[] appDefinedTableNames() {
        return new String[]{
                TABLE_USER,
                TABLE_ORDER
        };
    }

    @Override
    public String appSequence(String tableName) {
        String sql = "";

        if(tableName.equals(TABLE_ORDER)){
            sql = "CREATE SEQUENCE bm_order_order_seq "
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
                    + " (id VARCHAR2(20), "
                    + "password VARCHAR2(20) )";
        }

        if(tableName.equals(TABLE_ORDER)){
            sql = "CREATE TABLE "
                    + tableName
                    + " (seq NUMBER NOT NULL, "
                    + "uuid VARCHAR2(40), "
                    + "owner_buyer_uuid VARCHAR2(40), "
                    + "owner_seller_uuid VARCHAR2(40), "
                    + "owner_store_uuid VARCHAR2(40), "
                    + "owner_menu_uuid VARCHAR2(40), "
                    + "request_order DATE, "
                    + "response_order DATE, "
                    + "complete_order DATE, "
                    + "PRIMARY KEY(seq))";
        }
        return sql;
    }

    @Override
    public Common getCommon() {
        return new OrderCommon();
    }
}
