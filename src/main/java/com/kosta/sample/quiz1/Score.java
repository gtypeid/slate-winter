package com.kosta.sample.quiz1;
public class Score {

    public Score(){
        showTitle();
    }

    public Score(int k, int e, int m){
        this();
        showScore( new int[] {k, e, m} );
    }

    public Score(int[] scores){
        this();
        showScore(scores);
    }

    public void showTitle(){
        System.out.println("국어\t영어\t수학");
    }

    public void showScore(int[] scores){
        for (int in : scores){
            System.out.print(in + "\t");
        }
        System.out.println();
    }

    public static String subjectSum(Sukang[] sukangs){
        int subSize = sukangs[0].getScores().length;
        StringBuilder sResult = new StringBuilder();
        System.out.println("-------------");
        for(int i = 0; i < subSize; ++i){
            int subResult = 0;
            for (Sukang it : sukangs) {
                int[] score = it.getScores();
                subResult += score[i];
            }
            sResult.append(subResult).append("\t");
        }
        return sResult.toString();
    }
}
