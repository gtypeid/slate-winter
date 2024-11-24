package com.kosta.winter.controller.board;

import com.kosta.winter.controller.abs.CompleteResponse;
import com.kosta.winter.controller.abs.Controller;
import com.kosta.winter.controller.abs.ControllerProperties;
import com.kosta.winter.controller.abs.Get;
import com.kosta.winter.data.board.File;
import com.kosta.winter.data.board.User;
import com.kosta.winter.def.PathVariable;
import com.sun.net.httpserver.HttpExchange;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;


public class UserProfileController extends Controller implements Get, CompleteResponse {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet result;

    @Override
    public ControllerProperties getProperties() {
        return new ControllerProperties()
                .setRoutage("/user-profile/");
    }

    @Override
    public Blob doGet(HttpExchange exchange) {
        PathVariable pathVariable = pathVariable(exchange);
        String userID = pathVariable.value.get(0);
        User findUser = findUser(userID);
        if(findUser != null){
            String profileFileUUID = findUser.getProfile();
            if(!profileFileUUID.equals("default")){
                Blob file = getFile(profileFileUUID);
                if(file != null){
                    return file;
                }
            }
        }
        return null;
    }

    private Blob getFile(String fileUUID){
        conn = db.connection();
        String sql = "SELECT * FROM app_file WHERE uuid = ?";
        List<File> files = db.sqlQueryNoneClose(conn, pstmt, result, sql, File.class, fileUUID);
        if(!files.isEmpty()){
            File file = files.get(0);
            return file.getBinary();
        }
        return null;
    }

    private User findUser(String userID){
        String sql = "SELECT * FROM app_user WHERE id = ?";
        List<User> user = db.sqlQuery(sql, User.class, userID);
        if(!user.isEmpty()){
            return user.get(0);
        }
        return null;
    }

    @Override
    public void completeResponse() {
        db.close(result, pstmt, conn);
    }
}
