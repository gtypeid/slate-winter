package com.kosta.winter.controller.bm.seller;

import com.kosta.winter.controller.abs.Controller;
import com.kosta.winter.controller.abs.ControllerProperties;
import com.kosta.winter.controller.abs.Post;
import com.kosta.winter.data.bm.seller.Label;
import com.kosta.winter.data.bm.seller.Selector;
import com.kosta.winter.data.bm.seller.Store;
import com.kosta.winter.def.HttpResulter;
import com.kosta.winter.def.PathVariable;
import com.kosta.winter.def.bm.seller.SelectLabelMatcher;
import com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;

import java.util.List;
import java.util.stream.Collectors;

public class SellerSelectorLabelBindController extends Controller implements Post {
    SellerCommon common;

    @Override
    public ControllerProperties getProperties() {
        common = getCommon();
        return new ControllerProperties()
                .setRoutage("/selector-bind-label");
    }

    @Override
    public HttpResulter doPost(HttpExchange exchange, JSONObject jsonObject) {
        SelectLabelMatcher selectLabelMatcher = cast(jsonObject, SelectLabelMatcher.class);
        String storeUUID = selectLabelMatcher.getStoreUuid();
        Store store = common.getSellerStore(storeUUID);

        int statusCode = 400;
        String statusMSG = "";

        if(store != null){
            Selector selector = common.getStoreSelector(selectLabelMatcher.getSelectorUuid());
            if( selector != null){
                List<Label> storeLabels = common.getStoreLabels(storeUUID);
                if( validLabelSize(selectLabelMatcher, storeLabels.size()) ){
                    return bindSelector(selector, selectLabelMatcher, storeLabels);
                }
                else {
                    statusMSG = "잘못된 Label UUID";
                }
            }
            else {
                statusMSG = "잘못된 selector UUID";
            }
        }
        else {
            statusMSG = "잘못된 Store UUID";
        }

        return new HttpResulter()
                .setStatusCode(statusCode)
                .setMsg(statusMSG);
    }

    private boolean validLabelSize(SelectLabelMatcher matcher, int storeLabelSize){
        int size = matcher.getLabelUuids().size();
        return (size == storeLabelSize);
    }

    private HttpResulter bindSelector(Selector selector, SelectLabelMatcher matcher, List<Label> storeLabels) {

        selector.setCount( matcher.getCount() );

        List<String> matcherLable = matcher.getLabelUuids();
        List<Label> filterLable = storeLabels.stream().filter( it -> {
            return it.getUuid().equals(matcherLable.stream());
        }).collect(Collectors.toList());

        for(Label it : filterLable){
            common.updateLableOwner(it, selector.getUuid());
        }
        
        return new HttpResulter()
                .setStatusCode(201)
                .setMsg("lable owner update 성공");
    }
}
