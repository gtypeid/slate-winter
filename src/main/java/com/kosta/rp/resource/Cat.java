package com.kosta.rp.resource;

import java.awt.*;

public class Cat extends Resource {

    @Override
    protected void definedResource() {
        inData("　 　∧_∧      ");
        inData("　　(`･ω･´ ) 三");
        inData("　　O┳〇 )     ");
        inData("　　◎し◎- 三    ");
        setPivot( new Point(4,4) );
    }
}
