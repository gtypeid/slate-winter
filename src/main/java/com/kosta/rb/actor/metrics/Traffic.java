package com.kosta.rb.actor.metrics;

import com.kosta.rb.actor.metrics.dynamicflow.TrafficFlowContext;
import com.kosta.rb.actor.metrics.dynamicflow.abs.DynamicFlowActor;
import com.kosta.rb.comp.Graphic;
import com.kosta.rb.comp.Position;
import com.kosta.rb.def.BoardConfig;
import com.kosta.rb.def.abs.Config;

import java.awt.*;

public class Traffic extends DynamicFlowActor {


    protected Graphic gp;
    protected Graphic stringComp;
    protected Position ps;

    private Computer startDirComputer;
    private Computer endDirComputer;

    public Traffic(){
        ps = attachComp(Position.class);
        gp = attachComp(Graphic.class);
        gp.setLayer(BoardConfig.ELayer.FLOOR_FRONT)
                .setImage("arrow.png")
                .setSize(55, 55)
                .setPivotXY(-10, 45);

        stringComp = attachComp(Graphic.class);
        stringComp.setDrawText(" ", new Font("궁서", Font.BOLD, 15))
                .setPivotXY(0,15)
                .setVisible(false);
    }

    public void visible(boolean value){
        gp.setVisible(value);
        TrafficFlowContext ctx = getCTX();
        stringComp.setDrawText( toText(ctx) );
        stringComp.setVisible(value);
    }

    public String toText(TrafficFlowContext ctx){
        String text =
                 ctx.getType() + "\n"
                + ctx.getJson();
        return text;
    }

    public void setDirPosition(Computer sc, Computer ec) {
        Position scps = sc.getPosition();
        Position ecps = ec.getPosition();

        TrafficFlowContext ctx = this.getCTX();
        int endY = ecps.getY();

        int calc = 2;
        int calcX = Math.abs( ( ecps.getX() + scps.getX()) / 2 );
        int calcY = (ctx.getType().equals("request")) ? (endY -calc) : (endY +calc);

        getPosition().setPosition(calcX, calcY);

        startDirComputer = sc;
        endDirComputer = ec;
    }

    public Computer getStartDirComputer(){
        return startDirComputer;
    }

    public Computer getEndDirComputer(){
        return endDirComputer;
    }


}
