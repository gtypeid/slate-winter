package com.kosta.rb.core.sw;
import com.kosta.rb.actor.dice.Floor;
import com.kosta.rb.actor.dice.Node;
import com.kosta.rb.actor.metrics.Computer;
import com.kosta.rb.actor.metrics.Traffic;
import com.kosta.rb.core.Board;
import com.kosta.rb.core.Store;
import com.kosta.rb.def.BoardConfig;
import com.kosta.rb.comp.Graphic;
import com.kosta.rb.comp.Position;
import com.kosta.rb.core.abs.Actor;
import com.kosta.rb.def.MetricsConfig;
import com.kosta.rb.def.abs.Config;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Renderer extends JPanel {

    private ViewFrame viewFrame;
    private Node mainNode;
    private boolean isRender = false;

    protected Board board;
    protected Config bc;
    protected Store store;


    public Renderer(ViewFrame frame){
        viewFrame = frame;
        board = Board.getInstance();
        bc = board.getConfig();
        store = board.getStore();

        int width = bc.getWindowContext().getWindowSizeWidth() - 700;
        int height = bc.getWindowContext().getWindowSizeHeight() - 200;
        setBounds(0, 0, width, height);
    }


    public void render(Graphics g){
        if(!isRender) return;

        List<Actor> actors = store.getActors();
        for(Actor it : actors){
            actorDraw(it, g);
        }
    }

    public void setIsRender(boolean flag){
        isRender = flag;
        if(isRender){
            repaint();
        }
    }

    public boolean getIsRender(){
        return isRender;
    }

    public void setMainNode(Node node){
        mainNode = node;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(!isRender) return;

        List<Actor> actors = store.getActors();
        for(Actor it : actors){
            actorDraw(it, g);
        }

        if(bc instanceof BoardConfig){
            BoardConfig asBc = (BoardConfig) bc;
            if(mainNode.isDrawPath() && asBc.isDrawPath){
                ArrayList<Floor> path = mainNode.getDrawPathNode();
                drawPath(asBc, g, path);
            }
        }

        if(bc instanceof MetricsConfig){
            drawTrafficLine(g);
        }

    }

    private void drawTrafficLine(Graphics g){
        MetricsConfig asBc = (MetricsConfig) bc;
        List<Traffic> traffics = store.getActors(Config.ELayer.FLOOR_FRONT, Traffic.class);
        for(Traffic it : traffics){
            Graphic gp = it.getComp("Graphic");
            if ( gp.isVisible() ){
                Computer start = it.getStartDirComputer();
                Computer end = it.getEndDirComputer();

                int[] startXY = getActorRenderScreenXY(start);
                int[] middleXY = getActorRenderScreenXY(it);
                int[] endXY = getActorRenderScreenXY(end);

                int calc = 15;
                Graphics2D g2 = (Graphics2D)g;
                g2.setStroke( new BasicStroke(2) );
                g2.setColor( Color.red );
                g2.drawLine(startXY[0] + calc, startXY[1] + calc, middleXY[0] + calc, middleXY[1] + calc);
                g2.drawLine(middleXY[0] + calc, middleXY[1] + calc, endXY[0] + calc, endXY[1] + calc);
            }
        }
    }

    public void drawPath(BoardConfig bc, Graphics g, ArrayList<Floor> path){
        for(int i = 0; i < path.size(); i++){
            int next = i + 1;
            if(next >= path.size())
                next = i;

            Floor cf = path.get(i);
            Floor nf = path.get(next);

            int[] startXY = getActorRenderScreenXY(cf);
            int[] endXY = getActorRenderScreenXY(nf);
            int calc = 15;

            Graphics2D g2 = (Graphics2D)g;
            g2.setStroke( new BasicStroke(bc.drawPathLineStroke) );
            g2.setColor( bc.drawPathColor );
            g2.drawLine(startXY[0] + calc, startXY[1] + calc, endXY[0] + calc, endXY[1] + calc);
        }
    }

    private int[] getActorRenderScreenXY(Position pos, Graphic gp){
        int screenX = (pos.getX() * bc.floorWorldGrid) + gp.getPivotX();
        int screenY = (pos.getY() * bc.floorWorldGrid) + gp.getPivotY();
        return new int[]{ screenX, screenY };
    }

    private int[] getActorRenderScreenXY(Actor actor){
        Position pos = actor.getComp("Position");
        Graphic gp = actor.getComp("Graphic");
        return getActorRenderScreenXY(pos, gp);
    }

    private void actorDraw(Actor actor, Graphics g){
        Position pos = actor.getComp("Position");
        ArrayList<Graphic> gps = actor.getComps("Graphic");
        if(pos == null || gps == null) return;

        gps.forEach( gp ->{
            if(gp.isVisible()){
                int[] sXY = getActorRenderScreenXY(pos, gp);
                resourceDraw(g, gp, sXY[0], sXY[1]);
            }
        });
    }

    private void resourceDraw(Graphics g, Graphic gp, int screenX, int screenY){
        Image image = gp.getImage();
        if(image != null){
            int width = gp.getWidth();
            int height = gp.getHeight();
            g.drawImage(image, screenX, screenY, width, height, null);
        }

        String drawText = gp.getDrawText();
        if(drawText != null && !drawText.isEmpty()){
            Font font = gp.getFont();
            if(font != null){
                g.setFont(font);
            }
            for (String line : drawText.split("\n"))
                g.drawString(line, screenX, screenY += g.getFontMetrics().getHeight());
        }



    }

}
