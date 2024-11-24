package com.kosta.winter.def;

import com.kosta.winter.data.Binder;
import org.json.JSONObject;

public class HttpResulter {
    private String msg;
    private int statusCode;
    private String data;

    public void HttpResulter(){

    }

    public String getMsg() {
        return msg;
    }

    public HttpResulter setMsg(String msg) {
        this.msg = msg;
        return this;
    }


    public int getStatusCode() {
        return statusCode;
    }

    public HttpResulter setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public String getData() {
        return data;
    }

    public HttpResulter setData(String data) {
        this.data = data;
        return this;
    }

    public <T> HttpResulter setFilterData(T object){
        if( object instanceof Binder){
            T filter = ((Binder)object).filter();
            JSONObject jsonObject = new JSONObject(filter);
            setData( jsonObject.toString() );
        }
        return this;
    }
}
