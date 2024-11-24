package com.kosta.sample.shop;

public class User {
    private final String id;
    private final String comp;

    protected int totalPrice;
    protected int orderPoint;
    protected int point;
    protected float pointScale = 0.1f;

    public User(String comp, String id){
        this.comp = comp;
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public int getTotalPrice(){
        return totalPrice;
    }

    public int getOrderPoint(){
        return orderPoint;
    }

    public int getPoint(){
        return point;
    }

    public String getComp(){
        return comp;
    }

    public void order(int op, int price){
        orderPoint += op;
        totalPrice += price;
        point += (int)( (float)price * pointScale );
    }

    public void view(){
        System.out.println(" [COMP] " + comp + " [USER ID] " + id + " [TOTAL PRICE] : " + totalPrice +  " [ORDER POINT] : " + orderPoint + " [HAS POINT] : " + point);
    }
}
