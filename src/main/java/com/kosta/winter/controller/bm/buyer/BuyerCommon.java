package com.kosta.winter.controller.bm.buyer;

import com.kosta.winter.controller.abs.Common;
import com.kosta.winter.data.bm.buyer.BuyerUser;
import java.util.UUID;

public class BuyerCommon  extends Common {

    public boolean notDuplicateBuyerID(String buyerID){
        String table = "bm_buyer_user";
        return notDuplicateID(table, BuyerUser.class, buyerID);
    }

    public BuyerUser getBuyerUser(String buyerID){
        String table = "bm_buyer_user";
        return getMatchID(table, BuyerUser.class, buyerID);
    }

    public int signBuyerUser(BuyerUser buyerUser){
        buyerUser.setUuid( UUID.randomUUID().toString() );

        String table = "bm_buyer_user";
        String seq = "bm_buyer_user_seq.nextval";
        String sql =  "INSERT INTO "
                + table
                + " (SEQ, UUID, ID, PASSWORD, NICKNAME, PROFILE, LOCATION)"
                + " values(" + seq + ", ?, ?, ?, ?, ?, ?)";
        return db.sqlUpdate(sql, buyerUser);
    }
}
