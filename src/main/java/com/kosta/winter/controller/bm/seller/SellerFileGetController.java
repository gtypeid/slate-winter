package com.kosta.winter.controller.bm.seller;

import com.kosta.winter.controller.abs.CompleteResponse;
import com.kosta.winter.controller.abs.Controller;
import com.kosta.winter.controller.abs.ControllerProperties;
import com.kosta.winter.controller.abs.Get;
import com.kosta.winter.data.bm.seller.SellerFile;
import com.kosta.winter.data.board.File;
import com.kosta.winter.def.PathVariable;
import com.sun.net.httpserver.HttpExchange;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class SellerFileGetController extends Controller implements Get, CompleteResponse {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet result;

    @Override
    public ControllerProperties getProperties() {
        return new ControllerProperties()
                .setRoutage("/seller-file/");
    }

    @Override
    public Blob doGet(HttpExchange exchange) {
        PathVariable pathVariable = pathVariable(exchange);
        conn = db.connection();
        String fileUUID = pathVariable.value.get(0);
        String sql = "SELECT * FROM bm_seller_file WHERE uuid = ?";
        List<SellerFile> files = db.sqlQueryNoneClose(conn, pstmt, result, sql, SellerFile.class, fileUUID);
        if(!files.isEmpty()){
            SellerFile file = files.get(0);
            return file.getBinary();
        }
        return null;
    }

    @Override
    public void completeResponse() {
        db.close(result, pstmt, conn);
    }
}
