package com.kosta.winter.controller.board;

import com.kosta.winter.controller.abs.Controller;
import com.kosta.winter.controller.abs.ControllerProperties;
import com.kosta.winter.controller.abs.Post;
import com.kosta.winter.def.HttpResulter;
import com.kosta.winter.def.PathVariable;
import com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.UUID;

public class UserProfilePostController extends Controller implements Post {

    @Override
    public ControllerProperties getProperties() {
        return new ControllerProperties()
                .setRoutage("/user-profile-update/");
    }

    @Override
    public HttpResulter doPost(HttpExchange exchange, JSONObject jsonObject) {
        InputStream inputStream = exchange.getRequestBody();
        try {
            byte[] bytes = inputStream.readAllBytes();
            if(bytes != null && bytes.length != 0){
                String fileUUID = UUID.randomUUID().toString();

                if(isFileUpload(fileUUID, bytes)){
                    PathVariable pathVariable = pathVariable(exchange);
                    String userUUID = pathVariable.value.get(0);

                    String sql = "UPDATE app_user SET profile = ? WHERE uuid = ?";
                    int rs = db.sqlUpdate(sql, fileUUID, userUUID);
                    if(rs == 1){
                        return new HttpResulter()
                                .setData(fileUUID)
                                .setStatusCode(200)
                                .setMsg("프로필 업로드 성공");
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
                .setMsg("프로필 업로드 실패");
    }

    private boolean isFileUpload(String fileUUID, byte[] bytes) {
        boolean flag = false;
        java.util.Date jDate = new java.util.Date();
        Date mDate = new Date( jDate.getTime() );
        String sql =  "INSERT INTO app_file"
                + "(SEQ, UUID, BINARY, MDATE) "
                + "values(file_seq.nextval, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;
        try {
            conn = db.connection();
            Blob blob = conn.createBlob();
            blob.setBytes(1, bytes);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, fileUUID);
            pstmt.setBlob(2, blob);
            pstmt.setDate(3, mDate);
            if ( 1 == pstmt.executeUpdate() )
                flag = true;

        } catch (Exception e){
            System.out.println("Blob. ClassCastException :: ");
            System.out.println(e);
            System.out.println("Exception [Err_Location] : {}" + e.getStackTrace()[0]);
        }
        finally {
            db.close(result, pstmt, conn);
        }


        return flag;
    }
}
