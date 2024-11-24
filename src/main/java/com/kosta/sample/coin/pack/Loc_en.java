package com.kosta.sample.coin.pack;

public class Loc_en extends Localizing{
    @Override
    public void definedLocalizing() {
        definedVCoin = new int[]{
                100, 50, 20, 10, 5, 2, 1
        };

        storeLoc.put(ELocUI.insert, "inserted dollars :\t");
        storeLoc.put(ELocUI.value, "$ :\t");
        storeLoc.put(ELocUI.etc, "etc");
        storeLoc.put(ELocUI.end, "end");
    }
}
