package com.kosta.sample.coin.pack;


import java.util.HashMap;

public abstract class Localizing {

    protected int[] definedVCoin;
    protected HashMap<ELocUI, String> storeLoc = new HashMap<>();

    public Localizing(){
        definedLocalizing();
    }

    public String loc(ELocUI key){
        boolean hasKey = storeLoc.containsKey(key);
        if(hasKey){
            return storeLoc.get(key);
        }
        return "ERROR";
    }

    public int[] getDefinedVCoin(){
        return definedVCoin;
    }

    abstract public void definedLocalizing();

    public enum ELocUI {
        insert,
        value,
        etc,
        end
    }
}
