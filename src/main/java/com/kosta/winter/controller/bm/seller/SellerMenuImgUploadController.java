package com.kosta.winter.controller.bm.seller;

import com.kosta.winter.controller.abs.Controller;
import com.kosta.winter.controller.abs.ControllerProperties;
import com.kosta.winter.controller.abs.Post;
import com.kosta.winter.def.HttpResulter;
import com.kosta.winter.def.PathVariable;
import com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class SellerMenuImgUploadController extends Controller implements Post {
    SellerCommon common;

    @Override
    public ControllerProperties getProperties() {
        common = getCommon();
        return new ControllerProperties()
                .setRoutage("/menu-img/");
    }

    @Override
    public HttpResulter doPost(HttpExchange exchange, JSONObject jsonObject) {
        InputStream inputStream = exchange.getRequestBody();
        try {
            byte[] bytes = inputStream.readAllBytes();
            if(bytes != null && bytes.length != 0){
                String fileUUID = UUID.randomUUID().toString();

                if(common.isFileUpload(fileUUID, bytes)){
                    PathVariable pathVariable = pathVariable(exchange);
                    String menuUUID = pathVariable.value.get(0);

                    String sql = "UPDATE bm_seller_menu SET img = ? WHERE uuid = ?";
                    int rs = db.sqlUpdate(sql, fileUUID, menuUUID);
                    if(rs == 1){
                        return new HttpResulter()
                                .setData(fileUUID)
                                .setStatusCode(201)
                                .setMsg("메뉴 이미지 업로드 성공");
                    }
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        finally {
            try {
                inputStream.close();
            } catch (IOException e) {
            }
        }

        return new HttpResulter()
                .setStatusCode(400)
                .setMsg("메뉴 이미지 업로드 실패");
    }

}
