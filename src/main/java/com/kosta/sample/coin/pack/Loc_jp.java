package com.kosta.sample.coin.pack;

public class Loc_jp extends Localizing{
    @Override
    public void definedLocalizing() {
        definedVCoin = new int[]{
                10000, 5000, 2000, 1000, 500, 200, 100
        };

        storeLoc.put(ELocUI.insert, "挿入された円 :\t");
        storeLoc.put(ELocUI.value, "円 :\t");
        storeLoc.put(ELocUI.etc, "その他 :\t");
        storeLoc.put(ELocUI.end, "終了");
    }
}
