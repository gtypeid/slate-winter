package com.kosta.sample.quiz1;

import java.awt.*;

public class Main {

    public static void main(String[] args){

        String sum = Score.subjectSum( new Sukang[] {
                new Sukang(50,55,60),
                new Sukang( new int[] { 70, 80, 90 } ),
                new Sukang( new int[] { 90, 90, 90 } )

        });

        System.out.println(sum);
    }
}
