package com.kosta.winter.def;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    private String url;
    private String method;
    private Map<String, String> header = new HashMap<>();
    private String body = "";

    public String getUrl() {
        return url;
    }

    public HttpRequest setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public HttpRequest setMethod(String method) {
        this.method = method;
        return this;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public HttpRequest setHeader(Map<String, String> header) {
        this.header = header;
        return this;
    }

    public String getBody() {
        return body;
    }

    public HttpRequest setBody(String body) {
        this.body = body;
        return this;
    }

    @Override
    public String toString() {
        return "HTTPRequest{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", header=" + header +
                ", body='" + body + '\'' +
                '}';
    }

}
