package com.kosta.winter.core;

import com.kosta.winter.data.Binder;
import com.kosta.winter.def.AppConfig;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import java.lang.reflect.Constructor;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataBase {

    private AppConfig appConfig;
    private OracleConnectionPoolDataSource poolDataSource;
    public DataBase(){

    }

    public void inIt(AppConfig appConfig){
        this.appConfig = appConfig;
        createDataSource();
        if(appConfig.DB_TEST_CHECK)
            new CheckTable(this, appConfig);
    }

    public Connection connection() {
        try {
            return poolDataSource.getConnection();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void close(Connection conn){
        close(null, null, conn);
    }

    public void close( PreparedStatement pstmt, Connection conn){
        close(null, pstmt, conn);
    }

    public void close(ResultSet result, PreparedStatement pstmt, Connection conn){
        try {
            if(result != null) result.close();
            if(pstmt != null) pstmt.close();
            if(conn != null ) conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> ArrayList<T> sqlQuery(String sql, Class<T> type){
        Connection conn = connection();

        ArrayList<T> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet result = null;
        try {
            pstmt = conn.prepareStatement(sql);
            result = pstmt.executeQuery();
            while (result.next()){
                list.add( getObject(type, result) );
            }
        } catch (Exception e){

        }
        finally {
            close(result, pstmt, conn);
        }
        return list;
    }

    public <T> ArrayList<T> sqlQuery(String sql, Class<T> type, Object... params){
        Connection conn = connection();

        ArrayList<T> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet result = null;
        try {
            pstmt = conn.prepareStatement(sql);
            for(int i = 0; i < params.length; ++i){
                matchParam(pstmt, i, params[i]);
            }
            result = pstmt.executeQuery();
            while (result.next()){
                list.add( getObject(type, result) );
            }
        } catch (Exception e){

        }
        finally {
            close(result, pstmt, conn);
        }
        return list;
    }

    public <T> ArrayList<T> sqlQueryNoneClose( Connection conn, PreparedStatement pstmt, ResultSet result,
            String sql, Class<T> type, Object... params){
        ArrayList<T> list = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(sql);
            for(int i = 0; i < params.length; ++i){
                matchParam(pstmt, i, params[i]);
            }
            result = pstmt.executeQuery();
            while (result.next()){
                list.add( getObject(type, result) );
            }
        } catch (Exception e){
            close(result, pstmt, conn);
        }
        return list;
    }
    public boolean sqlExecute(String sql){
        Connection conn = connection();
        PreparedStatement pstmt = null;
        ResultSet result = null;
        try {
            pstmt = conn.prepareStatement(sql);
            return pstmt.execute(sql);
        } catch (Exception e){
            if(e.getMessage().contains("ORA-00955")){
                // System.out.println("동일한 SEQUENCE 존재합니다");
            }
            else if(e.getMessage().contains(("ORA-02449"))){
                // System.out.println("foreign keys");
            }
            else if(e.getMessage().contains(("ORA-00955"))){
                // System.out.println("SAME NAME");
            }
            else {
                System.out.println("sqlExecute ERROR :: " + e);
            }
        }
        finally {
            close(result, pstmt, conn);
        }
        return false;
    }

    public int sqlUpdate(String query, Binder binder) {
        return sqlUpdate(query, binder.getter());
    }

    public int sqlUpdate(String query, Object ...params){
        Connection conn = connection();
        PreparedStatement pstmt = null;
        ResultSet result = null;
        int matchIndex = 0;
        try {
            pstmt = conn.prepareStatement(query);
            for(int i = 0; i < params.length; ++i){
                matchIndex = i;
                matchParam(pstmt, i, params[i]);
            }
            return pstmt.executeUpdate();
        } catch (Exception e){
            System.out.println(":DataBaseSqlUpdate");
            System.out.println(":matchIndex : " + matchIndex + " :: " + params[matchIndex]);
            System.out.println(e);
            System.out.println("Exception [Err_Location] : {}" + e.getStackTrace()[0]);
        }
        finally {
            close(result, pstmt, conn);
        }
        return 0;
    }

    private void createDataSource(){
        try {
            poolDataSource = new OracleConnectionPoolDataSource();
            poolDataSource.setURL(appConfig.DB_URL);
            poolDataSource.setUser(appConfig.DB_ID);
            poolDataSource.setPassword(appConfig.DB_PW);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {

        }
    }
    private void matchParam(PreparedStatement pstmt, int index, Object param) throws SQLException {
        int curIndex = index + 1;
        if(param instanceof Integer){
            pstmt.setInt(curIndex, (int)param);
        }
        else if(param instanceof String){
            pstmt.setString(curIndex, (String)param);
        }
        else if(param instanceof Date){
            pstmt.setDate(curIndex, (Date)param);
        }
        else if(param instanceof List){
            List<String> list = (List<String>)param;
            String str = String.join(",", list);
            pstmt.setString(curIndex, str);
        }
        else if(param == null){
            pstmt.setString(curIndex, "");
        }

    }

    private <T> T getObject(Class<T> type, ResultSet item){
        try {
            Constructor<T> t = type.getDeclaredConstructor();
            T instance = t.newInstance();
            if( instance instanceof Binder){
                ((Binder)instance).setter(item);
            }
            return instance;
        } catch (Exception e) {
            System.out.println("SETTER ERROR " + type.getSimpleName());
            System.out.println(e);
            System.out.println("기본 생성자 없거나 Public 아닐 가능성");
        }
        return null;
    }
}
