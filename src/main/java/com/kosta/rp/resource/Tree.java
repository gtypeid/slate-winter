package com.kosta.rp.resource;

import java.awt.*;

public class Tree extends Resource {

    @Override
    protected void definedResource() {
        inData("  ^  ");
        inData(" / \\ ");
        inData("/   \\");
        inData("=====");
        inData(" ||| ");
        inData(" ||| ");
        inData(" ||| ");
        inData(" ||| ");
        setPivot( new Point(3,8) );
    }
}
