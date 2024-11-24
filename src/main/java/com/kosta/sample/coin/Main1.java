package com.kosta.sample.coin;

public class Main1 {

    static final int V10000 = 10000;
    static final int V5000 = 5000;
    static final int V1000 = 1000;
    static final int V500 = 500;
    static final int V100 = 100;
    static final int V50 = 50;
    static final int V10 = 10;

    public static void main(String[] args){

        int coin = 29897;

        System.out.println("투입된 금액 :\t" + coin);

        int result = coin;

        int won_10000 = result / V10000;
        result %= V10000;

        int won_5000 = result / V5000;
        result %= V5000;

        int won_1000 = result / V1000;
        result %= V1000;

        int won_500 = result / V500;
        result %= V500;

        int won_100 = result / V100;
        result %= V100;

        int won_50 = result / V50;
        result %= V50;

        int won_10 = result / V10;
        result %= V10;

        System.out.println(V10000 + "원 : \t" + won_10000);
        System.out.println(V5000 + "원 : \t" + won_5000);
        System.out.println(V1000 + "원 : \t" + won_1000);
        System.out.println(V500 + "원 : \t" + won_500);
        System.out.println(V100 + "원 : \t" + won_100);
        System.out.println(V50 + "원 : \t" + won_50);
        System.out.println(V10 + "원 : \t" + won_10);
        if(0 < result)
            System.out.println("기타 : \t" + result);

        System.out.println("종료");
    }
}
