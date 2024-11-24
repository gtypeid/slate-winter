package com.kosta.sample.coin;

public class Main2 {

    public static void main(String[] args){

        int coin = 29897;
        int[] definedVCoin = new int[]{
                10000, 5000, 1000, 500, 100, 50, 10
        };

        System.out.println("투입된 금액 :\t" + coin);

        int result = coin;
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
