package com.kosta.winter.core;

import com.kosta.rb.core.abs.HttpHandler;
import com.kosta.winter.def.HttpRequest;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class Http {

    public static final String GET = "GET";
    public static final String POST = "POST";

    public void doRequest(HttpRequest request, HttpHandler handler) {
        DataOutputStream outputStream = null;
        try {
            URL url = new URL(request.getUrl());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(request.getMethod());
            Map<String, String> headers = request.getHeader();
            headers.forEach(connection::setRequestProperty);
            connection.setDoOutput(true);

            if(request.getMethod().equals(POST)){
                outputStream = new DataOutputStream(connection.getOutputStream());
                outputStream.writeBytes(request.getBody());
                outputStream.flush();
                outputStream.close();
            }

            int responseCode = connection.getResponseCode();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String inputLine;

            while ((inputLine = bufferedReader.readLine()) != null)  {
                stringBuffer.append(inputLine);
            }
            bufferedReader.close();

            String response = stringBuffer.toString();
            handler.doResponse(responseCode, response);

        } catch (IOException e) {
            System.out.println("doRequest Error : " + e);
        } finally {
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                } finally {
                }
            }
        }
    }

}
