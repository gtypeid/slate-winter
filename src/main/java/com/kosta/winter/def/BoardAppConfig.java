package com.kosta.winter.def;

import com.kosta.winter.controller.abs.Common;
import com.kosta.winter.controller.abs.Controller;
import com.kosta.winter.controller.abs.SlateController;
import com.kosta.winter.controller.board.*;
import com.kosta.winter.controller.board.dice.DiceController;
import com.kosta.winter.controller.board.test.TestHTMLController;
import com.kosta.winter.controller.board.test.TestJSONArrayController;
import com.kosta.winter.controller.board.test.TestJSONController;

public class BoardAppConfig extends AppConfig{

    private final String TABLE_USER = "APP_USER";
    private final String TABLE_BOARD = "APP_BOARD";
    private final String TABLE_REPLY = "APP_REPLY";
    private final String TABLE_FILE = "APP_FILE";

    @Override
    public Controller[] appControllers() {
        return new Controller[]{
                // slate
                new SlateController(),

                // app
                new BoardDeleteController(),
                new BoardPostController(),
                new BoardsController(),

                new ReplyPostController(),
                new RepliesController(),

                new UserGetController(),
                new UserLoginController(),
                new UserSignController(),

                new UserProfileController(),
                new UserProfilePostController(),
                new FileController(),

                // test
                new TestHTMLController(),
                new TestJSONController(),
                new TestJSONArrayController(),

                // dice
                new DiceController(),
        };
    }

    @Override
    public String[] appDefinedTableNames() {
        return new String[]{
                TABLE_USER,
                TABLE_BOARD,
                TABLE_REPLY,
                TABLE_FILE
        };
    }

    @Override
    public String appSequence(String tableName) {
        String sql = "";

        if(tableName.equals(TABLE_USER)){
            sql = "CREATE SEQUENCE user_seq "
                    + "START WITH 1 "
                    + "INCREMENT BY 1 "
                    + "NOCACHE "
                    + "NOCYCLE";
        }

        if(tableName.equals(TABLE_BOARD)){
            sql = "CREATE SEQUENCE board_seq "
                    + "START WITH 1 "
                    + "INCREMENT BY 1 "
                    + "NOCACHE "
                    + "NOCYCLE";
        }

        if(tableName.equals(TABLE_REPLY)){
            sql = "CREATE SEQUENCE reply_seq "
                    + "START WITH 1 "
                    + "INCREMENT BY 1 "
                    + "NOCACHE "
                    + "NOCYCLE";
        }

        if(tableName.equals(TABLE_FILE)){
            sql = "CREATE SEQUENCE file_seq "
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
                    + "profile VARCHAR2(40), "
                    + "mdate DATE, "
                    + "PRIMARY KEY(seq))";
        }

        if(tableName.equals(TABLE_BOARD)){
            sql =  "CREATE TABLE "
                    + tableName
                    + " (seq NUMBER NOT NULL, "
                    + "uuid VARCHAR2(40), "
                    + "owner VARCHAR2(20), "
                    + "title VARCHAR2(20), "
                    + "contents VARCHAR2(200), "
                    + "mdate DATE, "
                    + "PRIMARY KEY(seq) )";
            // + "FOREIGN KEY(owner_seq) REFERENCES " + TABLE_USER + "(seq) )";

        }

        if(tableName.equals(TABLE_REPLY)){
            sql = "CREATE TABLE "
                    + tableName
                    + " (seq NUMBER NOT NULL, "
                    + "uuid VARCHAR2(40), "
                    + "owner VARCHAR2(20), "
                    + "bind_board VARCHAR2(40), "
                    + "contents VARCHAR2(200), "
                    + "mdate DATE, "
                    + "PRIMARY KEY(seq))";
        }

        if(tableName.equals(TABLE_FILE)){
            sql = "CREATE TABLE "
                    + tableName
                    + " (seq NUMBER NOT NULL, "
                    + "uuid VARCHAR2(40), "
                    + "binary BLOB, "
                    + "mdate DATE, "
                    + "PRIMARY KEY(seq))";
        }
        return sql;
    }

    @Override
    public Common getCommon() {
        return null;
    }
}
