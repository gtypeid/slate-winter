package com.kosta.winter.def.bm;

import com.kosta.winter.controller.abs.Common;
import com.kosta.winter.controller.abs.Controller;
import com.kosta.winter.controller.abs.SlateController;
import com.kosta.winter.controller.bm.seller.*;
import com.kosta.winter.def.AppConfig;

import java.sql.Date;

public class BMSellerAppConfig extends AppConfig {

    private final String TABLE_USER = "BM_SELLER_USER";
    private final String TABLE_STORE = "BM_SELLER_STORE";
    private final String TABLE_MENU = "BM_SELLER_MENU";
    private final String TABLE_FILE = "BM_SELLER_FILE";

    public BMSellerAppConfig(){
        PORT = 8082;
        INDEX_HTML = "bm-seller-index.html";
        DB_TEST_CHECK = true;
        DB_TEST_OVERLAP_REGENERATE = false;
    }

    @Override
    public Controller[] appControllers() {
        return new Controller[]{
                new SlateController(),

                new SellerLoginController(),
                new SellerUserSignController(),
                new SellerUserIDGetController(),
                new SellerStoreController(),
                new SellerStoresGetController(),
                new SellerStoreLogoUploadController(),
                new SellerMenuController(),
                new SellerMenuImgUploadController(),
                new SellerFileGetController()
        };
    }

    @Override
    public String[] appDefinedTableNames() {
        return new String[]{
                TABLE_USER,
                TABLE_STORE,
                TABLE_MENU,
                TABLE_FILE
        };
    }

    @Override
    public String appSequence(String tableName) {
        String sql = "";

        if(tableName.equals(TABLE_USER)){
            sql = "CREATE SEQUENCE bm_seller_user_seq "
                    + "START WITH 1 "
                    + "INCREMENT BY 1 "
                    + "NOCACHE "
                    + "NOCYCLE";
        }

        if(tableName.equals(TABLE_STORE)){
            sql = "CREATE SEQUENCE bm_seller_store_seq "
                    + "START WITH 1 "
                    + "INCREMENT BY 1 "
                    + "NOCACHE "
                    + "NOCYCLE";
        }

        if(tableName.equals(TABLE_MENU)){
            sql = "CREATE SEQUENCE bm_seller_menu_seq "
                    + "START WITH 1 "
                    + "INCREMENT BY 1 "
                    + "NOCACHE "
                    + "NOCYCLE";
        }

        if(tableName.equals(TABLE_FILE)){
            sql = "CREATE SEQUENCE bm_seller_file_seq "
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
                    + "mdate DATE default sysdate, "
                    + "PRIMARY KEY(seq))";
        }

        if(tableName.equals(TABLE_STORE)){
            sql = "CREATE TABLE "
                    + tableName
                    + " (seq NUMBER NOT NULL, "
                    + "uuid VARCHAR2(40), "
                    + "owner_seller_uuid VARCHAR2(40), "
                    + "title VARCHAR2(20), "
                    + "comments VARCHAR2(40), "
                    + "store_category VARCHAR2(20), "
                    + "logo VARCHAR2(40), "
                    + "mdate DATE default sysdate, "
                    + "PRIMARY KEY(seq))";
        }

        if(tableName.equals(TABLE_MENU)){
            sql = "CREATE TABLE "
                    + tableName
                    + " (seq NUMBER NOT NULL, "
                    + "uuid VARCHAR2(40), "
                    + "owner_store_uuid VARCHAR2(40), "
                    + "title VARCHAR2(20), "
                    + "comments VARCHAR2(40), "
                    + "price NUMBER, "
                    + "img VARCHAR2(40), "
                    + "mdate DATE default sysdate, "
                    + "PRIMARY KEY(seq))";
        }

        if(tableName.equals(TABLE_FILE)){
            sql = "CREATE TABLE "
                    + tableName
                    + " (seq NUMBER NOT NULL, "
                    + "uuid VARCHAR2(40), "
                    + "binary BLOB, "
                    + "mdate DATE default sysdate, "
                    + "PRIMARY KEY(seq))";
        }

        return sql;
    }

    @Override
    public Common getCommon() {
        return new SellerCommon();
    }
}
