package com.kosta.sample.quiz1;

public class Sukang extends Score{
    private int[] scores = new int[3];

    public Sukang(){
        super();
    }

    public Sukang(int k, int e, int m){
        super(k, e, m);
        this.scores[0] = k;
        this.scores[1] = e;
        this.scores[2] = m;
    }

    public Sukang(int[] scores){
        super(scores);
        this.scores = scores;
    }

    public int[] getScores(){
        return this.scores;
    }


}
