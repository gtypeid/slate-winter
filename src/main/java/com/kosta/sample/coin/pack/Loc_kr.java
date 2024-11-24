package com.kosta.sample.coin.pack;

public class Loc_kr extends Localizing{
    @Override
    public void definedLocalizing() {
        definedVCoin = new int[]{
                10000, 5000, 1000, 500, 100, 50, 10
        };

        storeLoc.put(ELocUI.insert, "투입된 금액 :\t");
        storeLoc.put(ELocUI.value, "원 :\t");
        storeLoc.put(ELocUI.etc, "기타 : \t");
        storeLoc.put(ELocUI.end, "종료");
    }
}
