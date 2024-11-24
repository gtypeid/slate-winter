package com.kosta.rb.core.sw;

import com.kosta.rb.core.Board;
import com.kosta.rb.core.Mode;
import com.kosta.rb.def.BoardConfig;
import com.kosta.rb.def.abs.Config;
import com.kosta.rb.def.abs.WindowContext;

import javax.swing.*;

public class ViewFrame extends JFrame {

    private Renderer renderer;
    private Slider slider;
    private ScrollBox scrollBox;
    private Mode mode;

    public ViewFrame(Mode mode){
        this.mode = mode;
        renderer = new Renderer(this);
        slider = new Slider();
        scrollBox = new ScrollBox(new JTextArea(20, 20));
        inItJFrame();
    }

    public Renderer getRenderer(){
        return renderer;
    }

    public Slider getSlider(){
        return slider;
    }

    public ScrollBox getScrollBox(){
        return scrollBox;
    }

    public void reDraw(){
        if(renderer.getIsRender())
            repaint();
    }

    public void addSwingComp(){
        add(renderer);
        if(mode.isHost()){
            add(slider);
            add(scrollBox);
        }
    }

    private void inItJFrame(){
        Board board = Board.getInstance();
        Config bc = board.getConfig();
        WindowContext wc = bc.getWindowContext();
        String title = wc.getWindowTitle() + " : " + board.getMode().getOwner();

        int width = wc.getWindowSizeWidth();
        int height = wc.getWindowSizeHeight();
        if(!mode.isHost()){
            width = (int)(wc.getWindowSizeWidth() * 0.45);
            height = (int)(wc.getWindowSizeHeight() * 0.82);
        }

        setTitle(title);
        setSize(width, height);
        setLocation(wc.getWindowStartPointX(), wc.getWindowStartPointY());
        setLayout(null);
        addSwingComp();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

}
