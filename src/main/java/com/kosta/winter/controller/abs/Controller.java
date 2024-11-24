package com.kosta.winter.controller.abs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosta.winter.controller.bm.seller.SellerCommon;
import com.kosta.winter.core.DataBase;
import com.kosta.winter.core.App;
import com.kosta.winter.def.AppConfig;
import com.kosta.winter.def.HttpResulter;
import com.kosta.winter.def.PathVariable;
import com.kosta.winter.def.Util;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

public abstract class Controller implements HttpHandler {

    private ObjectMapper objectMapper = new ObjectMapper();
    protected App app;
    protected DataBase db;

    private final String DEFINE_HTML = "text/html";
    private final String DEFINE_CSS = "text/css";
    private final String DEFINE_IMG = "image";

    public Controller(){
        app = App.getInstance();
        db = app.getDataBase();
    }

    abstract public ControllerProperties getProperties();

    public void handle(HttpExchange exchange){
        try {
            mappingMethod(exchange);
        } catch (IOException e) {
            System.out.println(e);
        } finally {
        }
    }

    protected <T> T getCommon(){
        return app.getCommon();
    }

    protected void responseErrorPage(HttpExchange exchange){
        try {
            int statusCode = 404;
            String contentType = "text/html";
            String errorPagePath = Util.dirResAppendPath("slate\\", "error.html");
            StringBuilder strb = new StringBuilder();
            Util.fileRead(strb, errorPagePath);

            String content = strb.toString();
            byte[] contentBytes = content.getBytes();

            response(exchange, contentType, contentBytes, statusCode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected <T> T cast(JSONObject jsonObject, Class<T> t){
        try {
            return objectMapper.readValue(jsonObject.toString(), t);
        } catch (JsonProcessingException e) {
            System.out.println("JSON TO OBJECT MAPPING ERROR : " + t.getName());
        }
        return null;
    }

    protected PathVariable pathVariable(HttpExchange exchange){
        String path = exchange.getRequestURI().getPath();
        String[] array = path.split("/");
        PathVariable pathVariable = new PathVariable();
        for(int i = 0; i < array.length; ++i){
            if(!array[i].isEmpty()){
                if( i % 2 == 1){
                    pathVariable.path.add(array[i]);
                }
                else {
                    pathVariable.value.add(array[i]);
                }
            }
        }
        return pathVariable;
    }

    private void mappingMethod(HttpExchange exchange) throws IOException {
        final String GET = "GET";
        final String POST = "POST";
        final String OPTIONS = "OPTIONS";

        String method = exchange.getRequestMethod();
        if(this instanceof Get && method.equals(GET) ){
            Object object = ((Get)this).doGet(exchange);
            objectMappingResolver(exchange, object);
        }
        else if(this instanceof Post && (method.equals(POST) )) {
            String contentType = getContentType(exchange);
            if(!contentType.equals(DEFINE_IMG)) {
                StringBuilder stb = new StringBuilder();
                if(isBodyParser(exchange, stb)){
                    JSONObject jsonObject = new JSONObject(stb.toString());
                    Object object = ((Post)this).doPost(exchange, jsonObject);
                    objectMappingResolver(exchange, object);
                }
            }
            else {
                Object object = ((Post)this).doPost(exchange, null);
                objectMappingResolver(exchange, object);
            }
        }
        else {
            System.out.println(":: OTHER");
            System.out.println(exchange.getRequestURI().getPath());
            System.out.println(exchange.getRequestMethod());
        }
    }

    private void staticResource(HttpExchange exchange, String matchSource) throws IOException {
        String slatePath = Util.dirResAppendPath("slate", matchSource);
        File file = new File( slatePath );
        if( file.canRead() ){
            String contentType = getContentType(exchange);
            int statusCode = 200;

            if(!contentType.equals(DEFINE_IMG)){
                StringBuilder strb = new StringBuilder();
                Util.fileRead(strb, slatePath);

                String content = strb.toString();
                byte[] contentBytes = content.getBytes();

                response(exchange, contentType, contentBytes, statusCode);
            }
            else {
                exchange.getResponseHeaders().set("Content-Type", contentType);
                exchange.sendResponseHeaders(statusCode, file.length());
                OutputStream outputStream = exchange.getResponseBody();
                Files.copy(file.toPath(), outputStream);
                outputStream.close();
            }


        }
        else {
            responseErrorPage(exchange);
        }
    }

    private String getContentType(HttpExchange exchange) {
        List<String> keys = exchange.getRequestHeaders().get("Accept");
        // List<String> dests = exchange.getRequestHeaders().get("Sec-Fetch-Dest");
        String contentType = "application/javascript";

        for (String key : keys){
            if(key.contains(DEFINE_HTML)){
                return contentType = DEFINE_HTML;
            }
            if(key.contains(DEFINE_CSS)){
                contentType = DEFINE_CSS;
            }
            if(key.contains(DEFINE_IMG)){
                contentType = DEFINE_IMG;
            }
        }

        return contentType;
    }

    private boolean isBodyParser(HttpExchange exchange, StringBuilder stb) {
        boolean flag = true;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
            br = new BufferedReader(isr);

            int b;
            while ((b = br.read()) != -1) {
                stb.append((char) b);
            }

        } catch (IOException e) {
            flag = false;
            return flag;

        } finally {
            try {
                br.close();
                isr.close();
            } catch (IOException e) {
            }
        }
        return flag;
    }

    private <T> void objectMappingResolver(HttpExchange exchange, T object) throws IOException {
        String contentType = "application/json";
        byte[] contentBytes = null;
        int statusCode = 200;

        if ( object instanceof String ){
            String path = (String)object;
            String matchSource = path.replace("/", "\\");
            if(matchSource.length() == 1) {
                AppConfig appConfig = app.getConfig();
                matchSource = "\\" + appConfig.INDEX_HTML;
            }

            staticResource(exchange, matchSource);
            return;
        }
        else if(object instanceof List){
            JSONArray jsonArray = new JSONArray();
            List<T> list = (List<T>) object;
            list.forEach( it ->{
                jsonArray.put( new JSONObject(it) );
            });

            contentBytes = jsonArray.toString().getBytes();
        }
        else if( object instanceof HttpResulter){
            HttpResulter httpResulter = (HttpResulter)object;
            JSONObject json = new JSONObject(object);
            contentBytes = json.toString().getBytes();
            //statusCode = httpResulter.getStatusCode();
        }
        else if(object instanceof Blob){
            try {
                contentType = "blob";
                Blob blob = (Blob) object;
                InputStream is = blob.getBinaryStream();
                byte[] bytes = is.readAllBytes();
                response(exchange, contentType, bytes, statusCode);
                is.close();
                CompleteResponse compResponse = (CompleteResponse) this;
                compResponse.completeResponse();

            } catch (SQLException e) {
                System.out.println("BLOB ERROR");
                System.out.println(e);
                System.out.println("------------------------");
            }

        }
        else {
            JSONObject json = new JSONObject(object);
            contentBytes = json.toString().getBytes();
        }
        response(exchange, contentType, contentBytes, statusCode);
    }

    private void response(HttpExchange exchange, String contentType, byte[] contentBytes, int statusCode) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", contentType);
        exchange.sendResponseHeaders(statusCode, contentBytes.length);
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(contentBytes);
        outputStream.close();
    }
}
