package com.kosta.rb.actor.metrics;

import com.kosta.rb.actor.metrics.dynamicflow.ComputerFlowContext;
import com.kosta.rb.actor.metrics.dynamicflow.abs.DynamicFlowActor;
import com.kosta.rb.comp.Graphic;
import com.kosta.rb.comp.Position;
import com.kosta.rb.def.BoardConfig;
import com.kosta.rb.def.abs.Config;

import java.awt.*;

public class Computer extends DynamicFlowActor {


    protected Graphic gp;
    protected Graphic stringComp;
    protected Position ps;

    public Computer(){
        ps = attachComp(Position.class);
        gp = attachComp(Graphic.class);
        gp.setLayer(BoardConfig.ELayer.FIGURE)
                .setImage("figure-blue.png")
                .setSize(55, 55)
                .setPivotXY(-10, 45);

        stringComp = attachComp(Graphic.class);
        stringComp.setDrawText(" ", new Font("궁서", Font.BOLD, 15))
                .setPivotXY(0,15)
                .setVisible(false);
    }

    public void visible(boolean value){
        gp.setVisible(value);
        ComputerFlowContext ctx = getCTX();
        stringComp.setDrawText( toText(ctx) );
        stringComp.setVisible(value);
    }

    public String toText(ComputerFlowContext ctx){
        String text =
                 ctx.getServerType() + "\n"
                + ctx.getIp() + " : " +ctx.getPort();
        return text;
    }

    public void changeComputer(byte type){

        /*
        if(type == 2){
            gp.setImage("floors.png")
                    .setSize(35, 35);
        }
        */
    }

}
