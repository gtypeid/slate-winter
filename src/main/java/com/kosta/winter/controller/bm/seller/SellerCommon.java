package com.kosta.winter.controller.bm.seller;

import com.kosta.winter.controller.abs.Common;
import com.kosta.winter.data.bm.seller.*;
import com.kosta.winter.def.HttpResulter;

import java.sql.*;
import java.util.List;
import java.util.UUID;

public class SellerCommon extends Common {

    public SellerUser getSellerUser(String sellerID){
        String table = "bm_seller_user";
        return getMatchID(table, SellerUser.class, sellerID);
    }

    public SellerUser getSellerUserMatchUUID(String sellerUUID){
        String table = "bm_seller_user";
        return getMatchUUID(table, SellerUser.class, sellerUUID);
    }

    public boolean notDuplicateSellerID(String sellerID){
        String table = "bm_seller_user";
        return notDuplicateID(table, SellerUser.class, sellerID);
    }

    public int signSellerUser(SellerUser sellerUser){
        sellerUser.setUuid( UUID.randomUUID().toString() );
        String table = "bm_seller_user";
        String seq = "bm_seller_user_seq.nextval";
        String sql =  "INSERT INTO "
                + table
                + " (SEQ, UUID, ID, PASSWORD)"
                + " values(" + seq + ", ?, ?, ?)";
        return db.sqlUpdate(sql, sellerUser);
    }

    public HttpResulter getSellerStoreMatchSellerUUID(String sellerUUID){
        String table = "bm_seller_store";
        String sql = "SELECT * FROM "
                + table
                + " WHERE owner_seller_uuid = ?";
        List<Store> finds = db.sqlQuery(sql, Store.class, sellerUUID);
        if(!finds.isEmpty()){
            return new HttpResulter()
                    .setMsg("스토어 존재")
                    .setFilterData( finds.get(0) )
                    .setStatusCode(200);
        }
        return new HttpResulter()
                .setMsg("스토어 없거나 잘못된 유저")
                .setStatusCode(400);
    }

    public Store getSellerStore(String storeUUID){
        String table = "bm_seller_store";
        return getMatchUUID(table, Store.class, storeUUID);
    }

    public List<Store> getSellerStores(String category){
        String table = "bm_seller_store";
        String sql = "SELECT * FROM "
                + table;
        if(category.equals("default")){
            return db.sqlQuery(sql, Store.class);
        }
        else {
            sql += " WHERE store_category = ?";
            return db.sqlQuery(sql, Store.class, category);
        }
    }

    public int insertStore(SellerUser sellerUser, Store store){
        store.setUuid( UUID.randomUUID().toString() );
        store.setOwnerSellerUuid( sellerUser.getUuid() );
        String table = "bm_seller_store";
        String seq = "bm_seller_store_seq.nextval";
        String sql =  "INSERT INTO "
                + table
                + " (SEQ, UUID, OWNER_SELLER_UUID, TITLE, COMMENTS, STORE_CATEGORY, LOGO)"
                + " values(" + seq + ", ?, ?, ?, ?, ?, ?)";
        return db.sqlUpdate(sql, store);
    }


    public List<Menu> getStoreMenus(String storeUUID){
        String table = "bm_seller_menu";
        String sql = "SELECT * FROM "
                + table
                + " WHERE owner_store_uuid = ?";
        return db.sqlQuery(sql, Menu.class, storeUUID);
    }

    public int insertMenu(Store store, Menu menu){
        menu.setUuid( UUID.randomUUID().toString() );
        menu.setOwnerStoreUuid( store.getUuid() );
        String table = "bm_seller_menu";
        String seq = "bm_seller_menu_seq.nextval";
        String sql =  "INSERT INTO "
                + table
                + " (SEQ, UUID, OWNER_STORE_UUID, TITLE, COMMENTS, PRICE, IMG)"
                + " values(" + seq + ", ?, ?, ?, ?, ?, ?)";
        return db.sqlUpdate(sql, menu);
    }

    public List<Selector> getStoreSelectors(String storeUUID){
        String table = "bm_seller_selector";
        String sql = "SELECT * FROM "
                + table
                + " WHERE owner_store_uuid = ?";
        return db.sqlQuery(sql, Selector.class, storeUUID);
    }

    public Selector getStoreSelector(String selectorUUID){
        String table = "bm_seller_selector";
        return getMatchUUID(table, Selector.class, selectorUUID);
    }

    public int insertSelector(Store store, Selector selector){
        selector.setUuid( UUID.randomUUID().toString() );
        selector.setOwnerStoreUuid( store.getUuid() );
        String table = "bm_seller_selector";
        String seq = "bm_seller_selector_seq.nextval";
        String sql =  "INSERT INTO "
                + table
                + " (SEQ, UUID, OWNER_STORE_UUID, NAME, COUNT)"
                + " values(" + seq + ", ?, ?, ?, ?)";
        return db.sqlUpdate(sql, store);
    }

    public List<Label> getStoreLabels(String storeUUID){
        String table = "bm_seller_label";
        String sql = "SELECT * FROM "
                + table
                + " WHERE owner_store_uuid = ?";
        return db.sqlQuery(sql, Label.class, storeUUID);
    }

    public int insertLabel(Store store, Label label){
        label.setUuid( UUID.randomUUID().toString() );
        label.setOwnerStoreUuid( store.getUuid() );
        String table = "bm_seller_label";
        String seq = "bm_seller_label_seq.nextval";
        String sql =  "INSERT INTO "
                + table
                + " (SEQ, UUID, OWNER_STORE_UUID, OWNER_SELECTOR_UUID, NAME, PRICE)"
                + " values(" + seq + ", ?, ?, ?, ?)";
        return db.sqlUpdate(sql, store);
    }

    public int updateLableOwner(Label label, String ownerSelectorUUID){
        String table = "bm_seller_label";
        String sql = "UPDATE "
                    + table
                    + " SET owner_selector_uuid = ? WHERE uuid = ?";
        return db.sqlUpdate(sql, ownerSelectorUUID, label.getUuid());
    }

    public boolean isFileUpload(String fileUUID, byte[] bytes) {
        boolean flag = false;
        String sql =  "INSERT INTO bm_seller_file"
                + "(SEQ, UUID, BINARY) "
                + "values(bm_seller_file_seq.nextval, ?, ?)";
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