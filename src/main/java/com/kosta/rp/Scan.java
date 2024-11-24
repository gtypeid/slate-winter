package com.kosta.rp;

import java.util.ArrayList;
import java.util.Scanner;

public class Scan {

    private boolean isRun;
    private Core core;
    private Property property;
    private ArrayList<Entity> store;

    public Scan(Core core){
        this.core = core;
        property = core.getProperty();
        store = core.getEntityStore();
        isRun = true;
    }

    public void run() {
        Scanner scan = new Scanner(System.in);
        String strMsg;
        while (isRun) {
            strMsg = scan.nextLine();

            if (strMsg.equals("exit")) {
                isRun = false;
            }

            if (strMsg.equals("l") && !property.color) {
                for (Entity it : store) {
                    it.left();
                    core.draw();
                }
            }

            if (strMsg.equals("r") && !property.color) {
                for (Entity it : store) {
                    it.right();
                    core.draw();
                }
            }

            if (strMsg.equals("color red")) {
                property.color = true;
                property.colorProperty = "\u001B[31m";
                core.draw();
            }

            if (strMsg.equals("color green")) {
                property.color = true;
                property.colorProperty = "\u001B[32m";
                core.draw();
            }

            if (strMsg.equals("color blue")) {
                property.color = true;
                property.colorProperty = "\u001B[34m";
                core.draw();
            }

            if (strMsg.equals("color off")) {
                property.colorProperty = "\u001B[30m";
                core.draw();
                property.color = false;
            }

            if (strMsg.equals("null blank")) {
                property.blank = ' ';
                core.draw();
            }

            if (strMsg.contains("blank ")) {
                if(strMsg.length() >= 7) {
                    char p = strMsg.charAt(6);
                    property.blank = p;
                    core.draw();
                }

            }
        }
    }
}
