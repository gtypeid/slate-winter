package com.kosta.sample.coin;

import com.kosta.sample.coin.pack.LocCoinMachine;
import com.kosta.sample.coin.pack.Loc_en;
import com.kosta.sample.coin.pack.Loc_jp;
import com.kosta.sample.coin.pack.Loc_kr;

public class Main4 {

    public static void main(String[] args) {

        int coin = 29897;
        new LocCoinMachine( new Loc_en() )
                .insertCoin(coin);
    }
}
