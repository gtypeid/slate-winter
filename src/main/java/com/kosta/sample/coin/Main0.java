package com.kosta.sample.coin;

public class Main0 {

    public static void main(String[] args){

        int coin = 29897;
        System.out.println("투입된 금액 :\t" + coin);

        int result = coin;

        int won_10000 = result / 10000;
        result %= 10000;

        int won_5000 = result / 5000;
        result %= 5000;

        int won_1000 = result / 1000;
        result %= 1000;

        int won_500 = result / 500;
        result %= 500;

        int won_100 = result / 100;
        result %= 100;

        int won_50 = result / 50;
        result %= 50;

        int won_10 = result / 10;
        result %= 10;

        System.out.println(10000 + "원 : \t" + won_10000);
        System.out.println(5000 + "원 : \t" + won_5000);
        System.out.println(1000 + "원 : \t" + won_1000);
        System.out.println(500 + "원 : \t" + won_500);
        System.out.println(100 + "원 : \t" + won_100);
        System.out.println(50 + "원 : \t" + won_50);
        System.out.println(10 + "원 : \t" + won_10);
        if(0 < result)
            System.out.println("기타 : \t" + result);

        System.out.println("종료");
    }
}
