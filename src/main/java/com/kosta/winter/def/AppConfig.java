package com.kosta.winter.def;

import com.kosta.winter.controller.abs.Common;
import com.kosta.winter.controller.abs.Controller;

public abstract class AppConfig {
    public int PORT = 8081;
    public boolean BANNER = true;

    public String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
    public String DB_ID = "it";
    public String DB_PW = "0000";
    public String DB_TYPE = "oracle.jdbc.driver.OracleDriver";

    public String INDEX_HTML = "index.html";

    public boolean DB_TEST_CHECK = true;
    public boolean DB_TEST_OVERLAP_REGENERATE = true;

    abstract public Controller[] appControllers();
    abstract public String[] appDefinedTableNames();
    abstract public String appSequence(String tableName);
    abstract public String appTable(String tableName);
    abstract public Common getCommon();
}
