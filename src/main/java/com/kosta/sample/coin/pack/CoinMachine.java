package com.kosta.sample.coin.pack;

public class CoinMachine {

    private final int[] definedVCoin;
    public CoinMachine(){
        definedVCoin = new int[]{
                10000, 5000, 1000, 500, 100, 50, 10
        };
    }

    public void insertCoin(int insertCoin){

        System.out.println("투입된 금액 :\t" + insertCoin);

        int result = insertCoin;

        for (int it : definedVCoin){
            int count = result / it;
            result %= it;
            System.out.println(it + "원 : \t" + count);
        }
        if(0 < result)
            System.out.println("기타 : \t" + result);

        System.out.println("종료");
    }
}
