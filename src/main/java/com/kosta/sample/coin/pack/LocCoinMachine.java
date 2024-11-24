package com.kosta.sample.coin.pack;


public class LocCoinMachine {

    private final Localizing localizing;
    public LocCoinMachine(Localizing localizing){
        this.localizing = localizing;
    }

    public void insertCoin(int insertCoin){

        System.out.println(localizing.loc(Localizing.ELocUI.insert) + insertCoin);

        int result = insertCoin;
        int[] definedVCoin = localizing.getDefinedVCoin();

        for (int it : definedVCoin){
            int count = result / it;
            result %= it;
            System.out.println(it + localizing.loc(Localizing.ELocUI.value) + count);
        }
        if(0 < result)
            System.out.println(localizing.loc(Localizing.ELocUI.etc) + result);

        System.out.println(localizing.loc(Localizing.ELocUI.end));
    }
}
