package com.kosta.winter;

import com.kosta.winter.core.App;
import com.kosta.winter.def.AppConfig;
import com.kosta.winter.def.BoardAppConfig;
import com.kosta.winter.def.bm.BMBuyerAppConfig;
import com.kosta.winter.def.bm.BMOrderAppConfig;
import com.kosta.winter.def.bm.BMSellerAppConfig;

public class Main {

    public static void main(String[] args){
        App app = App.getInstance();
        app.run( getBMApp(args[0]) );
    }

    public static AppConfig getBMApp(String arg){
        if(arg.equals("buyer")){
            return new BMBuyerAppConfig();
        }
        else if(arg.equals("seller")){
            return new BMSellerAppConfig();
        }
        else if(arg.equals("order")){
            return new BMOrderAppConfig();
        }
        else if(arg.equals("board")){
            return new BoardAppConfig();
        }
        return null;
    }
}
